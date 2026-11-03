package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    private By dashboardNameLocator = By.cssSelector("[data-test='breadcrumbs-container'] [data-test='dashboard-name']");
    private By addWidgetButtonLocator = By.cssSelector("[data-test='add-widget-button']");
    private By newDashboardButtonLocator = By.cssSelector("[data-test='new-dashboard-button']");
    private By widgetContainerLocator = By.cssSelector("[data-test='grid-item']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean isPageLoaded(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardNameLocator));
        return true;
    }

    public String getDashboardName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardNameLocator)).getText();
    }

    public boolean isAddWidgetButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addWidgetButtonLocator));
        return true;
    }

    public boolean isNewDashboardButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(newDashboardButtonLocator));
        return true;
    }

    public boolean isWidgetPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(widgetContainerLocator));
        return true;
    }
}