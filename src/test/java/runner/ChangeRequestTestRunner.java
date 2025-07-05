package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/change_request.feature"},
		          glue = {"step.defitions"},
		          dryRun = false
		        )
public class ChangeRequestTestRunner extends AbstractTestNGCucumberTests {	

}