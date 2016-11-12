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
import java.util.logging.Level;
import java.util.logging.Logger;
import test2.Reserva;
import test2.ListaReservas;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Cami
 */
public class CancelarReservasJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {    
        
        System.out.println("Ejecutando cancelar reserva " + new Date());
        //ListaReservas listaReserva = new ListaReservas();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -1);
        ArrayList<Reserva> reservas = ListaReservas.listaReserva;
        for(Reserva r : reservas)
        {
            if(r.Estado == 1 && r.fechaCreacion.before(calendar.getTime()))
            {
                r.Estado = 0;
            }
        }  
        
        
}
}
