package com.enterprise.automation.tests.payments;

import com.enterprise.automation.base.BaseTest;
import com.enterprise.automation.constants.HttpStatus;
import com.enterprise.automation.models.request.PaymentRequest;
import com.enterprise.automation.models.response.PaymentResponse;
import com.enterprise.automation.utils.DataGenerator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Payment Microservice (WireMock Stubbed)")
@Feature("Payment Gateway Processing")
public class PaymentMockTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Verify successful payment processing via WireMock stub")
    @Story("Process Valid Payment")
    @Severity(SeverityLevel.BLOCKER)
    public void testSuccessfulPaymentProcessing() {
        PaymentRequest paymentRequest = DataGenerator.generatePaymentRequest("ORD-5544", 199.99);

        Response response = paymentClient.processPayment(paymentRequest);

        assertThat(response.getStatusCode())
                .as("Successful payment should return 200 OK")
                .isEqualTo(HttpStatus.OK);

        PaymentResponse paymentResponse = response.as(PaymentResponse.class);
        assertThat(paymentResponse.getStatus()).isEqualTo("SUCCESS");
        assertThat(paymentResponse.getPaymentId()).isNotBlank();
        assertThat(paymentResponse.getTransactionReference()).startsWith("TXN-");
        assertThat(paymentResponse.getAmount()).isEqualTo(199.99);
    }

    @Test(groups = {"regression", "negative"}, description = "Verify declined payment when card number contains invalid sequence")
    @Story("Process Declined Payment")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeclinedPaymentWithInvalidCard() {
        PaymentRequest invalidCardRequest = DataGenerator.generatePaymentRequest("ORD-9999", 50.00);
        invalidCardRequest.setCardNumber("4111-0000-1111-2222"); // Triggers WireMock declined stub

        Response response = paymentClient.processPayment(invalidCardRequest);

        assertThat(response.getStatusCode())
                .as("Declined payment must return 400 Bad Request")
                .isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(response.jsonPath().getString("error"))
                .isEqualTo("PAYMENT_DECLINED");
    }

    @Test(groups = {"regression"}, description = "Verify payment refund processing")
    @Story("Process Payment Refund")
    @Severity(SeverityLevel.NORMAL)
    public void testRefundProcessing() {
        Response response = paymentClient.refundPayment("ORD-5544", 199.99);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.jsonPath().getString("status")).isEqualTo("REFUNDED");
        assertThat(response.jsonPath().getString("paymentId")).isEqualTo("REF-112233");
    }
}
