package com.jacknoob.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Blog
 * @author JackJun
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.jacknoob.blog.mapper")
public class BlogApplication {

    private static Logger logger = LoggerFactory.getLogger(BlogApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
