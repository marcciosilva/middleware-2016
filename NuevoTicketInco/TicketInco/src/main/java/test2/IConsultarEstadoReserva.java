package test2;

import java.text.ParseException;

import javax.jws.WebService;

@WebService
public interface IConsultarEstadoReserva {
	
	public EstadoReserva ConsultaEstadoReserva(long identificador_reserva) throws ParseException;

}
