package com.selenium.saucedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginPageTest {

    static int browser = 0;
    WebDriver driver;
    static ChromeOptions options;
    static String url;

    @BeforeAll
    public static void setUp() throws Exception {
        url = "https://www.saucedemo.com/";
        if(browser == 0) {
            options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
        }

    }

    @BeforeEach
    public void init() {
        if(browser == 0)
            driver = new ChromeDriver(options);
        driver.get(url);
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
    }


    @Test
    public void testLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.validLogin("standard_user", "secret_sauce");
        assertEquals(productsPage.getMessageTxt(), "Products");
    }
}