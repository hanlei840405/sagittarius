package com.galaxy.framework.sagittarius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
@EnableHystrix
public class SagittariusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SagittariusApplication.class, args);
	}
}
