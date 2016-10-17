package com.cct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.cct.constant.EstadoReporte;
import com.cct.constant.TipoReporte;

@Entity
@NamedQuery(name="Reporte.findAll", query="SELECT r FROM Reporte r")
public class Reporte implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reporte")
	private Long idReporte;

	@Enumerated(EnumType.STRING)
	private EstadoReporte estado;

	@Enumerated(EnumType.STRING)
	private TipoReporte tipo;

	private String url;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Reporte() {
	}

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public EstadoReporte getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoReporte estado) {
		this.estado = estado;
	}

	public TipoReporte getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoReporte tipo) {
		this.tipo = tipo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}