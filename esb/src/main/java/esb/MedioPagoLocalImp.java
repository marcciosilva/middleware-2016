package esb;

import javax.jws.WebService;

@WebService(endpointInterface = "esb",
            serviceName = "MedioPagoLocal")
public class MedioPagoLocalImp implements MedioPagoLocal{
	public void confirmarPago(String nroTarjeta, String fechaVencimeinto, String digitoVerificador, String monto){
		
	}
}
