package com.mood.consumer;

import java.util.*;

import com.mood.util.ConfigUtil;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class KafkaConsumer implements AbstractConsumer {
	private  static ConsumerConnector consumerConnector = null;
	private  static String topic = null;

	static 
	{
		initialize();
	}
	public static void initialize() {
		ConsumerConfig consumerConfig = new ConsumerConfig(ConfigUtil.getKafkaProperties());
		consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
		topic = ConfigUtil.getKafkaProperty("topic-name");
	}

	public void consume() {
          Map<String, Integer> topicCount = new HashMap<String, Integer>();       
          topicCount.put(topic, new Integer(1));
         
          Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams =
                consumerConnector.createMessageStreams(topicCount);         
         
          List<KafkaStream<byte[], byte[]>> kStreamList =
                                               consumerStreams.get(topic);
          
          for (final KafkaStream<byte[], byte[]> kStreams : kStreamList) {
                 ConsumerIterator<byte[], byte[]> consumerIte = kStreams.iterator();
                
                 
				while (consumerIte.hasNext())
				
                        System.out.println("Message consumed from topic [" + topic + "] : "       +
                                        new String(consumerIte.next().message()));   
          }
          if (consumerConnector != null)   consumerConnector.shutdown();          
    }
	
}
