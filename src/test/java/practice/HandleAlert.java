package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HandleAlert {
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
        //Accept alert
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='alertbtn']")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        Thread.sleep(3000);

        //Accept alert
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='confirmbtn']")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        Thread.sleep(3000);

        //dismiss alert
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='confirmbtn']")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();
        Thread.sleep(3000);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();

    }
}
