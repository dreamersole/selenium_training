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
import org.openqa.selenium.support.Color;
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
        String main_regular_price_font_color = product.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String main_campaign_price_font_color = product.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        // - normal_cost_font_is_crossed
        // - action_cost_font_is_bold
        Dimension main_regular_price_font_size = product.findElement(By.cssSelector("s.regular-price")).getSize();
        Dimension main_campaign_price_font_size = product.findElement(By.cssSelector("strong.campaign-price")).getSize();
        // TODO
        // Open the campaigns first found product page
        product.click();
        // Read the product info from the product page:
        String prod_product_name = driver.findElement(By.cssSelector("div#box-product h1.title")).getText();
        String prod_regular_price = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getText();
        String prod_campaign_price = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getText();
        String prod_regular_price_font_color = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getCssValue("color");
        String prod_campaign_price_font_color = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getCssValue("color");
        // - normal_cost_font_is_crossed
        // - action_cost_font_is_bold
        Dimension prod_regular_price_font_size = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getSize();
        Dimension prod_campaign_price_font_size = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getSize();
        // TODO
        // Verify all info
        assert main_product_name.equals(prod_product_name);
        assert main_campaign_price.equals(prod_campaign_price);
        assert main_regular_price.equals(prod_regular_price);
        assert isRed(main_campaign_price_font_color);
        assert isGrey(main_regular_price_font_color);
        assert isRed(prod_campaign_price_font_color);
        assert isGrey(prod_regular_price_font_color);
        // TODO
        assert main_campaign_price_font_size.height > main_regular_price_font_size.height;
        assert prod_campaign_price_font_size.height > prod_regular_price_font_size.height;
    }

    private void openMainPage() {
        driver.get("http://localhost/litecart/");
    }

    boolean isRed(String color_string) {
        Color color = Color.fromString(color_string);
        java.awt.Color c = color.getColor();
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        int a = c.getAlpha();
        return r > 0 && g == 0 && b == 0;
    }

    boolean isGrey(String color_string) {
        Color color = Color.fromString(color_string);
        java.awt.Color c = color.getColor();
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        int a = c.getAlpha();
        return r == g && g == b && b > 0;
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
