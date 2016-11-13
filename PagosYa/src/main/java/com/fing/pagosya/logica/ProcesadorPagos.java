/*
 * Copyright 2016 marccio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fing.pagosya.logica;

import com.fing.pagosya.dtos.Anulacion;
import com.fing.pagosya.dtos.Confirmacion;
import com.fing.pagosya.dtos.Pago;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marccio
 */
public class ProcesadorPagos {

	// Contador que almacena el id de confirmacion a
	// presentar ante un pago valido y ya no existente en el sistema.
	private long idConfirmacionPagoActual;
	private long idConfirmacionAnulacionActual;
	final static Logger logger = java.util.logging.Logger.
			getLogger(ProcesadorPagos.class.getName());
	private static Map<Long, Pago> pagosConfirmados;
	private static Map<Long, Anulacion> anulaciones;
	private static ProcesadorPagos instance;

	public static ProcesadorPagos getInstance() {
		if (instance == null) {
			instance = new ProcesadorPagos();
		}
		return instance;
	}

	public ProcesadorPagos() {
		idConfirmacionPagoActual = 1;
		idConfirmacionAnulacionActual = 1;
		pagosConfirmados = new HashMap<>();
		anulaciones = new HashMap<>();
		logger.log(Level.INFO,
				"Se inicializa idConfirmacionPago en el valor: {0}", Long.
				toString(idConfirmacionPagoActual));
	}

	public Confirmacion confirmarPago(Pago pago) {
		// Se loggea informacion de pago.
		logger.info(
				"########## Iniciado el proceso de confirmación de pago ##########");
		logger.log(Level.INFO, "Numero de tarjeta de credito: {0}", Long.
				toString(pago.
						getNumeroTarjeta()));
		logger.log(Level.INFO, "Fecha de vencimiento: {0}", pago.
				getFechaVencimiento().toString());
		logger.log(Level.INFO, "Digito verificador: {0}", Integer.toString(pago.
				getDigitoVerificador()));
		logger.log(Level.INFO, "Monto: {0}", Double.toString(pago.getMonto()));
		Confirmacion confirmacion = new Confirmacion();
		try {
			// Si ya hay un pago para la orden se retorna error. {"idConfirmacionPago":-1}
			if (pagosConfirmados.values().contains(pago)) {
				throw new Exception("Ya se procesó un pago con datos idénticos.");
			}
			// Si no se tenia un pag, entonces se genera un id de confirmacion.
			// Se genera un nuevo id.
			pagosConfirmados.put(idConfirmacionPagoActual, pago);
			confirmacion.setIdConfirmacionPago(idConfirmacionPagoActual);
			idConfirmacionPagoActual++;
			logger.info("El pago se confirmó exitosamente.");
			logger.log(Level.INFO, "Se generó el id de confirmacion: {0}",
					Long.toString(
							idConfirmacionPagoActual));
		} catch (Exception ex) {
			logger.info("Ocurrió un error al intentar confirmar el pago.");
			logger.log(Level.INFO, "Mensaje de excepción:{0}", ex.
					getMessage());
			confirmacion.setIdConfirmacionPago(-1);
		}
		logger.info(
				"########## Finalizado el proceso de confirmación de pago ##########");
		return confirmacion;
	}

	public Anulacion anularPago(long idConfirmacionPago) {
		// Se loggea informacion de pago.
		logger.info(
				"########## Iniciado el proceso de anulación de pago ##########");
		logger.log(Level.INFO, "Id de confirmación de pago recibido: {0}", Long.
				toString(
						idConfirmacionPago));
		Anulacion anulacion;
		// Busco el pago confirmado en mis pagos confirmados.
		Pago pago = pagosConfirmados.get(idConfirmacionPago);
		if (pago == null) {
			logger.info("Ocurrió un error al intentar anular el pago.");
			logger.info(
					"El id de confirmacion recibido no se encontró en el sistema.");
			anulacion = new Anulacion();
			// Se devuelve una anulacion invalida.
			anulacion.setIdConfirmacionAnulacionPago(-1);
		} else {
			anulacion = anulaciones.get(idConfirmacionPago);
			// Si el pago ya no estaba anulado, se genera una anulacion.
			if (anulacion == null) {
				anulacion = new Anulacion();
				anulacion.setIdConfirmacionAnulacionPago(
						idConfirmacionAnulacionActual);
				idConfirmacionAnulacionActual++;
				// La agrego al map de anulaciones.
				anulaciones.put(idConfirmacionPago, anulacion);
				logger.info("El pago se anuló exitosamente.");
			} else {
				logger.info("El pago se encontraba anulado.");
			}
		}
		return anulacion;
	}

}
