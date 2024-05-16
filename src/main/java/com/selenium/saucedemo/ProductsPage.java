package com.selenium.saucedemo;

import com.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {


    @FindBy(css = "span.title")
    private WebElement messageTxt;

    @FindBy(className = "product_sort_container")
    private WebElement productSortDropDown;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<Product> getProducts() {
        return driver.findElement(By.className("inventory_list")).findElements(By.className("inventory_item"))
                .stream()
                .map(Product::new) // Map WebElement to a product component
                .collect(Collectors.toList());
    }


    public String getMessageTxt() {
        return messageTxt.getText();
    }

    public Product getProduct(Predicate<Product> condition) {
        return getProducts()
                .stream()
                .filter(condition) // Filter by product name or price
                .findFirst()
                .orElseThrow();
    }

    public CartPage navigateToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
        return new CartPage(driver);
    }

    public void sort(SortProductBy sortType) {
        Select dropdown = new Select(productSortDropDown);

        switch (sortType) {
            case A_Z:
                dropdown.selectByValue("az");
                break;
            case Z_A:
                dropdown.selectByValue("za");
                break;
            case LOW_HIGH:
                dropdown.selectByValue("lohi");
                break;
            case HIGH_LOW:
                dropdown.selectByValue("hilo");
                break;
            default:
//                throw new Exception("Invalid sort option: " + sortType);
        }


    }
}
