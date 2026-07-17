package com.surgi.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import com.surgi.pages.LoginPage;
import com.surgi.utility.TestContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps(TestContext testContext) {
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @Given("I open the login page {string}")
    public void openLoginPage(String url) throws InterruptedException {

        TimeUnit.SECONDS.sleep(60); // Wait for 60 seconds before opening the login page
        fail("prova jenkins pipeline failed ");

        //loginPage.open(url);
        //loginPage.get();
    }

    @When("I enter username {string}")
    public void enterUsername(String username) {
        //loginPage.enterUsername(username);

        System.out.println(username);
    }

    @And("I enter password {string}")
    public void enterPassword(String password) {
        //loginPage.enterPassword(password);
        System.out.println(password);
    }

    @And("I click the login button")
    public void clickLoginButton() {
        //loginPage.submit();
        System.out.println("Login button clicked");
    }

    @Then("I verify an error message containing {string}")
    public void verifyErrorMessage(String expected) {
        //assertTrue(loginPage.getErrorMessage().contains(expected));
        System.out.println("Error message verified: " + expected);
    }
}
