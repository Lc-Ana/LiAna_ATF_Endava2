package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "steps",
        tags = "@AddContact",
        plugin = {"pretty","html:target/reports/cucumber-reports.html"},
        monochrome = false
)
public class TestRunner {
}
