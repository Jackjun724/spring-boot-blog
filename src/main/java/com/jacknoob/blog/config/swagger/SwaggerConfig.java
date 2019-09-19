package com.jacknoob.blog.config.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

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
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jacknoob.blog.web"))
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(securityContexts());
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.any())
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Lists.newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }
}
