package com.example.demoqa;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WebDriverTest {

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "drivers/linux/geckodriver");
    }

    @Test
    public void testEbay() {
        WebDriver webDriver =  new FirefoxDriver();
        webDriver.navigate().to("https://www.ebay.com/");
        assertThat("ebay",webDriver.getTitle().contains("eBay"),is(Boolean.TRUE));
    }
}
