package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AgileBoardPage;

public class AgileBoardTests extends BaseTest {

    @Test(description = "Проверка доступности страницы Agile Board")
    public void testNavigateToAgileBoard() {
        loginAsDefaultUser();

        AgileBoardPage agilePage = new AgileBoardPage(getDriver());
        agilePage.navigateTo(AGILE_URL);

        Assert.assertTrue(agilePage.isPageLoaded("agiles"), "Переход на страницу Agile Board не удался.");
    }

    @Test(description = "Проверка корректной загрузки таблицы задач на доске")
    public void testBoardTableLoadedCorrectly() {
        loginAsDefaultUser();

        AgileBoardPage agilePage = new AgileBoardPage(getDriver());
        agilePage.navigateTo(AGILE_URL);

        Assert.assertTrue(agilePage.isBoardTableDisplayed(), "Таблица Agile Board не найдена на странице.");
    }

    @Test(description = "Проверка наличия поля поиска на странице Agile Board")
    public void testSearchFieldExists() {
        loginAsDefaultUser();

        AgileBoardPage agilePage = new AgileBoardPage(getDriver());
        agilePage.navigateTo(AGILE_URL);

        Assert.assertTrue(agilePage.isSearchFieldPresent(), "На странице Agile Board не найдено поле поиска.");
    }

    @Test(description = "Проверка наличия кнопки переключения Backlog")
    public void testBacklogButtonExists() {
        loginAsDefaultUser();

        AgileBoardPage agilePage = new AgileBoardPage(getDriver());
        agilePage.navigateTo(AGILE_URL);

        Assert.assertTrue(agilePage.isBacklogButtonPresent(), "Кнопка переключения Backlog не найдена.");
    }
}