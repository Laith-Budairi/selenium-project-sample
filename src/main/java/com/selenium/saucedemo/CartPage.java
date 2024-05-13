package com.selenium.saucedemo;

import com.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<Product> getCartItems() {
        return driver.findElement(By.className("cart_list")).findElements(By.className("cart_item"))
                .stream()
                .map(Product::new) // Map WebElement to a product component
                .collect(Collectors.toList());
    }
}
