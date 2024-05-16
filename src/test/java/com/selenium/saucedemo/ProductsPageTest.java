package com.selenium.saucedemo;

import com.selenium.BrowserType;
import com.selenium.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductsPageTest {

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
    public void testAddRemoveProducts() {
        // validLogin
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.validLogin("standard_user", "secret_sauce");
        assertEquals(productsPage.getMessageTxt(), "Products");

        // add/remove products

        for (Product p : productsPage.getProducts()) {
            // assert default btn text
            assertEquals("Add to cart", p.getProductBtn().getText());

            // assert after add to cart
            p.addRemoveFromCart();
            assertEquals("Remove", p.getProductBtn().getText());

            // assert after remove from cart
            p.addRemoveFromCart();
            assertEquals("Add to cart", p.getProductBtn().getText());
        }

    }

    @Test
    public void TestSortProductsByPriceDescendingOrder() {
        // validLogin
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.validLogin("standard_user", "secret_sauce");
        assertEquals(productsPage.getMessageTxt(), "Products");

        // sort descending order
        productsPage.sort(SortProductBy.HIGH_LOW);;
        List<Product> products = productsPage.getProducts();

        int size = products.size();
        for (int i = 0; i < size - 1; i++) {
            assertTrue(products.get(i).getPrice().compareTo(products.get(i + 1).getPrice()) >= 0);
        }
    }

    @Test
    public void TestSortProductsByPriceAscendingOrder() throws InterruptedException {
        // validLogin
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.validLogin("standard_user", "secret_sauce");
        assertEquals(productsPage.getMessageTxt(), "Products");

        // sort ascending order
        productsPage.sort(SortProductBy.LOW_HIGH);;List<Product> products = productsPage.getProducts();

        int size = products.size();
        for (int i = 0; i < size - 1; i++) {
            assertTrue(products.get(i).getPrice().compareTo(products.get(i + 1).getPrice()) <= 0);
        }
    }

    @Test
    public void TestSortProductsByNameAscendingOrder() throws InterruptedException {
        // validLogin
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.validLogin("standard_user", "secret_sauce");
        assertEquals(productsPage.getMessageTxt(), "Products");
        Thread.sleep(4000);
        // sort ascending order
        productsPage.sort(SortProductBy.Z_A);;
        Thread.sleep(4000);
        List<Product> products = productsPage.getProducts();

        int size = products.size();
        for (int i = 0; i < size - 1; i++) {
            assertTrue(products.get(i).getName().compareTo(products.get(i + 1).getName()) >= 0);
        }
    }

    @Test
    public void TestSortProductsByNameDescendingOrder() throws InterruptedException {
        // validLogin
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.validLogin("standard_user", "secret_sauce");
        assertEquals(productsPage.getMessageTxt(), "Products");
        Thread.sleep(4000);
        // sort ascending order
        productsPage.sort(SortProductBy.A_Z);;
        Thread.sleep(4000);
        List<Product> products = productsPage.getProducts();

        int size = products.size();
        for (int i = 0; i < size - 1; i++) {
            assertTrue(products.get(i).getName().compareTo(products.get(i + 1).getName()) <= 0);
        }
    }
}