package test;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestLogin extends CommonAPI {

    @Test
    public void test1() throws InterruptedException {
        hoverOver("//span[@id='nav-link-accountList-nav-line-1']");
        //Actions actions=new Actions(driv);
        //actions.contextClick(); //Right click method
        //actions.moveToElement(driv.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']"))).build().perform(); //just hover over
        System.out.println("------------Hover over menu succeed------------");

        clickOn("//header/div[@id='navbar']/div[@id='nav-flyout-anchor']/div[@id='nav-flyout-accountList']/div[2]/div[1]/div[1]/div[1]/a[1]/span[1]");
        //driv.findElement(By.xpath("//header/div[@id='navbar']/div[@id='nav-flyout-anchor']/div[@id='nav-flyout-accountList']/div[2]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
        System.out.println("------------Click on login button succeed------------");

        String title= getCurrentTitle();
        //String title=driv.getTitle();
        Assert.assertEquals("Connexion Amazon", title);
        System.out.println("------------Login title page validation succeed------------");

        type("//input[@id='ap_email']","abcdefg@gmail.com");
        //driv.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("abcdefg@gmail.com");
        System.out.println("------------Typing email-address succeed------------");

        clickOn("#continue");
        //driv.findElement(By.cssSelector("#continue")).click(); //click continue

        type("#ap_password","abcdefg");
        //driv.findElement(By.cssSelector("#ap_password")).sendKeys("abcdefg");
        System.out.println("------------Typing password succeed------------");

        clickOn("#signInSubmit");
        //driv.findElement(By.cssSelector("#signInSubmit")).click();
        System.out.println("------------Click On succeed------------");

        String wrongEmailAdd=getTextFromElement("body.ap-locale-fr_FR.a-m-us.a-aui_72554-c.a-aui_accordion_a11y_role_354025-c.a-aui_killswitch_csa_logger_372963-c.a-aui_launch_2021_ally_fixes_392482-t1.a-aui_pci_risk_banner_210084-c.a-aui_preload_261698-c.a-aui_rel_noreferrer_noopener_309527-c.a-aui_template_weblab_cache_333406-c.a-aui_tnr_v2_180836-c.a-meter-animate:nth-child(2) div.a-section.a-padding-medium.auth-workflow:nth-child(2) div.a-section:nth-child(2) div.a-section div.a-section.a-spacing-base.auth-pagelet-container:nth-child(1) div.a-section div.a-box.a-alert.a-alert-error.auth-server-side-message-box.a-spacing-base:nth-child(1) div.a-box-inner.a-alert-container > h4.a-alert-heading");
        //String wrongEmailAdd=driv.findElement(By.cssSelector("body.ap-locale-fr_FR.a-m-us.a-aui_72554-c.a-aui_accordion_a11y_role_354025-c.a-aui_killswitch_csa_logger_372963-c.a-aui_launch_2021_ally_fixes_392482-t1.a-aui_pci_risk_banner_210084-c.a-aui_preload_261698-c.a-aui_rel_noreferrer_noopener_309527-c.a-aui_template_weblab_cache_333406-c.a-aui_tnr_v2_180836-c.a-meter-animate:nth-child(2) div.a-section.a-padding-medium.auth-workflow:nth-child(2) div.a-section:nth-child(2) div.a-section div.a-section.a-spacing-base.auth-pagelet-container:nth-child(1) div.a-section div.a-box.a-alert.a-alert-error.auth-server-side-message-box.a-spacing-base:nth-child(1) div.a-box-inner.a-alert-container > h4.a-alert-heading")).getText();
        Assert.assertEquals(wrongEmailAdd,"Un problème est survenu");
        System.out.println("------------error message Validation success------------");


    }
}
