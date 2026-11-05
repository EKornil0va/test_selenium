package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@data-test='username-field']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@data-test='password-field']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@data-test='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@data-test='error-message']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        enterUsername(username).enterPassword(password).clickLogin();
    }

    public WebElement getUsernameField() {
        return wait.until(ExpectedConditions.visibilityOf(usernameField));
    }

    public WebElement getPasswordField() {
        return wait.until(ExpectedConditions.visibilityOf(passwordField));
    }

    public WebElement getLoginButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    public WebElement getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public LoginPage enterUsername(String username) {
        getUsernameField().clear();
        getUsernameField().sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        getPasswordField().clear();
        getPasswordField().sendKeys(password);
        return this;
    }

    public void clickLogin() {
        getLoginButton().click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return getErrorMessage().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}