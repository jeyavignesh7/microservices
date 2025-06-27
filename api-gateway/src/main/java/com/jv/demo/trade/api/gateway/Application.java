package com.jv.demo.trade.api.gateway;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@CommonsLog
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
