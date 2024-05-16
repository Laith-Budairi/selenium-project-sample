package com.selenium.saucedemo;

import com.selenium.BrowserType;
import com.selenium.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartPageTest {

    static WebDriver driver;
    static String url;

    @BeforeAll
    public static void setUp() {
        url = "https://www.saucedemo.com/";
    }

    @BeforeEach
    public void init() {
        driver = WebDriverFactory.createWebDriver(BrowserType.FIREFOX);
        driver.get(url);
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testAddProductsToCart() {
        // validLogin
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
        backPack.addRemoveFromCart();
        bikeLight.addRemoveFromCart();

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