package com.jv.demo.trade.backend.service;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@CommonsLog
public class HsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsApplication.class, args);
	}

}
