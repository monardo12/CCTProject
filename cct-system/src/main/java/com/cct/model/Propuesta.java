package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name="Propuesta.findAll", query="SELECT p FROM Propuesta p")
public class Propuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idpropuesta;

	private Integer costo;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fechafin;

	@Temporal(TemporalType.DATE)
	private Date fechainicio;

	//bi-directional many-to-one association to PlanVenta
	@OneToMany(mappedBy="propuesta")
	private List<PlanVenta> planVentas;

	//bi-directional many-to-one association to Socio
	@ManyToOne
	@JoinColumn(name="idsocio")
	private Socio socio;

	public Propuesta() {
	}

	public Long getIdpropuesta() {
		return this.idpropuesta;
	}

	public void setIdpropuesta(Long idpropuesta) {
		this.idpropuesta = idpropuesta;
	}

	public Integer getCosto() {
		return this.costo;
	}

	public void setCosto(Integer costo) {
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

	public List<PlanVenta> getPlanVentas() {
		return this.planVentas;
	}

	public void setPlanVentas(List<PlanVenta> planVentas) {
		this.planVentas = planVentas;
	}

	public PlanVenta addPlanVenta(PlanVenta planVenta) {
		getPlanVentas().add(planVenta);
		planVenta.setPropuesta(this);

		return planVenta;
	}

	public PlanVenta removePlanVenta(PlanVenta planVenta) {
		getPlanVentas().remove(planVenta);
		planVenta.setPropuesta(null);

		return planVenta;
	}

	public Socio getSocio() {
		return this.socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

}