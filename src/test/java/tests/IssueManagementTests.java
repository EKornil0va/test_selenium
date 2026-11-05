package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.IssueManagementPage;

public class IssueManagementTests extends BaseTest {

    @Test(description = "Проверка доступности страницы задач")
    public void testNavigateToIssues() {
        loginAsDefaultUser();

        IssueManagementPage issuePage = new IssueManagementPage(getDriver());
        issuePage.navigateTo(TASK_BOARD);

        Assert.assertTrue(issuePage.isPageLoaded("issues"), "Переход на страницу задач не удался.");
    }

    @Test(description = "Проверка корректной загрузки интерфейса страницы задач")
    public void testIssuesPageLoadedCorrectly() {
        loginAsDefaultUser();

        IssueManagementPage issuePage = new IssueManagementPage(getDriver());
        issuePage.navigateTo(TASK_BOARD);

        Assert.assertTrue(issuePage.isPageLoaded("issues"), "URL не содержит 'issues' или страница не загрузилась.");
    }

    @Test(description = "Проверка наличия поля поиска на странице задач")
    public void testSearchFieldExists() {
        loginAsDefaultUser();

        IssueManagementPage issuePage = new IssueManagementPage(getDriver());
        issuePage.navigateTo(TASK_BOARD);

        Assert.assertTrue(issuePage.isSearchFieldPresent(), "На странице задач не найдено поле поиска или фильтрации.");
    }
}