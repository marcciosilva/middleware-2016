/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
//import org.apache.cxf.endpoint.Endpoint;
//import org.apache.cxf.jaxws.EndpointImpl;

/**
 *
 * @author javier
 */
@WebService(serviceName = "ConfirmacionReserva")
public class ConfirmacionReserva {

    /**
     * This is a sample web service operation
     */
    /*EndpointImpl jaxWsEndpoint = (EndpointImpl) Endpoint.publish("http://localhost/ConfirmacionReserva", "ConfirmarReserva");
    Endpoint cxfEndpoint = jaxWsEndpoint.getServer().getEndpoint();

    Map<String,Object> inProps = new HashMap<String,Object>();
// how to configure the properties is outlined below;
 
WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
cxfEndpoint.getInInterceptors().add(wssIn);
 
Map<String,Object> outProps = new HashMap<String,Object>();
// how to configure the properties is outlined below;
 
WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
cxfEndpoint.getOutInterceptors().add(wssOut);*/

    @WebMethod(operationName = "ConfirmarReserva")
    public String ConfirmarReserva(@WebParam(name = "idReserva") long idReserva, @WebParam(name = "idMedioPago")long idMedioPago, @WebParam(name = "nroTarjeta") String nroTarjeta, @WebParam(name = "fechaVencimeinto") Date fechaVencimiento, @WebParam(name = "digitoVerificador") int digitoVerificador) throws ParseException {
        
        //TODO: Hay que agregar WS-Addressing y WS-Security
        ListaReservas listaReservas = new ListaReservas();
        Reserva reserva = listaReservas.buscarReserva(idReserva);
        if(reserva != null)
        {
            if(idMedioPago == 1000)
            {
                // Ir al servicio local
                return "Medio de pago local";
            }
            else if(idMedioPago == 2000)
            {
                // Ir a al servicio de Pagos Ya
                return "Medio de pago PagosYa!";
            }
            else
            {
                return "El medio de pago no existe";
            }
        }
        return "No existe la reserva";
    }
}
