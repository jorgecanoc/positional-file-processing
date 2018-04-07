package com.isban.sql.model;

public class Sucursal {
	
	private Integer numeroDeSucursal;
	private String region;
	private String zona;
	private String tipo;
	private String subtipo;
	private String nombreSucursal;
	private String nombreDirectorSucursal;
	private String correoDirector;
	private String calle;
	private String numeroCalle;
	private String colonia;
	private String codigoPostal;
	private String poblacionODelegacion;
	private String estado;
	private String lada;
	private String telefono;
	private String horarioLunVie;
	private String horarioSab;

	public Integer getNumeroDeSucursal() {
		return numeroDeSucursal;
	}

	public void setNumeroDeSucursal(Integer numeroDeSucursal) {
		this.numeroDeSucursal = numeroDeSucursal;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getNombreDirectorSucursal() {
		return nombreDirectorSucursal;
	}

	public void setNombreDirectorSucursal(String nombreDirectorSucursal) {
		this.nombreDirectorSucursal = nombreDirectorSucursal;
	}

	public String getCorreoDirector() {
		return correoDirector;
	}

	public void setCorreoDirector(String correoDirector) {
		this.correoDirector = correoDirector;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumeroCalle() {
		return numeroCalle;
	}

	public void setNumeroCalle(String numeroCalle) {
		this.numeroCalle = numeroCalle;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPoblacionODelegacion() {
		return poblacionODelegacion;
	}

	public void setPoblacionODelegacion(String poblacionODelegacion) {
		this.poblacionODelegacion = poblacionODelegacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLada() {
		return lada;
	}

	public void setLada(String lada) {
		this.lada = lada;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getHorarioLunVie() {
		return horarioLunVie;
	}

	public void setHorarioLunVie(String horarioLunVie) {
		this.horarioLunVie = horarioLunVie;
	}

	public String getHorarioSab() {
		return horarioSab;
	}

	public void setHorarioSab(String horarioSab) {
		this.horarioSab = horarioSab;
	}

	@Override
	public String toString() {
		return "Sucursal [" + numeroDeSucursal + ";" + region + ";" + zona + ";"
				+ tipo + ";" + subtipo + ";" + nombreSucursal + ";"
				+ nombreDirectorSucursal + ";" + correoDirector + ";" + calle + ";"
				+ numeroCalle + ";" + colonia + ";" + codigoPostal + ";"
				+ poblacionODelegacion + ";" + estado + ";" + lada + ";" + telefono
				+ ";" + horarioLunVie + ";" + horarioSab + "]";
	}
}
