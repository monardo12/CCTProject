package com.cct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="Socio.findAll", query="SELECT s FROM Socio s")
public class Socio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idsocio;

	private String direccion;

	private String email;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to Propuesta
	@OneToMany(mappedBy="socio")
	private List<Propuesta> propuestas;

	public Socio() {
	}

	public Long getIdsocio() {
		return this.idsocio;
	}

	public void setIdsocio(Long idsocio) {
		this.idsocio = idsocio;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Propuesta> getPropuestas() {
		return this.propuestas;
	}

	public void setPropuestas(List<Propuesta> propuestas) {
		this.propuestas = propuestas;
	}

	public Propuesta addPropuesta(Propuesta propuesta) {
		getPropuestas().add(propuesta);
		propuesta.setSocio(this);

		return propuesta;
	}

	public Propuesta removePropuesta(Propuesta propuesta) {
		getPropuestas().remove(propuesta);
		propuesta.setSocio(null);

		return propuesta;
	}

}