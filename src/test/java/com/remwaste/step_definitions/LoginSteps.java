package com.remwaste.step_definitions;

import com.remwaste.pages.LoginPage;
import com.remwaste.utilities.ConfigurationReader;
import com.remwaste.utilities.Driver;
import com.remwaste.utilities.ReusableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class LoginSteps extends ReusableMethods {

    /***
     This class contains Cucumber step definitions related to login functionality.
     It implements the steps used in feature files to test various login scenarios,
     such as logging in with valid/invalid credentials, handling error messages,
     and verifying successful login.
     ***/
    LoginPage loginPage = new LoginPage();

    @Given("the user navigates to the homepage")
    public void the_user_navigates_to_the_homepage() {

        Driver.getDriver().get(ConfigurationReader.get("url"));
    }

    @When("the user clicks on the user icon at the top right corner")
    public void the_user_clicks_on_the_user_icon_at_the_top_right_corner() {

        loginPage.userIcon.click();

    }

    @When("the user selects the Login option from the dropdown")
    public void the_user_selects_the_login_option_from_the_dropdown() {

        loginPage.loginOption.click();
    }

    @When("the user is redirected to the login page in the same tab")
    public void the_user_is_redirected_to_the_login_page_in_the_same_tab() {

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("login"));

    }

    @When("the user enters a valid email address and password and clicks the Login button")
    public void the_user_enters_a_valid_email_address_and_password_and_clicks_the_login_button() {

        loginPage.loginWithValidCredentials();

    }

    @When("the user is redirected to the dashboard page in the same tab")
    public void the_user_is_redirected_to_the_dashboard_page_in_the_same_tab() throws InterruptedException {

        Thread.sleep(2000);
        Assert.assertTrue(loginPage.dashboardText.getText().contains("Dashboard"));

    }

    @Then("the user should see the name displayed at the top right corner")
    public void the_user_should_see_the_name_displayed_at_the_top_right_corner() {

        Assert.assertEquals("Erdem", loginPage.userNameText.getText());
        Driver.closeDriver();

    }

    @When("the user enters an invalid email address and invalid password and clicks the login button")
    public void the_user_enters_an_invalid_email_address_and_invalid_password_and_clicks_the_login_button()  {

        loginPage.loginWithInvalidCredentials();
        waitVisibilityOfElement(loginPage.alertMessage,5);

    }

    @When("the user should see an error message")
    public void the_user_should_see_an_error_message() {

        loginPage.assertErrorMessage();

    }

    @And("the user should remain on the login page")
    public void the_user_should_remain_on_the_login_page() {

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("login"));

    }

    @When("the user leaves the email address field blank and enters a valid password and clicks the login button")
    public void the_user_leaves_the_email_address_field_blank_and_enters_a_valid_password_and_clicks_the_login_button()  {

        loginPage.loginWithLeaveEmailAddressInputBoxBlankAndValidPassword();
        waitVisibilityOfElement(loginPage.alertMessage,5);

    }

    @When("the user enters a valid email address and leaves the password field blank and clicks the login button")
    public void the_user_enters_a_valid_email_address_and_leaves_the_password_field_blank_and_clicks_the_login_button()  {

        loginPage.loginWithValidEmailAddressAndLeavePasswordInputBoxBlank();
        waitVisibilityOfElement(loginPage.alertMessage,5);

    }

    @When("the user leaves the email address field blank and leaves the password field blank and clicks the login button")
    public void the_user_leaves_the_email_address_field_blank_and_leaves_the_password_field_blank_and_clicks_the_login_button()  {

        loginPage.loginWithLeaveEmailAddressInputBoxBlankAndLeavePasswordInputBoxBlank();
        waitVisibilityOfElement(loginPage.alertMessage,5);
    }

    }
