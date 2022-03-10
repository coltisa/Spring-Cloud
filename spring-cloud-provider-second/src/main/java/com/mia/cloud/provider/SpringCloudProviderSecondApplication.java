package com.mia.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//支持服务发现
@EnableDiscoveryClient
public class SpringCloudProviderSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudProviderSecondApplication.class, args);
	}

}
