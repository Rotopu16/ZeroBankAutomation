package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.pages.OnlineBankingPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class LoginStepDefs {

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        new LoginPage().login(ConfigurationReader.get("username"), ConfigurationReader.get("password"));
        Assert.assertTrue(Driver.get().getTitle().contains("Online Banking"));
    }

    @When("the user clicks on {string} link on the {string} page")
    public void the_user_clicks_on_link_on_the_page(String link, String page) {
        OnlineBankingPage onlineBankingPage = new OnlineBankingPage();
        onlineBankingPage.navigateToPage(page);
        onlineBankingPage.navigateToLink(link);
    }

    @Then("the {string} page should be displayed")
    public void thePageShouldBeDisplayed(String page) {
        BrowserUtils.waitFor(2);
        Assert.assertTrue(Driver.get().getTitle().contains(page));
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        new LoginPage().goTologinPage();
    }

    @When("the user enters wrong {string} or {string}")
    public void theUserEntersWrongOr(String username, String password) {
        new LoginPage().loginInvalid(username, password);
    }

    @Then("the user get error message {string}")
    public void theUserGetErrorMessage(String expectedMessage) {
        BrowserUtils.waitFor(2);
        Assert.assertEquals("Verify wrong username or password", expectedMessage,new LoginPage().errorMessage.getText()
        );
    }

    @When("the user enters {string} and {string}")
    public void theUserEntersAnd(String username, String password) {
        new LoginPage().login(username, password);
    }

    @Then("{string} page should be displayed")
    public void pageShouldBeDisplayed(String pageName) {
        OnlineBankingPage onlineBankingPage = new OnlineBankingPage();
        onlineBankingPage.navigateToPage(pageName);
        BrowserUtils.waitFor(2);
        Assert.assertTrue("Verify the" +pageName+" page", Driver.get().getTitle().contains(pageName));

    }
}
