package test2;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IConfirmacionReserva {

	public byte[] ConfirmarReserva(long idReserva, long idMedioPago, String nroTarjeta, Date fechaVencimiento, int digitoVerificador) throws ParseException;
}
