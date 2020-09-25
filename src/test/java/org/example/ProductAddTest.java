package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import sun.security.util.Debug;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.support.ui.WebDriverWait;


public class ProductAddTest {
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
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        testInternal();
    }

    @Test
    public void chromeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        testInternal();
    }

    private void testInternal() {
        // Open admin page and login
        openAdminPageAndLogIn();
        // Go to Catalog admin page
        driver.findElement(By.xpath("//span[.='Catalog']")).click();
        // Initiate product adding
        driver.findElement(By.xpath("//a[contains(.,'Add New Product')]")).click();
        // Fill product form
        // - General
        //driver.findElement(By.xpath("//li/a[.='General']")).click();
        String product_name = "Magic Stick";
        fillFieldByName("name[en]", product_name);
        // TODO
        // - Information
        driver.findElement(By.xpath("//li/a[.='Information']")).click();
        // TODO
        // - Prices
        driver.findElement(By.xpath("//li/a[.='Prices']")).click();
        // TODO
        // Submit
        driver.findElement(By.xpath("//button[@name='save']")).click();
        // Go to Catalog and check the product existing
        // TODO
    }

    private void openAdminPageAndLogIn() {
        openAdminPage();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private void openAdminPage() {
        driver.get("http://localhost/litecart/admin/");
    }

    private void fillFieldByName(String name, String value) {
        driver.findElement(By.name(name)).sendKeys(value);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
