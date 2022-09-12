package com.booking.platform.mybookingshow.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	

	@Bean
	   public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("bookmyshow-apis")
				.apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any()).build();
	}

	
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Booking Platform")
				.description("BookmyShow API reference for developers")
				.license("BookmyShow License")
				.licenseUrl("johndiraviyaml7@gmail.com").version("1.0").build();
	}
}