/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public long reserva_de_entradas(@WebParam(name = "identificador_evento") int identificador_evento, @WebParam(name = "fechaevento") Date fechaevento, @WebParam(name = "Listadehorarios") Horario[] listahorarios ) throws ParseException {
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
        // busco el evento y que la lista de horarios sea en la que quiero reservar
        ListaEventos eventos = new ListaEventos();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaevento);
        Evento e = eventos.buscarEvento(identificador_evento, c);
        List<Horario> horariosRetornar = new ArrayList<Horario>();
        if(e != null)
        {
            horariosRetornar = e.getHoarios();
        }     
        
        if (horariosRetornar != null)
        {
            for (int i = 0; i < horariosRetornar.size(); i++) {
                for (int j = 0; j < listahorarios.length; j++) {
                    if (horariosRetornar.get(i).hora==listahorarios[j].hora)
                    {   for (int k = 0; k < horariosRetornar.get(i).disponibilidades.size(); k++) {
                            for (int l = 0; l < listahorarios[j].disponibilidades.size(); l++) {
                                Disponibilidad d= horariosRetornar.get(i).disponibilidades.get(k);
                                Disponibilidad r= listahorarios[j].disponibilidades.get(l);
                                if  (d.cantidad >= r.cantidad && d.sector== r.sector)
                                {
                                    // genero el identificador de reserva para retornar
                                    return 1;
                                }
                                
                            }
                            
                        }
                    }
                }
            }
        }
        
        return 0;
    }
}
