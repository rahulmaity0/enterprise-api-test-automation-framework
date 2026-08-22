package com.enterprise.automation.models.response;

import com.enterprise.automation.models.request.OrderRequest.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {
    private String orderId;
    private Long userId;
    private List<OrderItem> items;
    private Double totalAmount;
    private String currency;
    private String status;
    private String createdAt;
    private String paymentStatus;

    public OrderResponse() {}

    public OrderResponse(String orderId, Long userId, List<OrderItem> items, Double totalAmount, String currency, String status, String createdAt, String paymentStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.status = status;
        this.createdAt = createdAt;
        this.paymentStatus = paymentStatus;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String orderId;
        private Long userId;
        private List<OrderItem> items;
        private Double totalAmount;
        private String currency;
        private String status;
        private String createdAt;
        private String paymentStatus;

        public Builder orderId(String orderId) { this.orderId = orderId; return this; }
        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder items(List<OrderItem> items) { this.items = items; return this; }
        public Builder totalAmount(Double totalAmount) { this.totalAmount = totalAmount; return this; }
        public Builder currency(String currency) { this.currency = currency; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(String createdAt) { this.createdAt = createdAt; return this; }
        public Builder paymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; return this; }

        public OrderResponse build() {
            return new OrderResponse(orderId, userId, items, totalAmount, currency, status, createdAt, paymentStatus);
        }
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}
