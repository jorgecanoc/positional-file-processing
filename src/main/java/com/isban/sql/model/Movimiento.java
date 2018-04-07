package com.isban.sql.model;

public class Movimiento {

	private Integer numeroDeMovimiento;
	private String referencia;
	private String descripcion;
	private Integer cuenta;
	private String fecha;
	private String hora;
	private Integer monto;

	public Integer getNumeroDeMovimiento() {
		return numeroDeMovimiento;
	}

	public void setNumeroDeMovimiento(Integer numeroDeMovimiento) {
		this.numeroDeMovimiento = numeroDeMovimiento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getMonto() {
		return monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "Movimiento [" + numeroDeMovimiento + ";" + referencia + ";"
				+ descripcion + ";" + cuenta + ";" + fecha + ";" + hora + ";" + monto + "]";
	}
}
