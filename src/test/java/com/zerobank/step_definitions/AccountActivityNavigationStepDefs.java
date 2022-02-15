package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountActivityNavigationStepDefs {

    @Then("Account drop down should have {string} selected")
    public void account_drop_down_should_have_selected(String selection) {
        Select accountDropdown = new Select(new AccountActivityPage().dropdownElement);
        String actualSelected= accountDropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(selection,actualSelected);

    }


    @Then("Account drop down should have the following options")
    public void accountDropDownShouldHaveTheFollowingOptions(List<String>expectedDropDownOptions) {
        Select accountDropdown = new Select(new AccountActivityPage().dropdownElement);
        List<WebElement> options = accountDropdown.getOptions();
        System.out.println("options.size() = " + options.size());

        boolean isDropDownThere = false;
        for (String expectedDropDownOption : expectedDropDownOptions) {
            isDropDownThere = false;
            for (WebElement option : options) {
                if (option.getText().equals(expectedDropDownOption)){
                    isDropDownThere=true;
                }
            }
            Assert.assertTrue("Verify"+expectedDropDownOption+"is present", isDropDownThere);
        }
   }

    @Then("Transactions table should have following column names")
    public void transactionsTableShouldHaveFollowingColumnNames(List<String>columnNames) {
        boolean isTrue=false;
        for (String columnName : columnNames) {
            isTrue=false;
            System.out.println("columnName = " + columnName);
            for (WebElement element : new AccountActivityPage().tableHeaders()) {
                if (element.getText().equals(columnName)){
                    System.out.println("element.getText() = " + element.getText());
                    isTrue=true;
                }
            }
            Assert.assertTrue("Verify"+columnName+"exists", isTrue);
        }
    }
}
