package test2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

//@WebService
@WebService(targetNamespace = "http://test2/", serviceName = "ConfirmacionReserva")
@Addressing(required = true)
@MTOM(enabled = true)
public interface IConfirmacionReserva {

	public void ConfirmarReserva(long idReserva, long idMedioPago, String nroTarjeta, Date fechaVencimiento, int digitoVerificador, @WebParam(name = "idConfirmacion", mode = WebParam.Mode.OUT) Holder<Long> idConfirmacion, @XmlMimeType("multipart/related") @WebParam(name = "imagenesBinarias", mode = WebParam.Mode.OUT) Holder<ArrayList<ImagenEntrada>> imagenesBinarias) throws ParseException;
}
