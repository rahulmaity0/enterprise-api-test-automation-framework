package com.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {
    private Long userId;
    private List<OrderItem> items;
    private Double totalAmount;
    private String currency;
    private String shippingAddress;
    private String paymentMethod;

    public OrderRequest() {}

    public OrderRequest(Long userId, List<OrderItem> items, Double totalAmount, String currency, String shippingAddress, String paymentMethod) {
        this.userId = userId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long userId;
        private List<OrderItem> items;
        private Double totalAmount;
        private String currency;
        private String shippingAddress;
        private String paymentMethod;

        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder items(List<OrderItem> items) { this.items = items; return this; }
        public Builder totalAmount(Double totalAmount) { this.totalAmount = totalAmount; return this; }
        public Builder currency(String currency) { this.currency = currency; return this; }
        public Builder shippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; return this; }
        public Builder paymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; return this; }

        public OrderRequest build() {
            return new OrderRequest(userId, items, totalAmount, currency, shippingAddress, paymentMethod);
        }
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OrderItem {
        private String productId;
        private String productName;
        private Integer quantity;
        private Double unitPrice;

        public OrderItem() {}
        public OrderItem(String productId, String productName, Integer quantity, Double unitPrice) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String productId;
            private String productName;
            private Integer quantity;
            private Double unitPrice;
            public Builder productId(String productId) { this.productId = productId; return this; }
            public Builder productName(String productName) { this.productName = productName; return this; }
            public Builder quantity(Integer quantity) { this.quantity = quantity; return this; }
            public Builder unitPrice(Double unitPrice) { this.unitPrice = unitPrice; return this; }
            public OrderItem build() { return new OrderItem(productId, productName, quantity, unitPrice); }
        }

        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        public Double getUnitPrice() { return unitPrice; }
        public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
    }
}
