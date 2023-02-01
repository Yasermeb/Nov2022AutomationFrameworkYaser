package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Ps4ControllerPage extends CommonAPI {

    Logger LOG = LogManager.getLogger(Ps4ControllerPage.class.getName());

    public Ps4ControllerPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='add-to-cart-button']//following-sibling::span")
    WebElement addToCartBtn;
    @FindBy(xpath = "//span[@id='attachSiNoCoverage-announce']/preceding-sibling::input")
    WebElement noThanksBtn;

    public void clickOnAddToCartButton(WebDriver driver){
        clickWithActions(driver, addToCartBtn);
        LOG.info("click add to cart button success");
    }
    public void clickOnNoThanksButton(WebDriver driver){
        waitForElementToBeVisible(driver, 10, noThanksBtn);
        clickOn(noThanksBtn);
        LOG.info("click on no thanks button success");
    }

}
