package com.remwaste.step_definitions;


import com.remwaste.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {

    @After
    public void tearDown(Scenario scenario) throws Exception {
       if (scenario.isFailed()) {
           TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
           scenario.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", "scenario_" + scenario.getName());
           Driver.closeDriver();
       }
        }
    }

