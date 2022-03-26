package com.mia.consul.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //激活Feign客户端
public class SpringCloudConsulClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConsulClientApplication.class, args);
		System.out.print("Now we are started.");
	}

}





