package test;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestSearch extends CommonAPI {

    @Test
    public void test1() throws InterruptedException {

        //Let's get rid of using driver.
        type("#twotabsearchtextbox","java book");
        //driv.findElement(By.id("twotabsearchtextbox")).sendKeys("java book"); //catch the element and enter text on it using By class and its static method like id, name...
        System.out.println("-----------------text sent success");

        clickOn("#nav-search-submit-button");
        //driv.findElement(By.cssSelector("#nav-search-submit-button")).click();
        System.out.println("-----------------click search success");

        String expectedTitle2 ="Amazon.fr : java book";
        String actualTitle2=getCurrentTitle();
        //String actualTitle2=driv.getTitle();
        Assert.assertEquals(expectedTitle2,actualTitle2);
        System.out.println("------------Java search title validation success-----------");
    }

    @Test
    public void test2() throws InterruptedException {
        type("#twotabsearchtextbox","selenium book" );
        //driv.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys("selenium book");
        System.out.println("-----------------text sent success");

        clickOn("#nav-search-submit-button");
        //driv.findElement(By.cssSelector("#nav-search-submit-button")).click();
        System.out.println("-----------------click search success");

        String expectedTitle ="Amazon.fr : selenium book";
        String actualTitle=getCurrentTitle();
        //String actualTitle2=driv.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
        System.out.println("-----------------selenium Search title validation success");
    }
    @Test
    public void test3(){
        selectOptionFromDropdown("#searchDropdownBox","Alexa Skills");
//        WebElement dropdown = driv.findElement(By.cssSelector("#searchDropdownBox")); //WE class from sel
//        Select select=new Select(dropdown);   //Handling dropdown, the par is the WE that corresponds to dropdown
//        select.selectByVisibleText("Alexa Skills");
        System.out.println("********Books option selected from dropdown");

        typeAndEnter("#twotabsearchtextbox", "java");
        //driv.findElement(By.id("twotabsearchtextbox")).sendKeys("java", Keys.ENTER); //Use keys enum in sel
        System.out.println("-----------------text sent succeed");

        String expectedTitle2 ="Amazon.fr : java";
        String actualTitle2=driv.getTitle();
        //String actualTitle2=driv.getTitle();
        Assert.assertEquals(expectedTitle2,actualTitle2);
        System.out.println("-----------------dropdown java title validation success");
    }
}
