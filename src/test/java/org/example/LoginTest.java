package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @Test
    public void findElementTest() {
        // Open admin page
        driver.get("http://localhost/litecart/admin/");
        // Log in
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        // Go through the left menu items
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Appearence']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Catalog']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Countries']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Currencies']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Customers']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Geo Zones']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Languages']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Modules']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Orders']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Pages']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Reports']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Settings']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Slides']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Tax']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Translations']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='Users']")).click();
        driver.findElement(By.xpath("//h1"));
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//*[.='vQmods']")).click();
        driver.findElement(By.xpath("//h1"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
