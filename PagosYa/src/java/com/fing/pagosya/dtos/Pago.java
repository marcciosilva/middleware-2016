package com.fing.pagosya.dtos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marccio
 */
@XmlRootElement
public class Pago implements Serializable {

	private long numeroTarjeta;
	private Calendar fechaVencimiento;
	private int digitoVerificador;
	private double monto;

	public Pago(long numeroTarjeta, Calendar fechaVencimiento,
			int digitoVerificador, double monto) {
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
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Pago other = (Pago) obj;
		if (this.numeroTarjeta != other.numeroTarjeta) {
			return false;
		}
		if (!Objects.equals(this.fechaVencimiento, other.fechaVencimiento)) {
			return false;
		}
		if (this.digitoVerificador != other.digitoVerificador) {
			return false;
		}
		if (Double.doubleToLongBits(this.monto) != Double.doubleToLongBits(
				other.monto)) {
			return false;
		}
		return true;
	}

}
