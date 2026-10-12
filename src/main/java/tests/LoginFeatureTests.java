package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginFeatureTests extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
                {LOGIN, PASSWORD, true, "Успешный вход администратора"},
                {LOGIN, WRONG_PASSWORD, false, "Вход с неверным паролем"},
                {FAKE_LOGIN, FAKE_PASSWORD, false, "Вход с несуществующим пользователем"}
        };
    }

    @Test(dataProvider = "loginData", description = "Проверка сценариев авторизации")
    public void testLoginScenarios(String username, String password, boolean shouldSucceed, String testName) {
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        if (shouldSucceed) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            String currentUrl = getDriver().getCurrentUrl();
            String pageTitle = getDriver().getTitle();
            System.out.println("URL после попытки входа: " + currentUrl);
            System.out.println("Title страницы: " + pageTitle);

            boolean isNotLoginPage = !currentUrl.contains("login") && !currentUrl.contains("oauth");

            Assert.assertTrue(isNotLoginPage, "Успешный вход не произошел. URL: " + currentUrl);

            System.out.println("" + testName);
        } else {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Неверные данные не вызвали ошибку. Тест: " + testName);
            System.out.println("" + testName);
        }
    }

}
