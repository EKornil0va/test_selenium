package utils;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import tests.BaseTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.openqa.selenium.OutputType.FILE;

public class ScreenshotListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testInstance).getDriver();
            if (driver != null) {
                try {
                    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(FILE);
                    Path destDir = Paths.get("target/screenshots");
                    Files.createDirectories(destDir);

                    String fileName = result.getName() + "_" + System.currentTimeMillis() + ".png";
                    Path destFile = destDir.resolve(fileName);

                    Files.copy(srcFile.toPath(), destFile);
                    System.err.println("Скриншот сохранен: " + destFile.toAbsolutePath());
                } catch (Exception e) {
                    System.err.println("Не удалось сделать скриншот: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
