package com.enterprise.automation.listeners;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Enterprise TestNG Listener for logging, Allure attachments, and test execution metrics.
 */
public class TestListener implements ITestListener, ISuiteListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ISuite suite) {
        log.info("===============================================================================");
        log.info(">>>>> STARTING TEST SUITE: {} <<<<<", suite.getName());
        log.info("===============================================================================");
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("===============================================================================");
        log.info(">>>>> COMPLETED TEST SUITE: {} <<<<<", suite.getName());
        log.info("===============================================================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("-------------------------------------------------------------------------------");
        log.info(">>> RUNNING TEST: {}.{}()", result.getTestClass().getRealClass().getSimpleName(), result.getMethod().getMethodName());
        log.info("-------------------------------------------------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(">>> TEST PASSED: {}.{}() [Duration: {} ms]",
                result.getTestClass().getRealClass().getSimpleName(),
                result.getMethod().getMethodName(),
                (result.getEndMillis() - result.getStartMillis()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error(">>> TEST FAILED: {}.{}()", result.getTestClass().getRealClass().getSimpleName(), result.getMethod().getMethodName());
        log.error("Failure Reason: ", result.getThrowable());
        attachFailureLog(result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown Error");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn(">>> TEST SKIPPED: {}.{}()", result.getTestClass().getRealClass().getSimpleName(), result.getMethod().getMethodName());
    }

    @Attachment(value = "Failure Summary", type = "text/plain")
    public String attachFailureLog(String message) {
        return message;
    }
}
