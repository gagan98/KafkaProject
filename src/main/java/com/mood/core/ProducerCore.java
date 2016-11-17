package com.mood.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mood.producer.AbstractProducer;
import com.mood.producer.KafkaProducer;

public class ProducerCore {
	
	public static AbstractProducer producer = new KafkaProducer();
	
	public static void main(String[] args) throws InterruptedException {
		BufferedReader br = null;
		String input = null;
        try {

            br = new BufferedReader(new InputStreamReader(System.in));
            
            while (true) {
                input = br.readLine();
                
                producer.addMessage(input);
                
                /*
                 * Press q to quit producing
                 */
                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    System.exit(0);
                }
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}