package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CountriesTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        FirefoxOptions firefox_options = new FirefoxOptions();
        driver = new FirefoxDriver(firefox_options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void countriesTest() {
        openAdminPageAndLogIn();

        // Check countries sorting
        openCountriesPage();
        List<WebElement> country_table_row_list = getCountryTableRowList();
        List<String> country_name_list = new ArrayList<>();
        List<String> country_zone_count_list = new ArrayList<>();
        for (WebElement element : country_table_row_list) {
            String name = element.findElements(By.xpath(".//td")).get(4).getText();
            country_name_list.add(name);
            String zone_count = element.findElements(By.xpath(".//td")).get(5).getText();
            country_zone_count_list.add(zone_count);
        }
        List<String> country_sorted_name_list = new ArrayList<>(country_name_list);
        country_sorted_name_list.sort(String::compareTo);
        assert country_name_list.equals(country_sorted_name_list);

        // Check country geo zones sorting
        for(int i = 0, n = country_zone_count_list.size(); i < n; ++i)
        {
            // Skip countries without geo zones
            if (country_zone_count_list.get(i).equals("0")) {
                continue;
            }

            openCountriesPage();
            // Open geo zones subpage for the i-th country:
            // - get table row
            WebElement row = getCountryTableRowList().get(i);
            // - get row field
            WebElement field = row.findElements(By.xpath(".//td")).get(4);
            // - get link placeholder
            WebElement link = field.findElement(By.xpath("./a"));
            // - open link
            // NOTE: Obviously, these above code lines can be merged in single one,
            //       but separated lines are more understandable and more debuggable
            link.click();
            // Check geo zones sorting
            List<String> geo_zone_name_list = getGeoZoneNameList();
            assert geo_zone_name_list.size() > 0;
            List<String> geo_zone_name_sorted_list = new ArrayList<>(geo_zone_name_list);
            geo_zone_name_sorted_list.sort(String::compareTo);
            assert geo_zone_name_list.equals(geo_zone_name_sorted_list);
        }
    }

    private void openAdminPageAndLogIn() {
        driver.get("http://localhost/litecart/admin/");
        // Log in
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
//        // Check for header
//        driver.findElement(By.xpath("//h1"));
    }

    private void openCountriesPage() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.xpath("//h1"));
    }

    private List<WebElement> getCountryTableRowList() {
        String xpath = "//form[@name='countries_form']//tr[contains(@class,'row')]";
        List<WebElement> list = driver.findElements(By.xpath(xpath));
        return list;
    }

    private List<String> getGeoZoneNameList() {
        List<String> geo_zone_name_list = new ArrayList<>();
        String xpath = "//table[@id='table-zones']//tr//td[3][./input[@type='hidden']]";
        List<WebElement> geo_zone_list = driver.findElements(By.xpath(xpath));
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
