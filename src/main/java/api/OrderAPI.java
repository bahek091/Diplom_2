package api;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.UserData;

import static io.restassured.RestAssured.given;

public class OrderAPI extends RestAPI {


    @Step("Create new order")
    @Description("Create new user for the user")
    public ValidatableResponse createOrder(String token, String id) {
        String data = String.format("{\"ingredients\": [\"%s\"]}", id);
        return given()
                .spec(requestSpecification())
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .and()
                .body(data)
                .when()
                .post(ORDER_URI)
                .then();
    }


    @Step("Get orders")
    @Description("Get list of orders for the user")
    public ValidatableResponse getOrders(String token) {
        return given()
                .spec(requestSpecification())
                .header("Authorization", token)
                .when()
                .get(ORDER_URI)
                .then();
    }

    @Step("Delete order")
    @Description("Delete order")
    public ValidatableResponse deleteOrder(UserData data) {
        return given()
                .spec(requestSpecification())
                .and()
                .body(data)
                .when()
                .post(ORDER_URI)
                .then();
    }

    @Step("Get ingredients information")
    @Description("Get all ingredients list")
    public ValidatableResponse getIngredients() {
        return given()
                .spec(requestSpecification())
                .when()
                .get(INGREDIENTS_URI)
                .then();
    }
}
