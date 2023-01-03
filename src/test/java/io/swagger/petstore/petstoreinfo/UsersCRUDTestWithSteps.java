package io.swagger.petstore.petstoreinfo;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.testbase.TestBase;
import io.swagger.petstore.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UsersCRUDTestWithSteps extends TestBase {
    static int id = 8852;
    static String userName = "abc45";
    static String firstName = "xyz" + TestUtils.getRandomValue();
    static String lastName = "fgh" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String password = "fghxyz";
    static String phone = "987665416552";
    static String userStatus = "active";

    @Steps
    UserSteps userSteps;

    @Title("This will create new user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUser(id, userName, firstName, lastName, email, password, phone, userStatus);
        response.log().all().statusCode(201);
    }

    @Title("Verify if the user was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> userMap = userSteps.getUserInfoByUserName(userName);
        Assert.assertThat(userMap, hasValue(userName));
    }

    @Title("Update the user information and verify the udated information")
    @Test
    public void test003() {
        firstName = firstName + "_updated";
        userSteps.updateUser(id, userName, firstName, lastName, email, password, phone, userStatus)
                .log().all().statusCode(200);
        // verify update
        HashMap<String, Object> userMap = userSteps.getUserInfoByUserName(userName);
        Assert.assertThat(userMap, hasValue(userName));

    }

    @Title("Delete the user and verify if the user is deleted!")
    @Test
    public void test004() {
        userSteps.deleteUser(userName).statusCode(204);
        userSteps.getUSerByUserName(userName).statusCode(404);

    }
}
