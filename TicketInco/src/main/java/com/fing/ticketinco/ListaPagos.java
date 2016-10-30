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
public class ListaPagos {
    static ArrayList<Pago> pagos;
    
    public void ListaPagos()
    {
        if(pagos == null)
        {
            pagos = new ArrayList<Pago>();
        }
    }
    
    public Pago buscarPago(long idConfPago)
    {
        for(Pago p : pagos)
        {
            if(p.idConfPago == idConfPago)
            {
                return p;
            }
        }
        
        return null;
    }
}
