package com.applitools.pages;

import com.applitools.Locators;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserProfileDisplayed() {
        return isElementDisplayed(Locators.USER_PROFILE_INFO);
    }

    public void waitTransactionsTableLoaded() {
        waitElementToBeAppear(Locators.TRANSACTIONS_TABLE);
    }

    public void clickSortingAmount() {
        clickElement(Locators.SORTING_AMOUNT);
    }

    public List<Float> getTransactionsAmountList() {
        List<Float> result = new ArrayList<>();
        List<String> amounts = getTexts(Locators.TRANSACTIONS_AMOUNTS);
        for (String amount:amounts) {
            String amountStr = amount.split(" USD")[0].replaceAll("[\\s,]", "");
            result.add(Float.parseFloat(amountStr));
        }
        return result;
    }

    public void clickCompareExpenses() {
        clickElement(Locators.COMPARE_EXPENSES_LINK);
    }

    public boolean isExpensesBarChartDisplayed() {
        return isElementDisplayed(Locators.EXPENSES_BAR_CHART);
    }

    public void clickShowDataForNextYear() {
        clickElement(Locators.SHOW_DATA_NEXT_YEAR_LINK);
    }

    public boolean isTwoFlashSaleGifsDisplayed() {
        return isElementDisplayed(Locators.FLASH_SALE_IMG1) && isElementDisplayed(Locators.FLASH_SALE_IMG2);
    }

}
