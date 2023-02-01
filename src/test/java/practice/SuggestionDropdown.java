package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SuggestionDropdown {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    }
    @Test
    public void test() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='autocomplete']")).sendKeys("can");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='autocomplete']")).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN,Keys.ENTER);
        Thread.sleep(3000);
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
