package com.remwaste.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml"
        },

        features = "src/test/resources",
        glue = "com/remwaste/step_definitions",
        dryRun = false,
        tags = "@scenario_6"
)
public class CukeRunner {
}
