package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean isPageLoaded(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='breadcrumbs-container'] [data-test='dashboard-name']")));
        return true;
    }

    public String getDashboardName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='breadcrumbs-container'] [data-test='dashboard-name']"))).getText();
    }

    public boolean isAddWidgetButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='add-widget-button']")));
        return true;
    }

    public boolean isWidgetPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test='grid-item']")));
        return true;
    }
}