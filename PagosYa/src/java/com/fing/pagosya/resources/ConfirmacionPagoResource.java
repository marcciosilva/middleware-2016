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
package com.fing.pagosya.resources;

import com.fing.pagosya.dtos.Confirmacion;
import com.fing.pagosya.dtos.Pago;
import com.fing.pagosya.logica.ConfirmacionPago;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
@Path("confirmacionPago")
public class ConfirmacionPagoResource {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of ConfirmacionPagoResource
	 */
	public ConfirmacionPagoResource() {
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response recibirPago(Pago pago) {
		Confirmacion resultado = ConfirmacionPago.getInstance().confirmarPago(
				pago);
		return Response.ok(resultado, MediaType.APPLICATION_JSON).build();
	}

}