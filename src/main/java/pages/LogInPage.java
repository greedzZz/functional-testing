package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverHandler;

public class LogInPage extends Page {
    private final String GOOGLE_BUTTON = "//span[contains(text(), 'Google')][1]";
    private final String INPUT_EMAIL = "//input[@type='email']";
    private final String INPUT_PASSWORD = "//input[@type='password']";
    private final String LOG_IN_BUTTON = "//input[@name='submit-to-login']";
    private final String INCORRECT_MESSAGE = "//span[@class='login-error password-error']";

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public void logInWithGoogle() {
        DriverHandler.waitUntilPageLoads(driver, 60);
        DriverHandler.waitUntilPresenceOfElement(driver, 60, GOOGLE_BUTTON);
        driver.findElement(By.xpath(GOOGLE_BUTTON)).click();
        DriverHandler.waitUntilPageLoads(driver, 60);
    }

    public MainPage correctLogin(String email, String password) {
        DriverHandler.waitUntilPageLoads(driver, 60);
        WebElement element = driver.findElement(By.xpath(INPUT_EMAIL));
        element.click();
        element.sendKeys(email);
        element = driver.findElement(By.xpath(INPUT_PASSWORD));
        element.click();
        element.sendKeys(password);
        driver.findElement(By.xpath(LOG_IN_BUTTON)).click();
        DriverHandler.waitUntilPageLoads(driver, 60);
        return new MainPage(driver);
    }

    public String incorrectLogin(String email, String password) {
        DriverHandler.waitUntilPageLoads(driver, 60);
        DriverHandler.waitUntilPresenceOfElement(driver, 60, INPUT_EMAIL);
        WebElement element = driver.findElement(By.xpath(INPUT_EMAIL));
        element.click();
        element.sendKeys(email);
        element = driver.findElement(By.xpath(INPUT_PASSWORD));
        element.click();
        element.sendKeys(password);
        driver.findElement(By.xpath(LOG_IN_BUTTON)).click();
        DriverHandler.waitUntilVisibilityOfElement(driver, 60, INCORRECT_MESSAGE);
        return driver.findElement(By.xpath(INCORRECT_MESSAGE)).getText();
    }
}
