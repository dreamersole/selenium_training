package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.security.SecureRandom;
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
        // Open main page
        openMainPage();
        // Initiate user account creating
        driver.findElement(By.xpath("//a[contains(@href, 'create_account')]")).click();
        // Fill user account form
        fillFieldByName("firstname", "Testfirstname");
        fillFieldByName("lastname", "Testlastname");
        fillFieldByName("address1", "Test Address 1");
        fillFieldByName("postcode", "12345");
        fillFieldByName("city", "Test City");
        // TODO: Country
        // TODO: State
        fillFieldByName("email", getRandomEmail());
        fillFieldByName("phone", "+1234567890");
        fillFieldByName("password", "secret");
        fillFieldByName("confirmed_password", "secret");
        // TODO
        // Submit (and implicit login)
        // TODO
        // Logout
        // TODO
        // Login #2
        // TODO
        // Logout #2
    }

    private void openMainPage() {
        driver.get("http://localhost/litecart/");
    }

    private void fillFieldByName(String name, String value) {
        driver.findElement(By.name(name)).sendKeys(value);
    }

    static SecureRandom random = new SecureRandom();
    static final String charsA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    static final String charsB = charsA + ".-_";

    private String getRandomEmail() {
        int length1 = 1 + random.nextInt(20);
        int length2 = 1 + random.nextInt(15);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(charsA.charAt(random.nextInt(charsA.length())));
        for(int i = 1; i < length1; ++i) {
            stringBuilder.append(charsB.charAt(random.nextInt(charsB.length())));
        }
        stringBuilder.append("@");
        stringBuilder.append(charsA.charAt(random.nextInt(charsA.length())));
        for(int i = 1; i < length2; ++i) {
            stringBuilder.append(charsB.charAt(random.nextInt(charsB.length())));
        }
        return stringBuilder.toString();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
