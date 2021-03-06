package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "plan_venta")
@NamedQuery(name = "PlanVenta.findAll", query = "SELECT p FROM PlanVenta p")
public class PlanVenta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPlanVenta;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	private String nombre;

	// bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	// bi-directional many-to-many association to Inventario
	@ManyToMany
	@JoinTable(name = "plan_venta_has_inventario", joinColumns = { @JoinColumn(name = "id_plan_venta") }, inverseJoinColumns = { @JoinColumn(name = "id_inventario") })
	private List<Inventario> inventarios;

	// bi-directional many-to-many association to Item
	@ManyToMany
	@JoinTable(name = "plan_venta_has_item", joinColumns = { @JoinColumn(name = "id_plan_venta") }, inverseJoinColumns = { @JoinColumn(name = "id_item") })
	private List<Item> items;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	// bi-directional many-to-many association to Propuesta
	@ManyToMany
	@JoinTable(name = "plan_venta_has_propuesta", joinColumns = { @JoinColumn(name = "id_plan_venta") }, inverseJoinColumns = { @JoinColumn(name = "id_propuesta") })
	private List<Propuesta> propuestas;

	// bi-directional many-to-many association to Servicio
	@ManyToMany
	@JoinTable(name = "plan_venta_has_servicio", joinColumns = { @JoinColumn(name = "id_plan_venta") }, inverseJoinColumns = { @JoinColumn(name = "id_servicio") })
	private List<Servicio> servicios;

	public PlanVenta() {
		// No require inicialización
	}

	public Long getIdPlanVenta() {
		return idPlanVenta;
	}

	public void setIdPlanVenta(Long idPlanVenta) {
		this.idPlanVenta = idPlanVenta;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Inventario> getInventarios() {
		return this.inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Propuesta> getPropuestas() {
		return this.propuestas;
	}

	public void setPropuestas(List<Propuesta> propuestas) {
		this.propuestas = propuestas;
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

}