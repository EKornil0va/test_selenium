package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTests extends BaseTest {

    @Test(description = "Проверка доступности страницы Dashboard")
    public void testNavigateToDashboard() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateTo(DASHBOARD_URL);

        Assert.assertTrue(dashboardPage.isPageLoaded("dashboard"), "Переход на страницу Dashboard не удался.");
        System.out.println("Страница Dashboard успешно открыта");
    }

    @Test(description = "Проверка корректного отображения названия Dashboard")
    public void testDashboardNameDisplayed() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateTo(DASHBOARD_URL);
        dashboardPage.isPageLoaded("dashboard");

        String title = dashboardPage.getDashboardName();
        Assert.assertEquals(title, "DEMO Dashboard", "Название Dashboard не совпадает с ожидаемым.");
        System.out.println("Название Dashboard отображается корректно: " + title);
    }

    @Test(description = "Проверка наличия кнопки добавления виджета")
    public void testAddWidgetButtonExists() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateTo(DASHBOARD_URL);
        dashboardPage.isPageLoaded("dashboard");

        Assert.assertTrue(dashboardPage.isAddWidgetButtonDisplayed(), "Кнопка 'Add widget' не найдена.");
        System.out.println("Кнопка добавления виджета присутствует на странице");
    }

    @Test(description = "Проверка наличия хотя бы одного виджета на Dashboard")
    public void testWidgetPresentOnDashboard() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateTo(DASHBOARD_URL);
        dashboardPage.isPageLoaded("dashboard");

        Assert.assertTrue(dashboardPage.isWidgetPresent(), "На странице Dashboard не найдено ни одного виджета.");
        System.out.println("Виджеты присутствуют на Dashboard");
    }

    void obtainingRedirectAfterSuccessfulLogin() {
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("oauth")));
    }
}