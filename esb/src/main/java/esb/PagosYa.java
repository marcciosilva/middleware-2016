package esb;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface PagosYa {
	public void confirmarPago(@WebParam(name = "nroTarjeta") String nroTarjeta,
			@WebParam(name = "fechaVencimiento") String fechaVencimiento,
			@WebParam(name = "digitoVerificador") String digitoVerificador, @WebParam(name = "monto") String monto);

	public void anularPago(@WebParam(name = "idConfirmacionPago") String idConfirmacionPago);
}
