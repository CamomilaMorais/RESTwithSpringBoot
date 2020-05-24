package com.camomila.config;

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
 * For another version JAVA (14):
 * import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
 */

@Configuration
/**
 * For another version JAVA (14):
 * @EnableSwagger2WebMvc
 */
@EnableSwagger2
public class SwaggerConfig {

    /**
     *
     * @Bean
     * public Docket api() {
     * return new Docket(DocumentationType.SWAGGER_2)
     * .select()
     * .apis(RequestHandlerSelectors.any())
     * .paths(PathSelectors.any())
     * .build();
     * }
    */

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.camomila"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "RESTful API with Spring Boot 2.1.3",
                "Some description about the API.",
                "v1","Terms of Service URL",
                new Contact("Camila Morais", "Some personal URL", "moraiscamomila@gmail.com"),
                "License of API","License of URL",
                Collections.emptyList());
    }
}
