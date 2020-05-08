package com.damowang.feign.cousumer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("microsevices-provider")
public interface HelloClient {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String helloservice(@RequestParam(value = "cloud") String cloud);
}

