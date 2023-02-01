package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ElementIsDisplayed {
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
        driver.findElement(By.xpath("//input[@id='displayed-text']")).sendKeys("");
        driver.findElement(By.xpath("//input[@id='hide-textbox']")).click();
        Thread.sleep(3000);
        System.out.println(driver.findElement(By.xpath("//input[@id='displayed-text']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='show-textbox']")).click();
        System.out.println(driver.findElement(By.xpath("//input[@id='displayed-text']")).isDisplayed());
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
