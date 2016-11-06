package com.fing.pagosya.dtos;

/**
 *
 * @author marccio
 */
public class Confirmacion {

	private boolean aprobada;
	private long idConfirmacion;

	public Confirmacion() {
	}

	public boolean isAprobada() {
		return aprobada;
	}

	public void setAprobada(boolean aprobada) {
		this.aprobada = aprobada;
	}

	public long getIdConfirmacion() {
		return idConfirmacion;
	}

	public void setIdConfirmacion(long idConfirmacion) {
		this.idConfirmacion = idConfirmacion;
	}

}
