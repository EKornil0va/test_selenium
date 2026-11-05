package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {
    public static final String BASE_URL = ConfigReader.getProperty("base.url");
    public static final String TASK_BOARD = ConfigReader.getProperty("task.board.url");
    public static final String LOGIN = ConfigReader.getProperty("login");
    public static final String PASSWORD = ConfigReader.getProperty("password");
    public static final String AGILE_URL = ConfigReader.getProperty("agile.url");
    public static final String DASHBOARD_URL = ConfigReader.getProperty("dashboard.url");

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        try {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver webDriver = new ChromeDriver(options);
        driver.set(webDriver);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    } catch (Exception e) {
        System.err.println("Ошибка при создании драйвера: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Не удалось инициализировать WebDriver", e);
    }
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    protected void waitForLoginRedirect() {
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("oauth")));
    }

    protected void loginAsDefaultUser() {
        getDriver().get(BASE_URL);
        pages.LoginPage loginPage = new pages.LoginPage(getDriver());
        loginPage.login(LOGIN, PASSWORD);
        waitForLoginRedirect();
    }
}