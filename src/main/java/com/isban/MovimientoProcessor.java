package com.isban;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.isban.sql.model.Movimiento;

public class MovimientoProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		String[] movimientoCsv = body.split(";");
		Movimiento movimiento = new Movimiento();
		movimiento.setNumeroDeMovimiento(Integer.valueOf(movimientoCsv[0]));
		movimiento.setReferencia(movimientoCsv[1]);
		movimiento.setDescripcion(movimientoCsv[2]);
		movimiento.setCuenta(Integer.valueOf(movimientoCsv[3]));
		movimiento.setFecha(movimientoCsv[4]);
		movimiento.setHora(movimientoCsv[5]);
		movimiento.setMonto(Integer.valueOf(movimientoCsv[6]));
		exchange.getIn().setBody(movimiento);
	}

}
