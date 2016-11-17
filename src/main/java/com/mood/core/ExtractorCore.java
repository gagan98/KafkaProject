package com.mood.core;

import com.mood.consumer.AbstractConsumer;
import com.mood.consumer.KafkaConsumer;
public class ExtractorCore {

	
	/*
	 * Main method added for testing purpose. 
	 */
	public static void main(String[] args) throws InterruptedException {
		AbstractConsumer consumer = new KafkaConsumer();
		consumer.consume();
	}


}
