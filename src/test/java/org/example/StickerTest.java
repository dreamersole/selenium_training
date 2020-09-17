package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StickerTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        //wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void stickerTest() {
        // Open main page
        driver.get("http://localhost/litecart/");
        // Find products shown on the main page
        List<WebElement> product_list = driver.findElements(By.cssSelector("li.product"));
        // Check for sticker
        for (int i = 0, n = product_list.size(); i < n; ++i) {
            assert product_list.get(i).findElements(By.cssSelector("div.sticker")).size() == 1;
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
