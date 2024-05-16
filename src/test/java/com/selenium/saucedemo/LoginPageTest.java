package com.selenium.saucedemo;

import com.selenium.BrowserType;
import com.selenium.WebDriverFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest {

    static WebDriver driver;
    static String url;

    @BeforeAll
    public static void setUp() {
        url = "https://www.saucedemo.com/";
    }

    @BeforeEach
    public void init() {
        driver = WebDriverFactory.createWebDriver(BrowserType.CHROME);
        driver.get(url);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.validLogin("standard_user", "secret_sauce");

        assertNotEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals(productsPage.getMessageTxt(), "Products");
    }

    @Test
    public void testLockedOutUserLogin() {
        LoginPage loginPage = new LoginPage(driver);
        String errorMsg = loginPage.invalidLogin("locked_out_user", "secret_sauce");

        assertTrue(errorMsg.contains("user has been locked out"));
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
    }
}