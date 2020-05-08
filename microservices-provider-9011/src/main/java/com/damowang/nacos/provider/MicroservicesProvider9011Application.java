package com.damowang.nacos.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicesProvider9011Application {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesProvider9011Application.class, args);
    }

}
