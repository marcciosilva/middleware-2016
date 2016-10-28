/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.text.ParseException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.apache.log4j.Logger;
import java.util.ArrayList;
/**
 *
 * @author javier
 */
@WebService(serviceName = "ConsultaEstadoReserva")
public class ConsultaEstadoReserva {
  final static Logger fgen = Logger.getLogger(ConsultaEstadoReserva.class);

    /**
     * Web service operation
     */
    @WebMethod(operationName = "consulta_estado_reserva")
    public EstadoReserva consulta_estado_reserva(@WebParam(name = "identificador_reserva") long identificador_reserva) throws ParseException {
        //TODO write your implementation code here:
         ListaReservas reservas= new ListaReservas();
         for (int i = 0; i < reservas.listaReserva.size(); i++) {
            Reserva r = reservas.listaReserva.get(i);
            if (r.idReserva == identificador_reserva)
            {
                 EstadoReserva e = new EstadoReserva();
                 e.estado= r.Estado;
                 return e;
            }
         }
        fgen.info ("Identificador de la reserva : " + identificador_reserva);         
        EstadoReserva e = new EstadoReserva();
        e.Errores= "No existe la reserva";
        return e;
    }
}
