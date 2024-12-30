package com.spapp.universalpetcare.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigService {

    private static volatile ConfigService instance;
    private final Dotenv dotenv;

    private ConfigService() {
        dotenv = Dotenv.load();
    }

    public static ConfigService getInstance() {
        if (instance == null) { // First check
            synchronized (ConfigService.class) {
                if (instance == null) { // Second check
                    instance = new ConfigService();
                }
            }
        }
        return instance;
    }

    public String getEnv(String key, String defaultValue) {
        String value = dotenv.get(key);
        return value != null ? value : defaultValue;
    }

    public String getRequiredEnv(String key) {
        String value = dotenv.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing required environment variable: " + key);
        }
        return value;
    }
}
