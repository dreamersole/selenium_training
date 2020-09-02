package org.example;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HelloTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void helloTest() {
        driver.get("https://ru.wikipedia.org/");
        driver.findElement(By.id("searchInput")).sendKeys("hello world");
        driver.findElement(By.id("searchButton")).click();
        wait.until(titleContains(" Википедия"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
