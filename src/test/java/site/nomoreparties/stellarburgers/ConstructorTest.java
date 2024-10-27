package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.pages.MainPage;

public class ConstructorTest {

    public static DriverRule driverRule = new DriverRule();
    WebDriver driver;
    private MainPage objMainPage;

    @Before
    public void startUp() {
        driverRule.initDriver();
        driver = driverRule.getDriver();
        objMainPage = new MainPage(driver);

    }

    @After
    public void tearDown() {
        driverRule.getDriver().quit();
    }


    @Test
    @DisplayName("Переход к разделу «Начинки» при клике на таб «Начинки» в конструкторе")
    public void goToFillingsAfterClickOnFillingsTab() {

        objMainPage.goToMainPage();
        objMainPage.waitForIngredientTabFillingsToAppear();
        objMainPage.clickIngredientFillingsTab();
        objMainPage.checkConstructorFillingsTabIsCurrent();

    }

    @Test
    @DisplayName("Переход к разделу «Соусы» при клике на таб «Соусы» в конструкторе")
    public void goToSaucesAfterClickOnSaucesTab() {

        objMainPage.goToMainPage();
        objMainPage.waitForIngredientTabSaucesToAppear();
        objMainPage.clickIngredientSaucesTab();
        objMainPage.checkConstructorSaucesTabIsCurrent();

    }

    @Test
    @DisplayName("Переход к разделу «Булки» при клике на таб «Булки» в конструкторе")
    public void goToBunsAfterClickOnBunsTab() {

        objMainPage.goToMainPage();
        objMainPage.waitForIngredientTabBunsToAppear();
        objMainPage.clickIngredientSaucesTab();
        objMainPage.clickIngredientBunsTab();
        objMainPage.checkConstructorBunsTabIsCurrent();

    }

}
