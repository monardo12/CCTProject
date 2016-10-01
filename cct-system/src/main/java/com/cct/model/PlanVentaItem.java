package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="plan_venta_item")
@NamedQuery(name="PlanVentaItem.findAll", query="SELECT p FROM PlanVentaItem p")
public class PlanVentaItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idplanventaitem;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="iditem")
	private Item item;

	//bi-directional many-to-one association to PlanVenta
	@ManyToOne
	@JoinColumn(name="idplanventa")
	private PlanVenta planVenta;

	public PlanVentaItem() {
	}

	public Integer getIdplanventaitem() {
		return this.idplanventaitem;
	}

	public void setIdplanventaitem(Integer idplanventaitem) {
		this.idplanventaitem = idplanventaitem;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public PlanVenta getPlanVenta() {
		return this.planVenta;
	}

	public void setPlanVenta(PlanVenta planVenta) {
		this.planVenta = planVenta;
	}

}