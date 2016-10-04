package com.cct.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s")
public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idservicio;

	private String descripcion;

	private String nombre;

	// bi-directional many-to-many association to Item
	@ManyToMany
	@JoinTable(name = "servicio_has_item", joinColumns = { @JoinColumn(name = "idservicio") }, inverseJoinColumns = { @JoinColumn(name = "iditem") })
	private List<Item> items;

	// bi-directional many-to-many association to PlanVenta
	@ManyToMany(mappedBy = "servicios")
	private List<PlanVenta> planVentas;

	public Servicio() {
	}

	public Long getIdservicio() {
		return this.idservicio;
	}

	public void setIdservicio(Long idservicio) {
		this.idservicio = idservicio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@JsonIgnore
	public List<PlanVenta> getPlanVentas() {
		return this.planVentas;
	}

	public void setPlanVentas(List<PlanVenta> planVentas) {
		this.planVentas = planVentas;
	}

}