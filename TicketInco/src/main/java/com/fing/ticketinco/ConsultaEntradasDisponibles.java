/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author javier
 */
@WebService(serviceName = "ConsultaEntradas")
public class ConsultaEntradasDisponibles {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "ConsultarEntradasDisponibles")
    public List<Horario> ConsultarEntradas(@WebParam(name = "idEvento") int idEvento, @WebParam(name = "fechaEvento") Calendar fechaEvento) throws ParseException {
        String res = "No encontre";
        ListaEventos eventos = new ListaEventos();     
        //DateFormat df = DateFormat.getInstance();
        //Date fecha = df.parse(fechaEvento);
        Evento e = eventos.buscarEvento(idEvento, fechaEvento);
        List<Horario> horariosRetornar = new ArrayList<Horario>();
        if(e != null)
        {
            horariosRetornar = e.getHoarios();
            res = "Encontre el evento";
        }        
        return horariosRetornar;        
    }
    
    
}
