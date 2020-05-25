package com.damowang.storage.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.damowang.storage.provider.dao")
@EnableFeignClients
public class MicroservicesProviderStorage9019Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesProviderStorage9019Application.class, args);
	}

}
