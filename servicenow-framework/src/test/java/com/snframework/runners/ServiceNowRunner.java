package com.snframework.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features= {"src/test/java/com/snframework/features"},
	dryRun = false,
	glue = {"com.snframework.steps"},
	plugin = {"pretty","html:reports/results.html"}
)
public class ServiceNowRunner extends AbstractTestNGCucumberTests {
	

}
