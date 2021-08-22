package com.ashifs.kafkarandomnumberproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
//I have added the @EnableScheduling as we would keep on producing messages every second.
@SpringBootApplication
public class KafkaRandomNumberProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaRandomNumberProducerApplication.class, args);
	}

}
