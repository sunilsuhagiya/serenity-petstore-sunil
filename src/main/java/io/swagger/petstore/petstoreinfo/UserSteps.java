package io.swagger.petstore.petstoreinfo;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.constants.EndPoints;
import io.swagger.petstore.model.UserPojo;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {
    @Step("Creating user with user id : {0},userName: {1}, firstName : {2}, lastName : {3},email : {4},password:{5}," +
            "phone{6}, userStatus : {7}")

    public ValidatableResponse createUser(Integer id, String userName, String firstName, String lastName,
                                          String email, String password, String phone, String userStatus) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUserName(userName);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(userPojo)
                .post(EndPoints.CREATE_USER)
                .then().log().all();
    }

    @Step("Getting the student information with firstName: {0}")

    public HashMap<String, Object> getUserInfoByUserName(String userName) {
        String p1 = "findAll{it.username == '";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_USERNAME)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + userName + p2);
    }

    @Step("Update user with user id : {0},userName: {1}, firstName : {2}, lastName : {3},email : {4},password:{5}," +
            "phone{6}, userStatus : {7}")
    public ValidatableResponse updateUser(Integer id, String userName, String firstName, String lastName,
                                          String email, String password, String phone, String userStatus) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUserName(userName);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_USERNAME)
                .then().log().all();
    }

    @Step("Deleting user information with userName: {0}")

    public ValidatableResponse deleteUser(String userName) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("username", userName)
                .when()
                .delete(EndPoints.DELETE_USER_BY_USERNAME)
                .then();
    }

    @Step("Getting user information with userName: {0}")
    public ValidatableResponse getUSerByUserName(String userName) {
        return SerenityRest.given().log().all()
                .header("accept", "application/json")
                .pathParam("username", userName)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_USERNAME)
                .then();
    }
}
