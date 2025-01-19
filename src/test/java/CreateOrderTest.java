import api.OrderAPI;
import api.UserAPI;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class CreateOrderTest extends BaseTest {

    @Test
    @DisplayName("Can create new order with authorization")
    public void canCreateOrderTest() {
        ValidatableResponse ingredientResponse = orderAPI.getIngredients();
        String ingredientId = ingredientResponse.extract().path(String.format(OrderAPI.INGREDIENT_ID_PATH_TEMPLATE, 0));
        ValidatableResponse response = orderAPI.createOrder(accessToken, ingredientId);

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    @DisplayName("Create new order without authorization")
    public void shouldnotButCanCreateOrderWithoutAthorizationTest() {
        ValidatableResponse ingredientResponse = orderAPI.getIngredients();
        String ingredientId = ingredientResponse.extract().path(String.format(OrderAPI.INGREDIENT_ID_PATH_TEMPLATE, 0));
        ValidatableResponse response = orderAPI.createOrder("", ingredientId);

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    @DisplayName("Cannot create order without ingredients")
    public void cannotCreateOrderWithoutIngredientsTest() {
        ValidatableResponse response = orderAPI.createOrder(accessToken, "");

        response.assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Cannot create order with incorrect ingredient hash ")
    public void cannotCreateOrderWithWrongHashTest() {
        ValidatableResponse response = orderAPI.createOrder(accessToken, OrderAPI.DUMMY_FIELD);

        response.assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
