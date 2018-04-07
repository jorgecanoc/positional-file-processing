package com.isban;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SplitFile implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String text = exchange.getIn().getBody(String.class);
		List<String> list = new ArrayList<String>();
		String[] splitText = text.split("\\n");
		for (int i = 0; i < splitText.length; i++) {
			list.add(splitText[i]);
		}
		
		String headerText = splitText[0];
		String lastLineText = splitText[splitText.length - 1];
		String fileName = exchange.getIn().getHeader("FileName", String.class);
		
		list.subList(1, splitText.length - 2);
		
		exchange.getIn().setBody(list);
	}

}
