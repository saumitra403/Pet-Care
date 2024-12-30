package com.spapp.universalpetcare.config;

import org.modelmapper.ModelMapper;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    };

    @Bean
    public ConfigService configService() {
        return ConfigService.getInstance();
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        try {
            String redisHost = configService().getRequiredEnv("REDIS_HOST");
            String redisPort = configService().getRequiredEnv("REDIS_PORT");
            String redisAddress = "redis://" + redisHost + ":" + redisPort;
            System.out.println("Attempting to connect to Redis at: " + redisAddress);

            config.useSingleServer().setAddress(redisAddress);
            RedissonClient redissonClient = Redisson.create(config);

            redissonClient.getBucket("test_connection").isExists();
            System.out.println("Successfully connected to Redis at: " + redisAddress);

            return redissonClient;
        } catch (Exception e) {
            System.out.println("Failed to connect to Redis: " + e.getMessage());
            throw new IllegalStateException("Error while initializing Redisson client", e);
        }
    }
}
