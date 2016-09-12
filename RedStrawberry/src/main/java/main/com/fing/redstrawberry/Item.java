/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.com.fing.redstrawberry;

/**
 *
 * @author javier
 */
public class Item {
	private Long identificadorproducto;
	private Integer cantidad;
	private String identificadortransaccion;
	private String fechaHora;

	public void setidentificadorproducto(Long identificadorproducto) {
		this.identificadorproducto = identificadorproducto;
	}

	public void setcantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setidentificadortransaccion(String identificadortransaccion) {
		this.identificadortransaccion = identificadortransaccion;
	}

	public void setfechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Long getidentificadorproducto() {
		return identificadorproducto;
	}

	public Integer getcantidad() {
		return cantidad;
	}

	public String getidentificadortransaccion() {
		return identificadortransaccion;
	}

	public String getfechaHora() {
		return fechaHora;
	}

}
