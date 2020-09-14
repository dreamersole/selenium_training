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

    @Test
    public void findElementTest() {
        // Open admin page
        driver.get("http://localhost/litecart/admin/");
        // Log in
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        // Go through the left menu items
        String[] top_name_list = {
                "Appearence",
                "Catalog",
                "Countries",
                "Currencies",
                "Customers",
                "Geo Zones",
                "Languages",
                "Modules",
                "Orders",
                "Pages",
                "Reports",
                "Settings",
                "Slides",
                "Tax",
                "Translations",
                "Users",
                "vQmods"
        };
        String[][] sub_name_list = {
                {"Template", "Logotype"},
                {"Catalog", "Product Groups", "Option Groups", "Manufacturers", "Suppliers", "Delivery Statuses", "Sold Out Statuses", "Quantity Units", "CSV Import/Export"},
                {},
                {},
                {"Customers", "CSV Import/Export", "Newsletter"},
                {},
                {"Languages", "Storage Encoding"},
                {"Background Jobs", "Customer", "Shipping", "Payment", "Order Total", "Order Success", "Order Action"},
                {"Orders", "Order Statuses"},
                {},
                {"Monthly Sales", "Most Sold Products", "Most Shopping Customers"},
                {"Store Info", "Defaults", "General", "Listings", "Images", "Checkout", "Advanced", "Security"},
                {},
                {"Tax Classes", "Tax Rates"},
                {"Search Translations", "Scan Files", "CSV Import/Export"},
                {},
                {"vQmods"}
        };
        String top_xpath;
        String sub_xpath;
        WebElement top_item;
        for(int i = 0, n = top_name_list.length; i < n; ++i)
        {
            // top-item
            top_xpath = "//ul[@id='box-apps-menu']//li[.//*[.='" + top_name_list[i] + "']]";
            driver.findElement(By.xpath(top_xpath)).click();
            driver.findElement(By.xpath("//h1"));
            for(int j = 0, m = sub_name_list[i].length; j < m; ++j)
            {
                // sub-item
                sub_xpath = ".//li[.//*[.='" + sub_name_list[i][j] + "']]";
                top_item = driver.findElement(By.xpath(top_xpath));
                top_item.findElement(By.xpath(sub_xpath)).click();
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
