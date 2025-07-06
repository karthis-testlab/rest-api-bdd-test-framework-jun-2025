package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		          features = {"src/test/java/features/incident.feature"},
		          glue = {"step.defintions.som"},
		          dryRun = false,
		          tags = "@smoke or @e2e",
		          plugin = {
		        		  "html:cucumber-reports/result.html" 
		          },	
		          publish = true
		        )
public class IncidentTestRunner extends AbstractTestNGCucumberTests {	

}