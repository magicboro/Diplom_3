package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
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
    private final By ingredientTabBuns = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    private final By ingredientTabSauces = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    private final By ingredientTabFillings = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу " + EnvConfig.MAIN_PAGE_PATH)
    public void open() {
        driver.get(EnvConfig.MAIN_PAGE_PATH);
    }

    @Step("Нажать на кнопку Войти в аккаунт")
    public void clickEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }

    public void waitForConstructorToAppear() {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(constructorSection));
    }

    public void waitForMakeOrderButtonToAppear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
    }

    @Step("Нажать на вкладку Булки")
    public void clickIngredientBunsTab() {
        driver.findElement(ingredientTabBuns).click();
    }

    public void waitForIngredientTabBunsToAppear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(ingredientTabBuns));
    }

    @Step("Проверить, что переход на вкладку Булки произошёл и она текущая")
    public void checkConstructorBunsTabIsCurrent() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(ingredientTabBuns, "class", "current"));
    }

    @Step("Нажать на вкладку Соусы")
    public void clickIngredientSaucesTab() {
        driver.findElement(ingredientTabSauces).click();
    }

    public void waitForIngredientTabSaucesToAppear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(ingredientTabSauces));
    }

    @Step("Проверить, что переход на вкладку Соусы произошёл и она текущая")
    public void checkConstructorSaucesTabIsCurrent() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(ingredientTabSauces, "class", "current"));
    }

    @Step("Нажать на вкладку Наполнители")
    public void clickIngredientFillingsTab() {
        driver.findElement(ingredientTabFillings).click();
    }

    public void waitForIngredientTabFillingsToAppear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(ingredientTabFillings));
    }

    @Step("Проверить, что переход на вкладку Наполнители произошёл и она текущая")
    public void checkConstructorFillingsTabIsCurrent() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(ingredientTabFillings, "class", "current"));
    }

    @Step("Проверить, что текущий URL " + EnvConfig.MAIN_PAGE_PATH)
    public void checkCurrentUrl() {
        Assert.assertEquals(EnvConfig.MAIN_PAGE_PATH, driver.getCurrentUrl());
    }

    @Step("Проверить переход на страницу " + EnvConfig.MAIN_PAGE_PATH)
    public void checkGoToMainPageLoggedIn() {
        waitForConstructorToAppear();
        waitForMakeOrderButtonToAppear();
        checkCurrentUrl();
    }

    @Step("Перейти на страницу " + EnvConfig.MAIN_PAGE_PATH)
    public void goToMainPage() {
        open();
        waitForConstructorToAppear();
    }




}
