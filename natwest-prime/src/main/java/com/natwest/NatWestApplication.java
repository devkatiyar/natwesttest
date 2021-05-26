package com.natwest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main Class to Start NatWest Prime Service Application.
 *
 */
@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = { "com.natwest.*" })
public class NatWestApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(NatWestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NatWestApplication.class, args);
		LOGGER.info("NatWestApplication Started Successfully!!");
	}
}