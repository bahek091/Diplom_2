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

public class UpdateUserTest extends BaseTest {


    @Test
    @DisplayName("Update all user fields with authorized user")
    public void canUpdateUserEmailWithAuthorizationTest() {
        String newEmail = "new" + userData.getEmail();
        ValidatableResponse response = userAPI.updateUser(accessToken, UserAPI.EMAIL_FIELD, newEmail);

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    @DisplayName("Update name with authorized user")
    public void canUpdateUserNameWithAuthorizationTest() {
        String newName = "new" + userData.getName();
        ValidatableResponse response = userAPI.updateUser(accessToken, UserAPI.NAME_FIELD, newName);

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    @DisplayName("Update password with authorized user")
    public void canUpdateUserPasswordWithAuthorizationTest() {
        String newPass = "new" + userData.getPassword();
        ValidatableResponse response = userAPI.updateUser(accessToken, UserAPI.EMAIL_FIELD, newPass);

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    @DisplayName("Update email with unauthorized user")
    public void cannotUpdateUserEmailWithAuthorizationTest() {
        String newEmail = "new" + userData.getEmail();
        ValidatableResponse response = userAPI.updateUser("", UserAPI.EMAIL_FIELD, newEmail);

        response.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(UserAPI.OK_FIELD, is(false));
    }

    @Test
    @DisplayName("Update name with unauthorized user")
    public void cannotUpdateUserNameWithAuthorizationTest() {
        String newName = "new" + userData.getName();
        ValidatableResponse response = userAPI.updateUser("", UserAPI.NAME_FIELD, newName);

        response.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(UserAPI.OK_FIELD, is(false));
    }

    @Test
    @DisplayName("Update password with unauthorized user")
    public void cannotUpdateUserPasswordWithAuthorizationTest() {
        String newPass = "new" + userData.getPassword();
        ValidatableResponse response = userAPI.updateUser("", UserAPI.EMAIL_FIELD, newPass);

        response.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(UserAPI.OK_FIELD, is(false));
    }

}
