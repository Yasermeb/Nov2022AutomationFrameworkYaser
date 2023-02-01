package base;

import com.relevantcodes.extentreports.LogStatus;
import jdk.jshell.execution.Util;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utility.Utility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class CommonAPI {
    /*
The above code is an example of a CommonAPI class which is a base class for test automation using Selenium WebDriver. The class contains several methods that provide common functionalities which can be shared across multiple test classes.
The class is using TestNG annotations like BeforeMethod and AfterMethod for initializing the browser and closing it respectively.

It has a getLocalDriver() method which is using to set up drivers for different web browsers like Chrome, Firefox etc. and also has a getCloudDriver() method to run the test case remotely in a cloud environment like BrowserStack.

It has also methods like clickOn, type, typeAndEnter which are interacting with web elements by using the web driver. And also it has method getTextFromElement to get the text from web element.

The tearDown() method is closing the browser after each test case.

It also has @parameters annotation which is used to pass parameters to the test cases, like browser type, version, OS and also the URL of the website which is under test.

It looks like the class is designed to provide a single point of access for the common test automation methods and browser setup and teardown, and it is used to set up the test environment, run the test cases and tear down the test environment.
     */
    Logger LOG = LogManager.getLogger(CommonAPI.class.getName());

    String takeScreenshot = Utility.getProperties().getProperty("take.screenshot", "false");
    String maximizeBrowser = Utility.getProperties().getProperty("browser.maximize", "true");
    String implicitWait = Utility.getProperties().getProperty("implicit.wait", "10");
    String username = Utility.decode(Utility.getProperties().getProperty("browserstack.username"));
    String password = Utility.decode(Utility.getProperties().getProperty("browserstack.password"));

    public WebDriver driver;

    //Report setup from line 66 to 122 *****************************************************************

    public static com.relevantcodes.extentreports.ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (takeScreenshot.equalsIgnoreCase("true")){
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshot(result.getName());
            }
        }
        driver.quit();
    }
    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
    //End of report setup*******************************************************************************

    public void getLocalDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            /*
        In the code snippet you provided, browserName.equalsIgnoreCase("chrome") is a conditional statement that
        compares the value of the variable "browserName" to the string "chrome" in a case-insensitive manner.
        If the comparison evaluates to true, the code inside the if block will execute, which in this case creates
        a new instance of ChromeDriver and assigns it to the "driver" variable. If the comparison evaluates to false,
        the code inside the else-if block will execute which in this case creates a new instance of FirefoxDriver and
        assigns it to the "driver" variable. This is used in the getLocalDriver() method to instantiate the WebDriver
        with the browser the user wants to test on.
             */
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
    }

    public void getCloudDriver(String envName, String os, String osVersion, String browser, String browserVersion, String username, String password) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("os", os);
        cap.setCapability("os_version", osVersion);
        cap.setCapability("browser", browser);
        cap.setCapability("browser_version", browserVersion);
        if (envName.equalsIgnoreCase("browserstack")) {
            cap.setCapability("resolution", "1024x768"); //applicable for browserStack only, not applicable for sauceLabs
            driver = new RemoteWebDriver(new URL("http://" + username + ":" + password + "@hub-cloud.browserstack.com:80/wd/hub"), cap);
        } else if (envName.equalsIgnoreCase("saucelabs")) {
            driver = new RemoteWebDriver(new URL("http://" + username + ":" + password + "@ondemand.saucelabs.com:80/wd.hub"), cap);
        }

    }
    //Setup---------------------------------------------------------------------------------------------
    @Parameters({"useCloudEnv", "envName", "os", "osVersion", "browserName", "browserVersion", "url"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("browserstack") String envName,
                      @Optional("windows") String os, @Optional("11") String osVersion,
                      @Optional("chrome") String browserName, @Optional("108") String browserVersion,
                      @Optional("https://www.google.com") String url) throws InterruptedException, MalformedURLException {
        if (useCloudEnv) {
            getCloudDriver(envName, os, osVersion, browserName, browserVersion, username, password);
        } else {
            getLocalDriver(browserName);  //this dependency do setup chrome even if u have mac or windows..
        }

        //System.setProperty("webdriver.chromedriver", "C:\\Users\\yasse\\Nov2022Automation\\driver\\chromedriver.exe"); //this will open the browser
        //bcz we created webdriver dependency but we add the next line instead
        //driv = new ChromeDriver();   //this will give us access to the ChromeDriver methods

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //add waiting time
        if (maximizeBrowser.equalsIgnoreCase("true")){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(implicitWait)));
        }
        driver.manage().window().maximize();          //maximise the window of the browser
        driver.get(url);       //this will open the url
        //driver.findElement(By.id("sp-cc-accept")).click();   //Accept cookies
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));// add waiting time

        //this will find the web element by using the locators
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    //--------------------------------------------------------------------------------------------------

    //generic methods
    public WebDriver getDriver() {
        return driver;
    }
    public String getCurrentTitle() {
        return driver.getTitle();
    }
    public String getTextFromElement(WebElement element) {
        return element.getText();
    }
    public void clickOn(WebElement element) {
        element.click();
    }
    /*
    This replaced:
    public void clickOn(String locator){
        WebElement element=driver.findElement(By.xpath()).click();
        element.click();
    }
     */
    public void type(WebElement element, String text) {
        element.sendKeys(text);
    }
    public void typeAndEnter(WebElement element, String text) {
        element.sendKeys(text, Keys.ENTER);
    }
    public void selectOptionFromDropdown(WebElement dropdown, String option) {
        Select select = new Select(dropdown);
        try {
            select.selectByVisibleText(option);
        } catch (Exception e) {
            select.selectByValue(option);
        }
    }
    public void hoverOver(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
    public void waitForElementToBeVisible(WebDriver driver, int duration, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void clickWithActions(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }
    public void clickWithJavascript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    public void captureScreenshot() {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("screenshots/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void takeScreenshot(String screenshotName){
        DateFormat df = new SimpleDateFormat("MMddyyyyHHmma");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(Utility.path + File.separator +"screenshots"+ File.separator + screenshotName+" "+df.format(date)+".jpeg"));
            LOG.info("Screenshot captured");
        } catch (Exception e) {
            LOG.info("Exception while taking screenshot "+e.getMessage());
        }
    }
}
