package com.remwaste.pages;

import com.remwaste.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UiPage {

    /***
     This class contains the locators and methods related to the Ui Page.
     ***/
    public UiPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(linkText = "New")
    public WebElement newOption;

    @FindBy(id = "title")
    public WebElement titleInputBox;

    @FindBy(id = "img")
    public WebElement imageUrlInputBox;

    @FindBy(id = "detail")
    public WebElement detailInputBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement addNewBlogButton;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-sm-12 MuiGrid-grid-md-6 MuiGrid-grid-lg-4 MuiGrid-grid-xl-3 css-1noz1rq']//div[2]/div")
    public List<WebElement> blogsTitleList;

    @FindBy (xpath = "//img[@class='MuiCardMedia-root MuiCardMedia-media MuiCardMedia-img css-1mg3qcy']")
    public List<WebElement> allBlogs;

    @FindBy (xpath = "//button[text()='Update']")
    public WebElement updateButton;

    @FindBy (xpath = "//button[text()='Update Blog']")
    public WebElement updateBlogButton;

    @FindBy (xpath = "//div[@role='alert']")
    public WebElement successUpdateMessage;

    @FindBy (xpath = "//button[text()='Delete']")
    public WebElement deleteButton;

    @FindBy (xpath = "//div[@role='alert']")
    public WebElement successDeleteMessage;


    public void lastBlogClick(){
        WebElement lastBlog = allBlogs.get(allBlogs.size()-1);
        lastBlog.click();

    }

    public void assertSuccessUpdateMessage(){

        String expectedSuccessUpdateMessage = "Record Updated";
        String actualSuccessUpdateMessage = successUpdateMessage.getText();
        Assert.assertEquals(expectedSuccessUpdateMessage,actualSuccessUpdateMessage);

    }

    public void assertSuccessDeleteMessage(){
        String expectedSuccessDeleteMessage = "Record Deleted";
        String actualSuccessDeleteMessage = successDeleteMessage.getText();
        Assert.assertEquals(expectedSuccessDeleteMessage,actualSuccessDeleteMessage);
    }


}


