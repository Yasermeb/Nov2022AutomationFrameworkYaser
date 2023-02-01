package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonAPI {
    /*
    This is the class definition for the HomePage class, which is extending a class called CommonAPI.
     CommonAPI is probably a class that provides a set of common methods and fields that are shared across
     multiple pages on the website.
     */
    Logger LOG = LogManager.getLogger(HomePage.class.getName());
    /*
    +To replace sout, Logger class from the Apache Log4j library to keep track of the actions in the test cases.
     This line of code is creating a logger object named "LOG" and initializing it to use the logger of the class "HomePage".
     It does this by calling the "getLogger" method from the "LogManager" class and passing in the name of the class
     "HomePage.class.getName()".
     LOG object allow you to add logging information to your application, and typically output them to a file or the console.
     The LogManager class provides factory methods to create instances of Logger, while the Logger is used to add log statements to your code.
     */

    public HomePage(WebDriver driver){      //This will initiate all elements of this class
        PageFactory.initElements(driver,this);
    }
    /*
    The class has a constructor that is initializing all the elements of the class by using PageFactory.initElements method.
    PageFactory.initElements is a utility method provided by Selenium to initialize web elements. By using this method,
    it finds elements on the web page and assigns them to the corresponding fields of the class, so they can be later used
    to interact with those elements.
    The constructor also initializes a logger that can be used to log events related to the HomePage class.
    The Logger class is from Apache Log4j library, that is used for logging purposes, this is done to keep track of
    the actions in the test cases.
    It appears that this is a base class for HomePage of the website where the web elements & web driver is being initialized,
     it also contains a logger class to keep the track of all the activities.
     */

    //object
    @FindBy(css = "#twotabsearchtextbox")
    WebElement searchField;

    @FindBy(css = "#nav-search-submit-button")
    WebElement searchButton;

    @FindBy(xpath ="//a[@id='nav-link-accountList']")
    WebElement floatingMenu;

    @FindBy(xpath = "//span[contains(., \"Sign in\")]")
    WebElement loginButton;

    @FindBy(css = "#searchDropdownBox")
    WebElement menuDropdown;

    @FindBy(xpath = "//div[@id='nav-cart-text-container']")
    WebElement cartLink;

    //reusable steps
    public void typeItemToSearch(String item){
        type(searchField, item);
        LOG.info("item name type success");
    }

    public void clickOnSearchButton(){
        clickOn(searchButton);
        LOG.info("click search success");
    }
    public void searchItem(String item){
        typeAndEnter(searchField,item);
        LOG.info("item name type and enter success");
    }
    public void selectOptionFromMenuDropdown(String option){
        selectOptionFromDropdown(menuDropdown, option);
        LOG.info(option+" option selected from the dropdown");
    }


    public void hoverOverFloatingMenu(WebDriver driver){
        hoverOver(driver, floatingMenu);
        LOG.info("hover over menu success");
    }
    public void clickOnLoginButton(){
        clickOn(loginButton);
        LOG.info("click on login button success");
    }
    public void clickOnCartLink(){
        clickOn(cartLink);
        LOG.info("click on cart link success");
    }
}
