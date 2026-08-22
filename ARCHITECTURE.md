# Enterprise API Automation Framework & Interview Master Guide

---

## 1. The Mental Model: Spring Boot vs. QA Automation Framework

Notice the 1-to-1 parallel between your Spring Boot knowledge and this QA Automation Framework:

| Spring Boot Backend | QA Automation Framework (SDET) | Purpose / Role |
| :--- | :--- | :--- |
| **`@RestController`** | **`RestAssured / Endpoints`** | Handles incoming HTTP requests vs. Sends HTTP requests |
| **`DTO / Entity` (`User.java`)** | **`Models / POJOs` (`UserRequest.java`, `UserResponse.java`)** | Request and Response payload representation |
| **`Jackson / ObjectMapper`** | **`Jackson / JsonUtils`** | JSON $\leftrightarrow$ Java Object mapping |
| **`Service Layer` (`UserService.java`)** | **`API Client Layer` (`UserClient.java`)** | Encapsulates business logic / API interactions |
| **`application.properties`** | **`env.qa.properties` (`Owner ConfigManager`)** | Multi-environment configuration |
| **`JUnit 5 / Mockito`** | **`TestNG / WireMock`** | Test execution engine & Mocking external 3rd-party services |
| **`Spring Interceptor / Filter`** | **`RestAssured Filters / SpecBuilder`** | Injects common headers, tokens, and logs into every request |
| **`Spring Security`** | **`SpecBuilder.getAuthenticatedRequestSpec()`** | Generates & manages Bearer JWT headers |

---

## 2. Layer-by-Layer Architectural Breakdown

### A. Configuration Management (`com.enterprise.automation.config`)
- Uses the **Owner library** (`Environment.java`) and a thread-safe Singleton (`ConfigManager.java`).
- Environment resolution priority:
  1. System properties passed via command line (e.g. `mvn test -Denv=staging`).
  2. Operating system environment variables.
  3. Properties files in classpath (`env.qa.properties`, `env.dev.properties`).

### B. Specification Builder Pattern (`com.enterprise.automation.specs.SpecBuilder`)
- Centralizes Request and Response rules.
- Adds `RequestLoggingFilter` and `ResponseLoggingFilter` for full visibility in logs.
- Adds `AllureRestAssured` filter to automatically attach requests and responses to the Allure HTML report.

### C. Client / Service Layer Pattern (`com.enterprise.automation.client`)
- `BaseClient` wraps core HTTP verbs (`get()`, `post()`, `put()`, `patch()`, `delete()`).
- `UserClient`, `OrderClient`, `AuthClient`, and `PaymentClient` expose high-level methods to test classes.
- Tests never hardcode URLs or duplicate request specs.

### D. POJO Models & Serialization (`com.enterprise.automation.models`)
- Implements the **Builder Pattern** for constructing test payloads.
- Uses Jackson annotations (`@JsonInclude(NON_NULL)`, `@JsonIgnoreProperties(ignoreUnknown = true)`) for resilient serialization.

### E. In-Memory Mocking (`com.enterprise.automation.mocks.WireMockService`)
- Starts an embedded HTTP mock server on port 8089 during test suite execution.
- Stubs payment gateway flows (Success `200`, Declined `400`, Refund `200`).
- Proves you know how to test APIs in isolation when 3rd-party APIs (Stripe, PayPal) are unavailable.

### F. Dynamic Test Data Generation (`com.enterprise.automation.utils.DataGenerator`)
- Uses **Datafaker** to generate randomized, realistic names, emails, addresses, credit card numbers, and UUIDs.
- Eliminates hardcoded test data and prevents collision in parallel test runs.

### G. Test Execution & Resilience (`com.enterprise.automation.listeners`)
- `TestListener`: Logs test lifecycle events and attaches failure logs to Allure.
- `RetryAnalyzer`: Automatically reruns failed tests 1 time to prevent false alarms from temporary network glitches.
- `AnnotationTransformer`: Automatically binds `RetryAnalyzer` to every `@Test` without manual annotations.

