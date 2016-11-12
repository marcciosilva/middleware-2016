package test2;

import java.text.ParseException;

import javax.jws.WebService;

@WebService
public interface IConsultarEstadoReserva {
	EstadoReserva Consulta_EstadoReserva(long identificador_reserva) throws ParseException;
}
