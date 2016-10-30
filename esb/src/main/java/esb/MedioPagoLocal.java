package esb;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface MedioPagoLocal {
	public void confirmarPago(@WebParam(name = "nroTarjeta") String nroTarjeta, @WebParam(name = "fechaVencimiento") String fechaVencimeinto, @WebParam(name = "digitoVerificador") String digitoVerificador, @WebParam(name = "monto") String monto);
}
