package com.enterprise.automation.client;

import com.enterprise.automation.constants.Endpoints;
import com.enterprise.automation.models.request.AuthRequest;
import com.enterprise.automation.specs.SpecBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthClient extends BaseClient {

    @Step("Authenticate user with username: {request.username}")
    public Response login(AuthRequest request) {
        return post(SpecBuilder.getRequestSpec(), Endpoints.AUTH_LOGIN, request);
    }

    @Step("Refresh authentication token")
    public Response refreshToken(String refreshToken) {
        return post(SpecBuilder.getRequestSpec(), Endpoints.AUTH_REFRESH, 
                java.util.Map.of("refresh_token", refreshToken));
    }
}
