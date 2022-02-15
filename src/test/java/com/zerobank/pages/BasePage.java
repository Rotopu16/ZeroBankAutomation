package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void navigateToPage(String page) {
        //*[.='Account Summary']
        String pageLocator = "//span[.='" + page + "']";

            BrowserUtils.waitFor(2);
            Driver.get().findElement(By.xpath(pageLocator)).click();
     }

     public void navigateToLink(String link) {

            BrowserUtils.waitFor(2);
            Driver.get().findElement(By.linkText(link)).click();
    }


}
