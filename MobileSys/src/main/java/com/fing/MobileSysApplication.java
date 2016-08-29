package com.fing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MobileSysApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MobileSysApplication.class, args);
		EventDrivenConsumer consumer = new EventDrivenConsumer();
		consumer.run();
	}
}
