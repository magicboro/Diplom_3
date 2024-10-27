package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.User;
import site.nomoreparties.stellarburgers.api.UserClient;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.RegistrationPage;

import java.util.Objects;

public class RegistrationTest{

    public static DriverRule driverRule = new DriverRule();
    private final UserClient client = new UserClient();
    private RegistrationPage objRegistrationPage;
    private LoginPage objLoginPage;
    private User user;
    private String accessToken;
    private WebDriver driver;

    @Before
    public void startUp() {
        driverRule.initDriver();
        driver = driverRule.getDriver();
        objRegistrationPage = new RegistrationPage(driver);
        objLoginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void userSuccessfulRegistrationTest() {

        user = User.random();

        objRegistrationPage.goToRegistrationPage();
        objRegistrationPage.sendRegistrationForm(user);
        objLoginPage.checkGoToLoginPage();

    }

    @Test
    @DisplayName("Ошибка при регистрации с паролем <6 символов")
    public void userRegistrationShortPasswordErrorTest() {

        user = User.shortPassword();

        objRegistrationPage.goToRegistrationPage();
        objRegistrationPage.sendRegistrationForm(user);
        objRegistrationPage.checkForWrongPasswordMessageToAppear();

    }

    @After
    public void tearDown() {

        if (!Objects.equals(driver.getCurrentUrl(), EnvConfig.REGISTRATION_PAGE_PATH)) {
            client.getUserTokenAndDeleteUser(client.getLoginResponse(user));
        }
        driverRule.getDriver().quit();
    }

}
