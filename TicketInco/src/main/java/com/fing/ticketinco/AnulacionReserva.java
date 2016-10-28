/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.apache.log4j.Logger;

/**
 *
 * @author javier
 */
@WebService(serviceName = "AnulacionReserva")
public class AnulacionReserva {
    
    final static Logger fgen = Logger.getLogger(AnulacionReserva.class);
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "AnularReserva")
    public String AnularReserva(@WebParam(name = "idConfirmacionPago") long idConfirmacionPago, @WebParam(name = "idMedioPago") long idMedioPago) {
        
        fgen.info ("Identificador de la confirmación del pago " + idConfirmacionPago);
        fgen.info ("Identificador del medio de pago " + idMedioPago);
        
        ListaPagos listaPagos = new ListaPagos();
        Pago pago = listaPagos.buscarPago(idConfirmacionPago);
        if(pago != null)
        {
            if(idMedioPago == 1000)
            {
                //LLamamos al servicio local para anular el pago
                return "Servicio local";
            }
            else if(idMedioPago == 2000)
            {
                //Llamar al servicio de PagosYa para anular el pago
                return "Servicio PagosYa";
            }
            else
            {
                return "El medio de pago no existe";
            }
        }
        else
        {
            return "No existe el pago con ese identificador.";
        }
        
    }
}
