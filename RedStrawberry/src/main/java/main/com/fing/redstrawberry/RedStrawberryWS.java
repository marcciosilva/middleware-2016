/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.com.fing.redstrawberry;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.apache.log4j.Logger;

/**
 *
 * @author javier
 */
@WebService(serviceName = "RedStrawberryWS")
public class RedStrawberryWS {

	/**
	 * This is a sample web service operation
	 */
	final static Logger fgen = Logger.getLogger(RedStrawberryWS.class);

	@WebMethod(operationName = "ProcesarItems")
	public void ProcesarItems(@WebParam(name = "ListaItem") Item[] lista) {
                fgen.info("Entro");
		for (int i = 0; i < lista.length; i++) {

			Item pedido = lista[i];
			fgen.info("Identificador producto :" + pedido.getidentificadorproducto());
			fgen.info("Cantidad :" + pedido.getcantidad().toString());
			fgen.info("Identificador de la transacción :" + pedido.getidentificadortransaccion());
			fgen.info("Fecha y hora :" + pedido.getfechaHora());

		}
	}
}
