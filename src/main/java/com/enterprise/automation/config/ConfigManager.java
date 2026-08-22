package com.enterprise.automation.config;

import org.aeonbits.owner.ConfigFactory;

/**
 * Thread-safe Singleton Configuration Manager.
 * Reads environment variables, JVM system properties, or falls back to properties file.
 */
public final class ConfigManager {

    private static volatile Environment environment;

    private ConfigManager() {}

    public static Environment getEnvironment() {
        if (environment == null) {
            synchronized (ConfigManager.class) {
                if (environment == null) {
                    // Default to 'qa' environment if not specified via -Denv=xxx
                    String env = System.getProperty("env", "qa");
                    System.setProperty("env", env);
                    environment = ConfigFactory.create(Environment.class);
                }
            }
        }
        return environment;
    }
}
