package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/incident.feature"},
		          glue = {"step.defitions"},
		          dryRun = false
		        )
public class IncidentTestRunner extends AbstractTestNGCucumberTests {	

}