package com.enterprise.automation.specs;

import com.enterprise.automation.config.ConfigManager;
import com.enterprise.automation.config.Environment;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Enterprise Request and Response Specification Builder.
 * Standardizes headers, base URIs, filters, timeouts, and logging across all API calls.
 */
public final class SpecBuilder {

    private static final Logger log = LogManager.getLogger(SpecBuilder.class);
    private static final Environment env = ConfigManager.getEnvironment();

    private SpecBuilder() {}

    /**
     * Standard Request Specification for default public/backend APIs.
     */
    public static RequestSpecification getRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(env.baseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);

        if (env.enableAllureFilter()) {
            builder.addFilter(new AllureRestAssured());
        }

        if (env.enableLogging()) {
            builder.addFilter(new RequestLoggingFilter(LogDetail.ALL))
                   .addFilter(new ResponseLoggingFilter(LogDetail.ALL));
        }

        return builder.build();
    }

    /**
     * Authenticated Request Specification containing Bearer Token.
     */
    public static RequestSpecification getAuthenticatedRequestSpec(String token) {
        String authToken = (token != null && !token.isEmpty()) ? token : env.authToken();
        return new RequestSpecBuilder()
                .addRequestSpecification(getRequestSpec())
                .addHeader("Authorization", authToken)
                .build();
    }

    /**
     * Request Specification for local or remote Mock Service (WireMock).
     */
    public static RequestSpecification getMockRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(env.mockServerHost())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);

        if (env.enableAllureFilter()) {
            builder.addFilter(new AllureRestAssured());
        }

        if (env.enableLogging()) {
            builder.addFilter(new RequestLoggingFilter(LogDetail.ALL))
                   .addFilter(new ResponseLoggingFilter(LogDetail.ALL));
        }

        return builder.build();
    }

    /**
     * Reusable Response Specification for verifying expected HTTP Status Code & Content Type.
     */
    public static ResponseSpecification getResponseSpec(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
