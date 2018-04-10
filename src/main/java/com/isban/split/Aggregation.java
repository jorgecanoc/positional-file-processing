package com.isban.split;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Aggregation implements AggregationStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(Aggregation.class);

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			String encabezado = newExchange.getIn().getHeader("Encabezado", String.class);
			String resultado = newExchange.getIn().getBody(String.class);
			resultado = encabezado + "\n" + resultado;
			newExchange.getIn().setBody(resultado);
			return newExchange;
		}

		String resultado = oldExchange.getIn().getBody(String.class);
		String nuevoResultado = newExchange.getIn().getBody(String.class);
		resultado = resultado + "\n" + nuevoResultado;
		newExchange.getIn().setBody(resultado);
		return newExchange;
	}

}
