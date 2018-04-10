package com.isban;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class AppendFoot implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String resultado = exchange.getIn().getBody(String.class);
		String foot = exchange.getIn().getHeader("Ultima_Linea", String.class);
		resultado = resultado + "\n" + foot;
		exchange.getIn().setBody(resultado);
	}

}
