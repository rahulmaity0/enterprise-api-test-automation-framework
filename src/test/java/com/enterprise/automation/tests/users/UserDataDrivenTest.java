package com.enterprise.automation.tests.users;

import com.enterprise.automation.base.BaseTest;
import com.enterprise.automation.constants.HttpStatus;
import com.enterprise.automation.dataprovider.UserDataProvider;
import com.enterprise.automation.models.request.UserRequest;
import com.enterprise.automation.models.response.UserResponse;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("User Management Microservice")
@Feature("Data-Driven Testing")
public class UserDataDrivenTest extends BaseTest {

    @Test(
            dataProvider = "dynamicUsersProvider",
            dataProviderClass = UserDataProvider.class,
            groups = {"regression", "data-driven"},
            description = "Data-driven test creating users with different generated payloads"
    )
    @Story("Data-Driven User Creation")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateUserWithDataProvider(UserRequest userRequest) {
        log.info("Testing user creation for: {} ({})", userRequest.getName(), userRequest.getEmail());

        Response response = userClient.createUser(userRequest);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);

        UserResponse createdUser = response.as(UserResponse.class);
        assertThat(createdUser.getName()).isEqualTo(userRequest.getName());
        assertThat(createdUser.getEmail()).isEqualTo(userRequest.getEmail());
    }

    @Test(
            dataProvider = "userRolesProvider",
            dataProviderClass = UserDataProvider.class,
            groups = {"regression"},
            description = "Verify role authorization mapping data provider"
    )
    @Story("User Role Validation")
    @Severity(SeverityLevel.MINOR)
    public void testUserRoleValidation(String username, String role, int expectedStatus) {
        log.info("Validating user: {} with role: {} expects status: {}", username, role, expectedStatus);
        assertThat(expectedStatus).isEqualTo(200);
        assertThat(role).startsWith("ROLE_");
    }
}
