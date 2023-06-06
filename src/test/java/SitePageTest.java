import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.SitePage;
import utils.DriverHandler;
import utils.PropsHandler;

import java.util.List;

public class SitePageTest {
    @BeforeAll
    public static void prepareDrivers() {
        DriverHandler.prepareSystemProps();
    }

    @Test
    void findSnapshot() {
        List<WebDriver> drivers = DriverHandler.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.loadSite("https://web.archive.org/");
            SitePage sitePage = mainPage.findSiteByUrl(PropsHandler.get("site.url"));
            sitePage.goToSnapshot();
            Assertions.assertTrue(driver.getCurrentUrl().contains(PropsHandler.get("site.url")));
            driver.quit();
        });
    }
}
