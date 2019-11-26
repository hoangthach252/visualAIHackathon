package com.applitools.traditionalTests;

import com.applitools.CommonData;
import com.applitools.pages.HomePage;
import com.applitools.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TraditionalTests {

    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test(testName = "TC1: Verify that the login page is displayed correctly", priority = 1)
    public void loginPageUIElements() throws InterruptedException {
        Reporter.log("TC1 Verify that the login page is displayed correctly", true);
        // Open the app V1
        driver.get(CommonData.APP_V1_URL);
        LoginPage loginPage = new LoginPage(driver);

        // Assert that the index logo is displayed
        assertThat(loginPage.isIndexLogoDisplayed()).isTrue();

        // Assert the Login Form Header is correct
        assertThat(loginPage.getAuthHeaderText()).isEqualTo("Login Form");

        // Assert the Login Username Label is correct
        assertThat(loginPage.getUserNameLabel()).isEqualTo("Username");

        // Assert the Login Username PlaceHolder Text is correct
        assertThat(loginPage.getUserNamePlaceHolder()).isEqualTo("Enter your username");

        // Assert the Login Password Label is correct
        assertThat(loginPage.getPasswordLabel()).isEqualTo("Password");

        // Assert the Login Username PlaceHolder Text is correct
        assertThat(loginPage.getPasswordPlaceHolder()).isEqualTo("Enter your password");

        // Assert that the username icon is displayed
        assertThat(loginPage.isUsernameIconDisplayed()).isTrue();

        // Assert that the password icon is displayed
        assertThat(loginPage.isPasswordIconDisplayed()).isTrue();

        // Assert that the Login button is displayed
        assertThat(loginPage.isLoginBtnDisplayed()).isTrue();

        // Assert the Login button Text is correct
        assertThat(loginPage.getLoginBtnText()).isEqualTo("Log In");

        // Assert the Remember Me check box is existed
        assertThat(loginPage.isRememberMeCheckboxExisted()).isTrue();

        // Assert the Remember Me check box text is correct
        assertThat(loginPage.getRememberMeText()).isEqualTo("Remember Me");

        // Assert the Twitter, Facebook and LinkedIn icons are displayed and Clickable
        assertThat(loginPage.isTwitterIconClickable()).isTrue();
        assertThat(loginPage.isFacebookIconClickable()).isTrue();
        assertThat(loginPage.isLinkedInIconClickable()).isTrue();
    }


    @Test(testName = "TC2.1: If you don’t enter the username and password and click the login button, it should throw an error", priority = 2)
    public void dataDrivenTest1() throws InterruptedException {
        Reporter.log("TC2.1: If you don’t enter the username and password and click the login button, it should throw an error", true);
        // Open the app V1
        driver.get(CommonData.APP_V1_URL);
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginBtn();

        // Assert that the error message is displayed
        assertThat(loginPage.getLoginErrorMsg()).isEqualTo("Both Username and Password must be present");

        // Assert that the error message background color
        assertThat(loginPage.getLoginErrorMsgBackgroundColor()).isEqualTo("rgba(252, 237, 190, 1)");
    }

    @Test(testName = "TC2.2: If you only enter the username and click the login button, it should throw an error", priority = 3)
    public void dataDrivenTest2() throws InterruptedException {
        Reporter.log("TC2.2: If you only enter the username and click the login button, it should throw an error", true);
        // Open the app V1
        driver.get(CommonData.APP_V1_URL);
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUserName("thachhoang");
        loginPage.clickLoginBtn();

        // Assert that the error message is displayed
        assertThat(loginPage.getLoginErrorMsg()).isEqualTo("Password must be present");

        // Assert that the error message background color
        assertThat(loginPage.getLoginErrorMsgBackgroundColor()).isEqualTo("rgba(252, 237, 190, 1)");
    }

    @Test(testName = "TC2.3: If you only enter the password and click the login button, it should throw an error", priority = 4)
    public void dataDrivenTest3() throws InterruptedException {
        Reporter.log("TC2.3: If you only enter the password and click the login button, it should throw an error", true);
        // Open the app V1
        driver.get(CommonData.APP_V1_URL);
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPassword("anyPassword");
        loginPage.clickLoginBtn();

        // Assert that the error message is displayed
        assertThat(loginPage.getLoginErrorMsg()).isEqualTo("Username must be present");

        // Assert that the error message background color
        assertThat(loginPage.getLoginErrorMsgBackgroundColor()).isEqualTo("rgba(252, 237, 190, 1)");
    }

    @Test(testName = "TC2.4: If you enter both username (any value) and password (any value), it should log you in", priority = 5)
    public void dataDrivenTest4() throws InterruptedException {
        Reporter.log("TC2.4: If you enter both username (any value) and password (any value), it should log you in", true);
        // Open the app V1
        driver.get(CommonData.APP_V1_URL);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginCredential("thachhoang", "anyPassword");
        homePage.waitTransactionsTableLoaded();

        // Assert that the user is logged in and see the user profile
        assertThat(homePage.isUserProfileDisplayed()).isTrue();
    }


    @Test(testName = "TC3: logged in and view the Recent Transactions table", priority = 6)
    public void tableSortTest() throws InterruptedException {
        Reporter.log("TC3: logged in and view the Recent Transactions table", true);
        // Open the app V1
        driver.get(CommonData.APP_V1_URL);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginCredential("thachhoang", "anyPassword");
        homePage.waitTransactionsTableLoaded();

        homePage.clickSortingAmount();

        // Assert that the amount list on UI is sorted correctly.
        List<Float> actualAmountList = homePage.getTransactionsAmountList();
        List<Float> copiedAmountList = new ArrayList<>(actualAmountList);
        Collections.sort(copiedAmountList);
        assertThat(actualAmountList).isEqualTo(copiedAmountList);
    }


    @Test(testName = "TC4: Canvas Chart Test: Validating Compare Expenses bar chart", priority = 7)
    public void canvasChartTest() throws InterruptedException {
        Reporter.log("TC4: Canvas Chart Test: Validating Compare Expenses bar chart", true);
        // Open the app V1
        driver.get(CommonData.APP_V1_URL);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginCredential("thachhoang", "anypassword");
        homePage.clickCompareExpenses();
        Thread.sleep(500); // Waiting for bar chart render animation

        /* Assert that correct bar chart is updated with new Data
            ####Selenium can not access the internal bars of the chart.#####
            ####Thus, I only check the whole chart is appeared.#############
        */
        assertThat(homePage.isExpensesBarChartDisplayed()).isTrue();

        // Click show data for next year
        homePage.clickShowDataForNextYear();
        Thread.sleep(500); // Waiting for bar chart render animation

        /* Assert that correct bar chart is updated with new Data
            ####Selenium can not access the internal bars of the chart.#####
            ####Thus, I only check the whole chart is appeared.#############
        */
        assertThat(homePage.isExpensesBarChartDisplayed()).isTrue();
    }


    @Test(testName = "TC5: Dynamic Content Test: Validate Flash sale gifs", priority = 8)
    public void dynamicContentTest() throws InterruptedException {
        Reporter.log("TC5: Dynamic Content Test: Validate Flash sale gifs", true);
        // Open the app V1
        driver.get(CommonData.APP_V1_DYNAMIC_CONTENT_URL);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginCredential("thachhoang", "anypassword");
        homePage.waitTransactionsTableLoaded();

        // Assert that two Flash sale Gifs are displayed
        assertThat(homePage.isTwoFlashSaleGifsDisplayed()).isTrue();
    }

    @AfterTest
    public void afterTest() {
        // Close all windows of the browser.
        driver.quit();
    }
}

