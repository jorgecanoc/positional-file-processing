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
		for (int i = 1; i < splitText.length; i++) {
			list.add(splitText[i]);
		}
		exchange.getIn().setBody(list);
	}

}
