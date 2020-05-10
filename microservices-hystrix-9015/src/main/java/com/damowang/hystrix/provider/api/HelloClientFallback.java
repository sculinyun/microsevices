package com.damowang.hystrix.provider.api;

import org.springframework.stereotype.Component;

/**
 * 描述:
 * helloclient fallback支持
 *
 * @author sculi
 * @create 2020-05-10 23:40
 */
@Component
public class HelloClientFallback implements HelloClient{
    @Override
    public String helloservice(String cloud) {
        return "系统超出限制";
    }
}
