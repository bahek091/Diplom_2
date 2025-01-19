import api.OrderAPI;
import api.UserAPI;
import io.restassured.response.ValidatableResponse;
import model.UserData;
import model.UserGenerator;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    protected String accessToken;
    protected String refreshToken;
    protected UserAPI userAPI = new UserAPI();
    protected OrderAPI orderAPI = new OrderAPI();
    protected UserData userData;

    @Before
    public void setUp() {
        userData = UserGenerator.getRandomUser();
        ValidatableResponse response = userAPI.createUser(userData)
                .log().all();

        accessToken = response.extract().path(UserAPI.ACCESS_TOKEN_FIELD);
        refreshToken = response.extract().path(UserAPI.REFRESH_TOKEN_FIELD);
    }

    @After
    public void cleanUp() {
        userAPI.logoutAndDeleteUser(accessToken, refreshToken);
    }


}
