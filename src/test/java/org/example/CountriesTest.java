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
        // Firefox driver
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
        for (WebElement element : country_table_row_list) {
            String name = element.findElements(By.xpath(".//td")).get(4).getText();
            country_name_list.add(name);
        }
        List<String> country_sorted_name_list = new ArrayList<>(country_name_list);
        country_sorted_name_list.sort(String::compareTo);
        assert country_name_list.equals(country_sorted_name_list);

        // Check country geo zones sorting
        boolean open_countries_page_required = true;
        for(int i = 0, n = getCountryTableRowList().size(); i < n; ++i)
        {
            // Go to countries page if required
            if(open_countries_page_required) {
                openCountriesPage();
                open_countries_page_required = false;
            }

            // Skip countries without geo zones
            List<WebElement> table_row_item_list = getCountryTableRowList().get(i).findElements(By.xpath(".//td"));
            String count_txt_value = table_row_item_list.get(5).getText();
            if (count_txt_value.equals("0")) {
                continue;
            }

            // Check geo zones sorting
            open_countries_page_required = true;
            table_row_item_list.get(4).click();
            List<String> geo_zone_name_list = getGeoZoneNameList();
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
        String xpath = "//table[@id='table-zones']//tr//td[3]";
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
