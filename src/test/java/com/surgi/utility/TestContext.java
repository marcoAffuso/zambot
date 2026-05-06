package com.surgi.utility;

import org.openqa.selenium.WebDriver;
import com.surgi.hooks.HooksInterface;
import com.surgi.pages.PageObjectManager;

public class TestContext {
    private WebDriver driver;
    private HooksInterface hooks;
    private PageObjectManager pageObjectManager;

    public TestContext(HooksInterface hooks) {
        this.hooks = hooks;
        this.driver = hooks.getDriver();
    }

    public PageObjectManager getPageObjectManager() {
        if (this.pageObjectManager == null) {
            this.pageObjectManager = new PageObjectManager(this.driver);
        }
        return this.pageObjectManager;
    }
}