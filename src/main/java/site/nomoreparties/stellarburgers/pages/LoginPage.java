package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.EnvConfig;
import site.nomoreparties.stellarburgers.api.User;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    private final By loginEmailField = By.cssSelector("input[name='name']");
    private final By loginPasswordField = By.cssSelector("input[name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу " + EnvConfig.LOGIN_PAGE_PATH)
    public void open() {
        driver.get(EnvConfig.LOGIN_PAGE_PATH);
    }

    @Step("Заполнить поле Email")
    public void enterEmail(String email) {
        driver.findElement(loginEmailField).sendKeys(email);
    }

    @Step("Заполнить поле Пароль")
    public void enterPassword(String password) {
        driver.findElement(loginPasswordField).sendKeys(password);
    }

    @Step("Нажать на кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Заполнить форму логина и войти")
    public void sendLoginForm(User user) {
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        clickLoginButton();
    }

    public void waitForLoginPageToAppear() {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    @Step("Проверить, что текущий URL " + EnvConfig.LOGIN_PAGE_PATH)
    public void checkCurrentUrl() {
        Assert.assertEquals(EnvConfig.LOGIN_PAGE_PATH, driver.getCurrentUrl());
    }

    @Step("Проверить переход на страницу " + EnvConfig.LOGIN_PAGE_PATH)
    public void checkGoToLoginPage() {
        waitForLoginPageToAppear();
        checkCurrentUrl();
    }

    @Step("Перейти на страницу " + EnvConfig.LOGIN_PAGE_PATH)
    public void goToLoginPage() {
        open();
        waitForLoginPageToAppear();
    }


}
