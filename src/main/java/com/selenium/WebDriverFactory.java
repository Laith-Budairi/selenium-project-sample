package com.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    public static WebDriver createWebDriver(BrowserType browserType) {
        WebDriver driver = null;

        switch (browserType) {
            case CHROME:
                // Initialize ChromeDriver and assign it to the driver variable
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
//                options.addArguments("--headless");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                // Initialize FirefoxDriver and assign it to the driver variable
                FirefoxOptions option = new FirefoxOptions();
//                option.addArguments("--remote-allow-origins=*");
//                option.addArguments("--headless");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(option);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        return driver;
    }
}
