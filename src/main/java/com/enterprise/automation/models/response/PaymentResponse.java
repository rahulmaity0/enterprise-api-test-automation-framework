package com.enterprise.automation.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponse {
    private String paymentId;
    private String orderId;
    private Double amount;
    private String currency;
    private String status;
    private String transactionReference;
    private String processedAt;
    private String message;

    public PaymentResponse() {}

    public PaymentResponse(String paymentId, String orderId, Double amount, String currency, String status, String transactionReference, String processedAt, String message) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.transactionReference = transactionReference;
        this.processedAt = processedAt;
        this.message = message;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String paymentId;
        private String orderId;
        private Double amount;
        private String currency;
        private String status;
        private String transactionReference;
        private String processedAt;
        private String message;

        public Builder paymentId(String paymentId) { this.paymentId = paymentId; return this; }
        public Builder orderId(String orderId) { this.orderId = orderId; return this; }
        public Builder amount(Double amount) { this.amount = amount; return this; }
        public Builder currency(String currency) { this.currency = currency; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder transactionReference(String transactionReference) { this.transactionReference = transactionReference; return this; }
        public Builder processedAt(String processedAt) { this.processedAt = processedAt; return this; }
        public Builder message(String message) { this.message = message; return this; }

        public PaymentResponse build() {
            return new PaymentResponse(paymentId, orderId, amount, currency, status, transactionReference, processedAt, message);
        }
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTransactionReference() { return transactionReference; }
    public void setTransactionReference(String transactionReference) { this.transactionReference = transactionReference; }
    public String getProcessedAt() { return processedAt; }
    public void setProcessedAt(String processedAt) { this.processedAt = processedAt; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
