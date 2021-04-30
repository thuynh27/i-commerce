package com.nab.tracking;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableRabbit
@SpringBootApplication
@EnableDiscoveryClient
public class ProductTrackingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductTrackingServiceApplication.class, args);
	}
	
    @Bean
    public Executor taskExecutor() {
        // We need execute many short-lived asynchronous tasks (send customer activities to CloudAMQP)
        // so Cached Thread Pool already meet our needs
        // for more advanced, we could use ThreadPoolExecutor
        return Executors.newCachedThreadPool();
    }

}
