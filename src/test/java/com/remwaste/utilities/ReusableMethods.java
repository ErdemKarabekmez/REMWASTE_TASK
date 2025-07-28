package com.remwaste.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReusableMethods {

    /***
     This class contains some methods used in the project.
     ***/


    /***
     This method allows us to click on elements using JavaScript when Selenium cannot perform the click action.
     ***/
    public void clickWithJS(WebElement element) {

        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].click();", element);

    }


    /***
    This method waits until the given WebElement is visible on the page within the specified timeout.
     ***/
    public void waitVisibilityOfElement(WebElement element, int second) {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOf(element));

    }




}
