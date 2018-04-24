package com.example.demoqa;

import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WebDriverTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "drivers/linux/geckodriver");
        driver =  new FirefoxDriver();
    }

    @Test
    public void testEbay(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.ebay.com/");

        assertThat("ebay",driver.getTitle().contains("eBay"),is(Boolean.TRUE));

        driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("shoes puma");


        // select size 10
        driver.findElement(By.xpath("//input[@id='gh-btn']")).click();


        WebElement checkBox = driver.findElement(By.id("e1-29"));

        if (!checkBox.isSelected()){
            checkBox.click();
        }

        List<WebElement> productList = driver
                .findElements(By
                        .xpath("//*[@id=\"GalleryViewInner\"]/li/div/div[2]/h3/a"));

        List<WebElement> productListPrices = driver
                .findElements(By
                        .xpath("//*[@id=\"GalleryViewInner\"]/li/div/div[3]/div[2]/div/span[1]/span"));


        List<EbayProduct> ebayProductList = new ArrayList<>();


        System.out.println("First 5 products on console");
        System.out.println("============================");

        for(int i=0;i<5;i++) {
            String product = productList.get(i).getText();
            String price = productListPrices.get(i).getText();
            Double doublePrice = Double.parseDouble(price.substring(4,price.length()));
            System.out.println(product + " - " + doublePrice);
            ebayProductList.add(new EbayProduct(product,doublePrice));
        }

        assertThat("First 5 products",ebayProductList.size(),is(5));
        System.out.println("====================\n");

        System.out.println("Sort by product name");
        System.out.println("====================");

        Comparator<EbayProduct> ebayProductComparator = Comparator.comparing(ebayProduct -> ebayProduct.getName());
        Collections.sort(ebayProductList, ebayProductComparator);
        ebayProductList.forEach(item -> System.out.println(item.getName() + " - " + item.getPrice()));
        assertThat("First Product must be",ebayProductList.get(0).getName(),
                is("Nuevo Puma Para hombres Calzado Atlético celular desorganizado Entrenamiento Cruzado Negro Elige Tamaño"));

        System.out.println("====================\n");

        System.out.println("Sort by price descending");
        System.out.println("====================");

        Comparator<EbayProduct> ebayPriceComparator = Comparator.comparing(ebayProduct -> ebayProduct.getPrice());
        Collections.sort(ebayProductList, ebayPriceComparator);
        Collections.reverse(ebayProductList);
        ebayProductList.forEach(item -> System.out.println(item.getName() + " - " + item.getPrice()));

        assertThat("Last price must be",ebayProductList.get(0).getPrice(),is(388.04));


    }
}
