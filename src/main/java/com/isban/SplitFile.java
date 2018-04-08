package com.isban;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplitFile implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(SplitFile.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		String text = exchange.getIn().getBody(String.class);
		List<String> list = new ArrayList<String>();
		String[] splitText = text.split("\\r?\\n");
		for (int i = 1; i < splitText.length - 1; i++) {
			list.add(splitText[i]);
		}
		
		String headerText = splitText[0];
		String lastLineText = splitText[splitText.length - 1];
		String fileName = exchange.getIn().getHeader("FileName", String.class);
		
		LOG.info("Encabezado: " +headerText);
		LOG.info("Ultima Linea: " +lastLineText);
		LOG.info("Nombre del archivo: " +fileName);
		
		exchange.getIn().setBody(list);
	}

}
