package site.nomoreparties.stellarburgers.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.EnvConfig;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    private final By loginEmailField = By.cssSelector("input[name='name']");
    private final By loginPasswordField = By.cssSelector("input[name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }



    public void open() {
        driver.get(EnvConfig.LOGIN_PAGE_PATH);
    }

    public void enterEmail(String email) {
        driver.findElement(loginEmailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(loginPasswordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


    public void waitForLoginPageToAppear() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        } catch (TimeoutException e) {
            System.out.println("Ошибка: Элемент '" + loginButton + "' не появился в течение " + EnvConfig.DEFAULT_TIMEOUT + " секунд.");
        }

    }

    public void checkCurrentUrl() {
        Assert.assertEquals(EnvConfig.LOGIN_PAGE_PATH, driver.getCurrentUrl());
    }


}
