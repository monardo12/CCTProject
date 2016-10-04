package com.cct.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "Propuesta.findAll", query = "SELECT p FROM Propuesta p")
public class Propuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idpropuesta;

	private double costo;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fechafin;

	@Temporal(TemporalType.DATE)
	private Date fechainicio;

	// bi-directional many-to-many association to PlanVenta
	@ManyToMany(mappedBy = "propuestas")
	private List<PlanVenta> planVentas;

	// bi-directional many-to-one association to Socio
	@ManyToOne
	@JoinColumn(name = "idsocio")
	private Socio socio;

	public Propuesta() {
	}

	public Long getIdpropuesta() {
		return this.idpropuesta;
	}

	public void setIdpropuesta(Long idpropuesta) {
		this.idpropuesta = idpropuesta;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Date getFechainicio() {
		return this.fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	@JsonIgnore
	public List<PlanVenta> getPlanVentas() {
		return this.planVentas;
	}

	public void setPlanVentas(List<PlanVenta> planVentas) {
		this.planVentas = planVentas;
	}

	public Socio getSocio() {
		return this.socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

}