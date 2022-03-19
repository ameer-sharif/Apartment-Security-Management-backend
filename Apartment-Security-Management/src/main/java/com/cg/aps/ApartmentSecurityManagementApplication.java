package com.cg.aps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ApartmentSecurityManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentSecurityManagementApplication.class, args);
	}
	
	@Bean public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2).select() 
				.apis(RequestHandlerSelectors.basePackage("com.cg.aps")).build(); 
		}

}
