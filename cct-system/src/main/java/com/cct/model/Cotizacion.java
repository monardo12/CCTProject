package com.cct.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cotizacion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id	
	@Getter @Setter
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idCotizacion;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idServicio")
	private Servicio servicio;

	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	@Getter @Setter
    @JoinTable(name="item", joinColumns = {
            @JoinColumn(name = "idcotizacion", referencedColumnName = "idcotizacion")}, inverseJoinColumns = {
            @JoinColumn(name = "iditem", referencedColumnName = "iditem")})
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Item> items;
	
	@Getter @Setter
	private Date fechaCreacion;

	@Getter @Setter
	private Integer cantidadItem;
		
	@Getter @Setter
	private String estado;

	@Getter @Setter
	private Double valorItem;
	
	@Getter @Setter
	private Double total;
	
}