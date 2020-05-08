package com.damowang.nacos.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * springcloud技术栈
 *
 * @author sculi
 * @create 2020-05-08 15:32
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloservice(@RequestParam(value = "cloud") String cloud) {
        return "hello" + cloud;
    }


}
