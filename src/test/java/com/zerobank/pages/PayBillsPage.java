package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PayBillsPage extends BasePage{

    @FindBy(linkText = "Add New Payee")
    public WebElement AddNewPayeeText;

    @FindBy(id= "np_new_payee_name")
    public WebElement PayeeName;

    @FindBy(id= "np_new_payee_address")
    public WebElement PayeeAdress;

    @FindBy(id= "np_new_payee_account")
    public WebElement PayeeAccount;

    @FindBy(id= "np_new_payee_details")
    public WebElement PayeeDetails;

    @FindBy(id= "add_new_payee")
    public WebElement AddButton;

    @FindBy(css= "div>#alert_content")
    public WebElement PayeeAddedText;

    @FindBy(linkText = "Purchase Foreign Currency")
    public WebElement PurchaseForeignCurrencyText;

    @FindBy(css="#pc_currency")
    public WebElement dropdownCurrenciesElement;

    @FindBy(xpath= "//span[contains(text(),'The payment was successfully submitted.')]")
    public WebElement successfulPaymentText;

    @FindBy(css="#sp_amount")
    public WebElement amountBox;

    @FindBy(css="#sp_date")
    public WebElement dateBox;

    @FindBy(css="#sp_description")
    public WebElement descriptionBox;

    @FindBy(css="#pay_saved_payees")
    public WebElement PayButton;

    public void fillPaymentForm(String amount, String date, String description) {
        BrowserUtils.waitForClickablility(amountBox, 2);
        amountBox.sendKeys(amount);
        dateBox.sendKeys(date);
        descriptionBox.sendKeys(description);
    }
    public void userFillsTheForm(String withWithout) {
        switch (withWithout){
            case "accordingly":
                fillPaymentForm("30","2022-05-08","Hard work pays back"  );
                System.out.println("payBillsPage.dateBox.getAttribute() = " + dateBox.getAttribute("value"));
                break;
            case "without amount box":
                fillPaymentForm("","2022-05-08","Hard work pays back"  );
                break;
            case "without date box":
                fillPaymentForm("30","","Hard work pays back"  );
                break;
            case "with alphabetical characters in amount field":
                fillPaymentForm("abc","2022-05-08","Hard work pays back"  );
                break;
            case "with special characters in amount field":
                fillPaymentForm("@-/","2022-05-08","Hard work pays back"  );
                break;
            case "with alphabetical characters in date field":
                fillPaymentForm("30","abc","Hard work pays back"  );
                break;
            case "with special characters in date field":
                fillPaymentForm("30","@?/","Hard work pays back"  );
                break;
        }
    }











}
