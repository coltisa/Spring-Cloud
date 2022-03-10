



# Spring Cloud



## Spring Cloud Gateway

相关的目录

spring-cloud-consul-client

Consul客户端，通过Consul消费其他服务

spring-cloud-gateway

Spring Cloud Gateway项目

spring-cloud-provider

Spring Cloud 服务

spring-cloud-provider-second

Spring Cloud 另一个服务





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
        - id: service_provider
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



## Consul

Consul开发模式启动

```
./consul agent -dev
```

Consul Windows版下载链接：https://releases.hashicorp.com/consul/1.11.4/consul_1.11.4_windows_amd64.zip



## 参考链接

IDEA部署Spring Cloud项目参考

https://blog.miacraft.cn/index.php/archives/128/

Spring Cloud Gateway示例参考

https://github.com/sunweisheng/spring-cloud-example