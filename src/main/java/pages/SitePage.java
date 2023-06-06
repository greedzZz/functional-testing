package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverHandler;

public class SitePage extends Page {
    private final String FIRST_JANUARY = "//div[text()='JAN']//following-sibling::div//a[text()=1]";
    private final String SNAPSHOT = "//ul[@class='day-tooltip-shapshot-list']//li[1]//a[1]";

    public SitePage(WebDriver driver) {
        super(driver);
    }

    public void goToSnapshot() {
        DriverHandler.waitUntilPageLoads(driver, 60);
        DriverHandler.waitUntilPresenceOfElement(driver, 60, FIRST_JANUARY);
        driver.findElement(By.xpath(FIRST_JANUARY)).click();
        DriverHandler.waitUntilPresenceOfElement(driver, 60, SNAPSHOT);
        driver.findElement(By.xpath(SNAPSHOT)).click();
        DriverHandler.waitUntilPageLoads(driver, 60);
    }

}
