package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="Servicio.findAll", query="SELECT s FROM Servicio s")
public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idservicio;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to ServicioItem
	@OneToMany(mappedBy="servicio")
	private List<ServicioItem> servicioItems;

	//bi-directional many-to-one association to ServicioPlanVenta
	@OneToMany(mappedBy="servicio")
	private List<ServicioPlanVenta> servicioPlanVentas;

	public Servicio() {
	}

	public Integer getIdservicio() {
		return this.idservicio;
	}

	public void setIdservicio(Integer idservicio) {
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

	public List<ServicioItem> getServicioItems() {
		return this.servicioItems;
	}

	public void setServicioItems(List<ServicioItem> servicioItems) {
		this.servicioItems = servicioItems;
	}

	public ServicioItem addServicioItem(ServicioItem servicioItem) {
		getServicioItems().add(servicioItem);
		servicioItem.setServicio(this);

		return servicioItem;
	}

	public ServicioItem removeServicioItem(ServicioItem servicioItem) {
		getServicioItems().remove(servicioItem);
		servicioItem.setServicio(null);

		return servicioItem;
	}

	public List<ServicioPlanVenta> getServicioPlanVentas() {
		return this.servicioPlanVentas;
	}

	public void setServicioPlanVentas(List<ServicioPlanVenta> servicioPlanVentas) {
		this.servicioPlanVentas = servicioPlanVentas;
	}

	public ServicioPlanVenta addServicioPlanVenta(ServicioPlanVenta servicioPlanVenta) {
		getServicioPlanVentas().add(servicioPlanVenta);
		servicioPlanVenta.setServicio(this);

		return servicioPlanVenta;
	}

	public ServicioPlanVenta removeServicioPlanVenta(ServicioPlanVenta servicioPlanVenta) {
		getServicioPlanVentas().remove(servicioPlanVenta);
		servicioPlanVenta.setServicio(null);

		return servicioPlanVenta;
	}

}