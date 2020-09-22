package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
//import org.openqa.selenium.support.ui.WebDriverWait;


public class UserAddTest {
    public WebDriver driver; // WARNING: NOT thread-safe yet
    //public WebDriverWait wait;

    @Before
    public void start() {
        //driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void firefoxTest() {
        driver = new FirefoxDriver();
        testInternal();
    }

    @Test
    public void chromeTest() {
        driver = new ChromeDriver();
        testInternal();
    }

    private void testInternal() {
        // MAIN PAGE
        openMainPage();
        // Get the campaigns first found product
        WebElement product = driver.findElement(By.cssSelector("div#box-campaigns li.product"));
        // Read the product info from the main page:
        String main_product_name = product.findElement(By.cssSelector("div.name")).getText();
        WebElement main_regular_price_web_element = product.findElement(By.cssSelector("s.regular-price"));
        WebElement main_campaign_price_web_element = product.findElement(By.cssSelector("strong.campaign-price"));
        String main_regular_price = main_regular_price_web_element.getText();
        String main_campaign_price = main_campaign_price_web_element.getText();
        // NOTE: for the main page prices
        // font color, size and style (bold, line-through) is defined by css or by default for the elements:
        // s.regular-price-> text-decoration-line: line-through, color: #777, font-size: 0.8em*18px
        // strong.campaign-price-> font-weight: bold, color: #c00, font-size: 18px
        // So, we may check tag and class names only, but we can also check some properties automatically:
        String main_regular_price_font_color = main_regular_price_web_element.getCssValue("color");
        String main_campaign_price_font_color = main_campaign_price_web_element.getCssValue("color");
        String main_regular_price_font_size = main_regular_price_web_element.getCssValue("font-size");
        String main_campaign_price_font_size = main_campaign_price_web_element.getCssValue("font-size");

        // PRODUCT PAGE
        // Open the campaigns first found product page
        product.click();
        // Read the product info from the product page:
        String prod_product_name = driver.findElement(By.cssSelector("div#box-product h1.title")).getText();
        WebElement prod_regular_price_web_element = driver.findElement(By.cssSelector("div#box-product .price-wrapper s.regular-price"));
        WebElement prod_campaign_price_web_element = driver.findElement(By.cssSelector("div#box-product .price-wrapper strong.campaign-price"));
        String prod_regular_price = prod_regular_price_web_element.getText();
        String prod_campaign_price = prod_campaign_price_web_element.getText();
        // NOTE: for the product page prices
        // font color, size and style (bold, line-through) is defined by css or by default for the elements:
        // div#box-product .price-wrapper s.regular-price-> text-decoration-line: line-through, color: #666, font-size: 16px
        // div#box-product .price-wrapper strong.campaign-price-> font-weight: bold, color: #c00, font-size: 22px
        // So, we may check tag and class names only, but we can also check some properties automatically:
        String prod_regular_price_font_color = prod_regular_price_web_element.getCssValue("color");
        String prod_campaign_price_font_color = prod_campaign_price_web_element.getCssValue("color");
        String prod_regular_price_font_size = prod_regular_price_web_element.getCssValue("font-size");
        String prod_campaign_price_font_size = prod_campaign_price_web_element.getCssValue("font-size");

        // Verify all info
        assert main_product_name.equals(prod_product_name);
        assert main_campaign_price.equals(prod_campaign_price);
        assert main_regular_price.equals(prod_regular_price);
        assert isRed(main_campaign_price_font_color);
        assert isGrey(main_regular_price_font_color);
        assert isRed(prod_campaign_price_font_color);
        assert isGrey(prod_regular_price_font_color);
        assert parseFontSize(main_campaign_price_font_size) > parseFontSize(main_regular_price_font_size);
        assert parseFontSize(prod_campaign_price_font_size) > parseFontSize(prod_regular_price_font_size);
        // NOTE: other font style parameters (bold, line-through) are defined in css for the tags/classes and checked implicitly/manually
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
        //int a = c.getAlpha();
        return r > 0 && g == 0 && b == 0;
    }

    boolean isGrey(String color_string) {
        Color color = Color.fromString(color_string);
        java.awt.Color c = color.getColor();
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        //int a = c.getAlpha();
        return r == g && g == b && b > 0;
    }

    double parseFontSize(String css_font_size) {
        String suffix_px = "px";
        if(css_font_size.endsWith(suffix_px)) {
            return Double.valueOf(css_font_size.substring(0, css_font_size.length() - suffix_px.length()));
        }

        throw new RuntimeException("font-size parsing error");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
