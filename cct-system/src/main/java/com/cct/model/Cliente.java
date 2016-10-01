package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idcliente;

	private String email;

	private String nombre;

	private Integer telefono;

	//bi-directional many-to-one association to PlanVenta
	@OneToMany(mappedBy="cliente")
	private List<PlanVenta> planVentas;

	public Cliente() {
	}

	public Integer getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
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

	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public List<PlanVenta> getPlanVentas() {
		return this.planVentas;
	}

	public void setPlanVentas(List<PlanVenta> planVentas) {
		this.planVentas = planVentas;
	}

	public PlanVenta addPlanVenta(PlanVenta planVenta) {
		getPlanVentas().add(planVenta);
		planVenta.setCliente(this);

		return planVenta;
	}

	public PlanVenta removePlanVenta(PlanVenta planVenta) {
		getPlanVentas().remove(planVenta);
		planVenta.setCliente(null);

		return planVenta;
	}

}