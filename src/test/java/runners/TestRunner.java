package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "steps",
        tags = "@Login",
        plugin = {"pretty","html:target/reports/cucumber-reports.html"},
        monochrome = true
)
public class TestRunner {
}
