package pages;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends CommonAPI {

    Logger LOG = LogManager.getLogger(SearchPage.class.getName());

    public SearchPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//span[contains(text(),'Wireless Controller for PlayStation 4')])[2]")
    WebElement ps4ControllerLink;

    public void clickOnPs4ControllerLink(){
        clickOn(ps4ControllerLink);
        LOG.info("click on ps4 controller success");
    }

}
