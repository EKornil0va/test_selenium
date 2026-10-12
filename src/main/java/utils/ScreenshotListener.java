package utils;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.openqa.selenium.OutputType.FILE;

public class ScreenshotListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        try {
            WebDriver driver = (WebDriver) testInstance.getClass().getMethod("getDriver").invoke(testInstance);

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(FILE);

            Path destDir = Paths.get("target/screenshots");
            Files.createDirectories(destDir);

            Path destFile = destDir.resolve(result.getName() + ".png");
            Files.copy(srcFile.toPath(), destFile);

            System.err.println("Скриншот сохранен: " + destFile.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
