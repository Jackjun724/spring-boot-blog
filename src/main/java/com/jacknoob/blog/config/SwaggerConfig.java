package com.jacknoob.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author JackJun
 * 2019/6/27 15:00
 * Life is not just about survival.
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Jack's Blog API")
                .description("Jack's Blog Swagger Api Document")
                .contact(new Contact("Jackjun", "http://www.jacknoob.com", "jackjun0724@163.com"))
                .version("1.0.1").build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jacknoob.blog.web"))
                .paths(PathSelectors.regex("/api/.*"))
                .build();
    }
}
