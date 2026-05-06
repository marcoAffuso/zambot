package com.surgi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage<LoginPage> {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorBox = By.cssSelector(".error, .alert-danger, [role='alert']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
    }

    public void enterUsername(String username) {
        typeText(usernameInput, username);
    }

    public void enterPassword(String password) {
        typeText(passwordInput, password);
    }

    public void submit() {
        clickElement(loginButton);
    }

    public String getErrorMessage() {
        return getElementText(errorBox);
    }
}
