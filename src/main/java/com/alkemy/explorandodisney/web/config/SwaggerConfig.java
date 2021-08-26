package com.alkemy.explorandodisney.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableSwagger2

public class SwaggerConfig {
        @Bean
        public Docket api(){
            return new Docket(DocumentationType.SWAGGER_2)
                    .securitySchemes(Arrays.asList(apiKey()))
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.alkemy.explorandodisney.web.controller"))

                    .build()
                    .apiInfo(apiInfo());
        }

    private ApiKey apiKey(){
        return new ApiKey("JWT", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Exploring Disney World",
                "Api Rest - Chanllage Alkemy.",
                "1.0",
                "",
                new Contact("Florencia Zabala", "https://www.linkedin.com/in/florencia-agustina-zabala/", "zabalafaz@gmail.com"),
                "", "", Collections.emptyList());
    }
}
