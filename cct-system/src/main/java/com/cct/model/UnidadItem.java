package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="unidad_item")
@NamedQuery(name="UnidadItem.findAll", query="SELECT u FROM UnidadItem u")
public class UnidadItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idunidaditem;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fechacompra;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="iditem")
	private Item item;

	public UnidadItem() {
	}

	public Integer getIdunidaditem() {
		return this.idunidaditem;
	}

	public void setIdunidaditem(Integer idunidaditem) {
		this.idunidaditem = idunidaditem;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechacompra() {
		return this.fechacompra;
	}

	public void setFechacompra(Date fechacompra) {
		this.fechacompra = fechacompra;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}