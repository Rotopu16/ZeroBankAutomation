package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PayBillsStepdefs {

    PayBillsPage payBillsPage = new PayBillsPage();

    @Given("{string} tab")
    public void tab(String text) {
        BrowserUtils.waitFor(2);
        Assert.assertEquals(text, payBillsPage.AddNewPayeeText.getText());
    }

    @And("creates new payee using following information")
    public void createsNewPayeeUsingFollowingInformation(Map<String, String> payeeInfo) {
        System.out.println("payeeInfo = " + payeeInfo);
        payBillsPage.PayeeName.sendKeys(payeeInfo.get("Payee Name"));
        payBillsPage.PayeeAdress.sendKeys(payeeInfo.get("Payee Address"));
        payBillsPage.PayeeAccount.sendKeys(payeeInfo.get("Account"));
        payBillsPage.PayeeDetails.sendKeys(payeeInfo.get("Payee details"));
        BrowserUtils.waitFor(1);
        payBillsPage.AddButton.click();
    }

    @Then("message {string} should be displayed")
    public void messageShouldBeDisplayed(String expectedText) {
        BrowserUtils.waitFor(1);
        String actualText = payBillsPage.PayeeAddedText.getText();
        System.out.println("actualText = " + actualText);
        Assert.assertEquals("Verify added message", expectedText, actualText);
    }

    @Given("the user accesses the Purchase foreign currency cash tab")
    public void theUserAccessesThePurchaseForeignCurrencyCashTab() {
        BrowserUtils.waitFor(2);
        Assert.assertEquals("Purchase Foreign Currency", payBillsPage.PurchaseForeignCurrencyText.getText());
    }

    @Then("following currencies should be available")
    public void followingCurrenciesShouldBeAvailable(List<String> currencies) {
        System.out.println("currencies = " + currencies);
        Select currenciesDropdown = new Select(payBillsPage.dropdownCurrenciesElement);
        List<WebElement> options = currenciesDropdown.getOptions();
        System.out.println("options.size() = " + options.size());

        for (WebElement option : options) {
            System.out.println("option.getText() = " + option.getText());
        }
        boolean isCurrencyAvailable = false;
        for (String currency : currencies) {
            isCurrencyAvailable = false;
            for (WebElement option : options) {
                if (option.getText().equals(currency)) {
                    isCurrencyAvailable = true;
                }
            }
            Assert.assertTrue("Verify the" + currency + " is available ", isCurrencyAvailable);
        }
    }

    @And("User clicks the Pay button")
    public void userClicksThePayButton() {
        BrowserUtils.waitForClickablility(payBillsPage.PayButton, 2);
        payBillsPage.PayButton.click();

    }

    @When("User fills the form {string}")
    public void userFillsTheForm(String withWithout) {
        payBillsPage.userFillsTheForm(withWithout);
    }

    @Then("{string} field should not accept {string}")
    public void fieldShouldNotAccept(String box, String characterType) {

        if (box.equalsIgnoreCase("amount")) {
            System.out.println("Amount box text = " + payBillsPage.amountBox.getAttribute("value"));
            Assert.assertTrue("Verify " + box + " field exclude " + characterType, payBillsPage.amountBox.getAttribute("value").isEmpty());
        } else if (box.equalsIgnoreCase("date")) {
            System.out.println("Date box text = " + payBillsPage.dateBox.getAttribute("value"));
            Assert.assertTrue("Verify " + box + " field exclude " + characterType, payBillsPage.dateBox.getAttribute("value").isEmpty());
        }
    }

    @Then("User gets {string} message in {string}")
    public void userGetsMessageIn(String expectedText, String messageField) {
        if (expectedText.equals("The payment was successfully submitted.")) {
            BrowserUtils.waitForVisibility(payBillsPage.successfulPaymentText, 2);
            Assert.assertEquals("Verify Successful Payment", expectedText, payBillsPage.successfulPaymentText.getText());
        } else if (expectedText.equals("Please fill out this field.")) {
            if (messageField.contains("Amount")) {
                BrowserUtils.waitFor(2);

                System.out.println("amountBox validationMessage is= " + payBillsPage.amountBox.getAttribute("validationMessage"));
                String actualText = payBillsPage.amountBox.getAttribute("validationMessage");
                Assert.assertEquals("Verify the message is displayed", expectedText, actualText);
            } else if (messageField.contains("Date")) {
                BrowserUtils.waitFor(2);
                System.out.println("dateBox.validationMessage is = " + payBillsPage.dateBox.getAttribute("validationMessage"));
                String actualText = payBillsPage.dateBox.getAttribute("validationMessage");
                Assert.assertEquals("Verify the message is displayed", expectedText, actualText);
            }
        }
    }

    @When("user tries to calculate cost without selecting a currency")
    public void userTriesToCalculateCostWithoutSelectingACurrency() {
        BrowserUtils.waitForClickablility(payBillsPage.calculateCostsButton, 5);
        payBillsPage.amountBoxInPurcForrCurr.sendKeys("30");
        payBillsPage.SelectedCurrencyRadioButton.click();
        payBillsPage.calculateCostsButton.click();
    }

    @Then("error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        BrowserUtils.waitFor(2);
        Alert alert = Driver.get().switchTo().alert();
        System.out.println("alert.getText() = " + alert.getText());
        Assert.assertTrue(alert.getText().contains("ensure that you have filled"));
    }

    @When("user tries to calculate cost without entering a value")
    public void userTriesToCalculateCostWithoutEnteringAValue() {
        BrowserUtils.waitForClickablility(payBillsPage.calculateCostsButton, 5);

        Select selCurrDrpDwn = new Select(payBillsPage.selCurrDrpDwnElement);
        selCurrDrpDwn.selectByIndex(2);
        System.out.println("selCurrDrpDwn.getOptions().get(2).getText() = " + selCurrDrpDwn.getOptions().get(2).getText());
        BrowserUtils.waitFor(1);
        payBillsPage.SelectedCurrencyRadioButton.click();
        payBillsPage.calculateCostsButton.click();

    }
}
