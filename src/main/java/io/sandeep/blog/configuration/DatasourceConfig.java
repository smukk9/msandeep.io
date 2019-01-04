package io.sandeep.blog.configuration;

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


    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(env.getProperty("DATABASE_URL"));
        dataSourceBuilder.username(env.getProperty("DATABASE_USER"));
        dataSourceBuilder.password(env.getProperty("DATABASE_PASSWORD"));
        dataSourceBuilder.driverClassName(env.getProperty("DATABASE_DRIVER"));
        return dataSourceBuilder.build();
    }
}
