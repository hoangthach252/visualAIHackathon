package com.applitools.pages;

import com.applitools.CommonData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {
    private WebDriver driver;
    
    public BasePage (WebDriver driver) {
        this.driver = driver;
    }

    protected void clickElement(By locator) {
        clickElement(locator, CommonData.DEFAULT_TIMEOUT);
    }

    protected void clickElement(By locator, long timeout){
        WebElement elm = waitElementToBeClickable(locator, timeout);
        elm.click();
    }

    protected String getText(By locator) {
        WebElement elm = waitElementToBeAppear(locator);
        return elm.getText();
    }

    protected List<String> getTexts(By locator) {
        List<String> result = new ArrayList<>();
        List<WebElement> elms = waitElementsToBeAppear(locator);
        for (WebElement elm : elms) {
            result.add(elm.getText());
        }
        return result;
    }

    protected void setValue(By locator, String value) {
        setValue(locator, value, CommonData.DEFAULT_TIMEOUT);
    }

    protected void setValue(By locator, String value, long timeout) {
        WebElement elm = waitElementToBeClickable(locator, timeout);
        elm.clear();
        elm.sendKeys(value);
    }

    protected String getAttribute(By locator, String cssAtr) {
        WebElement elm = waitElementToBeExist(locator);
        return elm.getAttribute(cssAtr);
    }

    protected String getCssValue(By locator, String cssVal) {
        WebElement elm = waitElementToBeExist(locator);
        return elm.getCssValue(cssVal);
    }

    protected WebElement waitElementToBeClickable(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitElementToBeAppear(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitElementToBeAppear(By locator) {
        return waitElementToBeAppear(locator, CommonData.DEFAULT_TIMEOUT);
    }

    protected List<WebElement> waitElementsToBeAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, CommonData.DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected WebElement waitElementToBeExist(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitElementToBeExist(By locator) {
        return waitElementToBeExist(locator, CommonData.DEFAULT_TIMEOUT);
    }

    protected boolean isElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, CommonData.CONDITION_TIMEOUT);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, CommonData.CONDITION_TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
