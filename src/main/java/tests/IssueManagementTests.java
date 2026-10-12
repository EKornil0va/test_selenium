package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class IssueManagementTests extends BaseTest {

    @Test(description = "Проверка доступности страницы задач")
    public void testNavigateToIssues() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        getDriver().get(TASK_BOARD);

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl != null && currentUrl.contains("issues"),
                "Переход на страницу задач не удался. URL: " + currentUrl);
        System.out.println("Страница задач успешно открыта");
    }

    @Test(description = "Проверка корректной загрузки интерфейса страницы задач")
    public void testIssuesPageLoadedCorrectly() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        getDriver().get(TASK_BOARD);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl != null && currentUrl.contains("issues"),
                "URL не содержит 'issues'. Текущий URL: " + currentUrl);

        String pageTitle = getDriver().getTitle();
        System.out.println("Заголовок страницы задач: '" + pageTitle + "'");

        String lowerTitle = pageTitle != null ? pageTitle.toLowerCase() : "";
        boolean isTitleValid = lowerTitle.contains("issue") ||
                lowerTitle.contains("задач") ||
                lowerTitle.contains("youtrack");

        Assert.assertTrue(isTitleValid, "Заголовок страницы не соответствует ожиданиям. Title: " + pageTitle);
        System.out.println("Страница задач загрузилась корректно");
    }

    @Test(description = "Проверка наличия поля поиска на странице задач")
    public void testSearchFieldExists() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        getDriver().get(TASK_BOARD);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String pageSource = getDriver().getPageSource();
        boolean hasSearch = pageSource != null && (pageSource.contains("Поиск") || pageSource.contains("Search") ||
                pageSource.contains("filter") || pageSource.contains("Фильтр"));

        Assert.assertTrue(hasSearch, "На странице задач не найдено поле поиска или фильтрации.");
        System.out.println("Элементы поиска/фильтрации присутствуют на странице");
    }
}
