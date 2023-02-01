package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Checkbox {
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
        driver.findElement(By.xpath("//input[@id='checkBoxOption1']")).click();
        System.out.println(driver.findElement(By.xpath("//input[@id='checkBoxOption1']")).isSelected());
        System.out.println(driver.findElement(By.xpath("//input[@id='checkBoxOption2']")).isSelected());
        System.out.println(driver.findElement(By.xpath("//input[@id='checkBoxOption3']")).isSelected() );
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
