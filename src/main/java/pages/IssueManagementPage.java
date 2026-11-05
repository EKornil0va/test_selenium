package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IssueManagementPage extends BasePage {

    @FindBy(xpath = "//span[@data-test='title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[contains(@placeholder, 'Search') or contains(@placeholder, 'Поиск') or contains(@placeholder, 'filter')]")
    private WebElement searchField;

    public IssueManagementPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public WebElement getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }

    public WebElement getSearchField() {
        return wait.until(ExpectedConditions.visibilityOf(searchField));
    }

    public boolean isPageLoaded(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        return getPageTitle().isDisplayed();
    }

    public boolean isSearchFieldPresent() {
        return getSearchField().isDisplayed();
    }
}