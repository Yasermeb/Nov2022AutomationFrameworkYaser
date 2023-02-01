package test;

import base.CommonAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utility.ReadFromExcel;

public class TestSearch extends CommonAPI {

    Logger LOG= LogManager.getLogger(TestLogin.class.getName());
    ReadFromExcel read=new ReadFromExcel("D:\\IdealProjects\\Nov2022AutomationFrameworkYaser\\data\\Classeur1.xlsx","test data");

    @Test
    public void searchJavaBook() throws InterruptedException {
        HomePage homePage=new HomePage(driver);

        String expectedTitle=read.getCellValueForGivenHeaderAndKey("key","home page title");
//      String expectedTitle = "Amazon.com. Spend less. Smile more."; //copy from the inspect page and search for title
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        LOG.info("land to Amazon webpage succeed");

        homePage.typeItemToSearch("java book");
        homePage.clickOnSearchButton();


        String expectedTitle2 =read.getCellValueForGivenHeaderAndKey("key","java search title");
        String actualTitle2=getCurrentTitle();
        Assert.assertEquals(expectedTitle2,actualTitle2);
        LOG.info("Java search title validation success");
    }

    @Test
    public void searchSeleniumBook() throws InterruptedException {
        HomePage homePage=new HomePage(driver);  //We cannot make it global

        String expectedHomePageTitle = read.getCellValueForGivenHeaderAndKey("key","home page title");
        String actualHomePageTitle = getCurrentTitle();
        Assert.assertEquals(expectedHomePageTitle, actualHomePageTitle);
        LOG.info("land to Amazon webpage succeed");

        homePage.typeItemToSearch("selenium book");
        homePage.clickOnSearchButton();

        String expectedSearchPageTitle =read.getCellValueForGivenHeaderAndKey("key","selenium search title");
        String actualSearchPageTitle=getCurrentTitle();
        Assert.assertEquals(expectedSearchPageTitle,actualSearchPageTitle);
        LOG.info("selenium Search title validation success");
    }
    @Test
    public void searchJava(){
        HomePage homePage=new HomePage(driver);

        String expectedHomePageTitle = read.getCellValueForGivenHeaderAndKey("key","home page title");
        String actualHomePageTitle = getCurrentTitle();
        Assert.assertEquals(expectedHomePageTitle, actualHomePageTitle);
        LOG.info("land to Amazon webpage succeed");

        homePage.selectOptionFromMenuDropdown("Books");
        homePage.searchItem("java");

        String expectedSearchPageTitle2 =read.getCellValueForGivenHeaderAndKey("key","java book search title");
        String actualSearchPageTitle2=driver.getTitle();
        Assert.assertEquals(expectedSearchPageTitle2,actualSearchPageTitle2);
        LOG.info("dropdown java title validation success");
    }

}
