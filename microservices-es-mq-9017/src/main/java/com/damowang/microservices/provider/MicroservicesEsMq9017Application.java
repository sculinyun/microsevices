package com.damowang.microservices.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicesEsMq9017Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesEsMq9017Application.class, args);
	}

}
