package esb;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface PagosYa {
	public long confirmarPago(@WebParam(name = "nroTarjeta") String nroTarjeta,
			@WebParam(name = "fechaVencimiento") String fechaVencimiento,
			@WebParam(name = "digitoVerificador") String digitoVerificador, @WebParam(name = "monto") String monto);

	public long anularPago(@WebParam(name = "idConfirmacionPago") String idConfirmacionPago);
}
