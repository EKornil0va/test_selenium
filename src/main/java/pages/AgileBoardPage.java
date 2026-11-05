package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AgileBoardPage extends BasePage {
    public AgileBoardPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean isPageLoaded(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='yt-agile-board-toolbar']")));
        return true;
    }

    public boolean isBoardTableDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".yt-agile-table")));
        return true;
    }

    public boolean isSearchFieldPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test='ring-query-assist-input']")));
        return true;
    }

    public boolean isBacklogButtonPresent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='backlogToggler']")));
        return true;
    }
}