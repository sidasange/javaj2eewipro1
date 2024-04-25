package com.eurekhaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekhaserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekhaserviceApplication.class, args);
	}

}
