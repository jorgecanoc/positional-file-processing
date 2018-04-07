package com.isban;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.isban.rfc.RFCCalculator;
import com.isban.sql.model.Cliente;

public class LineProcessor implements Processor {
	
	private String resultado;
	private String signoPrimerOperador;
	private String parteEnteraPrimerOperando;
	private String parteDecimalPrimerOperando;
	private String operador;
	private String signoSegundoOperador;
	private String parteEnteraSegundoOperador;
	private String parteDecimalSegundoOperador;
	private String numeroLinea;

	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		String[] split = body.split("\\s+");
		String encabezadoOperacion = split[0];
		String categoria = encabezadoOperacion.substring(0, 3);
		String operacion = encabezadoOperacion.substring(3, 6);
		
		switch (operacion) {
		case "MAT":
			String operacionFuente = split[1];
			signoPrimerOperador = operacionFuente.substring(0, 1);
			parteEnteraPrimerOperando = operacionFuente.substring(1,7);
			parteDecimalPrimerOperando = operacionFuente.substring(7,13);
			operador = operacionFuente.substring(13,14);
			parteEnteraPrimerOperando = operacionFuente.substring(14,20);
			parteDecimalSegundoOperador = operacionFuente.substring(20,26);
			Double primerOperador = new Double(signoPrimerOperador+parteEnteraPrimerOperando+"."+parteDecimalPrimerOperando);
			Double segundoOperador = new Double(signoSegundoOperador+parteEnteraSegundoOperador+"."+parteDecimalSegundoOperador);
			Double resultadoOperacion = null;
			switch (operador) {
			case "+":
				resultadoOperacion = primerOperador + segundoOperador;
				break;
			case "-":
				resultadoOperacion = primerOperador - segundoOperador;
				break;
			}
			String[] doubleResult = resultadoOperacion.toString().split(".");
			resultado = doubleResult[0] + doubleResult[1];
			break;
		case "RFC":
			String primerNombre = split[1];
			String segundoNombre = split[2];
			String primerApellido = split[3];
			String segundoApellido = split[4];
			String fecha = split[5];
			RFCCalculator rfcCalculator = new RFCCalculator.Builder()
            .name(primerNombre + "" + segundoNombre)
            .firstLastName(primerApellido)
            .secondLastName(segundoApellido)
            .birthday(Integer.valueOf(fecha.substring(6, 8)),Integer.valueOf(fecha.substring(4, 6)),Integer.valueOf(fecha.substring(0, 4)))
            .build();
			resultado = rfcCalculator.toString();
			break;
		}
		resultado = "RES" + operacion + numeroLinea + "    " + resultado + "OK";
		exchange.getIn().setBody(resultado);
	}

}
