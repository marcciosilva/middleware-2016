/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

import org.apache.log4j.Logger;

import esb_mediopagolocal.MedioPagoLocal;
import esb_mediopagolocal.MedioPagoLocalService;

/**
 *
 * @author javier
 */
@WebService(serviceName = "ConfirmacionReserva")
//@Addressing(required = true)
@MTOM(enabled=true)
@Addressing(enabled=true, required=true)
public class ConfirmacionReserva implements IConfirmacionReserva {

	final static Logger fgen = Logger.getLogger(ConfirmacionReserva.class);
	final static java.util.logging.Logger logger = java.util.logging.Logger
			.getLogger(ConfirmacionReserva.class.getName());

	/**
	 * This is a sample web service operation
	 */
	/*EndpointImpl jaxWsEndpoint = (EndpointImpl) Endpoint.publish("http://localhost/ConfirmacionReserva", "ConfirmarReserva");
	 Endpoint cxfEndpoint = jaxWsEndpoint.getServer().getEndpoint();

	 Map<String,Object> inProps = new HashMap<String,Object>();
	 // how to configure the properties is outlined below;

	 WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
	 cxfEndpoint.getInInterceptors().add(wssIn);

	 Map<String,Object> outProps = new HashMap<String,Object>();
	 // how to configure the properties is outlined below;

	 WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
	 cxfEndpoint.getOutInterceptors().add(wssOut);*/
	public void ConfirmarReserva(@WebParam(name = "idReserva")long idReserva, @WebParam(name = "idMedioPago") long idMedioPago, @WebParam(name = "nroTarjeta") String nroTarjeta, @WebParam(name = "fechaVencimiento") Date fechaVencimiento, @WebParam(name = "digitoVerificador") int digitoVerificador,@WebParam(name = "idConfirmacion", mode = WebParam.Mode.OUT) Holder<Long> idConfirmacion, @XmlMimeType("multimpart/related") @WebParam(name = "imagenesBinarias", mode = WebParam.Mode.OUT) Holder<ArrayList<ImagenEntrada>> imagenesBinarias) throws
			ParseException {
		try {
			fgen.info("Identificador de la reserva " + idReserva);
			fgen.info("Identificador del medio de pago " + idMedioPago);
			// fgen.info("Número tarjeta ", nroTarjeta);
			// fgen.info("Fecha vencimiento ", fechaVencimiento);
			// fgen.info("Dígito verificador ", digitoVerificador);
		} catch (Exception e) {

		}

		// TODO: Hay que agregar WS-Addressing y WS-Security
		ListaReservas listaReservas = new ListaReservas();
		Reserva reserva = listaReservas.buscarReserva(idReserva);
		//ConfirmarReservaRetornar respuestaConfirmar = new ConfirmarReservaRetornar();
		if (reserva != null) {
			if (reserva.Estado == 2) {
				//La reserva ya fue confirmada				
				idConfirmacion.value = (long) -2;
				System.out.println("La reserva " + reserva.idReserva + " ya se encuentra confirmada");
			} else if (reserva.Estado == 0) {
				//La reserva se encuentra en estado cancelado;				
				idConfirmacion.value = (long) 0;
				System.out.println("La reserva " + reserva.idReserva + " se encuentra cancelada");
			}

			double monto = calcularMonto(reserva);
			if (idMedioPago == 1000) {
				try {
					 MedioPagoLocalService medioPagoService
					 = new MedioPagoLocalService();
					 MedioPagoLocal medioPagoLocal = medioPagoService.
					 getMedioPagoLocalPort();
					String digitoVerificadorStr = String.valueOf(digitoVerificador);
					String montoStr = String.valueOf(monto);
					medioPagoLocal.confirmarPago(nroTarjeta, fechaVencimiento.toString(), digitoVerificadorStr,
							montoStr);
					reserva.Estado = 2;
					Pago pago = new Pago();
					pago.reservas.add(reserva);
					Pago.contadorIdConfPagoLocal++;
					pago.idConfPago = Pago.contadorIdConfPagoLocal;
					ListaPagos listaPagos = new ListaPagos();
					listaPagos.agregarPago(pago);
					idConfirmacion.value = pago.idConfPago;
					System.out.println("Se ha realizado el pago de la reserva " + reserva.idReserva + " exitosamente");
					System.out.println("El id de confirmacion el pago es: " + pago.idConfPago + " para el medio de pago local");
				} catch (Exception e) {
					System.out.println("Error " + e.getMessage());
				}
			} else if (idMedioPago == 2000) {
				// Ir a al servicio de Pagos Ya
				try {
					esb_pagosya.PagosYaService medioPagoService = new esb_pagosya.PagosYaService();
					esb_pagosya.PagosYa pagosYa = medioPagoService.getPagosYaPort();
					String digitoVerificadorStr = String.valueOf(digitoVerificador);
					String montoStr = String.valueOf(monto);
					// Recibo un id de confirmacion de PagosYa.
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
					long idConfirmacionRecibido = pagosYa.confirmarPago(nroTarjeta, sdf.format(fechaVencimiento),
							digitoVerificadorStr, montoStr);
					reserva.Estado = 2;
					Pago pago = new Pago();
					pago.reservas.add(reserva);
					pago.idConfPago = idConfirmacionRecibido;
					ListaPagos listaPagos = new ListaPagos();
					listaPagos.agregarPago(pago);
					System.out.println("Se ha realizado el pago de la reserva " + reserva.idReserva + " exitosamente");
					System.out.println("El id de confirmacion el pago es: " + pago.idConfPago + " en PagosYa!");
				} catch (Exception e) {
					System.out.println("Error " + e.getMessage());
				}
			} else {
				idConfirmacion.value =(long) -300;
				//System.out.println("La reserva " + reserva.idReserva + )
			}

			String pathFile = getClass().getClassLoader().getResource(
					"Entradas/cinemaTicket.jpg").getPath();
			ArrayList<ImagenEntrada> imagenesEntradasBinarias = new ArrayList<ImagenEntrada>();			
			byte[] imagenBinaria = obtenerByteImagen(pathFile);
			ImagenEntrada imagen = new ImagenEntrada();
			imagen.entrada = imagenBinaria;
			imagenesEntradasBinarias.add(imagen);
			reserva.Estado = 2;			
			imagenesBinarias.value = imagenesEntradasBinarias;
		}
		
	}

	private byte[] obtenerByteImagen(String filePath) {
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream inputStream = new BufferedInputStream(fis);
			byte[] fileBytes = new byte[(int) file.length()];
			inputStream.read(fileBytes);
			inputStream.close();
			return fileBytes;
		} catch (IOException ex) {
			java.util.logging.Logger.getLogger(ConfirmacionReserva.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@XmlRootElement(name = "pago")
	private class PagoPagosYa implements Serializable {

		private long numeroTarjeta;
		private Calendar fechaVencimiento;
		private int digitoVerificador;
		private double monto;

		public PagoPagosYa() {
		}

		public PagoPagosYa(long numeroTarjeta, Calendar fechaVencimiento, int digitoVerificador, double monto) {
			this.numeroTarjeta = numeroTarjeta;
			this.fechaVencimiento = fechaVencimiento;
			this.digitoVerificador = digitoVerificador;
			this.monto = monto;
		}

	}

	private double calcularMonto(Reserva r) {
		double monto = 0;
		ArrayList<Horario> horarios = r.horarios;

		for (Horario h : horarios) {
			ArrayList<Disponibilidad> disponibilidades = h.disponibilidades;
			for (Disponibilidad d : disponibilidades) {
				monto = monto + (d.cantidad * d.precio);
			}
		}
		return monto;
	}

}
