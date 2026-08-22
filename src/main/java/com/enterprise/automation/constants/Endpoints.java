package com.enterprise.automation.constants;

public final class Endpoints {

    private Endpoints() {}

    // Public REST Endpoints (JSONPlaceholder & Mock APIs)
    public static final String USERS = "/users";
    public static final String SINGLE_USER = "/users/{id}";
    public static final String POSTS = "/posts";
    public static final String SINGLE_POST = "/posts/{id}";
    public static final String COMMENTS = "/comments";

    // Authentication & Orders Microservices Endpoints
    public static final String AUTH_LOGIN = "/api/v1/auth/login";
    public static final String AUTH_REFRESH = "/api/v1/auth/refresh";
    public static final String ORDERS = "/api/v1/orders";
    public static final String SINGLE_ORDER = "/api/v1/orders/{orderId}";
    public static final String ORDER_STATUS = "/api/v1/orders/{orderId}/status";

    // Mocked Payment Gateway Endpoints (WireMock)
    public static final String PAYMENTS_PROCESS = "/api/v1/payments/process";
    public static final String PAYMENTS_REFUND = "/api/v1/payments/refund";
}
