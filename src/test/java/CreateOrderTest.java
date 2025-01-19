import api.OrderAPI;
import api.UserAPI;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class CreateOrderTest extends BaseTest {

    @Test
    public void canCreateOrderTest() {
        ValidatableResponse ingredientResponse = orderAPI.getIngredients();
        String ingredientId = ingredientResponse.extract().path(String.format(OrderAPI.INGREDIENT_ID_PATH_TEMPLATE, 0));
        ValidatableResponse response = orderAPI.createOrder(accessToken, ingredientId)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    public void shouldnotButCanCreateOrderWithoutAthorizationTest() {
        ValidatableResponse ingredientResponse = orderAPI.getIngredients();
        String ingredientId = ingredientResponse.extract().path(String.format(OrderAPI.INGREDIENT_ID_PATH_TEMPLATE, 0));
        ValidatableResponse response = orderAPI.createOrder("", ingredientId)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(UserAPI.OK_FIELD, is(true));
    }

    @Test
    public void cannotCreateOrderWithoutIngredientsTest() {
        ValidatableResponse response = orderAPI.createOrder(accessToken, "")
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void cannotCreateOrderWithWrongHashTest() {
        ValidatableResponse response = orderAPI.createOrder(accessToken, OrderAPI.DUMMY_FIELD)
                .log().all();

        response.assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
