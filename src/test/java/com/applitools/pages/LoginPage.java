package com.applitools.pages;

import com.applitools.Locators;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isIndexLogoDisplayed() {
        return isElementDisplayed(Locators.INDEX_LOGO);
    }

    public String getAuthHeaderText() {
        return getText(Locators.AUTH_HEADER).trim();
    }

    public String getUserNameLabel() {
        return getText(Locators.USERNAME_LABEL);
    }

    public String getUserNamePlaceHolder() {
        return getAttribute(Locators.USERNAME_INPUT, "placeholder");
    }

    public String getPasswordLabel() {
        return getText(Locators.PASSWORD_LABEL);
    }

    public String getPasswordPlaceHolder() {
        return getAttribute(Locators.PASSWORD_INPUT, "placeholder");
    }

    public boolean isUsernameIconDisplayed() {
        return isElementDisplayed(Locators.USERNAME_ICON);
    }

    public boolean isPasswordIconDisplayed() {
        return isElementDisplayed(Locators.PASSWORD_ICON);
    }

    public boolean isLoginBtnDisplayed() {
        return isElementDisplayed(Locators.LOGIN_BTN);
    }

    public String getLoginBtnText() {
        return getText(Locators.LOGIN_BTN);
    }

    public boolean isRememberMeCheckboxExisted() {
        return isElementClickable(Locators.REMEMBER_ME_CHECKBOX);
    }

    public String getRememberMeText() {
        return getText(Locators.REMEMBER_ME_TEXT);
    }

    public boolean isTwitterIconDisplayed() {
        return isElementDisplayed(Locators.TWITTER_ICON);
    }

    public boolean isFacebookIconDisplayed() {
        return isElementDisplayed(Locators.FACEBOOK_ICON);
    }

    public boolean isLinkedInIconDisplayed() {
        return isElementDisplayed(Locators.LINKEDIN_ICON);
    }

    public void enterUserName(String username) {
        setValue(Locators.USERNAME_INPUT, username);
    }

    public void enterPassword(String pwd) {
        setValue(Locators.PASSWORD_INPUT, pwd);
    }

    public void clickLoginBtn() {
        clickElement(Locators.LOGIN_BTN);
    }

    public void loginCredential(String username, String pwd) {
        setValue(Locators.USERNAME_INPUT, username);
        setValue(Locators.PASSWORD_INPUT, pwd);
        clickLoginBtn();
    }

    public String getLoginErrorMsg() {
        return getText(Locators.ERROR_MSG_TEXT);
    }

    public String getLoginErrorMsgBackgroundColor() {
        return getCssValue(Locators.ERROR_MSG_TEXT, "background-color");
    }
}
