/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author javier
 */
public class ListaReservas {
    
    public static ArrayList<Reserva> listaReserva;
    public static int contador_Id;
    
    public ListaReservas() throws ParseException
    {
        if(listaReserva == null)
        {
            listaReserva = new ArrayList<Reserva>();            
        }
    }    
    
    public Reserva buscarReserva(long idReserva)
    {        
        for(Reserva r : listaReserva)
        {
            if(r.idReserva == idReserva)
            {
                return r;
            }
        }
        return null;
    }
    
}
