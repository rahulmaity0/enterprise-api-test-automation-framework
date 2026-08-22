package com.enterprise.automation.utils;

import com.enterprise.automation.models.request.OrderRequest;
import com.enterprise.automation.models.request.PaymentRequest;
import com.enterprise.automation.models.request.UserRequest;
import net.datafaker.Faker;

import java.util.List;
import java.util.Locale;

/**
 * Dynamic synthetic test data generator using Datafaker.
 */
public final class DataGenerator {

    private static final Faker faker = new Faker(Locale.US);

    private DataGenerator() {}

    public static UserRequest generateUserRequest() {
        return UserRequest.builder()
                .name(faker.name().fullName())
                .username(faker.internet().username().toLowerCase() + "_" + faker.number().digits(4))
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().cellPhone())
                .website(faker.internet().domainName())
                .address(UserRequest.Address.builder()
                        .street(faker.address().streetAddress())
                        .suite(faker.address().buildingNumber())
                        .city(faker.address().city())
                        .zipcode(faker.address().zipCode())
                        .geo(UserRequest.Geo.builder()
                                .lat(String.valueOf(faker.address().latitude()))
                                .lng(String.valueOf(faker.address().longitude()))
                                .build())
                        .build())
                .company(UserRequest.Company.builder()
                        .name(faker.company().name())
                        .catchPhrase(faker.company().catchPhrase())
                        .bs(faker.company().bs())
                        .build())
                .build();
    }

    public static OrderRequest generateOrderRequest(Long userId) {
        return OrderRequest.builder()
                .userId(userId != null ? userId : (long) faker.number().numberBetween(1, 100))
                .currency("USD")
                .shippingAddress(faker.address().fullAddress())
                .paymentMethod("CREDIT_CARD")
                .totalAmount(199.99)
                .items(List.of(
                        OrderRequest.OrderItem.builder()
                                .productId("PROD-" + faker.number().digits(5))
                                .productName(faker.commerce().productName())
                                .quantity(faker.number().numberBetween(1, 3))
                                .unitPrice(99.99)
                                .build()
                ))
                .build();
    }

    public static PaymentRequest generatePaymentRequest(String orderId, double amount) {
        return PaymentRequest.builder()
                .orderId(orderId != null ? orderId : "ORD-" + faker.number().digits(6))
                .userId((long) faker.number().numberBetween(1, 100))
                .amount(amount > 0 ? amount : 199.99)
                .currency("USD")
                .paymentMethod("CREDIT_CARD")
                .cardNumber(faker.finance().creditCard())
                .cvv(String.valueOf(faker.number().numberBetween(100, 999)))
                .expiryMonthYear("12/28")
                .build();
    }
}
