package com.damowang.nacos.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicesProvider9012Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesProvider9012Application.class, args);
	}

}
