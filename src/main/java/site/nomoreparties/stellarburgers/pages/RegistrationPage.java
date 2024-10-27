package site.nomoreparties.stellarburgers.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.EnvConfig;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;

    private final By registrationNameField = By.xpath("//*[text()='Имя']/following-sibling::input");
    private final By registrationEmailField = By.xpath("//*[text()='Email']/following-sibling::input");
    private final By registrationPasswordField = By.xpath("//*[text()='Пароль']/following-sibling::input");
    private final By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By wrongPasswordError = By.xpath("//*[text()='Некорректный пароль']");
    private final By loginButton = By.xpath("//*[text()='Войти']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(EnvConfig.REGISTRATION_PAGE_PATH);
    }

    public void enterName(String name) {
        driver.findElement(registrationNameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(registrationEmailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(registrationPasswordField).sendKeys(password);
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }







    public void checkForRegistrationButtonToAppear() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
        } catch (TimeoutException e) {
            System.out.println("Ошибка: Элемент '" + registrationButton + "' не появился в течение " + EnvConfig.DEFAULT_TIMEOUT + " секунд.");
        }

    }

    public void checkForWrongPasswordMessageToAppear(){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(wrongPasswordError));
        } catch (TimeoutException e) {
            System.out.println("Ошибка: Сообщение об ошибке '" + wrongPasswordError + "' не появилось в течение " + EnvConfig.DEFAULT_TIMEOUT + " секунд.");
        }
    }

    public void checkCurrentUrl() {
        Assert.assertEquals(EnvConfig.REGISTRATION_PAGE_PATH, driver.getCurrentUrl());
    }



}
