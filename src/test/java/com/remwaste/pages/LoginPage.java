package com.remwaste.pages;

import com.remwaste.utilities.ConfigurationReader;
import com.remwaste.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    /***
     This class contains the locators and methods related to the Login Page.
     ***/
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorInherit MuiIconButton-sizeLarge css-1l1167e']")
    public WebElement userIcon;

    @FindBy(linkText = "Login")
    public WebElement loginOption;

    @FindBy(id = "email")
    public WebElement emailInputBox;

    @FindBy(id = "password")
    public WebElement passwordInputBox;

    @FindBy(xpath = "//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-fullWidth MuiButtonBase-root  css-nxzcop']")
    public WebElement loginButton;

    @FindBy (xpath = "//div[@class='MuiBox-root css-1izva4v']")
    public WebElement dashboardText;

    @FindBy (xpath = "//h5[@class='MuiTypography-root MuiTypography-h5 css-zq6grw']")
    public WebElement userNameText;

    @FindBy (xpath = "//div[@role='alert']")
    public WebElement alertMessage;


    public void loginWithValidCredentials(){

        emailInputBox.sendKeys(ConfigurationReader.get("validEmail"));
        passwordInputBox.sendKeys(ConfigurationReader.get("validPassword"));
        loginButton.click();

    }

    public void loginWithInvalidCredentials(){
        emailInputBox.sendKeys(ConfigurationReader.get("invalidEmail"));
        passwordInputBox.sendKeys(ConfigurationReader.get("invalidPassword"));
        loginButton.click();
    }

    public void loginWithLeaveEmailAddressInputBoxBlankAndValidPassword(){
        emailInputBox.sendKeys("");
        passwordInputBox.sendKeys(ConfigurationReader.get("validPassword"));
        loginButton.click();
    }

    public void loginWithValidEmailAddressAndLeavePasswordInputBoxBlank(){
        emailInputBox.sendKeys(ConfigurationReader.get("validEmail"));
        passwordInputBox.sendKeys("");
        loginButton.click();
    }

    public void loginWithLeaveEmailAddressInputBoxBlankAndLeavePasswordInputBoxBlank(){
        emailInputBox.sendKeys("");
        passwordInputBox.sendKeys("");
        loginButton.click();
    }

    public void assertErrorMessage(){

        String expectedErrorMessage = "Invalid password or not a valid email";
        String actualErrorMessage = alertMessage.getText();
        Assert.assertEquals(expectedErrorMessage,actualErrorMessage);

    }

    public void login(){
        Driver.getDriver().get(ConfigurationReader.get("url"));
        userIcon.click();
        loginOption.click();
        loginWithValidCredentials();
    }



}
