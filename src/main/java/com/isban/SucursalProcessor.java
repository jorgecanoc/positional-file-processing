package com.isban;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.isban.sql.model.Sucursal;

public class SucursalProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		String[] sucursalCsv = body.split(";");
		Sucursal sucursal = new Sucursal();
		sucursal.setRegion(sucursalCsv[0]);
		sucursal.setZona(sucursalCsv[1]);
		sucursal.setTipo(sucursalCsv[2]);
		sucursal.setSubtipo(sucursalCsv[3]);
		sucursal.setNumeroDeSucursal(Integer.valueOf(sucursalCsv[4]));
		sucursal.setNombreSucursal(sucursalCsv[5]);
		sucursal.setNombreDirectorSucursal(sucursalCsv[6]);
		sucursal.setCorreoDirector(sucursalCsv[7]);
		sucursal.setCalle(sucursalCsv[8]);
		sucursal.setNumeroCalle(sucursalCsv[9]);
		sucursal.setColonia(sucursalCsv[10]);
		sucursal.setCodigoPostal(sucursalCsv[11]);
		sucursal.setPoblacionODelegacion(sucursalCsv[12]);
		sucursal.setEstado(sucursalCsv[13]);
		sucursal.setLada(sucursalCsv[14]);
		sucursal.setTelefono(sucursalCsv[15]);
		sucursal.setHorarioLunVie(sucursalCsv[16]);
		sucursal.setHorarioSab(sucursalCsv[17]);
		exchange.getIn().setBody(sucursal);
	}

}
