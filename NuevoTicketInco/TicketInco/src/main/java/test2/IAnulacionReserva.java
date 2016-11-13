package test2;


import java.text.ParseException;

import javax.jws.WebService;

@WebService
public interface IAnulacionReserva {
	 public String AnularReserva( long idConfirmacionPago, long idMedioPago) throws ParseException;;
}
