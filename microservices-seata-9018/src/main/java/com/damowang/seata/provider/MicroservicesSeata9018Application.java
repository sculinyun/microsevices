package com.damowang.seata.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.damowang.seata.provider.dao")
public class MicroservicesSeata9018Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesSeata9018Application.class, args);
	}

}
