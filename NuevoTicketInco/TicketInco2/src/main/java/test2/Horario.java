package test2;
import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Cami
 */
public class Horario {

	@XmlElement
	Calendar hora;
	@XmlElement
	ArrayList<Disponibilidad> disponibilidades;

	public Horario() {
		hora = Calendar.getInstance();
		disponibilidades = new ArrayList<Disponibilidad>();

	}

	public Horario(Calendar date, ArrayList<Disponibilidad> disponibilidades) {
		this.hora = date;
		this.disponibilidades = disponibilidades;
	}

	public Calendar getHorario() {
		return hora;
	}

	public void setHorario(Calendar horario) {
		this.hora = horario;
	}

	public void agregarDisponibilidad(Disponibilidad disponibilidad) {
		disponibilidades.add(disponibilidad);
	}

	public Disponibilidad buscarDisponibilidad(String sector) {
		for (Disponibilidad d : disponibilidades) {
			if (d.getSector().equals(sector)) {
				return d;
			}
		}
		return null;
	}

}
