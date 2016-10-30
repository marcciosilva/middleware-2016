/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import com.fing.ws.MedioPagoLocal;
import com.fing.ws.MedioPagoLocalService;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.apache.log4j.Logger;
//import org.apache.cxf.endpoint.Endpoint;
//import org.apache.cxf.jaxws.EndpointImpl;

/**
 *
 * @author javier
 */
@WebService(serviceName = "ConfirmacionReserva")
public class ConfirmacionReserva {
    
    final static Logger fgen = Logger.getLogger(ConfirmacionReserva.class);
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
        try
        {
            fgen.info ("Identificador de la reserva " + idReserva);
            fgen.info ("Identificador del medio de pago " + idMedioPago);
            //fgen.info("Número tarjeta ", nroTarjeta);
            //fgen.info("Fecha vencimiento ", fechaVencimiento);
            //fgen.info("Dígito verificador ", digitoVerificador);
        }
        catch(Exception e)
        {
            
        }
        
           
        //TODO: Hay que agregar WS-Addressing y WS-Security
        ListaReservas listaReservas = new ListaReservas();
        Reserva reserva = listaReservas.buscarReserva(idReserva);
        if(reserva != null)
        {
            if(idMedioPago == 1000)
            {
                try
                {                    
                    MedioPagoLocalService medioPagoService = new MedioPagoLocalService();
                    MedioPagoLocal medioPagoLocal = medioPagoService.getMedioPagoLocalPort();
                    String digitoVerificadorStr = String.valueOf(digitoVerificador);
                    medioPagoLocal.confirmarPago(nroTarjeta, fechaVencimiento.toString(), digitoVerificadorStr, "1000");
                    
                }
                catch(Exception e)
                {
                    System.out.println("Error " + e.getMessage());
                }
                
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
