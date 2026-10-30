package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.IssueManagementPage;
import pages.LoginPage;

public class IssueManagementTests extends BaseTest {
    private static final String BASE_URL = "http://localhost:8080";
    private static final String TASK_BOARD = "http://localhost:8080/issues";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";

    @Test(description = "Проверка доступности страницы задач")
    public void testNavigateToIssues() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        IssueManagementPage issuePage = new IssueManagementPage(getDriver());
        issuePage.navigateTo(TASK_BOARD);

        Assert.assertTrue(issuePage.isPageLoaded("issues"), "Переход на страницу задач не удался.");
        System.out.println("Страница задач успешно открыта");
    }

    @Test(description = "Проверка корректной загрузки интерфейса страницы задач")
    public void testIssuesPageLoadedCorrectly() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        IssueManagementPage issuePage = new IssueManagementPage(getDriver());
        issuePage.navigateTo(TASK_BOARD);

        Assert.assertTrue(issuePage.isPageLoaded("issues"), "URL не содержит 'issues' или страница не загрузилась.");
        System.out.println("Страница задач загрузилась корректно");
    }

    @Test(description = "Проверка наличия поля поиска на странице задач")
    public void testSearchFieldExists() {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);

        obtainingRedirectAfterSuccessfulLogin();

        IssueManagementPage issuePage = new IssueManagementPage(getDriver());
        issuePage.navigateTo(TASK_BOARD);
        issuePage.isPageLoaded("issues");

        Assert.assertTrue(issuePage.isSearchFieldPresent(), "На странице задач не найдено поле поиска или фильтрации.");
        System.out.println("Элементы фильтрации присутствуют на странице");
    }

    void obtainingRedirectAfterSuccessfulLogin(){
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("oauth")));
    }

}
