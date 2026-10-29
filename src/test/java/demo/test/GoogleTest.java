package demo.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest {

    @Test
    public void openGoogle() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Google";

        System.out.println("Page Title: " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle);

        driver.quit();
    }
}