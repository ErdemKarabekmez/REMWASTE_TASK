package com.remwaste.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/***
 * This class execute feature files with specific configuration.
 * This class uses the Cucumber JUnit runner to run scenarios tagged with "@scenario_6".
 - Configuration details:
 - Plugins for reporting in pretty text, HTML, JSON, and JUnit XML formats.
 - Feature files located under "src/test/resources".
 - Step definitions located in "com.remwaste.step_definitions" package.
 - dryRun set false to execute the tests (not just check mapping).
 ***/
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml"
        },

        features = "src/test/resources",
        glue = "com.remwaste.step_definitions",
        dryRun = false,
        tags = "@scenario_12"



)
public class CukeRunner {
}
