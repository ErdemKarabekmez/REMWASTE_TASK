package com.remwaste.step_definitions;

import com.github.javafaker.Faker;
import com.remwaste.pages.LoginPage;
import com.remwaste.pages.UiPage;
import com.remwaste.utilities.Driver;
import com.remwaste.utilities.ReusableMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;


public class UiSteps extends ReusableMethods {

    Faker faker = new Faker();

    UiPage uiPage = new UiPage();
    LoginPage loginPage = new LoginPage();

    String title;

    @Given("the user is logged in to the platform")
    public void the_user_is_logged_in_to_the_platform() {

        loginPage.login();

    }

    @When("the user selects the New option from the dropdown")
    public void the_user_selects_the_new_option_from_the_dropdown() {

        uiPage.newOption.click();

    }

    @When("the user is redirected to the new blog page in the same tab")
    public void the_user_is_redirected_to_the_new_blog_page_in_the_same_tab() {

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("new-blog"));

    }

    @When("the user enters a title into the title input box")
    public void the_user_enters_a_title_into_the_title_input_box() {

        title = faker.name().title();
        uiPage.titleInputBox.sendKeys(title);

    }

    @When("the user enters an image url into the image url input box")
    public void the_user_enters_an_image_url_into_the_image_url_input_box() {

        uiPage.imageUrlInputBox.sendKeys("Test");

    }

    @When("the user enters blog details into the detail input box")
    public void the_user_enters_blog_details_into_the_detail_input_box() {

        uiPage.detailInputBox.sendKeys("Test");

    }

    @When("the user clicks on the add new blog button")
    public void the_user_clicks_on_the_add_new_blog_button() {

        uiPage.addNewBlogButton.click();

    }

    @Then("the new blog post should appear on the blog dashboard")
    public void the_new_blog_post_should_appear_on_the_blog_dashboard() {

        WebElement lastBlogTitle = uiPage.blogsTitleList.get(uiPage.blogsTitleList.size() - 1);
        String expectedTitle = title;
        String actualTitle = lastBlogTitle.getText();
        Assert.assertEquals(expectedTitle, actualTitle);

    }

    @When("the user clicks on the last blog post")
    public void the_user_clicks_on_the_last_blog_post()  {

        uiPage.lastBlogClick();

    }

    @When("the user clicks on the update button")
    public void the_user_clicks_on_the_update_button() {
        uiPage.updateButton.click();

    }

    @When("the user updates the title field")
    public void the_user_updates_the_title_field() {
        uiPage.titleInputBox.sendKeys(" update");

    }

    @When("the user clicks update blog button")
    public void the_user_clicks_update_blog_button()  {

        uiPage.updateBlogButton.click();

    }

    @When("the user should see a success update message")
    public void the_user_should_see_a_success_update_message() {

        waitVisibilityOfElement(uiPage.successUpdateMessage, 5);
        uiPage.assertSuccessUpdateMessage();
    }

    @When("the user clicks on the delete button")
    public void the_user_clicks_on_the_delete_button()  {

        uiPage.deleteButton.click();

    }

    @When("the user should see a success delete message")
    public void the_user_should_see_a_success_delete_message() {
        waitVisibilityOfElement(uiPage.successDeleteMessage, 5);
        uiPage.assertSuccessDeleteMessage();


    }


}




