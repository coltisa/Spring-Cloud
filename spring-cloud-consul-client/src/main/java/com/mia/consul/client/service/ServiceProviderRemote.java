package com.mia.consul.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "service-provider")
public interface ServiceProviderRemote {

    @RequestMapping("/hello") // 远程调用服务，利用Feign调用了service-provider
    public String Hello(@RequestParam String name);
}