package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPageTest {
    //public WebDriver driver;
    //public WebDriverWait wait;

    @Before
    public void start() {
        //driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void productPageFirefoxTest() {
        WebDriver driver = new FirefoxDriver();
        productPageTestInternal(driver);
        driver.quit();
    }

    @Test
    public void productPageChromeTest() {
        WebDriver driver = new ChromeDriver();
        productPageTestInternal(driver);
        driver.quit();
    }

    private void productPageTestInternal(WebDriver driver) {
        openMainPage(driver);
        // Get the campaigns first found product
        WebElement product = driver.findElement(By.cssSelector("div#box-campaigns li.product"));
        // Read the product info from the main page:
        // - product_name
        // - normal_cost
        // - action_cost
        // - normal_cost_font_is_crossed
        // - normal_cost_font_is_grey
        // - action_cost_font_is_bold
        // - action_cost_font_is_red
        // - normal_cost_font_size
        // - action_cost_font_size
        // TODO
        // Open the campaigns first found product page
        product.click();
        // Read the product info from the product page:
        // - product_name
        // - normal_cost
        // - action_cost
        // - normal_cost_font_is_crossed
        // - normal_cost_font_is_grey
        // - action_cost_font_is_bold
        // - action_cost_font_is_red
        // - normal_cost_font_size
        // - action_cost_font_size
        // TODO
        // Verify all info
        // TODO
    }

    private void openMainPage(WebDriver driver) {
        driver.get("http://localhost/litecart/");
    }

    @After
    public void stop() {
        //driver.quit();
        //driver = null;
    }
}
