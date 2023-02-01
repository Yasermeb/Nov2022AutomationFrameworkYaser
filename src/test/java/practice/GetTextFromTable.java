package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class GetTextFromTable {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    }
    @Test
    public void test(){
        String text= driver.findElement(By.xpath("//*[text()='Web Table Example']//following-sibling::table/tbody/tr[11]/td[2]")).getText();
        /*
        This is a line of code in the programming language Java that uses Selenium WebDriver to find an
         element on a web page. The specific element being located is a table cell (td) with
         the xpath "//*[text()='Web Table Example']//following-sibling::table/tbody/tr[11]/td[2]".
        Here is what the xpath expression means:
        "//*[text()='Web Table Example']" - This is looking for any element with the text "Web Table Example"
        "//following-sibling::table" - This looks for the next table element that is a sibling of the element
         with the text "Web Table Example"
        "/tbody/tr[11]/td[2]" - This looks for the 11th row and 2nd column of the located table.

        The returned element is assigned to the variable named "text".
        */
        System.out.println(text);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
