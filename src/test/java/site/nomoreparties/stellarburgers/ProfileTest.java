package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import site.nomoreparties.stellarburgers.api.User;
import site.nomoreparties.stellarburgers.api.UserClient;
import site.nomoreparties.stellarburgers.pages.*;

public class ProfileTest {

    public static DriverRule driverRule = new DriverRule();
    private WebDriver driver;
    private final UserClient client = new UserClient();
    private User user;
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private HeaderPage objHeaderPage;
    private ProfilePage objProfilePage;
    private String accessToken;
    private String refreshToken;

    @Before
    public void startUp() {
        driverRule.initDriver();
        driver = driverRule.getDriver();
        objProfilePage = new ProfilePage(driver);
        objLoginPage = new LoginPage(driver);
        objMainPage = new MainPage(driver);
        objHeaderPage = new HeaderPage(driver);
        user = User.random();
        ValidatableResponse createResponse = client.createUser(user);
        accessToken = client.getUserAccessToken(createResponse);
        refreshToken = client.getUserRefreshToken(createResponse);
        objMainPage.goToMainPage();
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem("accessToken", "Bearer " + accessToken);
        localStorage.setItem("refreshToken", refreshToken);
    }

    @After
    public void tearDown() {
        client.deleteUser(accessToken);
        driverRule.getDriver().quit();
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» с главной страницы для авторизованного пользователя")
    public void authUserGoToProfileTest() {

        objHeaderPage.clickProfileButton();
        objProfilePage.waitForProfileInfoToAppear();
        objProfilePage.checkCurrentUrl();

    }

    @Test
    @DisplayName("Выход из пользователя при нажатии на кнопку «Выйти» в личном кабинете")
    public void authUserLogoutTest() {

        objHeaderPage.clickProfileButton();
        objProfilePage.checkGoToProfilePage();
        objProfilePage.clickExitButton();
        objLoginPage.checkGoToLoginPage();

    }

}
