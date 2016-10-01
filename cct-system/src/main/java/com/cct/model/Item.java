package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer iditem;

	private String descripcion;

	private String nombre;

	private double precio;

	//bi-directional many-to-one association to PlanVentaItem
	@OneToMany(mappedBy="item")
	private List<PlanVentaItem> planVentaItems;

	//bi-directional many-to-one association to ServicioItem
	@OneToMany(mappedBy="item")
	private List<ServicioItem> servicioItems;

	//bi-directional many-to-one association to UnidadItem
	@OneToMany(mappedBy="item")
	private List<UnidadItem> unidadItems;

	public Item() {
	}

	public Integer getIditem() {
		return this.iditem;
	}

	public void setIditem(Integer iditem) {
		this.iditem = iditem;
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

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<PlanVentaItem> getPlanVentaItems() {
		return this.planVentaItems;
	}

	public void setPlanVentaItems(List<PlanVentaItem> planVentaItems) {
		this.planVentaItems = planVentaItems;
	}

	public PlanVentaItem addPlanVentaItem(PlanVentaItem planVentaItem) {
		getPlanVentaItems().add(planVentaItem);
		planVentaItem.setItem(this);

		return planVentaItem;
	}

	public PlanVentaItem removePlanVentaItem(PlanVentaItem planVentaItem) {
		getPlanVentaItems().remove(planVentaItem);
		planVentaItem.setItem(null);

		return planVentaItem;
	}

	public List<ServicioItem> getServicioItems() {
		return this.servicioItems;
	}

	public void setServicioItems(List<ServicioItem> servicioItems) {
		this.servicioItems = servicioItems;
	}

	public ServicioItem addServicioItem(ServicioItem servicioItem) {
		getServicioItems().add(servicioItem);
		servicioItem.setItem(this);

		return servicioItem;
	}

	public ServicioItem removeServicioItem(ServicioItem servicioItem) {
		getServicioItems().remove(servicioItem);
		servicioItem.setItem(null);

		return servicioItem;
	}

	public List<UnidadItem> getUnidadItems() {
		return this.unidadItems;
	}

	public void setUnidadItems(List<UnidadItem> unidadItems) {
		this.unidadItems = unidadItems;
	}

	public UnidadItem addUnidadItem(UnidadItem unidadItem) {
		getUnidadItems().add(unidadItem);
		unidadItem.setItem(this);

		return unidadItem;
	}

	public UnidadItem removeUnidadItem(UnidadItem unidadItem) {
		getUnidadItems().remove(unidadItem);
		unidadItem.setItem(null);

		return unidadItem;
	}

}