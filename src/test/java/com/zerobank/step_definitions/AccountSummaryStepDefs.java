package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.OnlineBankingPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class AccountSummaryStepDefs {

    @Then("the title should be {string}")
    public void theTitleShouldBe(String pageTitle) {
        BrowserUtils.waitFor(2);

        System.out.println("Driver.get().getTitle() = " + Driver.get().getTitle());

       Assert.assertEquals("Verify the page",pageTitle, Driver.get().getTitle().trim());
    }

    @Then("the the following account types should be seen")
    public void theTheFollowingAccountTypesShouldBeSeen(List<String>accountsNames) {

        System.out.println("accountsNames = " + accountsNames);
        for (String accountName : accountsNames) {
            WebElement accountType = Driver.get().findElement(By.xpath("//h2[contains(text(),'"+accountName+"')]"));
            System.out.println("accountType.getText() = " + accountType.getText());
            Assert.assertEquals("Verify the account type", accountName,accountType.getText());
        }
    }

    @Then("Credit Accounts table must have following columns")
    public void creditAccountsTableMustHaveFollowingColumns(List<String>columnNames) {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        String headerText = accountSummaryPage.CreditCardTableHeaders.getText();
        for (String columnName : columnNames) {
            Assert.assertTrue(headerText.contains(columnName));
        }
    }

    @When("Navigate to {string} page")
    public void navigateToPage(String pageName) {
        OnlineBankingPage onlineBankingPage = new OnlineBankingPage();
        onlineBankingPage.navigateToPage(pageName);
        BrowserUtils.waitFor(2);
        Assert.assertTrue("Verify the" +pageName+" page", Driver.get().getTitle().contains(pageName));
    }
}
