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
		// Integer lineaActual = exchange.getIn().getHeader("CamelSplitIndex",
		// Integer.class);
		// lineaActual++;
		Integer lineaActual = 0;
		LOG.info("LINEA: initMark." + body + ".endMark");
		String[] split = body.trim().split("\\s+");
		String encabezadoOperacion = split[0];
		String categoria = encabezadoOperacion.substring(0, 3);
		String operacion = encabezadoOperacion.substring(3, 6);
		try {
			numeroLinea = encabezadoOperacion.substring(6, 16);

			switch (operacion) {
			case "MAT":
				String operacionFuente = split[1];
				LOG.info(operacionFuente);
				String signoPrimerOperador = operacionFuente.substring(0, 1);
				String parteEnteraPrimerOperando = operacionFuente.substring(1, 7);
				String parteDecimalPrimerOperando = operacionFuente.substring(7, 13);
				String operador = operacionFuente.substring(13, 14);
				String signoSegundoOperador = operacionFuente.substring(14, 15);
				String parteEnteraSegundoOperador = operacionFuente.substring(15, 21);
				String parteDecimalSegundoOperador = operacionFuente.substring(21, 27);
				LOG.info(signoPrimerOperador + parteEnteraPrimerOperando + "." + parteDecimalPrimerOperando);
				LOG.info(signoSegundoOperador + parteEnteraSegundoOperador + "." + parteDecimalSegundoOperador);
				Double primerOperador = new Double(
						signoPrimerOperador + parteEnteraPrimerOperando + "." + parteDecimalPrimerOperando);
				Double segundoOperador = new Double(
						signoSegundoOperador + parteEnteraSegundoOperador + "." + parteDecimalSegundoOperador);
				Double resultadoOperacion = null;

				if (!categoria.equals("OPE")) {
					resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OP" + "01"
							+ "OPERACION NO VALIDA - CLAVE";
					break;
				}

				if (!numeroLinea.matches("^[0-9]*$")) {
					resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OP" + "03"
							+ "OPERACION NO VALIDA - NUMERO LINEA";
					break;
				}

				if (!(signoPrimerOperador.equals("\\+") || signoPrimerOperador.equals("\\-"))) {
					resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OM" + "01"
							+ "OPERACION NO VALIDA - SIGNO 1 NO VALIDO";
					;
					break;
				}

				if (!parteEnteraPrimerOperando.matches("^[0-9]*$")) {
					resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OM" + "02"
							+ "OPERACION NO VALIDA - ENTERO 1 NO VALIDO";
					;
					break;
				}

				if (!parteDecimalPrimerOperando.matches("^[0-9]*$")) {
					resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OM" + "03"
							+ "OPERACION NO VALIDA - DECIMAL 1 NO VALIDO";
					;
					break;
				}

				if (!(operador.equals("\\+") || operador.equals("\\-") || operador.equals("\\*")
						|| operador.equals("\\/"))) {
					resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OM" + "04"
							+ "OPERACION NO VALIDA - OPERADOR NO VALIDO";
					;
					break;
				}

				if (!(signoSegundoOperador.equals("\\+") || signoPrimerOperador.equals("\\-"))) {
					resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OM" + "05"
							+ "OPERACION NO VALIDA - SIGNO 2 NO VALIDO";
					;
					break;
				}

				if (!parteEnteraSegundoOperador.matches("^[0-9]*$")) {
					resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OM" + "06"
							+ "OPERACION NO VALIDA - ENTERO 2 NO VALIDO";
					;
					break;
				}

				if (!parteDecimalSegundoOperador.matches("^[0-9]*$")) {
					resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OM" + "07"
							+ "OPERACION NO VALIDA - DECIMAL 2 NO VALIDO";
					;
					break;
				}
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
				String parteDecimalResultado = String.format("%-12s", Long.parseLong(doubleResult[1])).replace(' ',
						'0');
				resultado = parteEnteraResultado + "." + parteDecimalResultado + "OK";
				resultado = "RES" + operacion + numeroLinea + "    " + resultado + "            OK";
				break;
			case "RFC":
				String primerNombre = split[1];
				String segundoNombre = split[2];
				String primerApellido = split[3];
				String segundoApellido = split[4];
				String fecha = split[5];
				RFCCalculator rfcCalculator = new RFCCalculator.Builder().name(primerNombre + "" + segundoNombre)
						.firstLastName(primerApellido).secondLastName(segundoApellido)
						.birthday(Integer.valueOf(fecha.substring(6, 8)), Integer.valueOf(fecha.substring(4, 6)),
								Integer.valueOf(fecha.substring(0, 4)))
						.build();
				resultado = rfcCalculator.toString();
				resultado = "RES" + operacion + numeroLinea + "    " + resultado + "            OK";
				break;
			default:
				resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OP" + "02"
						+ "OPERACION NO VALIDA - CLAVE";
				;
				break;

			}
		} catch (Exception e) {
			resultado = "RES" + operacion + String.format("%010d", lineaActual) + "    " + "ERR" + "OM" + "08"
					+ "NO SE PUDO CALCULAR RESPUESTA";
		}
		exchange.getIn().setBody(resultado);
	}

}
