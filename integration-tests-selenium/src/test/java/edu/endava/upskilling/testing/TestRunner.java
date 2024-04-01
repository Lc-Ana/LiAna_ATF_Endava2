package edu.endava.upskilling.testing;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "edu/endava/upskilling/testing/steps",
        tags = "@Run",
        plugin = {"html:target/reports/cucumber-reports.html"},
        monochrome = false
)
public class TestRunner {
}