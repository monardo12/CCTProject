package com.cct.dto;

import java.io.Serializable;
import java.util.Date;

import com.cct.constant.TipoReporte;

public class ReporteDTO implements Serializable{

	private static final long serialVersionUID = -9209612670984890931L;

	private Long id;
	
	private TipoReporte tipo;

	private Date fechaInicial;

	private Date fechaFinal;
	
	private Long idUsuario;
	
	private String key;
	
	private String signature;

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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
