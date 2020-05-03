package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.example.demo.model.Product;

@SpringBootApplication
@EnableJms
public class SpringBootJmsDemoApplication {
	
	private static final String MESSAGE_QUEUE = "message_queue";

	public static void main(String[] args) {
		
	//	SpringApplication.run(SpringBootJmsDemoApplication.class, args);
		
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootJmsDemoApplication.class, args);
		
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		for(int i=1;i<=20;i++) {
			Product product = new Product();
			product.setProductId(i*i);
			product.setName("Laptop" +i);
			product.setQuantity(10+i);
			
			System.out.println("Sending a product" +i);
			jmsTemplate.convertAndSend(MESSAGE_QUEUE,product);
		}
	}

}
