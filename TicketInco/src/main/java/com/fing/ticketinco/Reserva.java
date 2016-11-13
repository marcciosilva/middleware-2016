/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author javier
 */
public class Reserva {
    public long idReserva; 
    int idEvento;
    Calendar fechaEvento;
    public ArrayList<Horario> horarios;
    public int Estado;
    Date fechaCreacion = new Date();
}
