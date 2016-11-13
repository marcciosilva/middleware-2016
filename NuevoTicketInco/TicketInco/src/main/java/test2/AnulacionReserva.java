/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.apache.log4j.Logger;
import esb_mediopagolocal.*;
import esb_pagosya.*;

/**
 *
 * @author javier
 */
@WebService(serviceName = "AnulacionReserva")
public class AnulacionReserva  implements IAnulacionReserva{
    
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
            //Si el pago ya fue anulado retorno el mismo id de confirmación de anulación
            if(pago.idConfAnulacionPago != -1)
            {
                String idconfAnulacionPagoStr = String.valueOf(pago.idConfAnulacionPago);
                return idconfAnulacionPagoStr;
            }
            boolean okAnular = anular(pago);
            if(!okAnular)
            {
                return "Ocurrió un error anulando el pago.";
            }
            if(idMedioPago == 1000)
            {                
                //LLamamos al servicio local para anular el pago
            	esb_mediopagolocal.MedioPagoLocalService medioPagoService = new esb_mediopagolocal.MedioPagoLocalService();
                esb_mediopagolocal.MedioPagoLocal medioPagoLocal = medioPagoService.getMedioPagoLocalPort();
                String idConfirmacionPagoStr = String.valueOf(idConfirmacionPago);                
                medioPagoLocal.anularPago(idConfirmacionPagoStr);
                Pago.contadorIdConfAnulacionLocal++;
                pago.idConfAnulacionPago = Pago.contadorIdConfAnulacionLocal;
                String idconfAnulacionPagoStr = String.valueOf(pago.idConfAnulacionPago);
                return idconfAnulacionPagoStr;
            }
            else if(idMedioPago == 2000)
            {
                //Llamar al servicio de PagosYa para anular el pago
            	
            	esb_pagosya.PagosYaService pya= new esb_pagosya.PagosYaService();
            	esb_pagosya.PagosYa pyainterface = pya.getPagosYaPort();
            	
            	//esb_pagosya.MedioPagoLocalService pya= new esb_pagosya.MedioPagoLocalService();
            	//esb_pagosya.MedioPagoLocal pyainterface = pya.getMedioPagoLocalPort();
            	
            	String idConfirmacionPagoStr = String.valueOf(idConfirmacionPago); 
            	pyainterface.anularPago(idConfirmacionPagoStr);
            	Pago.contadorIdConfAnulacionLocal++;
                pago.idConfAnulacionPago = Pago.contadorIdConfAnulacionLocal;
                String idconfAnulacionPagoStr = String.valueOf(pago.idConfAnulacionPago);
                return idconfAnulacionPagoStr;               
            	
            	
                //return "Servicio PagosYa";
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
    
    private boolean anular(Pago pago)
    {
        ArrayList<Reserva> reservas = pago.reservas;
        for(Reserva r :reservas)
        {
            try {
                ArrayList<Horario> horarios = r.horarios;
                ListaEventos listaEventos = new ListaEventos();
                Evento evento = listaEventos.buscarEvento(r.idEvento, r.fechaEvento);
                for(Horario h : horarios)
                {
                    Horario horarioModificar = evento.buscarHorario(h.hora);
                    ArrayList<Disponibilidad> disponibilidadesReservadas = h.disponibilidades;
                    if(horarioModificar == null)
                    {
                        return false;
                    }
                    //ArrayList<Disponibilidad> disponibilidadesModificar = horarioModificar.disponibilidades;
                    for(Disponibilidad d : disponibilidadesReservadas)
                    {
                        Disponibilidad disponibilidadModificar = horarioModificar.buscarDisponibilidad(d.getSector());
                        disponibilidadModificar.cantidad = disponibilidadModificar.getCantidad() + d.getCantidad();
                    }
                }
                ListaReservas listaReservas = new ListaReservas();
                Reserva reservaModificar = listaReservas.buscarReserva(r.idReserva);
                reservaModificar.Estado = 0;
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(AnulacionReserva.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        return true;
    }
}
