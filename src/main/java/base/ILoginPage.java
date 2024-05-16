package base;

import com.selenium.base.BasePage;
import com.selenium.saucedemo.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public interface ILoginPage <T> {

    public T validLogin(String username, String password);

    public String invalidLogin(String username, String password) ;
}
