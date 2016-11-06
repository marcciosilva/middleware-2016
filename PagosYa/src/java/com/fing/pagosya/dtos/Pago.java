package com.fing.pagosya.dtos;

import java.util.Calendar;

/**
 *
 * @author marccio
 */
public class Pago {

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

}
