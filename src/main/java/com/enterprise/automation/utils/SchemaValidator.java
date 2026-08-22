package com.enterprise.automation.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.InputStream;

/**
 * RestAssured JSON Schema Validation Utility.
 */
public final class SchemaValidator {

    private SchemaValidator() {}

    public static void validateSchema(Response response, String schemaPathInClasspath) {
        InputStream schemaStream = SchemaValidator.class.getClassLoader().getResourceAsStream(schemaPathInClasspath);
        if (schemaStream == null) {
            throw new IllegalArgumentException("Schema file not found in classpath: " + schemaPathInClasspath);
        }
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaStream));
    }
}
