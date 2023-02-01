package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class HandleNewWindow {
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
        System.out.println(driver.getTitle());
       //is using the System.out.println() method to print out the title of the current web page.
        // The driver variable is an instance of Selenium's WebDriver, which is being used to interact
        // with the web page.
        driver.findElement(By.xpath("//button[@id='openwindow']")).click();
        //is using the findElement() method to locate a button on the page using its XPath, and then clicks
        // on the button using the click() method. This will likely open a new window or tab in the browser.
        Set<String> windows= driver.getWindowHandles();
        Iterator<String> it= windows.iterator();
        String parent=it.next();
        String newWin=it.next();
        //is getting all the window handles opened by selenium and store in Set windows and creates an Iterator
        // 'it' object to iterate through the window handles. And then store the current window handle in
        // 'parent' and new window handle in 'newWin'.
        driver.switchTo().window(newWin);
        //switches the driver's focus to the new window using the switchTo() method, and the handle of the new
        // window is passed as an argument to this method.
        driver.findElement(By.xpath("//a[contains(text(),'Contact')]")).click();
        //locates an element on the new window using an xpath and clicks on it.
        System.out.println(driver.getTitle()); //prints the title of the new window.
        driver.switchTo().window(parent);
        //switch the driver's focus back to the parent window using the switchTo() method, and the handle of
        // the parent window is passed as an argument to this method.
    }
    @AfterMethod
    public void tearDown(){
        driver.quit(); //This will close all the windows while close closes only the current window
    }
}
