package com.remwaste.step_definitions;


import com.remwaste.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;


public class Hooks {


    /***
     This method executed before each scenario.
     It prints a starting message, maximizes the browser window,
     and sets an implicit wait timeout of 15 seconds for locating elements.
     ***/
    @Before
    public void setUp() {
        System.out.println("Starting");
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }


    /***
     This method executed after each failed scenario.
     Typical usage includes taking screenshots on failure step, and closing the browser.
     ***/
    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
                scenario.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", "scenario_" + scenario.getName());
            } catch (Exception e) {
                System.out.println("Screenshot could not be taken: " + e.getMessage());
            }
        }
        Driver.closeDriver();
    }
}

