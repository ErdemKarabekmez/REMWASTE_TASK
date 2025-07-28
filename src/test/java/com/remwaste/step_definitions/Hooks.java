package com.remwaste.step_definitions;


import com.remwaste.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/***
 * This Hooks class contains teardown methods that run after Cucumber scenarios.
 * Typical usage includes taking screenshots on failure, and closing the browser.
 ***/
public class Hooks {

   /* @After
    public void tearDown(Scenario scenario) throws Exception {
       if (scenario.isFailed()) {
           TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
           scenario.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", "scenario_" + scenario.getName());
           Driver.closeDriver();
       }
        }*/
   @After
   public void tearDown(Scenario scenario) {
       // Senaryo başarısızsa screenshot al
       if (scenario.isFailed()) {
           try {
               TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
               scenario.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", "scenario_" + scenario.getName());
           } catch (Exception e) {
               System.out.println("Screenshot alınamadı: " + e.getMessage());
           }
       }
       // Her durumda driver'ı kapat
       Driver.closeDriver();
   }
    }

