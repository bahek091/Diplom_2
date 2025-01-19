package api;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.UserData;

import static io.restassured.RestAssured.given;

public class UserAPI extends RestAPI {


    @Step("Register new user")
    @Description("Create new user")
    public ValidatableResponse createUser(UserData data) {
        return given()
                .spec(requestSpecification())
                .and()
                .body(data)
                .when()
                .post(REGISTER_USER_URI)
                .then();
    }

    @Step("Delete user")
    @Description("Delete user by accessToken")
    public void deleteUser(String data) {
        String token = String.format("{\"token\":\"%s\"}", data);
        given()
                .spec(requestSpecification())
                .and()
                .body(token)
                .when()
                .delete(DELETE_USER_URI)
                .then();
    }

    @Step("Login user")
    @Description("Login to application with user credentials")
    public ValidatableResponse loginUser(UserData data) {
        return given()
                .spec(requestSpecification())
                .and()
                .body(data)
                .when()
                .post(LOGIN_USER_URI)
                .then();
    }

    @Step("Logout user")
    @Description("Logout from application by refreshToken")
    public void logoutUser(String data) {
        String token = String.format("{\"token\":\"%s\"}", data);
        given()
                .spec(requestSpecification())
                .and()
                .body(token)
                .when()
                .post(LOGOUT_USER_URI)
                .then();
    }

    @Step("Update user information")
    @Description("Update user information by access token")
    public ValidatableResponse updateUser(String token, String field, String value) {
        String bodyJson = String.format("{\"%s\":\"%s\"}", field, value);
        return given()
                .spec(requestSpecification())
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .and()
                .body(bodyJson)
                .when()
                .patch(UPDATE_USER_URI)
                .then();
    }

    public void logoutAndDeleteUser(String accessToken, String refreshToken) {
        if (!DUMMY_ID.equals(accessToken))
            logoutUser(accessToken);
        if (!DUMMY_ID.equals(refreshToken))
            deleteUser(refreshToken);

    }


}
