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

/**
 *
 * @author Cami
 */
public class ListaEventos {
    public static ArrayList<Evento> listaEventos;
    
    public ListaEventos() throws ParseException
    {
        if(listaEventos == null)
        {
            listaEventos = crearEventos();            
        }
    }
    
   public ArrayList<Evento> getListaEventos()
   {
       return listaEventos;
   }
   
   public Evento buscarEvento(int idEvento, Calendar fechaEvento)
   {       
       for(Evento e : listaEventos)
       {
           Date fechaE = e.fechaEvento.getTime();           
           Date fechaEventoDate = fechaEvento.getTime();           
           if(e.idEvento == idEvento)
           {             
               if(fechaE.equals(fechaEventoDate))
               {
                   return e;
               }               
           }
       }
       return null;
   }
    
    public void agregarEventos(Evento evento)
    {
        listaEventos.add(evento);
    }
    
    private ArrayList<Evento> crearEventos()
    {
        listaEventos = new ArrayList<Evento>();
        
        Disponibilidad d1 = new Disponibilidad("Sector 1", 300, 100);
        Disponibilidad d2 = new Disponibilidad("Sector 2", 450, 100);
        Disponibilidad d3 = new Disponibilidad("Sector 3", 800, 100);
        Disponibilidad d4 = new Disponibilidad("Sector 4", 1000, 100);  
        
        //Date fecha = new Date();
        Calendar c = Calendar.getInstance();
        c.set(2016, 10, 19,0,0,0);    
        c.set(Calendar.MILLISECOND, 0);  
        Horario h = new Horario();
        h.hora = c;
        h.agregarDisponibilidad(d1);
        h.agregarDisponibilidad(d2);
        h.agregarDisponibilidad(d3);
        h.agregarDisponibilidad(d4);
        ArrayList<Horario> horarios = new ArrayList<Horario>();
        horarios.add(h);
        
        Evento e = new Evento(1,c, horarios);
        
        listaEventos.add(e);
        return listaEventos;
    }
}
