import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LogInPage;
import pages.MainPage;
import utils.DriverHandler;
import utils.PropsHandler;

import java.util.ArrayList;
import java.util.List;

public class LogInPageTest {
    @BeforeAll
    public static void prepareDrivers() {
        DriverHandler.prepareSystemProps();
    }

    @Test
    void logInWithGoogle() {
        List<WebDriver> drivers = DriverHandler.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.loadSite("https://web.archive.org/");
            LogInPage logInPage = mainPage.logIn();
            logInPage.logInWithGoogle();
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            DriverHandler.waitUntilPageLoads(driver, 60);
            Assertions.assertTrue(driver.getCurrentUrl().contains(PropsHandler.get("google")));
            driver.quit();
        });
    }

    @Test
    void correctLogin() {
        List<WebDriver> drivers = DriverHandler.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.loadSite("https://web.archive.org/");
            LogInPage logInPage = mainPage.logIn();
            mainPage = logInPage.correctLogin(PropsHandler.get("correct.email"), PropsHandler.get("correct.password"));
            DriverHandler.waitUntilUrlToBe(driver, 10, PropsHandler.get("main.url"));
            Assertions.assertTrue(driver.getCurrentUrl().endsWith(PropsHandler.get("main.url")));
            driver.quit();
        });
    }

    @Test
    void incorrectLogin() {
        List<WebDriver> drivers = DriverHandler.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.loadSite("https://web.archive.org/");
            LogInPage logInPage = mainPage.logIn();
            String result = logInPage.incorrectLogin(PropsHandler.get("incorrect.email"), PropsHandler.get("correct.password"));
            Assertions.assertTrue(result.contains(PropsHandler.get("incorrect.data.message")));
            driver.quit();
        });
    }
}
