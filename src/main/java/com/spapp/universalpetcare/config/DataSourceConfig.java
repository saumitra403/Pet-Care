package com.spapp.universalpetcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private final ConfigService configService = ConfigService.getInstance();

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(configService.getRequiredEnv("DB_URL"))
                .username(configService.getRequiredEnv("DB_USERNAME"))
                .password(configService.getRequiredEnv("DB_PASSWORD"))
                .build();
    }
}