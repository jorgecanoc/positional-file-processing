package com.isban.split;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Aggregation implements AggregationStrategy {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Aggregation.class);

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		return null;
	}

}
