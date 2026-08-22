# Enterprise API Test Automation Framework

An industry-standard, production-grade API Test Automation & SDET Framework built in Java using **RestAssured**, **TestNG**, **Jackson**, **Lombok**, **AssertJ**, **WireMock**, and **Allure Reporting**.

---

## Architecture & Tech Stack

```
+-----------------------------------------------------------------------------------+
|                              Test Execution Layer                                 |
|               (TestNG Suites, DataProviders, RetryAnalyzer, Listeners)            |
+-----------------------------------------------------------------------------------+
                                          |
                                          v
+-----------------------------------------------------------------------------------+
|                                API Client Layer                                   |
|             (AuthClient, UserClient, OrderClient, PaymentClient)                 |
+-----------------------------------------------------------------------------------+
                                          |
                                          v
+-----------------------------------------------------------------------------------+
|                              Core Framework Engine                                |
|  - SpecBuilder (Request/Response Specs, Headers, Auth Token Injection)            |
|  - ConfigManager (Owner Library - Multi-environment properties: dev, qa, staging)  |
|  - DataGenerator (Datafaker synthetic payload generation)                         |
|  - WireMock Service (In-memory Mocking for 3rd-party services & payment stubs)     |
|  - Jackson Object Mapper (POJO Serialization / Deserialization)                   |
|  - SchemaValidator (JSON Schema Contract Testing)                                 |
+-----------------------------------------------------------------------------------+
                                          |
                                          v
+-----------------------------------------------------------------------------------+
|                              Reporting & CI/CD Layer                              |
|           (Allure Reports, Log4j2 Structured Logging, GitHub Actions)             |
+-----------------------------------------------------------------------------------+
```

---

## Key Features

1. **Client Service Pattern**: Clean separation of endpoints, request models, response models, and test assertions.
2. **Dynamic Serialization / Deserialization**: Java POJOs mapped cleanly using Jackson and Lombok (`@Builder`, `@Data`).
3. **In-Memory Service Mocking**: Integrated **WireMock** server simulating payment gateway responses (Success, Declined, Timeout) without real external dependencies.
4. **Data-Driven & Parallel Execution**: TestNG DataProviders running parameterized tests across multiple threads.
5. **Contract / Schema Validation**: Validates JSON response schemas against JSON Schema draft-07 definitions.
6. **Resilience & Auto-Retry**: Automatic test retry analyzer for transient network glitches.
7. **CI/CD Ready**: Includes GitHub Actions workflow executing suites and publishing Allure reports on push/PR.

---

## Directory Structure

```
├── .github/workflows/test-automation.yml   # CI/CD pipeline
├── pom.xml                                 # Maven dependencies & plugins
├── testng.xml                              # Master test suite (parallel)
├── testng-smoke.xml                        # Smoke test suite
├── testng-regression.xml                   # Regression test suite
├── src/
│   ├── main/
│   │   ├── java/com/enterprise/automation/
│   │   │   ├── client/                     # BaseClient, UserClient, AuthClient, PaymentClient
│   │   │   ├── config/                     # Owner Environment & ConfigManager
│   │   │   ├── constants/                  # Endpoints & HttpStatus constants
│   │   │   ├── listeners/                  # TestNG TestListener, RetryAnalyzer, AnnotationTransformer
│   │   │   ├── mocks/                      # WireMock in-memory service
│   │   │   ├── models/                     # Request and Response POJOs
│   │   │   ├── specs/                      # RestAssured SpecBuilder
│   │   │   └── utils/                      # DataGenerator, JsonUtils, SchemaValidator
│   │   └── resources/
│   │       ├── config/                     # env.dev.properties, env.qa.properties
│   │       ├── log4j2.xml                  # Log4j2 configuration
│   │       └── schemas/                    # JSON schema definition files
│   └── test/
│       └── java/com/enterprise/automation/
│           ├── base/                       # BaseTest setup & teardown
│           ├── dataprovider/               # TestNG DataProviders
│           └── tests/                      # UserCrudTest, PaymentMockTest, SchemaValidationTest
```

---

## Running the Tests

### 1. Run Master Test Suite (Default QA Environment)
```bash
mvn clean test
```

### 2. Run Specific Suite (e.g. Smoke Tests)
```bash
mvn clean test -DsuiteXmlFile=testng-smoke.xml
```

### 3. Run on Specific Environment (e.g. DEV or STAGING)
```bash
mvn clean test -Denv=dev
```

### 4. Generate and View Allure Report
```bash
mvn allure:serve
```
