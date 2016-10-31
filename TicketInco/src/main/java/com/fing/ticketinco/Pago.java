/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.util.ArrayList;

/**
 *
 * @author Cami
 */
public class Pago {
    public static long contadorIdConfPagoLocal;
    long medioPago;
    long idConfPago;
    ArrayList<Reserva> reservas;   
    
    public Pago()
    {
        reservas = new ArrayList<Reserva>();
    }
    
}
