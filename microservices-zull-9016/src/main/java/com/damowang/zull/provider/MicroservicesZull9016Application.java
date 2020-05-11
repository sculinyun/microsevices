package com.damowang.zull.provider;

import filter.RequestFilter;
import filter.ResponseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicesZull9016Application {

	@Bean
	public RequestFilter requestFilter() {
		return new RequestFilter();
	}

	@Bean
	public ResponseFilter responseFilter() {
		return new ResponseFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesZull9016Application.class, args);
	}

}
