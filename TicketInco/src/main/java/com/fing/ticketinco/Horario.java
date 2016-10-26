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
public class Horario {
    Date horario;
    List<Disponibilidad> disponibilidades;
    
    public Horario()
    {
        horario = new Date();
        disponibilidades = new ArrayList<Disponibilidad>();
    }
    
    public Horario(Date date, List<Disponibilidad> disponibilidades)
    {
        this.horario = date;
        this.disponibilidades = disponibilidades;
    }
    
    public Date getHorario()
    {
        return horario;
    }
    
    public void setHorario(Date horario)
    {
        this.horario = horario;
    }
    
    public void agregarDisponibilidad(Disponibilidad disponibilidad)
    {
        disponibilidades.add(disponibilidad);
    }
        
}
