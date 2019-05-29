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

	public static final String STRIP = "-----------------------------------------------------------------------";
	/**
	 * This is a Javadoc comment
	 * @param args to args class
	 */
	public static void main(String[] args) {

		SpringApplication.run(DaInvestmentApplication.class, args);
		log.info(STRIP);
		log.info(".................... REST API Investment : READY .......................");
		log.info(STRIP);
		log.info("....... API Docs: http://localhost:8081/investment/swagger-ui.html .....");
		log.info(STRIP);
		log.info("............. H2 DB: http://localhost:8081/investment/h2/ ..............");
		log.info(STRIP);
	}

}
