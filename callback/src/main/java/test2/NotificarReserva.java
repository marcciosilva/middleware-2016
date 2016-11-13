package test2;

import java.util.ArrayList;

import javax.jws.WebService;

@WebService(serviceName = "NotificarReserva")
public class NotificarReserva implements INotificarReserva{

	@Override
	public void ConfirmarReservaResponse(long idConfirmacion, ArrayList<ImagenEntrada> imagenesBinarias) {
		// TODO Auto-generated method stub
		System.out.println("idConfirmacion " + idConfirmacion);		
	}

}
