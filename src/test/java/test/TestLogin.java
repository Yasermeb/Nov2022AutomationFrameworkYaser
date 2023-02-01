package test;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utility.ConnectDB;

public class TestLogin extends CommonAPI {
    Logger LOG = LogManager.getLogger(TestLogin.class.getName());
    @Test
    public void invalidEmailAddress() throws InterruptedException {
        HomePage homePage=new HomePage(getDriver());
        //when you want to use a class from another package you create an object of this lcass
        /*
       This line of code is creating a new instance of the
      HomePage class and passing in a webDriver object.
      The HomePage class likely contains methods and fields
      that interact with elements on the home page of a website.
      The getDriver() method likely returns a WebDriver object,
      which is used to interact with a web page through a browser.This
      particular instance is likely used to perform various operations
      on the home page of the website, such as interacting with its UI elements.
         */
        LoginPage loginPage=new LoginPage(getDriver());
        String email = ConnectDB.getTableColumnData("select * from cred","password").get(0);

        homePage.hoverOverFloatingMenu(driver);
        homePage.clickOnLoginButton();

        String title= getCurrentTitle();
        Assert.assertEquals("Amazon Sign-In", title);
        LOG.info("Login title page validation succeed");

        loginPage.typeEmailAddress(email);
        loginPage.clickOnConnectButton();


        String error=loginPage.getErrorMessage();
        Assert.assertEquals(error,"There was a problem");
        LOG.info("error message validation success");
    }
    /*
    This looks like a Java code snippet for testing a login flow on a website using the Selenium WebDriver library. The code appears to be doing the following:

Create instances of the HomePage and LoginPage classes, which are likely classes that encapsulate elements and behavior specific to those pages.
Use the HomePage object to hover over the site's floating menu and click on the login button.
Assert that the title of the page that is loaded is "Amazon Sign-In".
Use the LoginPage object to type an email address (abcdefg@gmail.com) into the email field and click the "Connect" button.
Type a password ("abcdef") and click on the "Connect Password" button.
Get the error message from the page, and assert that it is equal to the string "There was a problem".
It looks like this is a junit test case validating error message on incorrect email login to amazon.
     */
}
