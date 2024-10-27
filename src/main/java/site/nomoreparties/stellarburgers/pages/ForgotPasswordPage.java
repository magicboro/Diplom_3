package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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


    public void open() {
        driver.get(EnvConfig.FORGOT_PASS_PAGE_PATH);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void waitForLoginButtonToAppear() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        } catch (TimeoutException e) {
            System.out.println("Ошибка: Элемент '" + loginButton + "' не появился в течение " + EnvConfig.DEFAULT_TIMEOUT + " секунд.");
        }

    }


}
