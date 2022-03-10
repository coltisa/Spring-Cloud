package com.mia.cloud.provider.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorld {

    @RequestMapping("/hello")
    public String Hello(@RequestParam String name){
        return "Hello " + name + " the second microservice for you.";
    }
}