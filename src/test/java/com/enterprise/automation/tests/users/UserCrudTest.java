package com.enterprise.automation.tests.users;

import com.enterprise.automation.base.BaseTest;
import com.enterprise.automation.constants.HttpStatus;
import com.enterprise.automation.models.request.UserRequest;
import com.enterprise.automation.models.response.UserResponse;
import com.enterprise.automation.utils.DataGenerator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("User Management Microservice")
@Feature("User CRUD Operations")
public class UserCrudTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Verify retrieving all users returns 200 OK")
    @Story("Get Users")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Fetches all active users from the repository and verifies list is not empty.")
    public void testGetAllUsers() {
        Response response = userClient.getAllUsers();

        assertThat(response.getStatusCode())
                .as("Status code must be 200 OK")
                .isEqualTo(HttpStatus.OK);

        UserResponse[] users = response.as(UserResponse[].class);
        assertThat(users)
                .as("Users list should not be null or empty")
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(10);

        assertThat(users[0].getEmail())
                .as("First user must have a valid email")
                .contains("@");
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify retrieving a single user by ID")
    @Story("Get User By ID")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetUserById() {
        long userId = 1L;
        Response response = userClient.getUserById(userId);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        UserResponse user = response.as(UserResponse.class);
        assertThat(user.getId()).isEqualTo(userId);
        assertThat(user.getName()).isNotBlank();
        assertThat(user.getEmail()).isNotBlank();
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify creating a new user with dynamic payload returns 201 Created")
    @Story("Create User")
    @Severity(SeverityLevel.BLOCKER)
    public void testCreateUser() {
        UserRequest newUserData = DataGenerator.generateUserRequest();

        Response response = userClient.createUser(newUserData);

        assertThat(response.getStatusCode())
                .as("User creation must return 201 CREATED")
                .isEqualTo(HttpStatus.CREATED);

        UserResponse createdUser = response.as(UserResponse.class);
        assertThat(createdUser.getId())
                .as("Generated User ID must not be null")
                .isNotNull();

        assertThat(createdUser.getName())
                .isEqualTo(newUserData.getName());

        assertThat(createdUser.getEmail())
                .isEqualTo(newUserData.getEmail());
    }

    @Test(groups = {"regression"}, description = "Verify updating existing user using PUT returns 200 OK")
    @Story("Update User")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdateUser() {
        long userId = 1L;
        UserRequest updateData = DataGenerator.generateUserRequest();

        Response response = userClient.updateUser(userId, updateData);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        UserResponse updatedUser = response.as(UserResponse.class);
        assertThat(updatedUser.getName()).isEqualTo(updateData.getName());
        assertThat(updatedUser.getEmail()).isEqualTo(updateData.getEmail());
    }

    @Test(groups = {"regression"}, description = "Verify partial update using PATCH returns 200 OK")
    @Story("Patch User")
    @Severity(SeverityLevel.NORMAL)
    public void testPatchUser() {
        long userId = 1L;
        Map<String, Object> patchData = Map.of("phone", "1-800-AUTOMATION");

        Response response = userClient.patchUser(userId, patchData);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.jsonPath().getString("phone")).isEqualTo("1-800-AUTOMATION");
    }

    @Test(groups = {"regression"}, description = "Verify deleting a user by ID returns 200 OK")
    @Story("Delete User")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteUser() {
        long userId = 1L;

        Response response = userClient.deleteUser(userId);

        assertThat(response.getStatusCode())
                .as("Deleting user should return 200 OK")
                .isEqualTo(HttpStatus.OK);
    }
}
