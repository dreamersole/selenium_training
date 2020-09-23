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
        driver.findElement(By.xpath("//td[contains(., 'Country')]/span[contains(@class,'select2')]")).click();
        driver.findElement(By.xpath("//body//input[contains(@class, 'select2-search')]")).sendKeys("United States" + Keys.ENTER);
        //driver.findElement(By.xpath("//td[contains(., 'Country')]//option[.='United States']")).click(); // BUG: dropdown list not closed yet in test
        //driver.findElement(By.xpath("//body//input[contains(@class, 'select2-search')]")).sendKeys(Keys.ESCAPE); // WORKAROUND: force closing dropdown list
        final String email = getRandomEmail();
        System.out.println("EMAIL GENERATED: " + email);
        fillFieldByName("email", email);
        fillFieldByName("phone", "+1234567890");
        final String password = "secret";
        fillFieldByName("password", password);
        fillFieldByName("confirmed_password", password);
        // Submit (and implicit login)
        driver.findElement(By.cssSelector("button[name=create_account]")).click();
        //sleep_1sec();
        // Logout
        logout();
        // Login #2
        login(email, password);
        // Logout #2
        logout();
    }

    private void openMainPage() {
        driver.get("http://localhost/litecart/");
    }

    private void fillFieldByName(String name, String value) {
        driver.findElement(By.name(name)).sendKeys(value);
    }

    static SecureRandom random = new SecureRandom();
    static final String chars_main_first = "abcdefghijklmnopqrstuvwxyz";
    static final String chars_main_middle = "abcdefghijklmnopqrstuvwxyz1234567890.-_";
    static final String chars_main_last = "abcdefghijklmnopqrstuvwxyz1234567890";
    static final String chars_domain_first = "abcdefghijklmnopqrstuvwxyz";
    static final String chars_domain_middle = "abcdefghijklmnopqrstuvwxyz.";
    static final String chars_domain_last = "abcdefghijklmnopqrstuvwxyz";

    private String getRandomEmail() {
        final int main_length = 2 + random.nextInt(18);
        final int domain_length = 2 + random.nextInt(13);
        StringBuilder stringBuilder = new StringBuilder();
        // email main part
        stringBuilder.append(getRandomString(chars_main_first, 1));
        stringBuilder.append(getRandomString(chars_main_middle, main_length));
        stringBuilder.append(getRandomString(chars_main_last, 1));
        // email domain
        stringBuilder.append("@");
        stringBuilder.append(getRandomString(chars_domain_first, 1));
        stringBuilder.append(getRandomString(chars_domain_middle, domain_length));
        stringBuilder.append(getRandomString(chars_domain_last, 1));
        return stringBuilder.toString();
    }

    private String getRandomString(String chars, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < length; ++i) {
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }

    private void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();
        //sleep_1sec();
    }

    private void logout() {
        driver.findElement(By.linkText("Logout")).click();
        //sleep_1sec();
    }

    private void sleep_1sec() {
        try {
            Thread.sleep(1000); // milliseconds
        } catch (InterruptedException e) {
            System.out.println("PAUSE INTERRUPTED");
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
