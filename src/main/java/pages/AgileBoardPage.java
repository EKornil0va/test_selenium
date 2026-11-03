package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AgileBoardPage extends BasePage {
    private By boardToolbar = By.cssSelector("[data-test='yt-agile-board-toolbar']");
    private By boardTable = By.cssSelector(".yt-agile-table");
    private By searchField = By.cssSelector("[data-test='ring-query-assist-input']");
    private By backlogButton = By.cssSelector("[data-test='backlogToggler']");

    public AgileBoardPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean isPageLoaded(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        wait.until(ExpectedConditions.visibilityOfElementLocated(boardToolbar));
        return true;
    }

    public boolean isBoardTableDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(boardTable));
        return true;
    }

    public boolean isSearchFieldPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchField));
        return true;
    }

    public boolean isBacklogButtonPresent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(backlogButton));
        return true;
    }
}