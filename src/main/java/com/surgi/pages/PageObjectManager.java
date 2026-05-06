package com.surgi.pages;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private final WebDriver webDriver;
    private LoginPage loginPage;

    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(webDriver);
        }
        return loginPage;
    }
}