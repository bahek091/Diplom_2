package model;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    @Step("Generate user")
    @Description("Generate random user data")
    public static UserData getRandomUser() {
        return new UserData(RandomStringUtils.randomAlphabetic(8) + "@ya.ru",
                RandomStringUtils.randomAlphabetic(8),
                RandomStringUtils.randomAlphabetic(8));
    }

}
