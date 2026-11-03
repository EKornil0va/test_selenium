package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AgileBoardPage;
import pages.LoginPage;

public class AgileBoardTests extends BaseTest {

    @Test(description = "Проверка доступности страницы Agile Board")
    public void testNavigateToAgileBoard() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        AgileBoardPage agilePage = new AgileBoardPage(getDriver());
        agilePage.navigateTo(AGILE_URL);

        Assert.assertTrue(agilePage.isPageLoaded("agiles"), "Переход на страницу Agile Board не удался.");
        System.out.println("Страница Agile Board успешно открыта");
    }

    @Test(description = "Проверка корректной загрузки таблицы задач на доске")
    public void testBoardTableLoadedCorrectly() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        AgileBoardPage agilePage = new AgileBoardPage(getDriver());
        agilePage.navigateTo(AGILE_URL);
        agilePage.isPageLoaded("agiles");

        Assert.assertTrue(agilePage.isBoardTableDisplayed(), "Таблица Agile Board не найдена на странице.");
        System.out.println("Таблица задач на доске загрузилась корректно");
    }

    @Test(description = "Проверка наличия поля поиска на странице Agile Board")
    public void testSearchFieldExists() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        AgileBoardPage agilePage = new AgileBoardPage(getDriver());
        agilePage.navigateTo(AGILE_URL);
        agilePage.isPageLoaded("agiles");

        Assert.assertTrue(agilePage.isSearchFieldPresent(), "На странице Agile Board не найдено поле поиска.");
        System.out.println("Поле поиска присутствует на странице Agile Board");
    }

    @Test(description = "Проверка наличия кнопки переключения Backlog")
    public void testBacklogButtonExists() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        AgileBoardPage agilePage = new AgileBoardPage(getDriver());
        agilePage.navigateTo(AGILE_URL);
        agilePage.isPageLoaded("agiles");

        Assert.assertTrue(agilePage.isBacklogButtonPresent(), "Кнопка переключения Backlog не найдена.");
        System.out.println("Кнопка Backlog присутствует на странице");
    }

    void obtainingRedirectAfterSuccessfulLogin() {
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("oauth")));
    }
}