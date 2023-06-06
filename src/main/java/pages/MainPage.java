package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverHandler;

public class MainPage extends Page {

    private final String SITE_INPUT = "//input[@placeholder='Enter a URL or words related to a site’s home page']";
    private final String COLLECTION_INPUT = "//input[@placeholder='Enter any keyword']";
    private final String COLLECTION_BUTTON = "//button[text()='SEARCH']";
    private final String SAVE_INPUT = "//input[@class='web-save-url-input web_input web_text'][@placeholder='https://']";
    private final String SAVE_BUTTON_FIRST = "//button[text()='SAVE PAGE']";
    private final String SAVE_BUTTON_SECOND = "//input[@value='SAVE PAGE']";
    private final String LOG_IN_BUTTON = "//a[@class='style-scope login-button'][text()='Log in']";
    private final String BOOKS_ICON = "//a[@title='Expand texts menu']";
    private final String BOOKS_LINK = "//a[@data-event-click-tracking='TopNav|AllBooksTexts']";
    private final String BOOKS_HEADER = "//div[@class='collection-titles']//h1";
    private final String FIRST_SITE_LINK = "//li[@class='result-item'][1]//div[@class='result-item-heading'][1]//a[1]";
    private final String COLLECTION_CONTENT_TEXT = "//div[@class='snippet']//span";
    private final String DONE_ICON = "//span[contains(text(),' Done!')]";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void findSiteByWord(String word) {
        DriverHandler.waitUntilPageLoads(driver, 60);
        WebElement element = driver.findElement(By.xpath(SITE_INPUT));
        element.click();
        element.sendKeys(word);
        element.sendKeys(Keys.ENTER);
    }

    public String getFirstSiteLink() {
        DriverHandler.waitUntilPageLoads(driver, 60);
        DriverHandler.waitUntilPresenceOfElement(driver, 60, FIRST_SITE_LINK);
        WebElement element = driver.findElement(By.xpath(FIRST_SITE_LINK));
        return element.getAttribute("href");
    }

    public void findCollection(String word) {
        DriverHandler.waitUntilPageLoads(driver, 60);
        WebElement element = driver.findElement(By.xpath(COLLECTION_INPUT));
        element.click();
        element.sendKeys(word);
        driver.findElement(By.xpath(COLLECTION_BUTTON)).click();
    }

    public String getCollectionContentText() {
        DriverHandler.waitUntilPageLoads(driver, 60);
        DriverHandler.waitUntilPresenceOfElement(driver, 60, COLLECTION_CONTENT_TEXT);
        WebElement element = driver.findElement(By.xpath(COLLECTION_CONTENT_TEXT));
        return element.getText();
    }

    public void savePage(String url) {
        DriverHandler.waitUntilPageLoads(driver, 60);
        WebElement element = driver.findElement(By.xpath(SAVE_INPUT));
        element.click();
        element.sendKeys(url);
        driver.findElement(By.xpath(SAVE_BUTTON_FIRST)).click();
        DriverHandler.waitUntilPageLoads(driver, 60);
        driver.findElement(By.xpath(SAVE_BUTTON_SECOND)).click();
    }

    public String getDoneIcon() {
        DriverHandler.waitUntilPageLoads(driver, 60);
        DriverHandler.waitUntilPresenceOfElement(driver, 60, DONE_ICON);
        WebElement element = driver.findElement(By.xpath(DONE_ICON));
        return element.getText();
    }

    public void goToBooksSection() {
        DriverHandler.waitUntilPageLoads(driver, 60);
        driver.findElement(By.xpath(BOOKS_ICON)).click();
        DriverHandler.waitImplicitly(driver, 1);
        driver.findElement(By.xpath(BOOKS_LINK)).click();
    }

    public String getBooksHeader() {
        DriverHandler.waitUntilPageLoads(driver, 60);
        WebElement element = driver.findElement(By.xpath(BOOKS_HEADER));
        return element.getText();
    }

    public SitePage findSiteByUrl(String url) {
        DriverHandler.waitUntilPageLoads(driver, 60);
        WebElement element = driver.findElement(By.xpath(SITE_INPUT));
        element.click();
        element.sendKeys(url);
        element.sendKeys(Keys.ENTER);
        DriverHandler.waitUntilPageLoads(driver, 60);
        return new SitePage(driver);
    }

    public LogInPage logIn() {
        DriverHandler.waitUntilPageLoads(driver, 60);
        WebElement element = driver.findElement(By.xpath(LOG_IN_BUTTON));
        element.click();
        DriverHandler.waitUntilPageLoads(driver, 60);
        return new LogInPage(driver);
    }
}
