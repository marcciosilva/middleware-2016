/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.util.List;
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
    public long reserva_de_entradas(@WebParam(name = "identificador_evento") int identificador_evento, @WebParam(name = "fechaevento") String fechaevento, @WebParam(name = "Listadehorarios") Horario[] listahorarios ) {
        //TODO write your implementation code here:
        fgen.info ("Identificador del evento" + identificador_evento);
        fgen.info ("Fecha del evento" + fechaevento);
        
        for (int i = 0; i < listahorarios.length; i++) {
        
             fgen.info ("Horario : " + listahorarios[i].getHorario().toString());
             
             List<Disponibilidad> listadisponibles = listahorarios[i].disponibilidades;
             for (int j = 0; j < listadisponibles.size() ; j++) {                
                fgen.info ("   Disponibilidad - Cantidad: " + listadisponibles.get(j).getCantidad());
                fgen.info ("   Disponibilidad - Precio: " + listadisponibles.get(j).getPrecio());
                fgen.info ("   Disponibilidad - Sector: " + listadisponibles.get(j).getSector());
             }           
             
             
        }
        
        return 0;
    }
}
