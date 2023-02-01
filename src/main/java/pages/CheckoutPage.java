package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends CommonAPI {

    Logger LOG = LogManager.getLogger(CheckoutPage.class.getName());

    public CheckoutPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
    WebElement checkoutBtn;

    public void clickOnCheckoutBtn(WebDriver driver){
        clickWithActions(driver, checkoutBtn);
        LOG.info("click on checkout button success");
    }
}
