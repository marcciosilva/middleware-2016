package esb;

import javax.jws.WebService;

@WebService
public interface MedioPagoLocal {
	public void confirmarPago(String nroTarjeta, String fechaVencimeinto, String digitoVerificador, String monto);
}
