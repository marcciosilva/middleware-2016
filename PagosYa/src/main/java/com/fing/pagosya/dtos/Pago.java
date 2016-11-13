package com.fing.pagosya.dtos;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author marccio
 */
@XmlRootElement
public class Pago implements Serializable {

	@SerializedName("numeroTarjeta")
	private long numeroTarjeta;

	@SerializedName("fechaVencimiento")
	@XmlJavaTypeAdapter(DateFormatterAdapter.class)
	private Date fechaVencimiento;

	@SerializedName("digitoVerificador")
	private int digitoVerificador;

	@SerializedName("monto")
	private double monto;

	public Pago() {
	}

	public Pago(long numeroTarjeta, Date fechaVencimiento,
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

	private static class DateFormatterAdapter extends XmlAdapter<String, Date> {

		@Override
		public Date unmarshal(final String v) throws Exception {
			java.util.logging.Logger logger = java.util.logging.Logger.
					getLogger(Pago.class.getName());
			logger.info("Fecha recibida: " + v);
			Date date = new Date(Long.parseLong(v));
			return date;
		}

		@Override
		public String marshal(Date v) throws Exception {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}
	}

}
