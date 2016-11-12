/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.util.ArrayList;

/**
 *
 * @author Cami
 */
public class ListaPagos {
    
    public static ArrayList<Pago> pagos;    
    
    public ListaPagos()
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
    
    public void agregarPago(Pago pago)
    {
        pagos.add(pago);
    }
}
