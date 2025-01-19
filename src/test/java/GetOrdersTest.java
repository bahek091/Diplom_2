import api.OrderAPI;
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

public class GetOrdersTest extends BaseTest {
  /*  String accessToken;
    String refreshToken;
    UserAPI userAPI = new UserAPI();
    OrderAPI orderAPI = new OrderAPI();
    UserData userData;
    /*
    Получение заказов конкретного пользователя:
        авторизованный пользователь,
        неавторизованный пользователь.
     */

    @Before
    @Override
    public void setUp() {
        accessToken = UserAPI.DUMMY_ID;
        refreshToken = UserAPI.DUMMY_ID;
    }

    @Test
    @DisplayName("Get orders fpr authorized user")
    public void canGetOrdersForUserTest() {
        userData = UserGenerator.getRandomUser();
        ValidatableResponse response = userAPI.createUser(userData);

        accessToken = response.extract().path(UserAPI.ACCESS_TOKEN_FIELD);
        refreshToken = response.extract().path(UserAPI.REFRESH_TOKEN_FIELD);
        String ingredientId = orderAPI.getIngredients().extract().path(String.format(OrderAPI.INGREDIENT_ID_PATH_TEMPLATE, 0));
        orderAPI.createOrder(accessToken, ingredientId);

        ValidatableResponse ordersResponse = orderAPI.getOrders(accessToken);

        ordersResponse.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    @DisplayName("Get orders for unauthorized user")
    public void cannotGetOrdersWithoutAuthorizationTest() {
        userData = UserGenerator.getRandomUser();
        ValidatableResponse response = userAPI.createUser(userData);

        accessToken = response.extract().path(UserAPI.ACCESS_TOKEN_FIELD);
        refreshToken = response.extract().path(UserAPI.REFRESH_TOKEN_FIELD);
        String ingredientId = orderAPI.getIngredients().extract().path(String.format(OrderAPI.INGREDIENT_ID_PATH_TEMPLATE, 0));
        orderAPI.createOrder(accessToken, ingredientId);

        ValidatableResponse ordersResponse = orderAPI.getOrders("");

        ordersResponse.assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(UserAPI.OK_FIELD, is(false));
    }
}
