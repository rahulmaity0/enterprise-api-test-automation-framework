package com.enterprise.automation.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class BaseClient {

    protected final Logger log = LogManager.getLogger(getClass());

    protected Response get(RequestSpecification spec, String path) {
        log.info("Executing GET request on endpoint: {}", path);
        return given().spec(spec).when().get(path);
    }

    protected Response get(RequestSpecification spec, String path, Map<String, ?> pathParams) {
        log.info("Executing GET request on endpoint: {} with path params: {}", path, pathParams);
        return given().spec(spec).pathParams(pathParams).when().get(path);
    }

    protected Response getWithQueryParams(RequestSpecification spec, String path, Map<String, ?> queryParams) {
        log.info("Executing GET request on endpoint: {} with query params: {}", path, queryParams);
        return given().spec(spec).queryParams(queryParams).when().get(path);
    }

    protected Response post(RequestSpecification spec, String path, Object body) {
        log.info("Executing POST request on endpoint: {}", path);
        return given().spec(spec).body(body).when().post(path);
    }

    protected Response put(RequestSpecification spec, String path, Object body, Map<String, ?> pathParams) {
        log.info("Executing PUT request on endpoint: {} with path params: {}", path, pathParams);
        return given().spec(spec).pathParams(pathParams).body(body).when().put(path);
    }

    protected Response patch(RequestSpecification spec, String path, Object body, Map<String, ?> pathParams) {
        log.info("Executing PATCH request on endpoint: {} with path params: {}", path, pathParams);
        return given().spec(spec).pathParams(pathParams).body(body).when().patch(path);
    }

    protected Response delete(RequestSpecification spec, String path, Map<String, ?> pathParams) {
        log.info("Executing DELETE request on endpoint: {} with path params: {}", path, pathParams);
        return given().spec(spec).pathParams(pathParams).when().delete(path);
    }
}
