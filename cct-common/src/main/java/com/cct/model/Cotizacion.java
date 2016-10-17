package com.cct.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Cotizacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_cotizacion")
	private Long idCotizacion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_servicio")
	private Servicio servicio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	@JoinTable(name="cotizacion_has_item", joinColumns = {
        @JoinColumn(name = "id_cotizacion", referencedColumnName = "id_cotizacion")}, inverseJoinColumns = {
        @JoinColumn(name = "id_item", referencedColumnName = "id_item")})
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Item> items;

	private Date fechaCreacion;

	private Integer cantidadItem;

	private String estado;

	private Double valorItem;

	private Double total;

	public Cotizacion() {

	}

	public Cotizacion(Long idCotizacion, Servicio servicio, Cliente cliente,
			Collection<Item> items, Date fechaCreacion, Integer cantidadItem,
			String estado, Double valorItem, Double total) {
		super();
		this.idCotizacion = idCotizacion;
		this.servicio = servicio;
		this.cliente = cliente;
		this.items = items;
		this.fechaCreacion = fechaCreacion;
		this.cantidadItem = cantidadItem;
		this.estado = estado;
		this.valorItem = valorItem;
		this.total = total;
	}

	public Long getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Long idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getCantidadItem() {
		return cantidadItem;
	}

	public void setCantidadItem(Integer cantidadItem) {
		this.cantidadItem = cantidadItem;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getValorItem() {
		return valorItem;
	}

	public void setValorItem(Double valorItem) {
		this.valorItem = valorItem;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}