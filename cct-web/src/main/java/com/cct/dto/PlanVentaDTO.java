package com.cct.dto;

public class PlanVentaDTO {

	private Integer idPlanVenta;
	
	private String descricion;
	
	private String estado;
	
	private String nombre;
	
	private ClienteDTO clienteDTO;

	public Integer getIdPlanVenta() {
		return idPlanVenta;
	}

	public void setIdPlanVenta(Integer idPlanVenta) {
		this.idPlanVenta = idPlanVenta;
	}

	public String getDescricion() {
		return descricion;
	}

	public void setDescricion(String descricion) {
		this.descricion = descricion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}
	
}
