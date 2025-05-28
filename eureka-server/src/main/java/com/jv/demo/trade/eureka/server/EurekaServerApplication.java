package com.jv.demo.trade.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		/**
		 * This custom config createing problem as it requires both custom*.yml and application.yml files
		 * with details of Eureka registration server (each during run and execution). Hence revered back with application.yml alone.
		 * Required further investigation if need to use customs-application.yml
		 **/
		// Tell server to look for custom-application.properties or custom-application.yml
		//System.setProperty("spring.config.name", "eureka-server-application");

		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
