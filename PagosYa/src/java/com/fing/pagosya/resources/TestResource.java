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

import com.fing.pagosya.dtos.Pago;
import java.util.Calendar;
import java.util.Locale;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("test")
public class TestResource {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of TestResource
	 */
	public TestResource() {
	}

	/**
	 * Retrieves representation of an instance of
	 * com.fing.pagosya.dtos.TestResource
	 *
	 * @return an instance of java.lang.String
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getXml() {
		Pago pago = new Pago(1234, Calendar.getInstance(Locale.FRENCH), 1,
				1000.1);
		return Response.status(201).entity(pago).build();
	}

	/**
	 * PUT method for updating or creating an instance of TestResource
	 *
	 * @param content representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@PUT
	@Consumes("application/xml")
	public void putXml(String content) {
	}
}