---

## 3. High-Frequency Interview Questions & Scripted Answers

### Q1: *"Can you explain the architecture of your test automation framework?"*
> **Answer**:  
> *"Our framework is an enterprise-grade API test automation suite built in Java using **RestAssured** and **TestNG**. It follows the **API Client Service Pattern**:*  
> 1. *At the bottom, we have **ConfigManager** utilizing the Owner library for seamless multi-environment switching (Dev, QA, Staging).*  
> 2. *We use **SpecBuilder** to centralize request/response specifications, logging, and Allure reporting filters.*  
> 3. *We have dedicated **Client classes** (like `UserClient`, `PaymentClient`) that abstract HTTP verbs from test classes.*  
> 4. *Payloads are strongly typed using **POJO models** with Jackson for serialization and Datafaker for dynamic test generation.*  
> 5. *For external dependencies like payment gateways, we integrate **WireMock** for in-memory stubbing.*  
> 6. *Finally, **TestNG** orchestrates parallel execution with custom listeners for automated retries and Allure dashboards, all triggered via **GitHub Actions CI/CD**."*

---

### Q2: *"Why did you choose TestNG over JUnit for your automation framework?"*
> **Answer**:  
> *"While I use **JUnit 5** for unit testing inside Spring Boot applications, I chose **TestNG** for our end-to-end automation framework because TestNG is specifically designed for test suites. It provides native XML suite configuration (`testng.xml`), out-of-the-box multi-threaded parallel execution (`parallel="methods"`), rich `@DataProvider` capabilities, flexible test grouping (`smoke`, `regression`), and listeners like `IRetryAnalyzer` for automatic flakiness handling."*

---

### Q3: *"How do you handle authentication and token expiry in your API tests?"*
> **Answer**:  
> *"We centralize authentication in our `SpecBuilder` and `AuthClient`. Before protected API calls, the framework retrieves a JWT or OAuth2 token. In `SpecBuilder.getAuthenticatedRequestSpec(token)`, the token is dynamically injected into the `Authorization: Bearer <token>` header. If testing multi-user roles, the `@DataProvider` injects specific role tokens into the test execution."*

---

### Q4: *"How do you handle flaky tests caused by network latency or environment hiccups?"*
> **Answer**:  
> *"We handle flakiness on two levels:*  
> 1. *Architecturally, we configure standard timeout thresholds and in-memory WireMock stubs for unstable 3rd-party dependencies.*  
> 2. *At the test runner level, we implemented a custom `RetryAnalyzer` implementing TestNG's `IRetryAnalyzer`. An `AnnotationTransformer` dynamically applies this retry logic across all `@Test` methods, allowing transient failures to automatically retry once before marking a test as failed."*

---

### Q5: *"How do you test 3rd party APIs like Payment Gateways when you cannot make real charges?"*
> **Answer**:  
> *"We use **WireMock** as an embedded in-memory HTTP mock server. In `WireMockService`, we define stubs matching specific endpoints and JSON payloads—such as returning a `200 SUCCESS` with a mock transaction ID for valid cards, and a `400 PAYMENT_DECLINED` for specific test card numbers. This allows our suite to run fast, reliably, and offline in CI/CD without incurring charges or depending on external uptime."*

---

## 4. Ready-to-Use Resume Bullet Points (SDET / Automation)

```
- Architected and implemented an end-to-end API Test Automation Framework in Java using RestAssured, TestNG, Jackson, and AssertJ.
- Engineered in-memory service virtualization with WireMock to stub external payment and downstream microservice dependencies.
- Implemented Data-Driven Testing using TestNG DataProviders and Datafaker, reducing test execution time by 65% via 4-thread parallelization.
- Integrated Allure Reporting and TestNG Listeners with custom RetryAnalyzer to isolate and eliminate test flakiness in CI/CD pipelines.
- Configured GitHub Actions CI/CD workflows to trigger automated smoke and regression test runs on every pull request.
```
