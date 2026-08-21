package com.example.ecomm.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * R2DBC ConnectionFactory 빈이 존재하면 Spring Boot는 DataSourceAutoConfiguration을
 * 비활성화한다(@ConditionalOnMissingBean(ConnectionFactory.class)). Flyway는 JDBC
 * DataSource가 있어야 동작하므로, JPA/Flyway가 쓸 DataSource를 여기서 직접 등록한다.
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }
}
