package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Browser {

    public static WebDriver driver;
    private static final int TIMEOUT = 10;

    /**
     * Sets up Firefox WebDriver with WebDriverManager (Bonigarcia)
     */
    public static void setup() {
        WebDriverManager.firefoxdriver().clearResolutionCache(); // Clear old cached drivers
        WebDriverManager.firefoxdriver().setup(); // Automatically downloads and sets GeckoDriver

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized"); // Ensures browser is maximized

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    /**
     * Waits for an element to be visible.
     */
    public static WebElement waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable.
     */
    public static WebElement waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for an element to be present in the DOM.
     */
    public static WebElement waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Reusable method to click on any element.
     */
    public static void clickElement(By locator) {
        try {
            WebElement element = waitForClickable(locator);
            element.click();
            System.out.println("Clicked element: " + locator);
        } catch (Exception e) {
            System.out.println("Error clicking element: " + locator);
            e.printStackTrace();
        }
    }

    /**
     * Quits the browser session
     */
    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}