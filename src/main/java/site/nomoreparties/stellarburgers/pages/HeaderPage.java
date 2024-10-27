package site.nomoreparties.stellarburgers.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.EnvConfig;

import java.time.Duration;

public class HeaderPage {

    private final WebDriver driver;

    private final By profileButton = By.xpath("//*[text()='Личный Кабинет']");



    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    public void waitForProfileButtonToAppear() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
        } catch (TimeoutException e) {
            System.out.println("Ошибка: Элемент '" + profileButton + "' не появился в течение " + EnvConfig.DEFAULT_TIMEOUT + " секунд.");
        }

    }


}
