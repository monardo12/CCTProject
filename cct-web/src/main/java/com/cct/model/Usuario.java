package com.cct.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Long idUsuario;

	private String email;

	private String nombre;

	//bi-directional many-to-one association to PlanVenta
	@OneToMany(mappedBy="usuario")
	private List<PlanVenta> planVentas;

	//bi-directional many-to-one association to Reporte
	@OneToMany(mappedBy="usuario")
	private List<Reporte> reportes;

	public Usuario() {
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@JsonIgnore
	public List<PlanVenta> getPlanVentas() {
		return this.planVentas;
	}

	public void setPlanVentas(List<PlanVenta> planVentas) {
		this.planVentas = planVentas;
	}

	@JsonIgnore
	public List<Reporte> getReportes() {
		return this.reportes;
	}

	public void setReportes(List<Reporte> reportes) {
		this.reportes = reportes;
	}

}