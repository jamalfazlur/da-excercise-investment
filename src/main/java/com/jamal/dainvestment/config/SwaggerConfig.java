package com.jamal.dainvestment.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Configuration for api-docs
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * This is a Javadoc comment
     * @param
     * @return swagger
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error/*")))
                .paths(Predicates.not(PathSelectors.regex("/actuator")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "Jamal Fazlur Rahman",
                "https://github.com/jamalfazlur",
                "jamal@doku.com");
        return new ApiInfo(
                "REST API Application for Investment Industry",
                "Swagger for API: Investment Industry",
                "Version 1.0.0",
                "",
                contact,
                "Free",
                "Free",
                Collections.emptyList());
    }
}
