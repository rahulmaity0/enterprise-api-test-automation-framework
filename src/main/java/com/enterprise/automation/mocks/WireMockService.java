package com.enterprise.automation.mocks;

import com.enterprise.automation.config.ConfigManager;
import com.enterprise.automation.constants.Endpoints;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * WireMock Server Service for stubbing downstream 3rd party / microservice dependencies.
 */
public final class WireMockService {

    private static final Logger log = LogManager.getLogger(WireMockService.class);
    private static WireMockServer wireMockServer;

    private WireMockService() {}

    public static synchronized void startServer() {
        if (wireMockServer == null || !wireMockServer.isRunning()) {
            int port = ConfigManager.getEnvironment().mockServerPort();
            log.info("Starting in-memory WireMock server on port: {}", port);
            wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(port));
            wireMockServer.start();
            configureFor("localhost", port);
            setupDefaultStubs();
        }
    }

    public static synchronized void stopServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            log.info("Stopping WireMock server");
            wireMockServer.stop();
        }
    }

    public static void setupDefaultStubs() {
        // 1. Stub: Successful Payment (200 OK)
        stubFor(post(urlEqualTo(Endpoints.PAYMENTS_PROCESS))
                .withRequestBody(matchingJsonPath("$.amount"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"paymentId\": \"PAY-987654321\",\n" +
                                "  \"orderId\": \"ORD-5544\",\n" +
                                "  \"amount\": 199.99,\n" +
                                "  \"currency\": \"USD\",\n" +
                                "  \"status\": \"SUCCESS\",\n" +
                                "  \"transactionReference\": \"TXN-ABC-123\",\n" +
                                "  \"processedAt\": \"2026-08-22T20:00:00Z\",\n" +
                                "  \"message\": \"Payment processed successfully\"\n" +
                                "}")));

        // 2. Stub: Payment Gateway Failure / Declined (400 Bad Request)
        stubFor(post(urlEqualTo(Endpoints.PAYMENTS_PROCESS))
                .withRequestBody(matchingJsonPath("$.cardNumber", containing("0000")))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"status\": 400,\n" +
                                "  \"error\": \"PAYMENT_DECLINED\",\n" +
                                "  \"message\": \"Card declined: Insufficient funds or invalid card details\"\n" +
                                "}")));

        // 3. Stub: Payment Refund (200 OK)
        stubFor(post(urlEqualTo(Endpoints.PAYMENTS_REFUND))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"paymentId\": \"REF-112233\",\n" +
                                "  \"status\": \"REFUNDED\",\n" +
                                "  \"message\": \"Refund processed successfully\"\n" +
                                "}")));
    }
}
