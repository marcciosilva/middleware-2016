/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;



import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOMFeature;
import javax.xml.ws.soap.SOAPBinding;


import com.fing.ws.MedioPagoLocal;
import com.fing.ws.MedioPagoLocalService;
import java.io.Serializable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


import org.apache.log4j.Logger;


import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import ws.callback.NotificarConfirmacionReserva;
import ws.callback.NotificarConfirmacionReserva_Service;
import ws.callback.ReservasRetornar;


/**
 *
 * @author javier
 */
@WebService(serviceName = "ConfirmacionReserva")
@Addressing(required = true)
//@Addressing(enabled=true, required=true)
public class ConfirmacionReserva {

	final static Logger fgen = Logger.getLogger(ConfirmacionReserva.class);
//	final static java.util.logging.Logger logger = java.util.logging.Logger.
//			getLogger(ConfirmacionReserva.class.getName());

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
	@WebMethod(operationName = "ConfirmarReserva")
	public byte[] ConfirmarReserva(@WebParam(name = "idReserva") long idReserva,
			@WebParam(name = "idMedioPago") long idMedioPago, @WebParam(name
					= "nroTarjeta") String nroTarjeta, @WebParam(name
					= "fechaVencimeinto") Date fechaVencimiento, @WebParam(name
					= "digitoVerificador") int digitoVerificador) throws
			ParseException {
		try {
			fgen.info("Identificador de la reserva " + idReserva);
			fgen.info("Identificador del medio de pago " + idMedioPago);
//			fgen.info("Número tarjeta ", nroTarjeta);
//			fgen.info("Fecha vencimiento ", fechaVencimiento);
//			fgen.info("Dígito verificador ", digitoVerificador);
		} catch (Exception e) {

		}

		//TODO: Hay que agregar WS-Addressing y WS-Security
		ListaReservas listaReservas = new ListaReservas();
		Reserva reserva = listaReservas.buscarReserva(idReserva);
                ReservasRetornar entradasRetornar = new ReservasRetornar();
		if (reserva != null) {
			if(reserva.Estado == 2)
                {
                    //entradasRetornar.descripcion = "La reserva ya fue confirmada";
                    //entradasRetornar.i = idReserva;                    
                    //return entradasRetornar;
                }
                else if(reserva.Estado == 0)
                {
                    //entradasRetornar.descripcion = "La reserva se encuentra en estado cancelado";
                    //entradasRetornar.idReserva = idReserva;                    
                    //return entradasRetornar;                    
                }
                
                
			double monto = calcularMonto(reserva);
			if (idMedioPago == 1000) {
				try {
					MedioPagoLocalService medioPagoService = new MedioPagoLocalService();
					MedioPagoLocal medioPagoLocal = medioPagoService.getMedioPagoLocalPort();
					String digitoVerificadorStr = String.valueOf(digitoVerificador);
					String montoStr = String.valueOf(monto);
					medioPagoLocal.confirmarPago(nroTarjeta, fechaVencimiento.toString(), digitoVerificadorStr, montoStr);
					reserva.Estado = 2;
					Pago pago = new Pago();
					pago.reservas.add(reserva);
					Pago.contadorIdConfPagoLocal++;
					pago.idConfPago = Pago.contadorIdConfPagoLocal;
					ListaPagos listaPagos = new ListaPagos();
					listaPagos.agregarPago(pago);
				} catch (Exception e) {
					System.out.println("Error " + e.getMessage());
				}

				//return "Medio de pago local";
			} else if (idMedioPago == 2000) {
				// Ir a al servicio de Pagos Ya

				try {
					Client client = ClientBuilder.newClient();
					WebTarget target = client.target(
							"http://192.168.1.11:8080/PagosYa/webresources/confirmacionPago");
					// Genero un objeto con los datos que precisa PagosYa!
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(fechaVencimiento);
					PagoPagosYa pagoPagosYa = new PagoPagosYa(
							idReserva, calendar,
							digitoVerificador,
							monto);
					// Genero una request y obtengo su response.
					javax.ws.rs.core.Response response = target.request(
							MediaType.APPLICATION_JSON).post(
									Entity.json(pagoPagosYa));
					String msg = response.readEntity(String.class);
					client.close();

					if (msg == null) {
						//return "Todo mal viejo";
					} else {
						//return "PagosYa! dice: " + msg;
					}
				} catch (Exception e) {					
				
					fgen.info(e.getMessage());
					//return e.getMessage();

				}
			} else {
				//return "El medio de pago no existe";
			}
                        //Generar entradas en formato binario para enviar a las notificaciones
                //URL url = new URL("\\resources\\Entradas\\cinemaTicket.jpg");
                //Image imgEntrada = ImageIO.read(new File("C:\\Users\\Cami\\Documents\\Fing\\Middleware\\Obligatorio2\\ImagenesEntradas\\cinemaTicket.jpg"));
                String pathFile = "C:\\Users\\Cami\\Documents\\Fing\\Middleware\\Obligatorio2\\ImagenesEntradas\\cinemaTicket.jpg";
                ArrayList<Image> imagenesEntradas = new ArrayList<Image>();
                //imagenesEntradas.add(imgEntrada);
                //enable MTOM in client
                
                NotificarConfirmacionReserva_Service service = new NotificarConfirmacionReserva_Service();
                NotificarConfirmacionReserva port = service.getNotificarConfirmacionReservaPort(new MTOMFeature(true));
                //NotificacionConfirmacionReservaEntrada notificacionWS = new NotificacionConfirmacionReservaEntrada();
                //BindingProvider bp = (BindingProvider) port;
                //SOAPBinding binding = (SOAPBinding) bp.getBinding();
                //binding.setMTOMEnabled(true);
                byte[] imagenBinaria = obtenerByteImagen(pathFile);
                ReservasRetornar retornarNotificacion = port.notificarEntradas(idReserva, imagenBinaria);
                byte[] imagenRet = retornarNotificacion.getImagenEntrada();
                reserva.Estado = 2;
                                
                //entradasRetornar.idReserva = idReserva;
                //entradasRetornar.respuesta = "Imagenes procesadas con exito";
                
                //return null;
                return imagenRet;
		}
		//return entradasRetornar;
                return null;
	}  

        private byte[] obtenerByteImagen(String filePath)
    {
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

		public PagoPagosYa(long numeroTarjeta, Calendar fechaVencimiento,
				int digitoVerificador, double monto) {
			this.numeroTarjeta = numeroTarjeta;
			this.fechaVencimiento = fechaVencimiento;
			this.digitoVerificador = digitoVerificador;
			this.monto = monto;
		}

	}

        private double calcularMonto(Reserva r)
        {
            double monto = 0;
            ArrayList<Horario> horarios = r.horarios;
            
            for(Horario h: horarios)
            {
                ArrayList<Disponibilidad> disponibilidades = h.disponibilidades;
                for(Disponibilidad d : disponibilidades)
                {
                    monto = monto + (d.cantidad * d.precio);
                }                
            }
            return monto;
        }

}
