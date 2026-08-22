package com.enterprise.automation.dataprovider;

import com.enterprise.automation.models.request.UserRequest;
import com.enterprise.automation.utils.DataGenerator;
import org.testng.annotations.DataProvider;

/**
 * TestNG DataProvider supplying dynamic and boundary test inputs.
 */
public class UserDataProvider {

    @DataProvider(name = "dynamicUsersProvider", parallel = true)
    public static Object[][] getDynamicUsers() {
        return new Object[][] {
                { DataGenerator.generateUserRequest() },
                { DataGenerator.generateUserRequest() },
                { DataGenerator.generateUserRequest() }
        };
    }

    @DataProvider(name = "userRolesProvider")
    public static Object[][] getUserRoles() {
        return new Object[][] {
                { "admin_user", "ROLE_ADMIN", 200 },
                { "standard_user", "ROLE_USER", 200 },
                { "readonly_user", "ROLE_GUEST", 200 }
        };
    }
}
