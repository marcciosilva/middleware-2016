/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.log4j.Logger;

/**
 *
 * @author javier
 */
@WebService(serviceName = "ConsultaEntradas")
public class ConsultaEntradasDisponibles {

	final static Logger fgen = Logger.getLogger(
			ConsultaEntradasDisponibles.class);

	/**
	 * This is a sample web service operation
	 */
	@WebMethod(operationName = "ConsultarEntradasDisponibles")
	public ListaHorariosRetornar ConsultarEntradas(
			@WebParam(name = "idEvento") int idEvento, @WebParam(name
					= "fechaEvento") Calendar fechaEvento) throws ParseException {

		fgen.info("Identificador del evento" + idEvento);
		fgen.info("Fecha del evento" + fechaEvento);

		ListaEventos eventos = new ListaEventos();
//		DateFormat df = DateFormat.getInstance();
//		Date fecha = df.parse(fechaEvento);
//		TODO borrar esto.

		Evento e = eventos.buscarEvento(idEvento, fechaEvento);
		ArrayList<Horario> horariosRetornar = new ArrayList<Horario>();
		if (e != null) {
			horariosRetornar = e.getHorarios();
		}

		ListaHorariosRetornar horarios = new ListaHorariosRetornar(
				horariosRetornar);
		return horarios;
	}

}
