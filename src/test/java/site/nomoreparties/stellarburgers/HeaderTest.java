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

public class HeaderTest {

    public static DriverRule driverRule = new DriverRule();
    private final UserClient client = new UserClient();
    private WebDriver driver;
    private MainPage objMainPage;
    private HeaderPage objHeaderPage;
    private User user;
    private String accessToken;
    private String refreshToken;


    @Before
    public void startUp() {
        driverRule.initDriver();
        driver = driverRule.getDriver();
        objMainPage = new MainPage(driver);
        objHeaderPage = new HeaderPage(driver);
        user = User.random();
        ValidatableResponse createResponse = client.createUser(user);
        accessToken = client.getUserAccessToken(createResponse);
        refreshToken = client.getUserRefreshToken(createResponse);
        objMainPage.open();
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
    @DisplayName("Переход на главную страницу по клику на «Конструктор» со страницы профиля")
    public void goToMainPageAfterConstructorButtonClickFromProfilePage() {

        objMainPage.goToMainPage();
        objHeaderPage.clickProfileButton();
        objHeaderPage.waitForConstructorButtonToAppear();
        objHeaderPage.clickConstructorButton();
        objMainPage.checkGoToMainPageLoggedIn();

    }

    @Test
    @DisplayName("Переход на главную страницу по клику на логотип Stellar Burgers со страницы профиля")
    public void goToMainPageAfterStellarBurgerLogoClickFromProfilePage() {

        objMainPage.goToMainPage();
        objHeaderPage.clickProfileButton();
        objHeaderPage.waitForStellarBurgerLogoToAppear();
        objHeaderPage.clickStellarBurgerLogo();
        objMainPage.checkGoToMainPageLoggedIn();

    }

}
