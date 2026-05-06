package com.surgi.hooks;

import com.surgi.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks implements HooksInterface {

    private DriverFactory driverFactory;
    private WebDriver driver;

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void beforeScenario() {
        driverFactory = new DriverFactory();
        driver = driverFactory.createDriver();
    }

    @After
    public void afterScenario() {
        driverFactory.destroyDriver();
        driver = null;
    }
}
