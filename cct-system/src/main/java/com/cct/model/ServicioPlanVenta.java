package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="servicio_plan_venta")
@NamedQuery(name="ServicioPlanVenta.findAll", query="SELECT s FROM ServicioPlanVenta s")
public class ServicioPlanVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idservicioplanventa;

	//bi-directional many-to-one association to PlanVenta
	@ManyToOne
	@JoinColumn(name="idplanventa")
	private PlanVenta planVenta;

	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="idservicio")
	private Servicio servicio;

	public ServicioPlanVenta() {
	}

	public Long getIdservicioplanventa() {
		return this.idservicioplanventa;
	}

	public void setIdservicioplanventa(Long idservicioplanventa) {
		this.idservicioplanventa = idservicioplanventa;
	}

	public PlanVenta getPlanVenta() {
		return this.planVenta;
	}

	public void setPlanVenta(PlanVenta planVenta) {
		this.planVenta = planVenta;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

}