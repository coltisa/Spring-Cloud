package com.mia.cloud.gateway.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class ErrorHandle {

    @RequestMapping("/default-error")
    public String DefaultErrorHandle(){
        return "This is a default error message";
    }

    @RequestMapping("/service-provider-error")
    public String ServiceProviderErrorHandle(){
        return "This is a error message special for service provider";
    }
}