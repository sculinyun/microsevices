package com.damowang.hystrix.provider.controller;

import com.damowang.hystrix.provider.api.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * feign调用服务
 *
 * @author sculi
 * @create 2020-05-08 16:23
 */
@RestController
public class FeignController {
    @Autowired
    HelloClient helloClient;

    @RequestMapping(value = "/feigntest", method = RequestMethod.GET)
    public String updateApp(@RequestParam(value = "cloud") String cloud) {
        return "feign" + helloClient.helloservice(cloud);
    }

}
