package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.support.ui.WebDriverWait;


public class ProductAddTest {
    private WebDriver driver; // WARNING: NOT thread-safe yet
    //private WebDriverWait wait;
    private DateTimeFormatter date_formatter;

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
        date_formatter = DateTimeFormatter.ofPattern("MMdduuuu");
        testInternal();
    }

    @Test
    public void chromeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        date_formatter = DateTimeFormatter.ofPattern("ddMMuuuu");
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
        // - General subpage
        //driver.findElement(By.xpath("//li/a[.='General']")).click();
        // -- do enable product
        driver.findElement(By.xpath("//input[@type='radio' and @name='status' and @value='1']")).click();
        // -- fill product name
        String product_name = "Magic Stick";
        fillFieldByName("name[en]", product_name);
        // -- fill product code
        String product_code = "magic-stick-001";
        fillFieldByName("code", product_code);
        // -- select categories
        // keep as is
        // -- select default category
        // keep as is
        // -- select product groups
        driver.findElement(By.xpath("//input[@type='checkbox' and @name='product_groups[]' and @value='1-1']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox' and @name='product_groups[]' and @value='1-2']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox' and @name='product_groups[]' and @value='1-3']")).click();
        // -- fill product quantity
        String product_quantity = "1";
        fillFieldByName("quantity", product_quantity);
        // -- select quantity unit
        // keep as is
        // -- select delivery status
        // keep as is
        // -- select sold out status
        // keep as is
        // -- upload images
        // TODO
        // -- date valid from
        LocalDate product_date_valid_from = LocalDate.of(2001, 01, 01);
        String product_date_valid_from_str = product_date_valid_from.format(date_formatter);
        fillDateFieldByName("date_valid_from", product_date_valid_from_str);
        // -- date valid to
        LocalDate product_date_valid_to = LocalDate.of(2002, 12, 31);
        String product_date_valid_to_str = product_date_valid_to.format(date_formatter);
        fillDateFieldByName("date_valid_to", product_date_valid_to_str);
        // - Information product
        driver.findElement(By.xpath("//li/a[.='Information']")).click();
        // TODO
        // - Prices subpage
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
        driver.findElement(By.name(name)).click();
        driver.findElement(By.name(name)).clear();
        driver.findElement(By.name(name)).sendKeys(value);
    }

    private void fillDateFieldByName(String name, String value) {
        driver.findElement(By.name(name)).click();
        driver.findElement(By.name(name)).clear();
        driver.findElement(By.name(name)).sendKeys("" + Keys.ARROW_LEFT + Keys.ARROW_LEFT);
        driver.findElement(By.name(name)).sendKeys(value);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
