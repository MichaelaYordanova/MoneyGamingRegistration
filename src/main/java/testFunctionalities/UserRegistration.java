package testFunctionalities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Browser;

public class UserRegistration {

    private static final WebDriver driver = Browser.driver; // Use WebDriver from Browser class

    private static final By JOIN_NOW_INITIAL_SCREEN = By.xpath("//a[@class='newUser green']");
    private static final By TITLE_DROPDOWN = By.id("title");
    private static final By FIRSTNAME_FIELD = By.id("forename");
    private static final By SURNAME_FIELD = By.xpath("//input[@name='map(lastName)']");
    private static final By TERMS_AND_CONDITIONS_CHECKBOX = By.xpath("//input[contains(@name, 'terms')]");
    private static final By JOIN_NOW_BUTTON = By.id("form");
    private static final By ERROR_MESSAGE = By.xpath("//label[@class='error' and @for='countryCodeMobile' and contains(text(), 'This field is required')]");

    /**
     * Opens the MoneyGaming sign-up page
     */
    public static void openMoneyGaming() {
        driver.get("https://moneygaming.qa.gameaccount.com");
    }

    /**
     * Selects a title from the dropdown (e.g., "Mrs")
     */
    public static void selectTitle(String title) {
        WebElement titleDropdown = Browser.waitForClickable(TITLE_DROPDOWN);
        titleDropdown.click(); // Open dropdown
        Browser.waitForVisibility(By.xpath("//select[@id='title']/option[text()='" + title + "']")).click();
    }

    /**
     * Clicks the "Join Now!" button
     */
    public static void clickJoinNowButton() {
        Browser.clickElement(JOIN_NOW_BUTTON);
    }

    /**
     * Scrolls to the bottom of the page
     */
    public static void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Accepts the Terms & Conditions checkbox
     */
    public static void acceptTermsAndConditions() {
        WebElement checkbox = Browser.waitForClickable(TERMS_AND_CONDITIONS_CHECKBOX);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
        checkbox.click();
    }

    /**
     * Registers a new user
     */
    public static void registration(String title, String firstName, String surname) {
        Browser.clickElement(JOIN_NOW_INITIAL_SCREEN);
        selectTitle(title);
        Browser.waitForVisibility(FIRSTNAME_FIELD).sendKeys(firstName);
        Browser.waitForVisibility(SURNAME_FIELD).sendKeys(surname);
        scrollToBottom();
        acceptTermsAndConditions();
        clickJoinNowButton();
    }

    /**
     * Verifies if the "This field is required" message is displayed
     */
    public static void verifyTheFieldIsRequired(String expected, String errorMessage) {
        WebElement errorElement = Browser.waitForVisibility(ERROR_MESSAGE);
        if (!errorElement.getText().trim().equals(expected)) {
            throw new AssertionError(errorMessage + " Expected: '" + expected + "' but found: '" + errorElement.getText() + "'");
        }
    }
    // Testing CI/CD setup

}