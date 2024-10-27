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

    @Before
    public void startUp() {
        driverRule.initDriver();
    }

    @After
    public void tearDown() {
        driverRule.getDriver().quit();
        //        11. Удалить пользователя, если создался через API
    }

    @Test
    @DisplayName("Успешная авторизация пользователя")
    public void userSuccessfulLoginTest() {

        User user = User.random();
        UserClient client = new UserClient();
        ValidatableResponse createResponse = client.createUser(user);
        String accessToken = client.getUserAccessToken(createResponse);


        WebDriver driver = driverRule.getDriver();
        LoginPage objLoginPage = new LoginPage(driver);
        MainPage objMainPage = new MainPage(driver);


        objLoginPage.open();
        objLoginPage.waitForLoginPageToAppear();
        objLoginPage.enterEmail(user.getEmail());
        objLoginPage.enterPassword(user.getPassword());
        objLoginPage.clickLoginButton();
        objMainPage.waitForConstructorToAppear();
        objMainPage.checkCurrentUrl();
        objMainPage.waitForMakeOrderButtonToAppear();

        client.deleteUser(accessToken);

    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void userLoginButtonOnMainPageTest() {

        WebDriver driver = driverRule.getDriver();
        LoginPage objLoginPage = new LoginPage(driver);
        MainPage objMainPage = new MainPage(driver);

        objMainPage.open();
        objMainPage.clickEnterAccountButton();
        objLoginPage.waitForLoginPageToAppear();
        objLoginPage.checkCurrentUrl();

    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void userLoginButtonOnHeaderTest() {

        WebDriver driver = driverRule.getDriver();
        LoginPage objLoginPage = new LoginPage(driver);
        MainPage objMainPage = new MainPage(driver);
        HeaderPage objHeaderPage = new HeaderPage(driver);

        objMainPage.open();
        objMainPage.waitForConstructorToAppear();
        objHeaderPage.clickProfileButton();
        objLoginPage.waitForLoginPageToAppear();
        objLoginPage.checkCurrentUrl();

    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации,")
    public void userLoginButtonOnRegistrationPageTest() {

        WebDriver driver = driverRule.getDriver();
        LoginPage objLoginPage = new LoginPage(driver);
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);

        objRegistrationPage.open();
        objRegistrationPage.checkForRegistrationButtonToAppear();
        objRegistrationPage.clickLoginButton();
        objLoginPage.waitForLoginPageToAppear();
        objLoginPage.checkCurrentUrl();

    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля.")
    public void userLoginButtonOnForgotPasswordPageTest() {

        WebDriver driver = driverRule.getDriver();
        LoginPage objLoginPage = new LoginPage(driver);
        ForgotPasswordPage objForgotPasswordPage = new ForgotPasswordPage(driver);

        objForgotPasswordPage.open();
        objForgotPasswordPage.waitForLoginButtonToAppear();
        objForgotPasswordPage.clickLoginButton();
        objLoginPage.waitForLoginPageToAppear();
        objLoginPage.checkCurrentUrl();

    }


}
