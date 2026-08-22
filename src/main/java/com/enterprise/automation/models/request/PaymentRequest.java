package com.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {
    private String orderId;
    private Long userId;
    private Double amount;
    private String currency;
    private String paymentMethod;
    private String cardNumber;
    private String cvv;
    private String expiryMonthYear;

    public PaymentRequest() {}

    public PaymentRequest(String orderId, Long userId, Double amount, String currency, String paymentMethod, String cardNumber, String cvv, String expiryMonthYear) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryMonthYear = expiryMonthYear;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String orderId;
        private Long userId;
        private Double amount;
        private String currency;
        private String paymentMethod;
        private String cardNumber;
        private String cvv;
        private String expiryMonthYear;

        public Builder orderId(String orderId) { this.orderId = orderId; return this; }
        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder amount(Double amount) { this.amount = amount; return this; }
        public Builder currency(String currency) { this.currency = currency; return this; }
        public Builder paymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; return this; }
        public Builder cardNumber(String cardNumber) { this.cardNumber = cardNumber; return this; }
        public Builder cvv(String cvv) { this.cvv = cvv; return this; }
        public Builder expiryMonthYear(String expiryMonthYear) { this.expiryMonthYear = expiryMonthYear; return this; }

        public PaymentRequest build() {
            return new PaymentRequest(orderId, userId, amount, currency, paymentMethod, cardNumber, cvv, expiryMonthYear);
        }
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public String getExpiryMonthYear() { return expiryMonthYear; }
    public void setExpiryMonthYear(String expiryMonthYear) { this.expiryMonthYear = expiryMonthYear; }
}
