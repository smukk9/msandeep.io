package io.sandeep.blog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket blogApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.sandeep.blog.resource"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        return new ApiInfo(
                "msandeep.io API documentation",
                "msandeep.io API Documentation",
                "1.0",
                "Terms and service",
                new Contact("M.Sandeep", "https://www.chcek.com/me", "contact@msandeep.io"),
                "Apache License",
                "https://msandeep.io/license", Collections.emptyList());
    }
}
