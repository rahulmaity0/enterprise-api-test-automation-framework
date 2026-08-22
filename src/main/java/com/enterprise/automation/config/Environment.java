package com.enterprise.automation.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
    "system:properties",
    "system:env",
    "classpath:config/env.${env}.properties",
    "classpath:config/env.qa.properties"
})
public interface Environment extends Config {

    @Key("base.url")
    @DefaultValue("https://jsonplaceholder.typicode.com")
    String baseUrl();

    @Key("mock.server.port")
    @DefaultValue("8089")
    int mockServerPort();

    @Key("mock.server.host")
    @DefaultValue("http://localhost:8089")
    String mockServerHost();

    @Key("default.timeout.seconds")
    @DefaultValue("10")
    int timeoutSeconds();

    @Key("auth.token")
    @DefaultValue("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.default-token")
    String authToken();

    @Key("api.version")
    @DefaultValue("v1")
    String apiVersion();

    @Key("enable.allure.filter")
    @DefaultValue("true")
    boolean enableAllureFilter();

    @Key("enable.logging")
    @DefaultValue("true")
    boolean enableLogging();
}
