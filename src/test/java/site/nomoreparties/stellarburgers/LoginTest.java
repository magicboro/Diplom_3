package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.User;
import site.nomoreparties.stellarburgers.api.UserClient;
import site.nomoreparties.stellarburgers.pages.*;

public class LoginTest {


    public static DriverRule driverRule = new DriverRule();
    private final UserClient client = new UserClient();
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private HeaderPage objHeaderPage;
    private RegistrationPage objRegistrationPage;
    private ForgotPasswordPage objForgotPasswordPage;

    @Before
    public void startUp() {
        driverRule.initDriver();
        WebDriver driver = driverRule.getDriver();
        objLoginPage = new LoginPage(driver);
        objMainPage = new MainPage(driver);
        objHeaderPage = new HeaderPage(driver);
        objRegistrationPage = new RegistrationPage(driver);
        objForgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @After
    public void tearDown() {
        driverRule.getDriver().quit();
    }

    @Test
    @DisplayName("Успешная авторизация пользователя")
    public void userSuccessfulLoginTest() {

        User user = User.random();
        ValidatableResponse createResponse = client.createUser(user);

        objLoginPage.goToLoginPage();
        objLoginPage.sendLoginForm(user);
        objMainPage.checkGoToMainPageLoggedIn();

        client.getUserTokenAndDeleteUser(createResponse);
    }

    @Test
    @DisplayName("Переход на страницу логина по кнопке «Войти в аккаунт» на главной")
    public void userLoginButtonOnMainPageTest() {

        objMainPage.goToMainPage();
        objMainPage.clickEnterAccountButton();
        objLoginPage.checkGoToLoginPage();

    }

    @Test
    @DisplayName("Переход на страницу логина по кнопке «Личный кабинет» на главной")
    public void userLoginButtonOnHeaderTest() {

        objMainPage.goToMainPage();
        objHeaderPage.clickProfileButton();
        objLoginPage.checkGoToLoginPage();

    }

    @Test
    @DisplayName("Переход на страницу логина по кнопке «Войти» на форме регистрации")
    public void userLoginButtonOnRegistrationPageTest() {

        objRegistrationPage.goToRegistrationPage();
        objRegistrationPage.clickLoginButton();
        objLoginPage.checkGoToLoginPage();

    }

    @Test
    @DisplayName("Переход на страницу логина по кнопке «Войти» на форме восстановления пароля.")
    public void userLoginButtonOnForgotPasswordPageTest() {

        objForgotPasswordPage.goToForgotPasswordPage();
        objForgotPasswordPage.clickLoginButton();
        objLoginPage.checkGoToLoginPage();

    }


}
