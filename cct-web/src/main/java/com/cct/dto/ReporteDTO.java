package com.cct.dto;

import java.util.Date;

import com.cct.constant.TipoReporte;

public class ReporteDTO {

	private Long id;
	
	private TipoReporte tipo;

	private Date fechaInicial;

	private Date fechaFinal;
	
	private Long idUsuario;

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

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ReporteDTO [id=" + id + ", tipo=" + tipo + ", fechaInicial="
				+ fechaInicial + ", fechaFinal=" + fechaFinal + ", idUsuario="
				+ idUsuario + "]";
	}
	
}
