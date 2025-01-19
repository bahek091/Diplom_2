import api.UserAPI;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.UserData;
import model.UserGenerator;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginUserTest extends BaseTest {


    @Test
    @DisplayName("Login with correct credentials")
    public void canLoginWithCorrectCredentialsTest() {
        ValidatableResponse response = userAPI.loginUser(userData);

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true))
                .body(UserAPI.REFRESH_TOKEN_FIELD, notNullValue());
    }

    @Test
    @DisplayName("Login without password")
    public void cannotLoginWithoutPasswordTest() {
        userData.setPassword(null);
        ValidatableResponse response = userAPI.loginUser(userData);

        response.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(UserAPI.OK_FIELD, is(false));

    }

    @Test
    @DisplayName("Login without email")
    public void cannotLoginWithoutEmailTest() {
        userData.setEmail(null);
        ValidatableResponse response = userAPI.loginUser(userData);

        response.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(UserAPI.OK_FIELD, is(false));

    }

    @Test
    @DisplayName("Login without name")
    public void canLoginWithoutNameTest() {
        userData.setName(null);
        ValidatableResponse response = userAPI.loginUser(userData);

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }
}
