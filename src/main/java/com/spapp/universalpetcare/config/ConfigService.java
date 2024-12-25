package com.spapp.universalpetcare.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigService {

    // Private static instance of the singleton class
    private static ConfigService instance;
    private final Dotenv dotenv;

    // Private constructor to prevent instantiation
    private ConfigService() {
        dotenv = Dotenv.load();
    }

    // Public method to provide access to the singleton instance
    public static ConfigService getInstance() {
        if (instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }

    // Method to get environment variable with a default value
    public String getEnv(String key, String defaultValue) {
        String value = dotenv.get(key);
        return value != null ? value : defaultValue;
    }

    // Method to get the database URL
    public String getDbUrl() {
        return getEnv("DB_URL", null);
    }

    // Method to get the database username
    public String getDbUsername() {
        return getEnv("DB_USERNAME", null);
    }

    // Method to get the database password
    public String getDbPassword() {
        return getEnv("DB_PASSWORD", null);
    }
}
