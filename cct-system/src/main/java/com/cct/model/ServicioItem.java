package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="servicio_item")
@NamedQuery(name="ServicioItem.findAll", query="SELECT s FROM ServicioItem s")
public class ServicioItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idservicioitem;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="iditem")
	private Item item;

	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="idservicio")
	private Servicio servicio;

	public ServicioItem() {
	}

	public Long getIdservicioitem() {
		return this.idservicioitem;
	}

	public void setIdservicioitem(Long idservicioitem) {
		this.idservicioitem = idservicioitem;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

}