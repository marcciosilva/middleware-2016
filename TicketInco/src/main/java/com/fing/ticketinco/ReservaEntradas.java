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
        ArrayList<Horario> horariosReserva = new ArrayList<Horario>();
        for (int i = 0; i < listahorarios.length; i++) {
            
            Horario horario = listahorarios[i];
             fgen.info ("Horario : " + horario.getHorario().toString());             
             horariosReserva.add(horario);
             List<Disponibilidad> listadisponibles = listahorarios[i].disponibilidades;
             for (int j = 0; j < listadisponibles.size() ; j++) {                
                fgen.info ("   Disponibilidad - Cantidad: " + listadisponibles.get(j).getCantidad());
                fgen.info ("   Disponibilidad - Precio: " + listadisponibles.get(j).getPrecio());
                fgen.info ("   Disponibilidad - Sector: " + listadisponibles.get(j).getSector());
             }           
             
             
        }       
        //Inicializo o tomo lo que esta en memoria de la lista de reservas
        ListaReservas reservas= new ListaReservas();        
        // busco el evento y que la lista de horarios sea en la que quiero reservar
        ListaEventos eventos = new ListaEventos();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaevento);
        Evento e = eventos.buscarEvento(identificador_evento, c);
        List<Horario> horariosRetornar = new ArrayList<Horario>();
        if(e != null)
        {
            horariosRetornar = e.getHorarios();
        }     
        
        if (horariosRetornar != null)
        {
            for (int i = 0; i < horariosRetornar.size(); i++) {
                for (int j = 0; j < listahorarios.length; j++) {
                    Date fechaE = horariosRetornar.get(i).getHorario().getTime();           
                    Date fechaEventoDate = listahorarios[j].hora.getTime();              
                    if(fechaE.equals(fechaEventoDate))                   
                    {   for (int k = 0; k < horariosRetornar.get(i).disponibilidades.size(); k++) {
                            for (int l = 0; l < listahorarios[j].disponibilidades.size(); l++) {
                                Disponibilidad d= horariosRetornar.get(i).disponibilidades.get(k);
                                Disponibilidad r= listahorarios[j].disponibilidades.get(l);
                                if  (d.cantidad >= r.cantidad && d.sector.equalsIgnoreCase(r.sector) && d.precio==r.precio)
                                {
                                   d.setCantidad(d.cantidad-r.cantidad);
                                   //Reserva reserv= new Reserva();
                                   //reservas.contador_Id= reservas.contador_Id +1;
                                   //reserv.idReserva= reservas.contador_Id;
                                   //reserv.Estado=1;
                                   //reserv.idEvento = identificador_evento;
                                   //reserv.horarios.add(listahorarios[j]);
                                   //reservas.listaReserva.add(reserv);
                                   //return reserv.idReserva;
                                }
                                else if(d.cantidad < r.cantidad && d.sector.equalsIgnoreCase(r.sector) && d.precio==r.precio)
                                {
                                    //Si hay alguna solicitud de de reserva que no se pueda cumplir. Re reorna 0.
                                    //TODO: Hay que volver para atras las cantidades modificadas.
                                    return 0;
                                }
                                
                            }
                            
                        }
                    }
                }
            }
            Reserva reserv= new Reserva();
            reservas.contador_Id= reservas.contador_Id +1;
            reserv.idReserva= reservas.contador_Id;
            reserv.Estado=1;
            reserv.idEvento = identificador_evento;
            reserv.horarios = horariosReserva;
            reserv.fechaEvento = c;
            reservas.listaReserva.add(reserv);
            return reserv.idReserva;
        }
        
        return 0;
    }
}
