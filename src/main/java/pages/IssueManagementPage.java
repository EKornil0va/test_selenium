package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IssueManagementPage extends BasePage {
    private By pageTitleHeader = By.xpath("//span[@data-test='title']");
    private By searchField = By.xpath("//input[contains(@placeholder, 'Search') or contains(@placeholder, 'Поиск') or contains(@placeholder, 'filter')]");

    public IssueManagementPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean isPageLoaded(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitleHeader));
        return true;
    }

    public boolean isSearchFieldPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchField));
        return true;
    }
}
