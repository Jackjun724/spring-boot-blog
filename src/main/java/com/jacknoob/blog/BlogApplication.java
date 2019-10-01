package com.jacknoob.blog;

import com.jacknoob.blog.common.Constants;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Blog
 * @author JackJun
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.jacknoob.blog.mapper")
public class BlogApplication extends SpringBootServletInitializer {

    private static Logger logger = LoggerFactory.getLogger(BlogApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        if (System.getProperty(Constants.SPRING_PROFILE_KEY) == null) {
            System.setProperty(Constants.SPRING_PROFILE_KEY, Constants.SPRING_PROFILE_DEVELOPMENT);
        }
        SpringApplication app = new SpringApplication(BlogApplication.class);
        Environment env = app.run(args).getEnvironment();
        logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        if (System.getProperty(Constants.SPRING_PROFILE_KEY) == null) {
            System.setProperty(Constants.SPRING_PROFILE_KEY, "prod");
        }
        builder.sources(this.getClass());
        return super.configure(builder);
    }
}
