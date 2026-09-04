package com.surgi.hooks;

import com.surgi.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    public void afterScenario(Scenario scenario) throws IOException{

        if(scenario.isFailed()){
            String testName = sanitize(scenario.getName());

            Path artifactDirectory = Path.of(
                "target",
                "failure-artifacts",
                testName
            );

            Files.createDirectories(
                artifactDirectory
            );

            // Nome originale dello scenario
            Files.writeString(
                artifactDirectory.resolve("scenario_name.txt"),
                scenario.getName(),
                StandardCharsets.UTF_8
            );

            // DOM corrente
            Files.writeString(
                artifactDirectory.resolve("page.html"),
                driver.getPageSource(),
                StandardCharsets.UTF_8
            );

            // URL corrente
            Files.writeString(
                artifactDirectory.resolve("current_url.txt"),
                driver.getCurrentUrl(),
                StandardCharsets.UTF_8
            );

            // Screenshot
            // byte[] screenshot = (
            //     (TakesScreenshot) driver
            // ).getScreenshotAs(
            //     OutputType.BYTES
            // );

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] screenshot = Files.readAllBytes(screenshotFile.toPath());
            scenario.attach(
                screenshot,
                "image/png",
                "screenshot.png"
            );
 
            Files.write(
                artifactDirectory.resolve("screenshot.png"),
                screenshot
            );
        }

        driverFactory.destroyDriver();
        driver = null;
    }

    private String sanitize(String name) {
        return name.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
    }
}
