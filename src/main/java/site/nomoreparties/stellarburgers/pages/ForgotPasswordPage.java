package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.EnvConfig;

import java.time.Duration;

public class ForgotPasswordPage {

    private final WebDriver driver;
    private final By loginButton = By.xpath("//*[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу " + EnvConfig.FORGOT_PASS_PAGE_PATH)
    public void open() {
        driver.get(EnvConfig.FORGOT_PASS_PAGE_PATH);
    }

    @Step("Кликнуть на кнопку «Войти»")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


    public void waitForLoginButtonToAppear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    @Step("Переход на страницу " + EnvConfig.FORGOT_PASS_PAGE_PATH)
    public void goToForgotPasswordPage() {
        open();
        waitForLoginButtonToAppear();
    }


}
