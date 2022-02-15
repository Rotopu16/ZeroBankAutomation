package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FindTransactionsStepDefs {

    @Given("the user accesses the {string} tab")
    public void theUserAccessesTheTab(String text) {
        BrowserUtils.waitFor(2);
        Assert.assertEquals(text, new AccountActivityPage().FindTransactionsText.getText());

    }

    @When("the user enters date range from {string} to {string}")
    public void theUserEntersDateRangeFromTo(String fromDate, String toDate) {
        BrowserUtils.waitFor(2);
        new AccountActivityPage().fromDate.clear();
        new AccountActivityPage().fromDate.sendKeys(fromDate);
        new AccountActivityPage().toDate.clear();
        new AccountActivityPage().toDate.sendKeys(toDate);

    }

    @And("clicks search")
    public void clicksSearch() {
        new AccountActivityPage().FindButton.click();
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void resultsTableShouldOnlyShowTransactionsDatesBetweenTo(String fromDate, String toDate) throws ParseException {
        BrowserUtils.waitFor(2);//get all rows dynamically //1.find number of rows
        String tableRowsPath = "//*[@id='filtered_transactions_for_account']/table/tbody/tr";
        List<WebElement> numRows = Driver.get().findElements(By.xpath(tableRowsPath));

        System.out.println("numRows.size() = " + numRows.size());
        //2.iterate one by one
        for (int i = 1; i <= numRows.size(); i++) {
            boolean isBetween=false;
            WebElement rowOneCell = Driver.get().findElement(By.xpath(tableRowsPath + "[" + i + "]/td[1]"));
            String actualText = rowOneCell.getText();

            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date actualDate = sdformat.parse(actualText);
            Date ExpectedFromDate = sdformat.parse(fromDate);
            Date ExpectedToDate=sdformat.parse(toDate);
            if(actualDate.compareTo(ExpectedFromDate) >= 0 && actualDate.compareTo(ExpectedToDate)<=0) {
                isBetween=true;
            }
            Assert.assertTrue("Verify the date is between",isBetween);
        }
 //By manipulating the rowtext create an Assertion
            //            String [] arrActualText = actualText.split("-");
//            String [] arrExpextedFromText= fromDate.split("-");
//            String [] arrExpextedToText= toDate.split("-");
//            int actualYear= Integer.parseInt(arrActualText[0]);
//            int expectedFromYear=Integer.parseInt(arrExpextedFromText[0]);
//            int expectedToYear=Integer.parseInt(arrExpextedFromText[0]);
//            int actualMonth= Integer.parseInt(arrActualText[1]);
//            int expectedFromMonth=Integer.parseInt(arrExpextedFromText[1]);
//            int expectedToMonth=Integer.parseInt(arrExpextedToText[1]);
//            int actualDay= Integer.parseInt(arrActualText[2]);
//            int expectedFromDay=Integer.parseInt(arrExpextedFromText[2]);
//            int expectedToDay=Integer.parseInt(arrExpextedToText[2]);
//
//            if (actualYear>=expectedFromYear && actualYear<=expectedToYear){
//                if (actualMonth>=expectedFromMonth && actualMonth <=expectedToMonth){
//                    if(actualDay>=)
//                }
//            }
       }

    @And("the results should be sorted by most recent date")
    public void theResultsShouldBeSortedByMostRecentDate() throws ParseException {
        BrowserUtils.waitFor(2);//get all rows dynamically //1.find number of rows
        String tableRowsPath = "//*[@id='filtered_transactions_for_account']/table/tbody/tr";
        List<WebElement> numRows = Driver.get().findElements(By.xpath(tableRowsPath));

        System.out.println("numRows.size() = " + numRows.size());
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date maxDate= sdformat.parse("9999-12-31");
        //2.iterate one by one
        for (int i = 1; i <= numRows.size(); i++) {

            boolean isRecent=false;
            WebElement rowOneCell = Driver.get().findElement(By.xpath(tableRowsPath + "[" + i + "]/td[1]"));
            String actualText = rowOneCell.getText();

            Date actualDate = sdformat.parse(actualText);

            if(maxDate.compareTo(actualDate) >= 0 ) {
                isRecent=true;
            }
            Assert.assertTrue("Verify the date is between",isRecent);
            maxDate= actualDate;
        }

    }

    @And("the results table should only not contain transactions dated {string}")
    public void theResultsTableShouldOnlyNotContainTransactionsDated(String unExpectedDate) throws ParseException {
        BrowserUtils.waitFor(2);//get all rows dynamically //1.find number of rows
        String tableRowsPath = "//*[@id='filtered_transactions_for_account']/table/tbody/tr";
        List<WebElement> numRows = Driver.get().findElements(By.xpath(tableRowsPath));

        System.out.println("numRows.size() = " + numRows.size());
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date previousDate= sdformat.parse(unExpectedDate);
        //2.iterate one by one
        for (int i = 1; i <= numRows.size(); i++) {

            boolean isIncluded=false;
            WebElement rowOneCell = Driver.get().findElement(By.xpath(tableRowsPath + "[" + i + "]/td[1]"));
            String actualText = rowOneCell.getText();

            Date actualDate = sdformat.parse(actualText);

            if(previousDate.compareTo(actualDate) == 0 ) {
                isIncluded=true;
            }
            Assert.assertFalse("Verify the date is not include",isIncluded);
        }
    }

    @When("the user enters description {string}")
    public void theUserEntersDescription(String description) {
        BrowserUtils.waitFor(2);
        new AccountActivityPage().DescriptionBox.clear();
        new AccountActivityPage().DescriptionBox.sendKeys(description);

    }

    @Then("results table should only show descriptions containing {string}")
    public void resultsTableShouldOnlyShowDescriptionsContaining(String description) {
        BrowserUtils.waitFor(2);//get all rows dynamically //1.find number of rows

        AccountActivityPage accountActivityPage = new AccountActivityPage();
        List<WebElement> numRows = accountActivityPage.getRows();

        System.out.println("numRows.size() = " + numRows.size());
        boolean isDescribed=false;
        //2.iterate one by one
        for (int i = 1; i <= numRows.size(); i++) {
            WebElement rowOneCell = Driver.get().findElement(By.xpath(accountActivityPage.tableRowsPath + "[" + i + "]/td[2]"));
            isDescribed=false;
            if(rowOneCell.getText().contains(description)){
                isDescribed=true;
            }
            Assert.assertTrue("is Described", isDescribed);
         }
        BrowserUtils.waitFor(2);
        Assert.assertTrue("is Described", isDescribed);
    }

    @But("results table should not show descriptions containing {string}")
    public void resultsTableShouldNotShowDescriptionsContaining(String description) {
        BrowserUtils.waitFor(2);//get all rows dynamically //1.find number of rows

        AccountActivityPage accountActivityPage = new AccountActivityPage();
        List<WebElement> numRows = accountActivityPage.getRows();

        System.out.println("numRows.size() = " + numRows.size());
        //2.iterate one by one
        for (int i = 1; i <= numRows.size(); i++) {
            WebElement rowOneCell = Driver.get().findElement(By.xpath(accountActivityPage.tableRowsPath + "[" + i + "]/td[2]"));
            boolean isDescribed=true;
            if(rowOneCell.getText().contains(description)){
                isDescribed=false;
            }
            Assert.assertTrue("is Described", isDescribed);

        }
    }

    @Then("results table should show at least one result under {string}")
    public void resultsTableShouldShowAtLeastOneResultUnder(String type) {
        BrowserUtils.waitFor(2);//get all rows dynamically //1.find number of rows
        String tableRowsPath = "//*[@id='filtered_transactions_for_account']/table/tbody/tr";
        List<WebElement> numRows = Driver.get().findElements(By.xpath(tableRowsPath));

        System.out.println("numRows.size() = " + numRows.size());
        boolean isThereResult=false;
        //2.iterate one by one
        for (int i = 1; i <= numRows.size(); i++) {
            int typeColumnNo=0;
            if (type.equalsIgnoreCase("Deposit")){typeColumnNo=3;};
            if (type.equalsIgnoreCase("Withdrawal")){typeColumnNo=4;};
            
            WebElement rowOneCell = Driver.get().findElement(By.xpath(tableRowsPath + "[" + i + "]/td["+typeColumnNo+"]"));
            System.out.println(i + "-" + rowOneCell.getText());
            //By manipulating the rowtext create an Assertion

            if (!rowOneCell.getText().isEmpty()){
                isThereResult=true;
            }
        }
        Assert.assertTrue("Verify there is a result", isThereResult);

    }

    @When("user selects type {string}")
    public void userSelectsType(String type) {
        Select typeDropdown = new Select(new AccountActivityPage().dropdownTypeElement);
        typeDropdown.selectByVisibleText(type);
    }

    @But("results table should show no result under {string}")
    public void resultsTableShouldShowNoResultUnder(String type) {
        BrowserUtils.waitFor(2);//get all rows dynamically //1.find number of rows
        String tableRowsPath = "//*[@id='filtered_transactions_for_account']/table/tbody/tr";
        List<WebElement> numRows = Driver.get().findElements(By.xpath(tableRowsPath));

        System.out.println("numRows.size() = " + numRows.size());
        int typeColumnNo = 0;
        if (type.equalsIgnoreCase("Deposit")) {
            typeColumnNo = 3;
        }
        if (type.equalsIgnoreCase("Withdrawal")) {
            typeColumnNo = 4;
        }
        boolean isThereResult = false;
        //2.iterate one by one
        for (int i = 1; i <= numRows.size(); i++) {
            isThereResult=true;
            WebElement rowOneCell = Driver.get().findElement(By.xpath(tableRowsPath + "[" + i + "]/td[" + typeColumnNo + "]"));
            System.out.println(i + "-" + rowOneCell.getText());

            //By manipulating the rowtext create an Assertion
            if (rowOneCell.getText().isEmpty()){
                isThereResult=false;
            }
        }
        Assert.assertFalse("Verify there is no result",isThereResult);
    }
}
