package com.damowang.hystrix.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class MicroservicesHystrix9015Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesHystrix9015Application.class, args);
	}

}
