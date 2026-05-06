package com.surgi.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public WebDriver createDriver() {
        WebDriver existingDriver = driverThreadLocal.get();
        if (existingDriver != null) {
            return existingDriver;
        }

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        WebDriver createdDriver = new ChromeDriver(options);
        driverThreadLocal.set(createdDriver);
        return driverThreadLocal.get();
    }

    public void destroyDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}
