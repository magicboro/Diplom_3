package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.User;
import site.nomoreparties.stellarburgers.api.UserClient;
import site.nomoreparties.stellarburgers.api.UserCredentials;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.RegistrationPage;

public class RegistrationTest {

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
    @DisplayName("Успешная регистрация пользоателя")
    public void userSuccessfulRegistrationTest() {

        User user = User.random();

        WebDriver driver = driverRule.getDriver();
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        LoginPage objLoginPage = new LoginPage(driver);


        objRegistrationPage.open(); //Перейти на страницу регистрации https://stellarburgers.nomoreparties.site/register
        objRegistrationPage.checkForRegistrationButtonToAppear(); //Проверить, что форма на месте
        objRegistrationPage.enterName(user.getName());
        objRegistrationPage.enterEmail(user.getEmail());
        objRegistrationPage.enterPassword(user.getPassword());
        objRegistrationPage.clickRegistrationButton();

        objLoginPage.waitForLoginPageToAppear();
        objLoginPage.checkCurrentUrl();

        //удаление - залогиниться, взять токен и удалить

        UserCredentials userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        UserClient client = new UserClient();
        ValidatableResponse loginResponse = client.loginUser(userCredentials);
        String accessToken = client.getUserAccessToken(loginResponse);
        client.deleteUser(accessToken);



        /*

        3. Кликнуть в поле "Имя"
        4. Ввести Имя
        5. Кликнуть в поле "Email"
        6. Ввести почту
        7. Кликнуть в поле "Пароль"
        8. Ввести Пароль
        9. Нажать кнопку "Зарегистрироваться"
        10. Проверить, что произошёл переход на страницу https://stellarburgers.nomoreparties.site/login
        11. Удалить пользователя через API
         */


    }

    @Test
    @DisplayName("Ошибка при регистрации с паролем <6 символов")
    public void userRegistrationShortPasswordErrorTest() {

        /*
        1. Перейти на страницу регистрации https://stellarburgers.nomoreparties.site/register
        2. Проверить, что форма на месте
        3. Кликнуть в поле "Имя"
        4. Ввести Имя
        5. Кликнуть в поле "Email"
        6. Ввести почту
        7. Кликнуть в поле "Пароль"
        8. Ввести Пароль <6 символов
        9. Нажать кнопку "Зарегистрироваться"
        10. Проверить, что URL не поменялся
        11. Проверить, что появилось сообщение об ошибке
        11. Удалить пользователя через API, если есть

         */

        User user = User.random();

        WebDriver driver = driverRule.getDriver();
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);


        objRegistrationPage.open(); //Перейти на страницу регистрации https://stellarburgers.nomoreparties.site/register
        objRegistrationPage.checkForRegistrationButtonToAppear(); //Проверить, что форма на месте
        objRegistrationPage.enterName(user.getName());
        objRegistrationPage.enterEmail(user.getEmail());
        objRegistrationPage.enterPassword("12345");
        objRegistrationPage.clickRegistrationButton();

        objRegistrationPage.checkCurrentUrl();
        objRegistrationPage.checkForWrongPasswordMessageToAppear();

    }

}
