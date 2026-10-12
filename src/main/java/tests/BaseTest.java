package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    static String BASE_URL = "http://localhost:8080";
    static String TASK_BOARD = "http://localhost:8080/issues";
    static String PASSWORD = "admin";
    static String LOGIN = "admin";
    static String WRONG_PASSWORD = "wrongPassword";
    static String FAKE_PASSWORD = "fakeUser";
    static String FAKE_LOGIN = "fakePass";


    @BeforeMethod
    public void setUp() {
        System.out.println("Начало инициализации драйвера в потоке: " + Thread.currentThread().getId());
        try {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");

            WebDriver webDriver = new ChromeDriver(options);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.set(webDriver);
            System.out.println("Драйвер успешно инициализирован.");

        } catch (Exception e) {
            System.err.println("Ошибка при создании драйвера: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Не удалось инициализировать WebDriver", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Закрытие драйвера в потоке: " + Thread.currentThread().getId());
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            System.out.println("Драйвер закрыт.");
        }
    }

    public WebDriver getDriver() {
        WebDriver wd = driver.get();
        if (wd == null) {
            System.err.println("getDriver() вернул null! Проверьте логи выше, @BeforeMethod явно упал.");
        }
        return wd;
    }
}