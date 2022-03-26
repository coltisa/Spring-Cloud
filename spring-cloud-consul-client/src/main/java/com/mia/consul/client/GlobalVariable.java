package com.mia.consul.client;

import org.springframework.beans.factory.annotation.Value;

public class GlobalVariable {
    @Value("${spring.datasource.druid.username}")
    private String username;
    @Value("${spring.datasource.druid.password}")
    private String password;
}
