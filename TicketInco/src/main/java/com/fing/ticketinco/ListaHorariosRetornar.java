/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Cami
 */
public class ListaHorariosRetornar {
    @XmlElement
    ArrayList<Horario> horarios;
    
    public ListaHorariosRetornar()
    {
        horarios = new ArrayList<Horario>();
    }
    
    public ListaHorariosRetornar(ArrayList<Horario> horarios)
    {
        this.horarios = horarios;
    }
    public List<Horario> getHorarios()
    {
        return horarios;
    }
    
    public void setHorarios(ArrayList<Horario> horarios)
    {
        this.horarios = horarios;
    }
}
