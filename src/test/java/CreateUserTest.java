import api.UserAPI;
import io.restassured.response.ValidatableResponse;
import model.UserGenerator;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class CreateUserTest extends BaseTest {

    @Before
    @Override
    public void setUp() {
        accessToken = UserAPI.DUMMY_ID;
        refreshToken = UserAPI.DUMMY_ID;
    }

    @Test
    public void checkUniqueUserCreationTest() {
        userData = UserGenerator.getRandomUser();
        ValidatableResponse response = userAPI.createUser(userData)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));

        accessToken = response.extract().path(UserAPI.ACCESS_TOKEN_FIELD);
        refreshToken = response.extract().path(UserAPI.REFRESH_TOKEN_FIELD);
    }

    @Test
    public void cannotCreateTheSameUserTest() {
        userData = UserGenerator.getRandomUser();
        ValidatableResponse response = userAPI.createUser(userData)
                .log().all();
        accessToken = response.extract().path(UserAPI.ACCESS_TOKEN_FIELD);
        refreshToken = response.extract().path(UserAPI.REFRESH_TOKEN_FIELD);

        userAPI.createUser(userData)
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(UserAPI.MESSAGE_FIELD, is(UserAPI.USER_EXISTS_MESSAGE));
    }

    @Test
    public void cannotCreateUserWithoutPasswordTest() {
        userData = UserGenerator.getRandomUser();
        userData.setPassword(null);

        ValidatableResponse response = userAPI.createUser(userData)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(UserAPI.OK_FIELD, is(false))
                .body(UserAPI.MESSAGE_FIELD, is(UserAPI.MISSED_FIELD_MESSAGE));
    }

    @Test
    public void cannotCreateUserWithoutEmailTest() {
        userData = UserGenerator.getRandomUser();
        userData.setEmail(null);

        ValidatableResponse response = userAPI.createUser(userData)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(UserAPI.OK_FIELD, is(false))
                .body(UserAPI.MESSAGE_FIELD, is(UserAPI.MISSED_FIELD_MESSAGE));
    }

    @Test
    public void cannotCreateUserWithoutNameTest() {
        userData = UserGenerator.getRandomUser();
        userData.setName(null);

        ValidatableResponse response = userAPI.createUser(userData)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(UserAPI.OK_FIELD, is(false))
                .body(UserAPI.MESSAGE_FIELD, is(UserAPI.MISSED_FIELD_MESSAGE));
    }

}
