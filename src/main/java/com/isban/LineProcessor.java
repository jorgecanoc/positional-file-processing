package com.isban;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isban.rfc.RFCCalculator;
import com.isban.sql.model.Cliente;

public class LineProcessor implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(LineProcessor.class);
	
	private String resultado;
	private String numeroLinea;

	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		
		LOG.info("LINEA: initMark." + body + ".endMark");
		String[] split = body.trim().split("\\s+");
		String encabezadoOperacion = split[0];
		String categoria = encabezadoOperacion.substring(0, 3);
		String operacion = encabezadoOperacion.substring(3, 6);
		numeroLinea = encabezadoOperacion.substring(6,16);
		
		switch (operacion) {
		case "MAT":
			String operacionFuente = split[1];
			LOG.info(operacionFuente);
			String signoPrimerOperador = operacionFuente.substring(0, 1);
			String parteEnteraPrimerOperando = operacionFuente.substring(1,7);
			String parteDecimalPrimerOperando = operacionFuente.substring(7,13);
			String operador = operacionFuente.substring(13,14);
			String signoSegundoOperador = operacionFuente.substring(14,15);
			String parteEnteraSegundoOperador = operacionFuente.substring(15,21);
			String parteDecimalSegundoOperador = operacionFuente.substring(21,27);
			LOG.info(signoPrimerOperador+parteEnteraPrimerOperando+"."+parteDecimalPrimerOperando);
			LOG.info(signoSegundoOperador+parteEnteraSegundoOperador+"."+parteDecimalSegundoOperador);
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
			case "*":
				resultadoOperacion = primerOperador * segundoOperador;
				break;
			case "/":
				resultadoOperacion = primerOperador / segundoOperador;
				break;
			}
			LOG.info(resultadoOperacion.toString());
			String[] doubleResult = resultadoOperacion.toString().split("\\.");
			String parteEnteraResultado = String.format("%012d", Long.parseLong(doubleResult[0]));
			String parteDecimalResultado = String.format("%-12s", Long.parseLong(doubleResult[1])).replace(' ', '0');
			resultado = parteEnteraResultado + "." + parteDecimalResultado + "OK";
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
		resultado = "RES" + operacion + numeroLinea + "    " + resultado + "            OK";
		exchange.getIn().setBody(resultado);
	}

}
