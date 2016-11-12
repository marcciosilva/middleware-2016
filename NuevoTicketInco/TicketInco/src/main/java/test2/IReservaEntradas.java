package test2;

import java.text.ParseException;
import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IReservaEntradas {
	public long reserva_de_entradas(int identificador_evento, Date fechaevento, Horario[] listahorarios ) throws ParseException;

}
