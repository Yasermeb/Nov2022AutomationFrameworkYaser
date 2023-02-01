package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonAPI {
    /*
    This is a class for the LoginPage of a website, it's using PageFactory and FindBy annotations for web element locators.
It appears that it's also using a Logger class from the Apache Log4j library to keep track of the actions in the test cases.
It is also extending a class called CommonAPI which is probably a class that provides a set of common methods and fields that are shared across multiple pages on the website.
It uses some of the reusable methods like typeEmailAddress, typePassword, clickOnConnectButton, clickOnConnectPasswordButton , getErrorMessage that are interacting with the web elements by using the webdriver.
It looks like the class was used for automating the test cases of login page of a website,
It uses standard selenium methods like type, clickOn and some custom methods like getTextFromElement
It also used WebDriver driver as parameter in constructor and PageFactory.initElements to initialize all the elements of the class by using the driver instance.
It also has an error message web element errorMessage which can be used to validate the error message when there is a problem with login credential.
     */

    Logger LOG = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage(WebDriver driver){      //This will initiate all elements of this class
        PageFactory.initElements(driver,this);
    }
    //constructor created with driver to be passed later as parameter, we create obj of loginpage class in the
    //login test class, and : driver=new chromedriver() will be in setup and in methods of loginpage we use it with pars;

    @FindBy(xpath = "//input[@id='ap_email']")
    WebElement emailField;

    @FindBy(css = "#continue")
    WebElement connectButton;

    @FindBy(xpath="//input[@id='ap_password']")
    WebElement passwordField;

    @FindBy(css = "#signInSubmit")
    WebElement connectPasswordButton;

    @FindBy(xpath = "//h4[contains(text(),'There was a problem')]")
    WebElement errorMessage;

    //reusable methods
    public void typeEmailAddress(String emailAddress){
        type(emailField, emailAddress);
        LOG.info("Typing email-address succeed");
    }
    public void typePassword(String password){
        type(passwordField, password);
        LOG.info("Typing password succeed");
    }
    public void clickOnConnectButton(){
        clickOn(connectButton);
        LOG.info("click connect button success");
    }
    public void clickOnConnectPasswordButton(){
        clickOn(connectPasswordButton);
        LOG.info("click connect Password button success");
    }
    public String getErrorMessage(){
        return getTextFromElement(errorMessage);
    }

}
