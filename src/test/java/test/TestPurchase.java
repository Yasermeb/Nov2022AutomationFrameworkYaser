package test;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;
import pages.Ps4ControllerPage;
import pages.SearchPage;

public class TestPurchase extends CommonAPI {

    Logger LOG = LogManager.getLogger(TestPurchase.class.getName());

    @Test
    public void purchasePs4Controller() throws InterruptedException {
        //land on amazon (done)
        String actualHomePageTitle = getCurrentTitle();
        String expectedHomePageTitle = "Amazon.com. Spend less. Smile more.";
        LOG.info("actual home page title"+ actualHomePageTitle);
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
        LOG.info("land to amazon home page success");

        //type ps4 controller
        //click on search
        String item = "ps4 controller";
        HomePage homePage = new HomePage(getDriver());
        homePage.searchItem(item);

        //select the one with amazon choice tag
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.clickOnPs4ControllerLink();

        //add to cart
        Ps4ControllerPage ps4ControllerPage = new Ps4ControllerPage(getDriver());
        ps4ControllerPage.clickOnAddToCartButton(getDriver());

        //no to protection plan
        ps4ControllerPage.clickOnNoThanksButton(getDriver());

        Thread.sleep(3000);
        //click on cart
        homePage.clickOnCartLink();

        Thread.sleep(3000);
        //proceed to checkout
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.clickOnCheckoutBtn(getDriver());

        //land on login page
        String loginPageTitle = getCurrentTitle();
        Assert.assertEquals(loginPageTitle, "Amazon Sign-In");
        LOG.info("login title page validation success");
    }

}
