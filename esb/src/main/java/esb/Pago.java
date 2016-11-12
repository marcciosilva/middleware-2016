package esb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marccio
 */
public class Pago implements Serializable {

	private long numeroTarjeta;
	private Calendar fechaVencimiento;
	private int digitoVerificador;
	private double monto;

	public Pago() {
	}

	public Pago(long numeroTarjeta, Calendar fechaVencimiento, int digitoVerificador, double monto) {
		this.numeroTarjeta = numeroTarjeta;
		this.fechaVencimiento = fechaVencimiento;
		this.digitoVerificador = digitoVerificador;
		this.monto = monto;
	}

	public long getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Calendar getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Calendar fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getDigitoVerificador() {
		return digitoVerificador;
	}

	public void setDigitoVerificador(int digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "numeroTarjeta: " + Long.toString(numeroTarjeta) + ", fechaVencimiento: " + fechaVencimiento.toString()
				+ ", digitoVerificador: " + Integer.toString(digitoVerificador) + ", monto: " + Double.toString(monto);
	}

}
