package esb.dtos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author marccio
 */
public class Pago implements Serializable {

	@SerializedName("numeroTarjeta")
	private long numeroTarjeta;
	
	@SerializedName("fechaVencimiento")
	private Date fechaVencimiento;
	
	@SerializedName("digitoVerificador")
	private int digitoVerificador;
	
	@SerializedName("monto")
	private double monto;

	public Pago() {
	}

	public Pago(long numeroTarjeta, Date fechaVencimiento, int digitoVerificador, double monto) {
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

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
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
