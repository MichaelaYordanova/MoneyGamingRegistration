package core;

import org.openqa.selenium.By;

import static utils.Browser.driver;

public class CommonBaseActions {

    protected static void type(By elementLocator, String whatToType) {
        driver.findElement(elementLocator).sendKeys(whatToType);
    }

    /**
     * Gets current URL address
     *
     * @return the current URL address
     */
    protected static String getURL() {
        return driver.getCurrentUrl();
    }

    public static void openMoneyGaming() {
        driver.get("https://moneygaming.qa.gameaccount.com/");
    }

    /**
     * Clicks on element based on provided locator
     *
     * @param elementLocator provided locator
     */

    public static void click(By elementLocator) {
        driver.findElement(elementLocator).click();
    }


    /**
     * Gets text from element based on provided locator
     *
     * @param elementLocator provided locator
     */
    protected static String getText(By elementLocator) {
        return driver.findElement(elementLocator).getText();
    }


}

