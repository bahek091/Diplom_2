package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAPI {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";


    public static final String REGISTER_USER_URI = "api/auth/register";
    public static final String LOGIN_USER_URI = "api/auth/login";
    public static final String LOGOUT_USER_URI = "api/auth/logout";
    public static final String DELETE_USER_URI = "api/auth/user";
    public static final String UPDATE_USER_URI = "api/auth/user";

    public static final String USER_EXISTS_MESSAGE = "User already exists";
    public static final String MISSED_FIELD_MESSAGE = "Email, password and name are required fields";

    public static final String ORDER_URI = "api/orders";
    public static final String INGREDIENTS_URI = "api/ingredients";

    public static final String INGREDIENT_ID_PATH_TEMPLATE = "data[%d]._id";


    public static final String OK_FIELD = "success";
    public static final String MESSAGE_FIELD = "message";
    public static final String ACCESS_TOKEN_FIELD = "accessToken";
    public static final String REFRESH_TOKEN_FIELD = "refreshToken";

    public static final String EMAIL_FIELD = "email";
    public static final String NAME_FIELD = "name";

    public static final String DUMMY_ID = "-1";
    public static final String DUMMY_FIELD = "dummy";

    public RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured())
                .log().all();
    }

}
