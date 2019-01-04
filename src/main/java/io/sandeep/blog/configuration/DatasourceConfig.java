package io.sandeep.blog.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration

public class DatasourceConfig {

    @Autowired
    private Environment env;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        if (env.containsProperty("DATABASE_URL")) {

            logger.info("property file exists with name : {}",env.getProperty("PROPERTY_FILENAME"));
            dataSourceBuilder.url(env.getProperty("DATABASE_URL"));
            dataSourceBuilder.username(env.getProperty("DATABASE_USER"));
            dataSourceBuilder.password(env.getProperty("DATABASE_PASSWORD"));
            dataSourceBuilder.driverClassName(env.getProperty("DATABASE_DRIVER"));

            logger.info("DataSource configured for database: {}", env.getProperty("DATABASE_NAME"));

            return dataSourceBuilder.build();
        } else {

            logger.error("property file does not exists");
            return dataSourceBuilder.build();

        }
    }
}
