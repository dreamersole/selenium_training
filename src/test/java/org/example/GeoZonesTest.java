package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GeoZonesTest {

    public WebDriver driver;
    //public WebDriverWait wait;

    @Before
    public void start() {
        FirefoxOptions firefox_options = new FirefoxOptions();
        driver = new FirefoxDriver(firefox_options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void geoZonesTest() {
        openAdminPageAndLogin();
        openGeoZonesPage();
        // Iterate through the country list and check each one as follows
        for(int i = 0, n = getCountryList().size(); i < n; ++i)
        {
            openGeoZonesPage();
            // open the i-th country subpage
            getCountryList().get(i).click();
            driver.findElement(By.xpath("//h1"));
            // check sorting for the i-th country
            List<String> geo_zone_name_list = getGeoZoneNameList();
            List<String> geo_zone_name_sorted_list = new ArrayList<>(geo_zone_name_list);
            geo_zone_name_sorted_list.sort(String::compareTo);
            assert geo_zone_name_list.equals(geo_zone_name_sorted_list);
        }
    }

    private void openAdminPageAndLogin() {
        driver.get("http://localhost/litecart/admin/");
        // Log in
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private void openGeoZonesPage() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
    }

    private List<WebElement> getCountryList() {
        String xpath = "//form[@name='geo_zones_form']//tr[contains(@class,'row')]/td[3]/a";
        List<WebElement> list = driver.findElements(By.xpath(xpath));
        return list;
    }

    private List<String> getGeoZoneNameList() {
        String xpath = "//table[@id='table-zones']//tr//td[3]//option[@selected]";
        List<WebElement> geo_zone_list = driver.findElements(By.xpath(xpath));
        List<String> geo_zone_name_list = new ArrayList<>();
        for (WebElement webElement : geo_zone_list) {
            String name = webElement.getText();
            geo_zone_name_list.add(name);
        }
        return geo_zone_name_list;
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
