package com.enterprise.automation.client;

import com.enterprise.automation.constants.Endpoints;
import com.enterprise.automation.models.request.PaymentRequest;
import com.enterprise.automation.specs.SpecBuilder;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

public class PaymentClient extends BaseClient {

    @Step("Process payment for order: {request.orderId} with amount: {request.amount}")
    public Response processPayment(PaymentRequest request) {
        return post(SpecBuilder.getMockRequestSpec(), Endpoints.PAYMENTS_PROCESS, request);
    }

    @Step("Refund payment for order: {orderId}")
    public Response refundPayment(String orderId, double amount) {
        return post(SpecBuilder.getMockRequestSpec(), Endpoints.PAYMENTS_REFUND, 
                Map.of("orderId", orderId, "amount", amount));
    }
}
