package pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import utils.DriverHandler;
import utils.PropsHandler;

@Data
public abstract class Page {

    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void loadSite(String resourceLocator) {
        driver.get(resourceLocator);
        driver.manage().window().maximize();
        DriverHandler.waitUntilPageLoads(driver, 60);
    }

}
