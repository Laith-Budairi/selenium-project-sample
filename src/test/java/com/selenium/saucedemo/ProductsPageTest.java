package com.selenium.saucedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductsPageTest {

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
    public void testAddProductsInCart() {
        // login
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.validLogin("standard_user", "secret_sauce");
        assertEquals(productsPage.getMessageTxt(), "Products");

        // Find 2 products to add to cart
        List<Product> products = productsPage.getProducts();
        Product backPack = productsPage.getProduct(p -> p.getName().equals("Sauce Labs Backpack"));
        Product bikeLight = productsPage.getProduct(p -> p.getName().equals("Sauce Labs Bike Light"));
        backPack.getPrice();
        bikeLight.getPrice();

        // add products to cart
        backPack.addToCart();
        bikeLight.addToCart();

        // navigate to cart
        CartPage cartPage = productsPage.navigateToCart();

        // assert cart items
        List<Product> cartItems = cartPage.getCartItems();

        assertEquals(2, cartItems.size());
        assertEquals(backPack.getName(), cartItems.get(0).getName());
        assertEquals(backPack.getPrice(), cartItems.get(0).getPrice());
        assertEquals(bikeLight.getName(), cartItems.get(1).getName());
        assertEquals(bikeLight.getPrice(), cartItems.get(1).getPrice());

    }
}