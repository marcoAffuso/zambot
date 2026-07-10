package com.surgi.driver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

public class DriverFactory {

    private final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


        private ChromeOptions configChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        chromeOptions.setCapability(ChromeOptions.LOGGING_PREFS, logPrefs);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        chromeOptions.addArguments("--remote-allow-origins=*");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", "it");
        chromeOptions.setExperimentalOption("prefs", prefs);        
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-dev-shm-usage");

        /*
         * queste istruzioni sono state commentate perchè su docker chromiumnon parte
         */
        if(System.getProperty("os.name").equalsIgnoreCase("linux")){
            chromeOptions.setBinary("/usr/bin/chromium-browser");
        }


        if(System.getProperty("headless")!=null && System.getProperty("headless").equalsIgnoreCase("true")){
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--headless=new");

        }

        return chromeOptions;
    }

    public WebDriver createDriver() {
        WebDriver existingDriver = driverThreadLocal.get();
        if (existingDriver != null) {
            return existingDriver;
        }

        if(System.getProperty("os.name").equalsIgnoreCase("linux")){
            System.out.println("sto in linux carico il chromedriver da /usr/bin/chromedriver");
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        }else{
            WebDriverManager.chromedriver().setup();
        }


        ChromeOptions options = configChromeOptions();
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
