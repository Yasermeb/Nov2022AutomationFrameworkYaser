package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class CommonAPI {
    public WebDriver driv;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chromedriver", "C:\\Users\\yasse\\Nov2022Automation\\driver\\chromedriver.exe"); //this will open the browser
        driv = new ChromeDriver();   //this will give us access to the ChromeDriver methods
        driv.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //add waiting time
        driv.manage().window().maximize();          //maximise the window of the browser
        driv.get("https://www.amazone.com/");       //this will open the url
        driv.findElement(By.id("sp-cc-accept")).click();   //Accept cookies

        //this will find the web element by using the locators
        System.out.println("-----------------land to amazone webpage succeed----------------");
        String expectedTitle = "Amazon.fr : livres, DVD, jeux vidéo, musique, high-tech, informatique, jouets, vêtements, chaussures, sport, bricolage, maison, beauté, puériculture, épicerie et plus encore !"; //copy from the inspect page and search for title
        String actualTitle = driv.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        System.out.println("-----------------title validation succeed");
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() {
        driv.close();
    }

    //generic methods
    public String getCurrentTitle() {
        return driv.getTitle();
    }

    public String getTextFromElement(String cssOrXpath) {
        try {
            return driv.findElement(By.cssSelector(cssOrXpath)).getText();
        } catch (Exception e) {
            return driv.findElement(By.xpath(cssOrXpath)).getText();
        }
    }

    public void clickOn(String cssOrXpath) {
        try {
            driv.findElement(By.cssSelector(cssOrXpath)).click();
        } catch (Exception e) {
            driv.findElement(By.xpath(cssOrXpath)).click();
        }
    }

    public void type(String cssOrXpath, String text) {
        try {
            driv.findElement(By.cssSelector(cssOrXpath)).sendKeys(text);
        } catch (Exception e) {
            driv.findElement(By.xpath(cssOrXpath)).sendKeys(text);
        }
    }

    public void typeAndEnter(String cssOrXpath, String text) {
        try {
            driv.findElement(By.cssSelector(cssOrXpath)).sendKeys(text, Keys.ENTER);
        } catch (Exception e) {
            driv.findElement(By.xpath(cssOrXpath)).sendKeys(text, Keys.ENTER);
        }
    }

    public void selectOptionFromDropdown(String cssOrXpath, String option) {
        WebElement dropdown;
        try {
            dropdown = driv.findElement(By.cssSelector(cssOrXpath));
            Select select = new Select(dropdown);
            try {
                select.selectByVisibleText(option);
            } catch (Exception e2) {
                select.selectByValue(option);
            }

        } catch (Exception e) {
            dropdown = driv.findElement(By.xpath(cssOrXpath));
            Select select = new Select(dropdown);
            try {
                select.selectByVisibleText(option);
            } catch (Exception e2) {
                select.selectByValue(option);
            }
        }

    }

    public void hoverOver(String cssOrXpath) {
        Actions actions = new Actions(driv);
        try {
            actions.moveToElement(driv.findElement(By.cssSelector(cssOrXpath))).build().perform();
        } catch (Exception e) {
            actions.moveToElement(driv.findElement(By.xpath(cssOrXpath))).build().perform();
        }
    }
}
