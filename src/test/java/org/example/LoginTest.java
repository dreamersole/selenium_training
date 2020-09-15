package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class LoginTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        // Firefox driver
        FirefoxOptions firefox_options = new FirefoxOptions();
        // firefox_options.setLegacy(true);
        // firefox_options.setBinary("");
        driver = new FirefoxDriver(firefox_options);
        // // Chromium driver
        // ChromeOptions chrome_options = new ChromeOptions();
        // chrome_options.setBinary("/usr/bin/chromium");
        // driver = new ChromeDriver(chrome_options);
        // wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void loginTest() {
        // Open admin page
        driver.get("http://localhost/litecart/admin/");
        // Log in
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }



    List<WebElement> getTopList() {
        String top_xpath = "//ul[@id='box-apps-menu']/li"; // top items only: "/li"
        return driver.findElements(By.xpath(top_xpath));
    }

    List<WebElement> getSubList(WebElement top_item) {
        String sub_xpath = ".//li";
        return top_item.findElements(By.xpath(sub_xpath));
    }

    @Test
    public void findElementTest() {
        // Open admin page
        driver.get("http://localhost/litecart/admin/");
        // Log in
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        // Go through the left menu items
        for(int i = 0, n = getTopList().size(); i < n; ++i)
        {
            // top-item
            getTopList().get(i).click();
            driver.findElement(By.xpath("//h1"));
            WebElement top_item = getTopList().get(i);
            for(int j = 0, m = getSubList(top_item).size(); j < m; ++j)
            {
                // sub-item
                top_item = getTopList().get(i);
                getSubList(top_item).get(j).click();
                driver.findElement(By.xpath("//h1"));
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
