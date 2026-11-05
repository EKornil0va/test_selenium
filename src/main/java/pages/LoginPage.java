package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-test='username-field']")));
        userField.clear();
        userField.sendKeys(username);

        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-test='password-field']")));
        passField.clear();
        passField.sendKeys(password);

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-test='login-button']")));
        btn.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-test='error-message']")));
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}