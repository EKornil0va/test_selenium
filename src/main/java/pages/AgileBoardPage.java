package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AgileBoardPage extends BasePage {

    @FindBy(xpath = "//*[@data-test='yt-agile-board-toolbar']")
    private WebElement boardToolbar;

    @FindBy(xpath = "//div[contains(@class, 'yt-agile-table')]")
    private WebElement boardTable;

    @FindBy(xpath = "//*[@data-test='ring-query-assist-input']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@data-test='backlogToggler']")
    private WebElement backlogToggler;

    public AgileBoardPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public WebElement getBoardToolbar() {
        return wait.until(ExpectedConditions.visibilityOf(boardToolbar));
    }

    public WebElement getBoardTable() {
        return wait.until(ExpectedConditions.visibilityOf(boardTable));
    }

    public WebElement getSearchField() {
        return wait.until(ExpectedConditions.visibilityOf(searchField));
    }

    public WebElement getBacklogToggler() {
        return wait.until(ExpectedConditions.visibilityOf(backlogToggler));
    }

    public boolean isPageLoaded(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        return getBoardToolbar().isDisplayed();
    }

    public boolean isBoardTableDisplayed() {
        return getBoardTable().isDisplayed();
    }

    public boolean isSearchFieldPresent() {
        return getSearchField().isDisplayed();
    }

    public boolean isBacklogButtonPresent() {
        return getBacklogToggler().isDisplayed();
    }
}