package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.EnvConfig;

import java.time.Duration;

public class HeaderPage {

    private final WebDriver driver;

    private final By profileButton = By.xpath("//*[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath("//*[text()='Конструктор']");
    private final By stellarBurgerLogo = By.xpath("//*[@class='AppHeader_header__logo__2D0X2']");


    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на кнопку «Личный кабинет»")
    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    public void waitForProfileButtonToAppear() {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(profileButton));

    }

    @Step("Кликнуть на кнопку «Конструктор»")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void waitForConstructorButtonToAppear() {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(constructorButton));
    }


    @Step("Кликнуть на лого Stellar Burger")
    public void clickStellarBurgerLogo() {
        driver.findElement(stellarBurgerLogo).click();
    }

    public void waitForStellarBurgerLogoToAppear() {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(stellarBurgerLogo));
    }

}
