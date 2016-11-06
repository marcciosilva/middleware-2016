/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.callback;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author Cami
 */
@WebService(serviceName = "NotificarConfirmacionReserva")
@Addressing
public class NotificarConfirmacionReserva {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "NotificarEntradas")
    @MTOM
    public ReservasRetornar notificarConfirmacionResreva(@WebParam(name = "idReserva") long idReserva, @WebParam(name = "entradaImagen") byte[] entradaImagen) {
        ReservasRetornar reservas = new ReservasRetornar();
        reservas.idReserva = idReserva;
        if(entradaImagen != null)
        {
            reservas.imagenEntrada = entradaImagen;
        }
        
        return reservas;
    }
}
