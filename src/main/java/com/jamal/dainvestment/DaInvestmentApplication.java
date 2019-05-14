package com.jamal.dainvestment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This is a Javadoc comment
 */

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class DaInvestmentApplication {

	/**
	 * This is a Javadoc comment
	 */
	public static void main(String[] args) {
		SpringApplication.run(DaInvestmentApplication.class, args);
		log.info("REST API Investment : READY ...");
	}

}
