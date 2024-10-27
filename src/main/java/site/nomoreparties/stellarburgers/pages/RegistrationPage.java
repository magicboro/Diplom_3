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

    @Step("Открыть страницу " + EnvConfig.REGISTRATION_PAGE_PATH)
    public void open() {
        driver.get(EnvConfig.REGISTRATION_PAGE_PATH);
    }

    @Step("Заполнить поле Имя")
    public void enterName(String name) {
        driver.findElement(registrationNameField).sendKeys(name);
    }

    @Step("Заполнить поле Email")
    public void enterEmail(String email) {
        driver.findElement(registrationEmailField).sendKeys(email);
    }

    @Step("Заполнить поле Пароль")
    public void enterPassword(String password) {
        driver.findElement(registrationPasswordField).sendKeys(password);
    }

    @Step("Нажать на кнопку Зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Заполнить форму регистрации и зарегистрироваться")
    public void sendRegistrationForm(User user) {
        enterName(user.getName());
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        clickRegistrationButton();
    }

    @Step("Кликнуть на кнопку «Войти»")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void waitForRegistrationButtonToAppear() {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(registrationButton));
    }

    @Step("Перейти на страницу " + EnvConfig.MAIN_PAGE_PATH)
    public void goToRegistrationPage() {
        open();
        waitForRegistrationButtonToAppear();
    }

    @Step("Проверить, что появилось сообщение об ошибке неверном пароле")
    public void checkForWrongPasswordMessageToAppear(){
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(wrongPasswordError));
    }

    @Step("Проверить, что текущий URL " + EnvConfig.REGISTRATION_PAGE_PATH)
    public void checkCurrentUrl() {
        Assert.assertEquals(EnvConfig.REGISTRATION_PAGE_PATH, driver.getCurrentUrl());
    }

}
