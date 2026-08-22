package com.enterprise.automation.client;

import com.enterprise.automation.constants.Endpoints;
import com.enterprise.automation.models.request.UserRequest;
import com.enterprise.automation.specs.SpecBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

public class UserClient extends BaseClient {

    @Step("Get all users")
    public Response getAllUsers() {
        return get(SpecBuilder.getRequestSpec(), Endpoints.USERS);
    }

    @Step("Get user by ID: {userId}")
    public Response getUserById(long userId) {
        return get(SpecBuilder.getRequestSpec(), Endpoints.SINGLE_USER, Map.of("id", userId));
    }

    @Step("Create new user with email: {request.email}")
    public Response createUser(UserRequest request) {
        return post(SpecBuilder.getRequestSpec(), Endpoints.USERS, request);
    }

    @Step("Update user ID: {userId}")
    public Response updateUser(long userId, UserRequest request) {
        return put(SpecBuilder.getRequestSpec(), Endpoints.SINGLE_USER, request, Map.of("id", userId));
    }

    @Step("Patch user ID: {userId}")
    public Response patchUser(long userId, Map<String, Object> partialUpdates) {
        return patch(SpecBuilder.getRequestSpec(), Endpoints.SINGLE_USER, partialUpdates, Map.of("id", userId));
    }

    @Step("Delete user by ID: {userId}")
    public Response deleteUser(long userId) {
        return delete(SpecBuilder.getRequestSpec(), Endpoints.SINGLE_USER, Map.of("id", userId));
    }
}
