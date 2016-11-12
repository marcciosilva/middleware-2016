/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

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
            inicializarScheduler();
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
        
        Disponibilidad d1 = new Disponibilidad("Sector 1", 300.25, 100);
        Disponibilidad d2 = new Disponibilidad("Sector 2", 450.30, 100);
        Disponibilidad d3 = new Disponibilidad("Sector 3", 800.75, 100);
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
    
    private void inicializarScheduler()
    {
                try {
                    //Inicializo el scheduler
                    SchedulerFactory sf = new StdSchedulerFactory();
                    Scheduler sched = sf.getScheduler();
                    
                    JobDetail job = newJob(CancelarReservasJob.class)
                            .withIdentity("job1", "group1")
                            .build();
                    
                    Date runTime = evenMinuteDate(new Date());
                    
                    // Trigger the job to run on the next round minute
                    Trigger trigger = newTrigger()
                            .withIdentity("trigger1", "group1")
                            .withSchedule(cronSchedule("0 0/10 * 1/1 * ? *"))
                            .startAt(runTime)
                            .build();
                    // Cada 1 minuto:   0 0/1 * 1/1 * ? *
                    // Cada 2 minutos: 	0 0/2 * 1/1 * ? *
                    // Cada 3 mintuos: 	0 0/3 * 1/1 * ? *
                    // Cada 5 minutos: 	0 0/5 * 1/1 * ? *
                    // Cada 10 minutoas: 	0 0/10 * 1/1 * ? *
                    sched.scheduleJob(job, trigger);
                    sched.start();
                    
                } catch (SchedulerException ex) {
                    Logger.getLogger(ListaEventos.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}
