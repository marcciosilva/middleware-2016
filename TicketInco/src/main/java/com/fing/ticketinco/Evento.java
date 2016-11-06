/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Cami
 */
public class Evento {
    int idEvento;
    Calendar fechaEvento;
    ArrayList<Horario> horarios;
    
    public Evento()
    {
        idEvento = 0;
        fechaEvento = Calendar.getInstance();
        horarios = new ArrayList<Horario>();
    }
    
    public Evento(int idEvento, Calendar fechaEvento, ArrayList<Horario> horarios)
    {
        this.idEvento = idEvento;
        this.fechaEvento = fechaEvento;
        this.horarios = horarios;
    }
    
    public int getIdEvento()
    {
        return idEvento;
    }
    
    public void setIdEvento(int idEvento)
    {
        this.idEvento = idEvento;
    }
    
    public Calendar getFechaEvento()
    {
        return fechaEvento;
    }
    
    public void setFechaEvento(Calendar fechaEvento)
    {
        this.fechaEvento = fechaEvento;
    }
    
    public ArrayList<Horario> getHorarios()
    {
        return horarios;
    }
    
    public void setHorarios(ArrayList<Horario> horarios)
    {
        this.horarios = horarios;
    }
    
    public void agregarHorario(Horario horario)
    {
        horarios.add(horario);
    }
    
    public Horario buscarHorario(Calendar hora)
    {
        for(Horario h : horarios)
        {
            Date horaH = h.hora.getTime();           
            Date horaBuscar = hora.getTime();    
            if(horaH.equals(horaBuscar))
            {
                return h;
            }
        }
        return null;
    }
}
