package com.mia.consul.client.controller;

import com.mia.consul.client.service.ServiceProviderRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebInterface {
    @Autowired
    ServiceProviderRemote remote;

    @RequestMapping("/consume")
    public String Consume(){
//        远程消费Hello函数
        String first = remote.Hello("first round and we got");
//        第二次远程消费Hello函数
        String second = remote.Hello("second round and we got");
        return first + " | " + second;
    }

    @RequestMapping("/")
    public String Default(){
        return "Hello this is a default message in case there is no message for you";
    }

    @Value("${SingleValue}")
    private String SingleValue;

    @RequestMapping(value = "/re")
    public String ReadVal() {
        return "Define Value is :" + SingleValue;
    }

}