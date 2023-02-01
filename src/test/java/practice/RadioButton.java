package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class RadioButton {
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
        List <WebElement> radioButtons=driver.findElements(By.xpath("//input[@name=\"radioButton\"]"));
        for (WebElement radioButton : radioButtons){
            if(radioButton.getAttribute("value").equals("radio1")){
                radioButton.click();
            }
        }
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
