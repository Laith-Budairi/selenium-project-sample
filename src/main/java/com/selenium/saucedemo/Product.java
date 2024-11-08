package com.selenium.saucedemo;

import com.selenium.base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product extends BaseComponent {

    private String name;
    private BigDecimal price;


    public Product(WebElement element) {
        super(element);
    }

    public String getName() {
        if (name == null)
            name = root.findElement(By.className("inventory_item_name")).getText();

        return name;
    }

    public BigDecimal getPrice() {
        if (price == null)
            price = new BigDecimal(
                    root.findElement(By.className("inventory_item_price"))
                            .getText()
                            .replace("$", "")
            ).setScale(2, RoundingMode.UNNECESSARY);
        return price; // Sanitation and formatting
    }

    public void addRemoveFromCart() {
        root.findElement(By.tagName("button")).click();
    }

    public WebElement getProductBtn() {
        return root.findElement(By.tagName("button"));
    }
}
