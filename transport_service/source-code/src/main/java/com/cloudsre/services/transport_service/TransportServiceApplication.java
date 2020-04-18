package com.cloudsre.services.transport_service;

import org.springframework.boot.SpringApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TransportServiceApplication extends SpringBootServletInitializer {
	
	
	private static final Class<TransportServiceApplication> applicationClass = TransportServiceApplication.class;
	private static final Logger logger = LoggerFactory.getLogger(applicationClass);

	public static void main(String[] args) {
		logger.info("\n----Begin logging TransportServiceApplication----");
		
		SpringApplication.run(applicationClass, args);
		
		logger.info("----End logging TransportServiceApplication----");
		
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(applicationClass);
	    }


}
