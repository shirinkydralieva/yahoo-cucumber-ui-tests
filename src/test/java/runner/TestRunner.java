package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions", "hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "com.epam.reportportal.cucumber.ScenarioReporter"
    }
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
