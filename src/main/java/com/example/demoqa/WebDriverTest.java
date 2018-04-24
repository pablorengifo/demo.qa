package com.example.demoqa;

import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WebDriverTest {

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "drivers/linux/geckodriver");
    }

    @Test
    public void testEbay() {
        WebDriver driver =  new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.ebay.com/");
        assertThat("ebay",driver.getTitle().contains("eBay"),is(Boolean.TRUE));

        driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("shoes puma");

        driver.findElement(By.xpath("//input[@id='gh-btn']")).click();

                //driver.findElement(By.id("gh-ac"));
    }
}
