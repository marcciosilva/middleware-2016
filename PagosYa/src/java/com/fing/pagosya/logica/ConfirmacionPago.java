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

import com.fing.pagosya.dtos.Confirmacion;
import com.fing.pagosya.dtos.Pago;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marccio
 */
public class ConfirmacionPago {

	private long idConfirmacionPago;
	final static Logger logger = java.util.logging.Logger.
			getLogger(ConfirmacionPago.class.getName());
	private List<Pago> pagosConfirmados;
	private static ConfirmacionPago instance;

	public static ConfirmacionPago getInstance() {
		if (instance == null) {
			instance = new ConfirmacionPago();
		}
		return instance;
	}

	public ConfirmacionPago() {
		idConfirmacionPago = 0;
		pagosConfirmados = new ArrayList<>();
		logger.log(Level.INFO,
				"Se inicializa idConfirmacionPago en el valor: {0}", Long.
				toString(idConfirmacionPago));
	}

	public Confirmacion confirmarPago(Pago pago) {
		// Se loggea informacion de pago.
		logger.info(
				"########## Iniciado el proceso de confirmación de pago ##########");
		logger.log(Level.INFO, "Numero de tarjeta de credito: {0}", Long.
				toString(pago.
						getNumeroTarjeta()));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");
		logger.log(Level.INFO, "Fecha de vencimiento: {0}", simpleDateFormat.
				format(pago.
						getFechaVencimiento().getTime()));
		logger.log(Level.INFO, "Digito verificador: {0}", Integer.toString(pago.
				getDigitoVerificador()));
		logger.log(Level.INFO, "Monto: {0}", Double.toString(pago.getMonto()));
		Confirmacion confirmacion = new Confirmacion();
		try {
			// Si ya hay un pago para la orden se retorna error. {"idConfirmacionPago":-1}
			if (pagosConfirmados.contains(pago)) {
				throw new Exception("Ya se procesó un pago con datos idénticos.");
			}
			// Si no se tenia un pag, entonces se genera un id de confirmacion.
			// Se genera un nuevo id.
			idConfirmacionPago++;
			pagosConfirmados.add(pago);
			confirmacion.setIdConfirmacion(idConfirmacionPago);
			confirmacion.setAprobada(true);
			logger.info("El pago se confirmó exitosamente.");
			logger.log(Level.INFO, "Se generó el id de confirmacion: {0}",
					Long.toString(
							idConfirmacionPago));
		} catch (Exception ex) {
			logger.info("Ocurrió un error al intentar confirmar el pago.");
			logger.log(Level.INFO, "Mensaje de excepción:{0}", ex.
					getMessage());
			confirmacion.setIdConfirmacion(-1);
			confirmacion.setAprobada(false);
		}
		logger.info(
				"########## Finalizado el proceso de confirmación de pago ##########");
		return confirmacion;
	}
}
