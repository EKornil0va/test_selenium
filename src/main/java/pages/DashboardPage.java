package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//div[@data-test='breadcrumbs-container']//span[@data-test='dashboard-name']")
    private WebElement dashboardName;

    @FindBy(xpath = "//button[@data-test='new-dashboard-button']")
    private WebElement newDashboardButton;

    @FindBy(xpath = "//button[@data-test='add-widget-button']")
    private WebElement addWidgetButton;

    @FindBy(xpath = "//div[@data-test='grid-item']")
    private WebElement gridItem;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getDashboardName() {
        return wait.until(ExpectedConditions.visibilityOf(dashboardName)).getText();
    }

    public WebElement getAddWidgetButton() {
        return wait.until(ExpectedConditions.visibilityOf(addWidgetButton));
    }

    public WebElement getGridItem() {
        return wait.until(ExpectedConditions.visibilityOf(gridItem));
    }

    public boolean isPageLoaded(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        return getDashboardName() != null;
    }

    public boolean isAddWidgetButtonDisplayed() {
        return getAddWidgetButton().isDisplayed();
    }

    public boolean isWidgetPresent() {
        return getGridItem().isDisplayed();
    }
}