/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Cami
 */
public class Evento {
    int idEvento;
    Date fechaEvento;
    List<Horario> horarios;
    
    public Evento()
    {
        idEvento = 0;
        fechaEvento = new Date();
        horarios = new ArrayList<Horario>();
    }
    
    public Evento(int idEvento, Date fechaEvento, List<Horario> horarios)
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
    
    public Date getFechaEvento()
    {
        return fechaEvento;
    }
    
    public void setFechaEvento(Date fechaEvento)
    {
        this.fechaEvento = fechaEvento;
    }
    
    public List<Horario> getHoarios()
    {
        return horarios;
    }
    
    public void setHorarios(List<Horario> horarios)
    {
        this.horarios = horarios;
    }
    
    public void agregarHorario(Horario horario)
    {
        horarios.add(horario);
    }
}
