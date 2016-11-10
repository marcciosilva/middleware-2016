/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.pagosya.rest;

import com.fing.pagosya.dtos.Anulacion;
import com.fing.pagosya.dtos.Confirmacion;
import com.fing.pagosya.logica.ProcesadorPagos;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author marccio
 */
@Path("anulacionPago")
public class AnulacionPagoResource {

	@Context
	private UriInfo context;
	final static Logger logger = java.util.logging.Logger.
			getLogger(ConfirmacionPagoResource.class.getName());

	/**
	 * Creates a new instance of AnulacionPagoResource
	 */
	public AnulacionPagoResource() {
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response recibirPago(Confirmacion confirmacionPago) {
		Anulacion anulacion = ProcesadorPagos.getInstance().anularPago(
				confirmacionPago.getIdConfirmacionPago());
		return Response.ok(anulacion, MediaType.APPLICATION_JSON).
				build();
	}

}
