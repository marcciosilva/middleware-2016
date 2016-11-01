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
    public static long contadorIdConfAnulacionLocal;
    long medioPago;
    long idConfPago;
    long idConfAnulacionPago;
    ArrayList<Reserva> reservas;   
    
    public Pago()
    {
        reservas = new ArrayList<Reserva>();
        idConfAnulacionPago = -1;
        idConfPago = -1;
    }
    
}
