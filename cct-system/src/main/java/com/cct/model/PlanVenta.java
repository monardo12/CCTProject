package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="plan_venta")
@NamedQuery(name="PlanVenta.findAll", query="SELECT p FROM PlanVenta p")
public class PlanVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idplanventa;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fechacreacion;

	private String nombre;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Propuesta
	@ManyToOne
	@JoinColumn(name="idpropuesta")
	private Propuesta propuesta;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	//bi-directional many-to-one association to PlanVentaItem
	@OneToMany(mappedBy="planVenta")
	private List<PlanVentaItem> planVentaItems;

	//bi-directional many-to-one association to ServicioPlanVenta
	@OneToMany(mappedBy="planVenta")
	private List<ServicioPlanVenta> servicioPlanVentas;

	public PlanVenta() {
	}

	public Integer getIdplanventa() {
		return this.idplanventa;
	}

	public void setIdplanventa(Integer idplanventa) {
		this.idplanventa = idplanventa;
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

	public Date getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
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

	public Propuesta getPropuesta() {
		return this.propuesta;
	}

	public void setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PlanVentaItem> getPlanVentaItems() {
		return this.planVentaItems;
	}

	public void setPlanVentaItems(List<PlanVentaItem> planVentaItems) {
		this.planVentaItems = planVentaItems;
	}

	public PlanVentaItem addPlanVentaItem(PlanVentaItem planVentaItem) {
		getPlanVentaItems().add(planVentaItem);
		planVentaItem.setPlanVenta(this);

		return planVentaItem;
	}

	public PlanVentaItem removePlanVentaItem(PlanVentaItem planVentaItem) {
		getPlanVentaItems().remove(planVentaItem);
		planVentaItem.setPlanVenta(null);

		return planVentaItem;
	}

	public List<ServicioPlanVenta> getServicioPlanVentas() {
		return this.servicioPlanVentas;
	}

	public void setServicioPlanVentas(List<ServicioPlanVenta> servicioPlanVentas) {
		this.servicioPlanVentas = servicioPlanVentas;
	}

	public ServicioPlanVenta addServicioPlanVenta(ServicioPlanVenta servicioPlanVenta) {
		getServicioPlanVentas().add(servicioPlanVenta);
		servicioPlanVenta.setPlanVenta(this);

		return servicioPlanVenta;
	}

	public ServicioPlanVenta removeServicioPlanVenta(ServicioPlanVenta servicioPlanVenta) {
		getServicioPlanVentas().remove(servicioPlanVenta);
		servicioPlanVenta.setPlanVenta(null);

		return servicioPlanVenta;
	}

}