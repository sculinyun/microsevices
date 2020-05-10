package com.damowang.hystrix.provider.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "microsevices-provider",fallback = HelloClientFallback.class)
public interface HelloClient {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String helloservice(@RequestParam(value = "cloud") String cloud);
}

