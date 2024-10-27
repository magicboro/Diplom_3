package site.nomoreparties.stellarburgers.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.EnvConfig;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    private final By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By constructorSection = By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']");
    private final By enterAccountButton = By.xpath("//button[text()='Войти в аккаунт']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(EnvConfig.MAIN_PAGE_PATH);
    }

    public void clickEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }


    public void waitForConstructorToAppear() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(constructorSection));
        } catch (TimeoutException e) {
            System.out.println("Ошибка: Элемент '" + constructorSection + "' не появился в течение " + EnvConfig.DEFAULT_TIMEOUT + " секунд.");
        }
    }

    public void waitForMakeOrderButtonToAppear() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
        } catch (TimeoutException e) {
            System.out.println("Ошибка: Элемент '" + makeOrderButton + "' не появился в течение " + EnvConfig.DEFAULT_TIMEOUT + " секунд.");
        }
    }

    public void checkCurrentUrl() {
        Assert.assertEquals(EnvConfig.MAIN_PAGE_PATH, driver.getCurrentUrl());
    }


}
