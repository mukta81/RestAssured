package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features={"src/test/java/features/Issues.feature"},
				 glue={"steps"},
				 dryRun=false,
				 plugin={"pretty","html:reports/results.html",
							"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"})
public class JiraRunner extends AbstractTestNGCucumberTests {

}
