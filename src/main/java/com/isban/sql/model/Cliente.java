package com.isban.sql.model;

public class Cliente {

	private Integer numeroDeCuenta;
	private Integer sucursal;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String productoTarjetaCredito;
	private String productoHipoteca;
	private String email;
	private String telefono;

	public Integer getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(Integer numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getProductoTarjetaCredito() {
		return productoTarjetaCredito;
	}

	public void setProductoTarjetaCredito(String productoTarjetaCredito) {
		this.productoTarjetaCredito = productoTarjetaCredito;
	}

	public String getProductoHipoteca() {
		return productoHipoteca;
	}

	public void setProductoHipoteca(String productoHipoteca) {
		this.productoHipoteca = productoHipoteca;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente [" + numeroDeCuenta + ";" + sucursal + ";" + nombres
				+ ";" + apellidoPaterno + ";" + apellidoMaterno
				+ ";" + productoTarjetaCredito + ";" + productoHipoteca
				+ ";" + email + ";" + telefono + "]";
	}
	
}
