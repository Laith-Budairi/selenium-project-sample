package com.selenium.saucedemo;

import base.ILoginPage;
import com.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage extends BasePage implements ILoginPage <ProductsPage>{

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    private String errorMsg;

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Override
    public ProductsPage validLogin(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginBtn.click();
        return new ProductsPage(driver);
    }

    @Override
    public String invalidLogin(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginBtn.click();
        return driver.findElement(By.xpath("//*[@data-test='error']")).getText();
    }
}
