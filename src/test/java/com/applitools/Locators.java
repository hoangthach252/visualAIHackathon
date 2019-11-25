package com.applitools;

import org.openqa.selenium.By;

public class Locators {
    // LOGIN PAGE LOCATORS
    public static By INDEX_LOGO = By.cssSelector(".logo-w a img");
    public static By AUTH_HEADER = By.cssSelector(".auth-header");
    public static By USERNAME_INPUT = By.xpath("//input[@id='username']");
    public static By USERNAME_LABEL = By.xpath("//input[@id='username']/../label");
    public static By USERNAME_ICON = By.xpath("//input[@id='username']/../div");
    public static By PASSWORD_INPUT = By.xpath("//input[@id='password']");
    public static By PASSWORD_LABEL = By.xpath("//input[@id='password']/../label");
    public static By PASSWORD_ICON = By.xpath("//input[@id='password']/../div");
    public static By LOGIN_BTN = By.cssSelector("button#log-in");
    public static By REMEMBER_ME_CHECKBOX = By.cssSelector("input.form-check-input");
    public static By REMEMBER_ME_TEXT = By.cssSelector("label.form-check-label");
    public static By TWITTER_ICON = By.cssSelector("a img[src*='twitter']");
    public static By FACEBOOK_ICON = By.cssSelector("a img[src*='facebook']");
    public static By LINKEDIN_ICON = By.cssSelector("a img[src*='linkedin']");
    public static By ERROR_MSG_TEXT = By.cssSelector("div.alert-warning");

    // HOME PAGE LOCATORS
    public static By USER_PROFILE_INFO = By.cssSelector(".logged-user-w.avatar-inline");
    public static By TRANSACTIONS_TABLE = By.id("transactionsTable");
    public static By SORTING_AMOUNT = By.cssSelector("#transactionsTable #amount");
    public static By TRANSACTIONS_AMOUNTS = By.cssSelector("#transactionsTable td.text-right span");
    public static By COMPARE_EXPENSES_LINK = By.id("showExpensesChart");
    public static By EXPENSES_BAR_CHART = By.id("canvas");
    public static By SHOW_DATA_NEXT_YEAR_LINK = By.id("addDataset");
    public static By FLASH_SALE_IMG1 = By.cssSelector(".element-balances #flashSale img");
    public static By FLASH_SALE_IMG2 = By.cssSelector(".element-balances #flashSale2 img");

}
