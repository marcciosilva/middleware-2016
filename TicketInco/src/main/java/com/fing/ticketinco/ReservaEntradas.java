/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import org.apache.log4j.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author javier
 */
@WebService(serviceName = "ReservaEntradas")
public class ReservaEntradas {
    
    final static Logger fgen = Logger.getLogger(ReservaEntradas.class);
    /**
     * Web service operation
     */
    @WebMethod(operationName = "reserva_de_entradas")
    public long reserva_de_entradas(@WebParam(name = "identificador_evento") int identificador_evento, @WebParam(name = "fechaevento") String fechaevento) {
        //TODO write your implementation code here:
        fgen.info ("Identificador del evento" + identificador_evento);
        fgen.info ("Fecha del evento" + fechaevento);
        
        return 0;
    }
}
