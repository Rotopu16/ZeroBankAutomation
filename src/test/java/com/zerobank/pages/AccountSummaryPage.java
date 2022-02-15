package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSummaryPage extends BasePage{

    @FindBy(xpath = "//h2[contains(text(),'Cash Accounts')]")
    public WebElement CashAccountsText;

    @FindBy(xpath = "(//table//thead)[3]")
    public WebElement CreditCardTableHeaders;


}
