package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idusuario;

	private String email;

	private String nombre;

	//bi-directional many-to-one association to PlanVenta
	@OneToMany(mappedBy="usuario")
	private List<PlanVenta> planVentas;

	public Usuario() {
	}

	public Long getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
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

	public List<PlanVenta> getPlanVentas() {
		return this.planVentas;
	}

	public void setPlanVentas(List<PlanVenta> planVentas) {
		this.planVentas = planVentas;
	}

	public PlanVenta addPlanVenta(PlanVenta planVenta) {
		getPlanVentas().add(planVenta);
		planVenta.setUsuario(this);

		return planVenta;
	}

	public PlanVenta removePlanVenta(PlanVenta planVenta) {
		getPlanVentas().remove(planVenta);
		planVenta.setUsuario(null);

		return planVenta;
	}

}