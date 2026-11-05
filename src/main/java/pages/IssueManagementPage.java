package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IssueManagementPage extends BasePage {
    public IssueManagementPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean isPageLoaded(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title']")));
        return true;
    }

    public boolean isSearchFieldPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@placeholder, 'Search') or contains(@placeholder, 'Поиск') or contains(@placeholder, 'filter')]")));
        return true;
    }
}
