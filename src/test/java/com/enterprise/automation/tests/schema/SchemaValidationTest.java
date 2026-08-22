package com.enterprise.automation.tests.schema;

import com.enterprise.automation.base.BaseTest;
import com.enterprise.automation.constants.HttpStatus;
import com.enterprise.automation.utils.SchemaValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("API Contract & Schema Testing")
@Feature("JSON Schema Conformance")
public class SchemaValidationTest extends BaseTest {

    @Test(groups = {"regression", "contract"}, description = "Verify User response matches defined JSON Schema contract")
    @Story("User Schema Validation")
    @Severity(SeverityLevel.CRITICAL)
    public void testUserJsonSchemaConformance() {
        Response response = userClient.getUserById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Validate response body against schemas/user-schema.json
        SchemaValidator.validateSchema(response, "schemas/user-schema.json");
    }
}
