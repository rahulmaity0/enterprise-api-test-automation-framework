package com.enterprise.automation.base;

import com.enterprise.automation.client.AuthClient;
import com.enterprise.automation.client.OrderClient;
import com.enterprise.automation.client.PaymentClient;
import com.enterprise.automation.client.UserClient;
import com.enterprise.automation.config.ConfigManager;
import com.enterprise.automation.config.Environment;
import com.enterprise.automation.mocks.WireMockService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

/**
 * Base Test Class providing setup, teardown, client instantiation, and lifecycle management.
 */
public abstract class BaseTest {

    protected static final Logger log = LogManager.getLogger(BaseTest.class);
    protected static final Environment env = ConfigManager.getEnvironment();

    protected UserClient userClient;
    protected AuthClient authClient;
    protected OrderClient orderClient;
    protected PaymentClient paymentClient;

    @BeforeSuite(alwaysRun = true)
    public void globalSetup() {
        log.info(">>> Initializing Test Automation Framework on Environment: [{}] <<<", System.getProperty("env", "qa"));
        log.info(">>> Target Base URL: [{}] <<<", env.baseUrl());
        
        // Start WireMock in-memory server for stubbed services
        WireMockService.startServer();
    }

    @BeforeClass(alwaysRun = true)
    public void setupClass() {
        userClient = new UserClient();
        authClient = new AuthClient();
        orderClient = new OrderClient();
        paymentClient = new PaymentClient();
    }

    @AfterSuite(alwaysRun = true)
    public void globalTeardown() {
        log.info(">>> Global Test Suite Completed. Shutting down mock services. <<<");
        WireMockService.stopServer();
    }
}
