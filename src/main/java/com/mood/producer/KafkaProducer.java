package com.mood.producer;

import com.mood.producer.AbstractProducer;
import com.mood.util.ConfigUtil;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer implements AbstractProducer {

	private static String	topic	= null;
	private static Producer<Integer, String> producer;
	static 
	{
		initialize();
	}
	public static void initialize() {
			ProducerConfig producerConfig = new ProducerConfig(ConfigUtil.getKafkaProperties());
			producer = new Producer<Integer, String>(producerConfig);
			topic = ConfigUtil.getKafkaProperty("topic-name");

	}
	@Override
	public void addMessage(String message) {
		KeyedMessage<Integer, String> keyedMsg =
                new KeyedMessage<Integer, String>(topic, message);
   producer.send(keyedMsg); // This publishes message on given topic
   	
 return;
	}

}
 	
