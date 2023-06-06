import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utils.DriverHandler;
import utils.PropsHandler;

import java.util.List;

public class MainPageTest {
    @BeforeAll
    public static void prepareDrivers() {
        DriverHandler.prepareSystemProps();
    }

    @Test
    void findSiteByWord() {
        List<WebDriver> drivers = DriverHandler.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.loadSite("https://web.archive.org/");
            mainPage.findSiteByWord(PropsHandler.get("site.word"));
            Assertions.assertTrue(mainPage.getFirstSiteLink().contains(PropsHandler.get("first.site.link")));
            driver.quit();
        });
    }

    @Test
    void findCollection() {
        List<WebDriver> drivers = DriverHandler.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.loadSite("https://web.archive.org/");
            mainPage.findCollection(PropsHandler.get("collection.keyword"));
            Assertions.assertTrue(mainPage.getCollectionContentText().toLowerCase().contains(PropsHandler.get("collection.keyword")));
            driver.quit();
        });
    }

    @Test
    void savePage() {
        List<WebDriver> drivers = DriverHandler.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.loadSite("https://web.archive.org/");
            mainPage.savePage(PropsHandler.get("save.page.url"));
            Assertions.assertTrue(mainPage.getDoneIcon().contains(PropsHandler.get("done.icon.text")));
            driver.quit();
        });
    }

    @Test
    void changeSection() {
        List<WebDriver> drivers = DriverHandler.getDrivers();
        drivers.parallelStream().forEach(driver -> {
            MainPage mainPage = new MainPage(driver);
            mainPage.loadSite("https://web.archive.org/");
            mainPage.goToBooksSection();
            Assertions.assertTrue(mainPage.getBooksHeader().contains(PropsHandler.get("book.section.header")));
            driver.quit();
        });
    }
}
