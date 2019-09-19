package com.jacknoob.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Arrays;

/**
 * @author JackJun
 * 18/09/2019 11:40
 * Life is not just about survival.
 */
@Configuration
public class WebConfiguration implements ServletContextInitializer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);
    @Inject
    private Environment env;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("Active Profiles {}", Arrays.toString(env.getActiveProfiles()));
    }
}
