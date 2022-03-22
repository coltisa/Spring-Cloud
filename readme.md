



# Spring Cloud



![](https://raw.githubusercontent.com/coltisa/Spring-Cloud/main/spring_cloud.png)



## Spring Cloud Gateway

相关的项目目录说明

### spring-cloud-consul-client

Consul客户端，通过Consul消费其他服务

从Consul中获取其他服务的信息，以下为Consul配置信息，注意实验环境的consul地址都配置成consul_server.az，需要自己改hosts

```
spring.application.name=spring-cloud-consul-client
server.port=9003
spring.cloud.consul.host=consul_server.az
spring.cloud.consul.port=8500
#设置不需要注册到 consul 中
spring.cloud.consul.discovery.register=false
```

利用Feign调用其他service-provider服务

```java
package com.mia.consul.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "service-provider")
public interface ServiceProviderRemote {

    @RequestMapping("/hello") // 远程调用服务，利用Feign调用了service-provider
    public String Hello(@RequestParam String name);
}
```



### spring-cloud-gateway

Spring Cloud Gateway项目，通过Consul联动，对请求到后端的服务进行负载，后端模拟的服务分别为cloud-provider和cloud-provider-second



Spring Cloud Gateway网关路由配置示例

```yml
server:
  port: 9000
spring:
  cloud:
    consul:
      host: consul_server.az
      port: 8500
      discovery:
        register: true
        prefer-ip-address: true
        health-check-path: /actuator/health
    gateway:
      routes:
        - id: default_route # 默认的路由，会转发到bing
          uri: https://www.bing.com
          predicates:
            - Path=/default
        - id: service_provider # 模拟的后端服务，通过Consul的注册服务名查找
          uri: lb://service-provider
          predicates:
            - Path=/service-provider/{segment}
          filters:
            - SetPath=/{segment}
            - name: Hystrix # 熔断器械机制，如果服务提供者有故障则直接转发到相关故障页面
              args:
                name: service-provider-fallback
                fallbackUri: forward:/service-provider-error
            - name: Retry
              args:
                retries: 3
                statuses: BAD_GATEWAY,BAD_REQUEST
      default-filters:
        - name: Hystrix
          args:
            name: fallbackcmd
            fallbackUri: forward:/default-error
  application:
    name: gateway
```

默认负载均衡的策略是轮询，可以自定义其他规则，参考链接：https://blog.csdn.net/lovelichao12/article/details/107062967



### spring-cloud-provider

Spring Cloud 模拟的后端服务

```java
package com.mia.cloud.provider.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping("/hello")
    public String Hello(@RequestParam String name){
        return "Hello " + name + "  a initial microservice for you.";
    }
}
```



### spring-cloud-provider-second

Spring Cloud 模拟的另一个 另一个服务

```java
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
```





## Consul

Consul开发模式启动

```
./consul agent -dev
```

Consul Windows版下载链接：https://releases.hashicorp.com/consul/1.11.4/consul_1.11.4_windows_amd64.zip



## 参考链接

Spring Cloud Gateway示例参考

https://github.com/sunweisheng/spring-cloud-example



IDEA部署Spring Cloud项目参考

https://blog.miacraft.cn/index.php/archives/128/