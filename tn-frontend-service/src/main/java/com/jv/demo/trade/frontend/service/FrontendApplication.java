package com.jv.demo.trade.frontend.service;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
@CommonsLog
public class FrontendApplication {

	public static void main(String[] args) {
		/**
		 * This custom config createing problem as it requires both custom*.yml and application.yml files
		 * with details of Eureka registration server. Hence revered back with application.yml alone.
		 * Required further investigation if need to use customs-application.yml
		 **/
		// Tell server to look for custom-application.properties or custom-application.yml
		//System.setProperty("spring.config.name", "frontend-application");

		SpringApplication.run(FrontendApplication.class, args);
	}

}
