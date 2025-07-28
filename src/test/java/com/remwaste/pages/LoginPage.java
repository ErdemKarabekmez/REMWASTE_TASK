package com.remwaste.pages;

import com.remwaste.utilities.ConfigurationReader;
import com.remwaste.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    /***
     Page Object Model class for the Login Page of the application.
     This class encapsulates the WebElements and actions related to the login functionality,
     such as entering email and password, clicking the login button, and performing various login scenarios.
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


    /***
      This method fetches the valid email and password from the configuration
      file and performs the login action by filling the input fields and clicking
      the login button.
     ***/
    public void loginWithValidCredentials(){
        emailInputBox.sendKeys(ConfigurationReader.get("validEmail"));
        passwordInputBox.sendKeys(ConfigurationReader.get("validPassword"));
        loginButton.click();

    }

    /***
     This method fetches the invalid email and password from the configuration
     file, filling the input fields with these values, and clicks the login button.
     It is typically used to verify that the application properly handles failed login attempts.
     ***/
    public void loginWithInvalidCredentials(){
        emailInputBox.sendKeys(ConfigurationReader.get("invalidEmail"));
        passwordInputBox.sendKeys(ConfigurationReader.get("invalidPassword"));
        loginButton.click();
    }


    /***
     This method tests the behavior of the login functionality when the email
     field is empty but a valid password is entered. It fills the password field,
     leaves the email field blank, and clicks the login button.
     ***/
    public void loginWithLeaveEmailAddressInputBoxBlankAndValidPassword(){
        emailInputBox.sendKeys("");
        passwordInputBox.sendKeys(ConfigurationReader.get("validPassword"));
        loginButton.click();
    }

    /***
     This method tests the login functionality response when the email
     field is filled correctly but the password field is left empty.
     It fills the email field, leaves the password field blank, and clicks the login button.
     ***/
    public void loginWithValidEmailAddressAndLeavePasswordInputBoxBlank(){
        emailInputBox.sendKeys(ConfigurationReader.get("validEmail"));
        passwordInputBox.sendKeys("");
        loginButton.click();
    }

    /***
     This method tests how the login functionality handles the case when
     neither email nor password is provided. It leaves both fields blank
     and clicks the login button.
     ***/
    public void loginWithLeaveEmailAddressInputBoxBlankAndLeavePasswordInputBoxBlank(){
        emailInputBox.sendKeys("");
        passwordInputBox.sendKeys("");
        loginButton.click();
    }

    /***
     This method retrieves the actual error message text from the alertMessage element
     and asserts that it equals the expected error message:
     "Invalid password or not a valid email".
     ***/
    public void assertErrorMessage(){
        String expectedErrorMessage = "Invalid password or not a valid email";
        String actualErrorMessage = alertMessage.getText();
        Assert.assertEquals(expectedErrorMessage,actualErrorMessage);
    }

    /***
     This method opens the URL defined in the configuration file.
     Clicks on the user icon to reveal login options.
     Selects the login option.
     Performs login with valid credentials by calling loginWithValidCredentials() method
     ***/
    public void login(){
        Driver.getDriver().get(ConfigurationReader.get("url"));
        userIcon.click();
        loginOption.click();
        loginWithValidCredentials();
    }



}
