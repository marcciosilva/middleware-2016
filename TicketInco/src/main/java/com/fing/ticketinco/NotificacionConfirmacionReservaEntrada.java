/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fing.ticketinco;

import java.awt.Image;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author javier
 */
@WebService(serviceName = "NotificacionConfirmacionReservaEntrada")
@Addressing
public class NotificacionConfirmacionReservaEntrada {

    /**
     * This is a sample web service operation
     */
    @MTOM
    @WebMethod(operationName = "notificacionConfirmacionReserva")
    public ArrayList<byte[]> notificacionConfirmacionReserva(@WebParam(name = "idReserva") long idReserva, @WebParam(name = "listaEntradas") ArrayList<byte[]> listaEntradas) {
        if( listaEntradas != null)
        {
            return listaEntradas;
        }
        return null;
    }
}
