import api.UserAPI;
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
    public void canUpdateUserEmailWithAuthorizationTest() {
        String newEmail = "new" + userData.getEmail();
        ValidatableResponse response = userAPI.updateUser(accessToken, UserAPI.EMAIL_FIELD, newEmail)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    public void canUpdateUserNameWithAuthorizationTest() {
        String newName = "new" + userData.getName();
        ValidatableResponse response = userAPI.updateUser(accessToken, UserAPI.NAME_FIELD, newName)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    public void canUpdateUserPasswordWithAuthorizationTest() {
        String newPass = "new" + userData.getPassword();
        ValidatableResponse response = userAPI.updateUser(accessToken, UserAPI.EMAIL_FIELD, newPass)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    public void cannotUpdateUserEmailWithAuthorizationTest() {
        String newEmail = "new" + userData.getEmail();
        ValidatableResponse response = userAPI.updateUser("", UserAPI.EMAIL_FIELD, newEmail)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(UserAPI.OK_FIELD, is(false));
    }

    @Test
    public void cannotUpdateUserNameWithAuthorizationTest() {
        String newName = "new" + userData.getName();
        ValidatableResponse response = userAPI.updateUser("", UserAPI.NAME_FIELD, newName)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(UserAPI.OK_FIELD, is(false));
    }

    @Test
    public void cannotUpdateUserPasswordWithAuthorizationTest() {
        String newPass = "new" + userData.getPassword();
        ValidatableResponse response = userAPI.updateUser("", UserAPI.EMAIL_FIELD, newPass)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(UserAPI.OK_FIELD, is(false));
    }

}
