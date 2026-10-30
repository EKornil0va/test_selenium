package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CsvReader;

public class LoginFeatureTests extends BaseTest {
    private static final String BASE_URL = "http://localhost:8080";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return CsvReader.getData("login_data.csv");
        };

    @Test(dataProvider = "loginData", description = "Проверка сценариев авторизации")
    public void testLoginScenarios(String username, String password, boolean shouldSucceed, String testName) {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        if (shouldSucceed) {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("oauth")));

            String currentUrl = getDriver().getCurrentUrl();
            String pageTitle = getDriver().getTitle();
            System.out.println("URL после попытки входа: " + currentUrl);
            System.out.println("Title страницы: " + pageTitle);

            boolean isNotLoginPage = !currentUrl.contains("login") && !currentUrl.contains("oauth");
            Assert.assertTrue(isNotLoginPage, "Успешный вход не произошел. URL: " + currentUrl);
            System.out.println(testName + " - пройден");
        } else {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Неверные данные не вызвали ошибку. " + testName);
            System.out.println(testName + " - пройден");
        }
    }
}
