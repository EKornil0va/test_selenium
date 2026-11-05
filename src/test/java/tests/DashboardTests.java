package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;

public class DashboardTests extends BaseTest {

    @Test(description = "Проверка доступности страницы Dashboard")
    public void testNavigateToDashboard() {
        loginAsDefaultUser();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateTo(DASHBOARD_URL);

        Assert.assertTrue(dashboardPage.isPageLoaded("dashboard"), "Переход на страницу Dashboard не удался.");
    }

    @Test(description = "Проверка корректного отображения названия Dashboard")
    public void testDashboardNameDisplayed() {
        loginAsDefaultUser();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateTo(DASHBOARD_URL);

        String title = dashboardPage.getDashboardName();
        Assert.assertEquals(title, "DEMO Dashboard", "Название Dashboard не совпадает с ожидаемым.");
    }

    @Test(description = "Проверка наличия кнопки добавления виджета")
    public void testAddWidgetButtonExists() {
        loginAsDefaultUser();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateTo(DASHBOARD_URL);

        Assert.assertTrue(dashboardPage.isAddWidgetButtonDisplayed(), "Кнопка 'Add widget' не найдена.");
    }

    @Test(description = "Проверка наличия хотя бы одного виджета на Dashboard")
    public void testWidgetPresentOnDashboard() {
        loginAsDefaultUser();

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateTo(DASHBOARD_URL);

        Assert.assertTrue(dashboardPage.isWidgetPresent(), "На странице Dashboard не найдено ни одного виджета.");
    }
}