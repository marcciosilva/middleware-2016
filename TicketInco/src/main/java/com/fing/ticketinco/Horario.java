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
public class Horario {
    Calendar horario;
    List<Disponibilidad> disponibilidades;
    
    public Horario()
    {
        horario = Calendar.getInstance();
        disponibilidades = new ArrayList<Disponibilidad>();
    }
    
    public Horario(Calendar date, List<Disponibilidad> disponibilidades)
    {
        this.horario = date;
        this.disponibilidades = disponibilidades;
    }
    
    public Calendar getHorario()
    {
        return horario;
    }
    
    public void setHorario(Calendar horario)
    {
        this.horario = horario;
    }
    
    public void agregarDisponibilidad(Disponibilidad disponibilidad)
    {
        disponibilidades.add(disponibilidad);
    }
        
}
