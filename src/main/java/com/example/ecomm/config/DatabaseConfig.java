package com.example.ecomm.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// R2DBC ConnectionFactory가 있으면 DataSourceAutoConfiguration이 비활성화되므로, Flyway용 DataSource를 직접 등록
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }
}
