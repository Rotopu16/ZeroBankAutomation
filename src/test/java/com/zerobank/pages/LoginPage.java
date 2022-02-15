package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{


    @FindBy(css="#user_login")
    public WebElement LogInUserName;

    @FindBy(css="#user_password")
    public WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement SignInButton;

    @FindBy(css="div[class*='alert-error']")
    public WebElement errorMessage;

    public void goTologinPage() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
        Driver.get().manage().window().maximize();
        BrowserUtils.waitFor(2);
    }

    public void login(String userNameStr, String passwordStr) {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
        Driver.get().manage().window().maximize();
        LogInUserName.sendKeys(userNameStr);
        password.sendKeys(passwordStr);
        SignInButton.click();
        Driver.get().get("http://zero.webappsecurity.com/online-banking.html");
        BrowserUtils.waitFor(2);
        // verification that we logged
    }
    public void loginInvalid(String userNameStr, String passwordStr) {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
        Driver.get().manage().window().maximize();
        LogInUserName.sendKeys(userNameStr);
        password.sendKeys(passwordStr);
        SignInButton.click();
        BrowserUtils.waitFor(2);
        // verification that we logged
    }

}
