package test2;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

//@WebService
@WebService(targetNamespace = "http://test2/", serviceName = "ConfirmacionReserva")
@Addressing(required = true)
@MTOM
public interface IConfirmacionReserva {

	public ConfirmarReservaRetornar ConfirmarReserva(long idReserva, long idMedioPago, String nroTarjeta, Date fechaVencimiento, int digitoVerificador) throws ParseException;
}
