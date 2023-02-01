package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DragAndDrop {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://jqueryui.com/droppable/");
    }
    @Test
    public void test() throws InterruptedException {
        driver.switchTo().frame(0);
        System.out.println("Switch to iFrame success");
        WebElement draggable=driver.findElement(By.xpath("//body/div[@id='draggable']"));
        WebElement droppable= driver.findElement(By.xpath("//body/div[@id='droppable']"));
        Actions actions=new Actions(driver);
        actions.dragAndDrop(draggable,droppable).build().perform();
        Thread.sleep(3000);

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
