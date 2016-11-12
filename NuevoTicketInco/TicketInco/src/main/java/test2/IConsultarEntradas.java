package test2;

import java.text.ParseException;
import java.util.Calendar;

import javax.jws.WebService;

@WebService
public interface IConsultarEntradas {

	public ListaHorariosRetornar ConsultarEntradas(int idEvento, Calendar fechaEvento) throws ParseException;
}
