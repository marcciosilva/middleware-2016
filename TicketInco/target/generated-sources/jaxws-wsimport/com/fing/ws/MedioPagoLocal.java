
package com.fing.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MedioPagoLocal", targetNamespace = "http://esb/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MedioPagoLocal {


    /**
     * 
     * @param idConfirmacionPago
     */
    @WebMethod
    @RequestWrapper(localName = "anularPago", targetNamespace = "http://esb/", className = "com.fing.ws.AnularPago")
    @ResponseWrapper(localName = "anularPagoResponse", targetNamespace = "http://esb/", className = "com.fing.ws.AnularPagoResponse")
    public void anularPago(
        @WebParam(name = "idConfirmacionPago", targetNamespace = "")
        String idConfirmacionPago);

    /**
     * 
     * @param digitoVerificador
     * @param monto
     * @param nroTarjeta
     * @param fechaVencimiento
     */
    @WebMethod
    @RequestWrapper(localName = "confirmarPago", targetNamespace = "http://esb/", className = "com.fing.ws.ConfirmarPago")
    @ResponseWrapper(localName = "confirmarPagoResponse", targetNamespace = "http://esb/", className = "com.fing.ws.ConfirmarPagoResponse")
    public void confirmarPago(
        @WebParam(name = "nroTarjeta", targetNamespace = "")
        String nroTarjeta,
        @WebParam(name = "fechaVencimiento", targetNamespace = "")
        String fechaVencimiento,
        @WebParam(name = "digitoVerificador", targetNamespace = "")
        String digitoVerificador,
        @WebParam(name = "monto", targetNamespace = "")
        String monto);

}
