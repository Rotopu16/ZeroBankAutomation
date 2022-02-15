package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountActivityPage extends BasePage{

    @FindBy(css="#aa_accountId")
    public WebElement dropdownElement;

    @FindBy(css="#ui-tabs-2 > h2")
    public WebElement FindTransactionsText;

    @FindBy(css="#aa_fromDate")
    public WebElement fromDate;

    @FindBy(css="#aa_toDate")
    public WebElement toDate;

    @FindBy(css="[type='submit']")
    public WebElement FindButton;

    @FindBy(css="[type='submit']")
    public WebElement table;

    @FindBy(css="#aa_description")
    public WebElement DescriptionBox;

    @FindBy(css="#aa_type")
    public WebElement dropdownTypeElement;

    public String tableRowsPath = "//*[@id='filtered_transactions_for_account']/table/tbody/tr";
    public String tableHeadersPath= "//table//th";

    public List<WebElement> getRows(){

        List<WebElement> numRows = Driver.get().findElements(By.xpath(tableRowsPath));

        return numRows;
    }
    public List<WebElement> tableHeaders(){

        List<WebElement> tableHeaders = Driver.get().findElements(By.xpath(tableHeadersPath));

        return tableHeaders;
    }


}
