package com.cct.dto;

import java.util.Date;

import com.cct.constant.TipoReporte;

public class ReporteDTO {

	private TipoReporte tipo;

	private Date fechaInicial;

	private Date fechaFinal;

	public TipoReporte getTipo() {
		return tipo;
	}

	public void setTipo(TipoReporte tipo) {
		this.tipo = tipo;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

}
