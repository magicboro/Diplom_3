package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.EnvConfig;

import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;

    private final By profileContent = By.xpath("//*[@class='Account_contentBox__2CPm3']");
    private final By exitButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу " + EnvConfig.PROFILE_PAGE_PATH)
    public void open() {
        driver.get(EnvConfig.PROFILE_PAGE_PATH);
    }

    @Step("Нажать на кнопку Выйти")
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }

    public void waitForProfileInfoToAppear() {
            new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(profileContent));
    }

    @Step("Проверить, что текущий URL " + EnvConfig.PROFILE_PAGE_PATH)
    public void checkCurrentUrl() {
        Assert.assertEquals(EnvConfig.PROFILE_PAGE_PATH, driver.getCurrentUrl());
    }

    @Step("Проверить переход на страницу " + EnvConfig.PROFILE_PAGE_PATH)
    public void checkGoToProfilePage() {
        waitForProfileInfoToAppear();
        checkCurrentUrl();
    }


}
