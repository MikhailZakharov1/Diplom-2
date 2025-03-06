import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static constants.RandomData.*;

public class LoginUserTest {

    private UserSteps userSteps;
    private String accessToken;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
        ValidatableResponse responseCreate = userSteps.createUser(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        accessToken = userSteps.getAccessToken(responseCreate);
    }

    @Test
    @DisplayName("Успешный вход пользователя в систему")
    @Description("При вводе действительного адреса электронной почты и пароля успешный запрос возвращает токен доступа")
    public void loginUserSuccess() {
        ValidatableResponse responseLogin = userSteps.login(RANDOM_EMAIL, RANDOM_PASS);
        userSteps.checkAnswerSuccess(responseLogin);
    }

    @Test
    @DisplayName("Пользователь вошел в систему с неправильным адресом электронной почты")
    @Description("При вводе неверного кода ответа на электронное письмо будет возвращен код 401 Unauthorized")
    public void loginUserWithWrongEmailUnauthorized() {
        ValidatableResponse responseLogin = userSteps.login("wrongEmail@yandex.ru", RANDOM_PASS);
        userSteps.checkAnswerWithWrongData(responseLogin);
    }

    @Test
    @DisplayName("Пользователь вошел в систему с неправильным паролем")
    @Description("При вводе неверного пароля будет возвращен код ответа 401 Unauthorized")
    public void loginUserWithWrongPassUnauthorized() {
        ValidatableResponse responseLogin = userSteps.login(RANDOM_EMAIL, "123456");
        userSteps.checkAnswerWithWrongData(responseLogin);
    }

    @After
    public void close() {
        userSteps.deletingUsersAfterTests(accessToken);
    }

}
