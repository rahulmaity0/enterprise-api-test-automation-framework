package com.enterprise.automation.client;

import com.enterprise.automation.constants.Endpoints;
import com.enterprise.automation.models.request.OrderRequest;
import com.enterprise.automation.specs.SpecBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

public class OrderClient extends BaseClient {

    @Step("Create new order for user: {request.userId}")
    public Response createOrder(OrderRequest request, String token) {
        return post(SpecBuilder.getAuthenticatedRequestSpec(token), Endpoints.ORDERS, request);
    }

    @Step("Get order by ID: {orderId}")
    public Response getOrderById(String orderId, String token) {
        return get(SpecBuilder.getAuthenticatedRequestSpec(token), Endpoints.SINGLE_ORDER, Map.of("orderId", orderId));
    }

    @Step("Update order status for order ID: {orderId} to status: {status}")
    public Response updateOrderStatus(String orderId, String status, String token) {
        return patch(SpecBuilder.getAuthenticatedRequestSpec(token), Endpoints.ORDER_STATUS, 
                Map.of("status", status), Map.of("orderId", orderId));
    }
}
