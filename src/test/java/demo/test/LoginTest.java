package demo.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    String url = "https://saraswati.mp.gov.in/eHRMS_PreProd/main/";

    // Input field locators
    By username = By.id("username");
    By password = By.id("password");
    By captcha = By.id("captcha");

    // Login button
    By loginButton =
            By.xpath("//button[contains(normalize-space(),'Login')]");


    // TC01 - Valid Login
    @Test
    public void validLoginTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {

            driver.get(url);
            driver.manage().window().maximize();

            // Enter Username
            driver.findElement(username)
                    .sendKeys(System.getenv("EHRMS_USERNAME"));

            // Enter Password
            driver.findElement(password)
                    .sendKeys(System.getenv("EHRMS_PASSWORD"));

            System.out.println(
                    "Username and Password entered successfully."
            );

            // Manual CAPTCHA
            System.out.println(
                    "Please enter CAPTCHA manually within 10 seconds..."
            );

            Thread.sleep(10000);

            // DO NOT enter CAPTCHA automatically
            // You will enter CAPTCHA manually in browser.

            // Click Login
            driver.findElement(loginButton).click();

            System.out.println(
                    "TC01 - Valid Login: Login button clicked."
            );

            // TODO:
            // Add dashboard verification here.

        } finally {

            driver.quit();
        }
    }


    // TC02 - Blank Login
    @Test
    public void blankLoginTest() {

        WebDriver driver = new ChromeDriver();

        try {

            driver.get(url);
            driver.manage().window().maximize();

            // Click Login without entering any details
            driver.findElement(loginButton).click();

            System.out.println(
                    "Blank login submitted."
            );

            // Username validation
            String usernameError =
                    driver.findElement(
                            By.xpath(
                                "//*[contains(text(),'Username Required')]"
                            )
                    ).getText();

            // Password validation
            String passwordError =
                    driver.findElement(
                            By.xpath(
                                "//*[contains(text(),'Password Required')]"
                            )
                    ).getText();

            // CAPTCHA validation
            String captchaError =
                    driver.findElement(
                            By.xpath(
                                "//*[contains(text(),'Captcha Required')]"
                            )
                    ).getText();

            System.out.println(
                    "Username Error: " + usernameError
            );

            System.out.println(
                    "Password Error: " + passwordError
            );

            System.out.println(
                    "Captcha Error: " + captchaError
            );

            Assert.assertTrue(
                    usernameError.contains("Username Required"),
                    "Username validation not displayed"
            );

            Assert.assertTrue(
                    passwordError.contains("Password Required"),
                    "Password validation not displayed"
            );

            Assert.assertTrue(
                    captchaError.contains("Captcha Required"),
                    "Captcha validation not displayed"
            );

            System.out.println(
                    "TC02 - Blank Login: PASS"
            );

        } finally {

            driver.quit();
        }
    }


    // TC03 - Username Only
    @Test
    public void usernameOnlyTest() {

        WebDriver driver = new ChromeDriver();

        try {

            driver.get(url);
            driver.manage().window().maximize();

            // Enter only Username
            driver.findElement(username)
                    .sendKeys(System.getenv("EHRMS_USERNAME"));

            // Click Login
            By loginWithUsername = By.linkText("Login With Username");

            // Password validation
            String passwordError =
                    driver.findElement(
                            By.xpath(
                                "//*[contains(text(),'Password Required')]"
                            )
                    ).getText();

            System.out.println(
                    "Password Error: " + passwordError
            );

            Assert.assertTrue(
                    passwordError.contains("Password Required"),
                    "Password validation not displayed"
            );

            System.out.println(
                    "TC03 - Username Only: PASS"
            );

        } finally {

            driver.quit();
        }
    }
}