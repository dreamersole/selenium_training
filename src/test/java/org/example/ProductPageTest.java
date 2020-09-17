package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;


public class ProductPageTest {
    public WebDriver driver; // WARNING: NOT thread-safe yet
    //public WebDriverWait wait;

    @Before
    public void start() {
        //driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void productPageFirefoxTest() {
        driver = new FirefoxDriver();
        productPageTestInternal();
    }

    @Test
    public void productPageChromeTest() {
        driver = new ChromeDriver();
        productPageTestInternal();
    }

    private void productPageTestInternal() {
        openMainPage();
        // Get the campaigns first found product
        WebElement product = driver.findElement(By.cssSelector("div#box-campaigns li.product"));
        // Read the product info from the main page:
        String main_product_name = product.findElement(By.cssSelector("div.name")).getText();
        String main_regular_price = product.findElement(By.cssSelector("s.regular-price")).getText();
        String main_campaign_price = product.findElement(By.cssSelector("strong.campaign-price")).getText();
        // - normal_cost_font_is_crossed
        // - normal_cost_font_is_grey
        // - action_cost_font_is_bold
        // - action_cost_font_is_red
        Dimension main_regular_price_font_size = product.findElement(By.cssSelector("s.regular-price")).getSize();
        Dimension main_campaign_price_font_size = product.findElement(By.cssSelector("strong.campaign-price")).getSize();
        // TODO
        // Open the campaigns first found product page
        product.click();
        // Read the product info from the product page:
        String prod_product_name = driver.findElement(By.cssSelector("div#box-product h1.title")).getText();
        String prod_regular_price = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getText();
        String prod_campaign_price = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getText();
        // - normal_cost_font_is_crossed
        // - normal_cost_font_is_grey
        // - action_cost_font_is_bold
        // - action_cost_font_is_red
        Dimension prod_regular_price_font_size = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getSize();
        Dimension prod_campaign_price_font_size = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getSize();
        // TODO
        // Verify all info
        assert main_product_name.equals(prod_product_name);
        assert main_campaign_price.equals(prod_campaign_price);
        assert main_regular_price.equals(prod_regular_price);
        // TODO
        assert main_campaign_price_font_size.height > main_regular_price_font_size.height;
        assert prod_campaign_price_font_size.height > prod_regular_price_font_size.height;
        // TODO
    }

    private void openMainPage() {
        driver.get("http://localhost/litecart/");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
