package cn.luckyvv.blog.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author JackJun
 * 2019/7/8 20:05
 * Life is not just about survival, but VV and distance.
 */
@Configuration
public class LiquibaseConfig {

    @Inject
    private Environment env;

    @Bean
    public SpringLiquibase liquibase (DataSource dataSource){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setContexts("dev");
        return liquibase;
    }
}
