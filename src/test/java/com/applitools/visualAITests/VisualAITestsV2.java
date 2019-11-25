package com.applitools.visualAITests;

import com.applitools.CommonData;
import com.applitools.Locators;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.pages.HomePage;
import com.applitools.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VisualAITestsV2 {

    private EyesRunner runner;
    private Eyes eyes;
    private static BatchInfo batchInfo;
    private WebDriver driver;
    private String viewPortHeight;
    private String viewPortWidth;

    @BeforeClass
    public static void setBatch() {
        // Must be before ALL tests (at Class-level)
        batchInfo = new BatchInfo("Hackathon Batch for Visual AI Tests");
    }

    @BeforeTest
    public void beforeTest() {
        //Initialize the Runner .
        runner = new ClassicRunner();
        // Initialize the eyes SDK
        eyes = new Eyes(runner);
        eyes.setBatch(batchInfo);
        //Check if the Applitools API key is set in the environment
        String APPLITOOLS_API_KEY = System.getenv("APPLITOOLS_API_KEY");
        if (APPLITOOLS_API_KEY == null) {
            System.out.println("\n\n**** Please set APPLITOOLS_API_KEY in your environment ***");
            System.out.println("On Mac/Linux: export APPLITOOLS_API_KEY='YOUR_API_KEY'");
            System.out.println("On Windows: set APPLITOOLS_API_KEY='YOUR_API_KEY'");
            System.exit(0);
        } else {
            //Set Eyes API key
            eyes.setApiKey(APPLITOOLS_API_KEY);
        }

        //Initialize Chrome browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Get the inner viewport from browser for setting Eyes RectangleSize.
        JavascriptExecutor je  = (JavascriptExecutor) driver;
        viewPortHeight = je.executeScript("return window.innerHeight;").toString();
        viewPortWidth = je.executeScript("return window.innerWidth;").toString();
    }


    @Test(testName = "TC1 Verify that the login page is displayed correctly", priority = 1)
    public void loginPageUIElements() {
        Reporter.log("TC1 Verify that the login page is displayed correctly", true);

        eyes.open(driver, "Hackathon Demo app V1", "TC1 Login Page UI Elements Test", new RectangleSize(
                Integer.parseInt(viewPortWidth), Integer.parseInt(viewPortHeight)));

        // Open the app V2
        driver.get(CommonData.APP_V2_URL);

        // Visual checkpoint #1.
        eyes.checkWindow("Login Page");

        // End the test.
        eyes.closeAsync();
    }


    @Test(testName = "TC2.1 If you don’t enter the username and password and click the login button, it should throw an error", priority = 2)
    public void dataDrivenTest1() throws InterruptedException {
        Reporter.log("TC2.1: If you don’t enter the username and password and click the login button, it should throw an error", true);

        eyes.open(driver, "Hackathon Demo app V1", "TC2.1 Data-Driven Test: empty both", new RectangleSize(
                Integer.parseInt(viewPortWidth), Integer.parseInt(viewPortHeight)));

        // Open the app V2
        driver.get(CommonData.APP_V2_URL);
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLoginBtn();

        // Visual checkpoint #1.
        eyes.checkWindow("Login Page Error Msg");

        // End the test.
        eyes.closeAsync();
    }


    @Test(testName = "TC2.2 If you only enter the username and click the login button, it should throw an error", priority = 3)
    public void dataDrivenTest2() throws InterruptedException {
        Reporter.log("TC2.2: If you only enter the username and click the login button, it should throw an error", true);

        eyes.open(driver, "Hackathon Demo app V1", "TC2.2 Data-Driven Test: empty pwd", new RectangleSize(
                Integer.parseInt(viewPortWidth), Integer.parseInt(viewPortHeight)));

        // Open the app V2
        driver.get(CommonData.APP_V2_URL);
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUserName("thachhoang");
        loginPage.clickLoginBtn();

        // Visual checkpoint #1.
        eyes.checkWindow("Login Page Error Msg 2");

        // End the test.
        eyes.closeAsync();
    }

    @Test(testName = "TC2.3 If you only enter the password and click the login button, it should throw an error", priority = 4)
    public void dataDrivenTest3() throws InterruptedException {
        Reporter.log("TC2.3: If you only enter the password and click the login button, it should throw an error", true);

        eyes.open(driver, "Hackathon Demo app V1", "TC2.3 Data-Driven Test: empty username", new RectangleSize(
                Integer.parseInt(viewPortWidth), Integer.parseInt(viewPortHeight)));

        // Open the app V2
        driver.get(CommonData.APP_V2_URL);
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPassword("anypassword");
        loginPage.clickLoginBtn();

        // Visual checkpoint #1.
        eyes.checkWindow("Login Page Error Msg 3");

        // End the test.
        eyes.closeAsync();
    }

    @Test(testName = "TC2.4 If you enter both username (any value) and password (any value), it should log you in", priority = 5)
    public void dataDrivenTest4() throws InterruptedException {
        Reporter.log("TC2.4: If you enter both username (any value) and password (any value), it should log you in", true);

        eyes.open(driver, "Hackathon Demo app V1", "TC2.4 Data-Driven Test: login success", new RectangleSize(
                Integer.parseInt(viewPortWidth), Integer.parseInt(viewPortHeight)));

        // Open the app V2
        driver.get(CommonData.APP_V2_URL);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginCredential("thachhoang", "anyPassword");

        // Visual checkpoint #1.
        eyes.checkRegion(Locators.USER_PROFILE_INFO, "Login Page success");

        // End the test.
        eyes.closeAsync();
    }


    @Test(testName = "TC3 logged in and view the Recent Transactions table", priority = 6)
    public void tableSortTest() throws InterruptedException {
        Reporter.log("TC3: logged in and view the Recent Transactions table", true);

        eyes.open(driver, "Hackathon Demo app V1", "TC3 Recent Transaction table", new RectangleSize(
                Integer.parseInt(viewPortWidth), Integer.parseInt(viewPortHeight)));

        // Open the app V2
        driver.get(CommonData.APP_V2_URL);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginCredential("thachhoang", "anyPassword");
        homePage.waitTransactionsTableLoaded();
        homePage.clickSortingAmount();

        // Visual checkpoint #1.
        eyes.checkRegion(Locators.TRANSACTIONS_TABLE, "Transactions Table sorting");

        // End the test.
        eyes.closeAsync();
    }


    @Test(testName = "TC4 Canvas Chart Test: Validating Compare Expenses bar chart", priority = 7)
    public void canvasChartTest() throws InterruptedException {
        Reporter.log("TC4: Canvas Chart Test: Validating Compare Expenses bar chart", true);

        eyes.open(driver, "Hackathon Demo app V1", "TC4 Canvas Chart Test", new RectangleSize(
                Integer.parseInt(viewPortWidth), Integer.parseInt(viewPortHeight)));

        // Open the app V2
        driver.get(CommonData.APP_V2_URL);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginCredential("thachhoang", "anypassword");
        homePage.clickCompareExpenses();
        Thread.sleep(500); // Waiting for bar chart render animation

        // Visual validation for Expenses Comparision 2017 and 2018.
        eyes.checkWindow("Expenses Comparision 2017 and 2018");

        homePage.clickShowDataForNextYear();
        Thread.sleep(500); // Waiting for bar chart render animation

        // Visual validation for Expenses Comparision 2017, 2018 AND 2019.
        eyes.checkWindow("Expenses Comparision 2017, 2018 AND 2019");

        // End the test.
        eyes.closeAsync();
    }


    @Test(testName = "TC5 Dynamic Content Test: Validate Flash sale gifs", priority = 8)
    public void dynamicContentTest() throws InterruptedException {
        Reporter.log("TC5: Dynamic Content Test: Validate Flash sale gifs", true);

        eyes.open(driver, "Hackathon Demo app V1", "TC5 Dynamic Content Test", new RectangleSize(
                Integer.parseInt(viewPortWidth), Integer.parseInt(viewPortHeight)));
        eyes.setMatchLevel(MatchLevel.LAYOUT2);

        // Open the app V2 dynamic content
        driver.get(CommonData.APP_V2_DYNAMIC_CONTENT_URL);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginCredential("thachhoang", "anypassword");
        homePage.waitTransactionsTableLoaded();

        // Visual validation for checking two Flash sale Gifs are displayed
        eyes.checkWindow("validating two flash sale gifs");

        // End the test.
        eyes.closeAsync();
    }

    @AfterTest
    public void afterTest() {
        // Close all windows of the browser.
        driver.quit();

        // Close the Eyes.
        eyes.abortIfNotClosed();
    }
}

