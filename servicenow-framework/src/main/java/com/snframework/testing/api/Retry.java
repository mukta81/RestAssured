	package com.snframework.testing.api;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.snframework.general.utils.PropertiesHandler.config;;

public class Retry implements IRetryAnalyzer{

	int counter = 0;
	
	@Override
	public boolean retry(ITestResult result) {
		System.out.println("Running "+counter+" time");
		if (counter < Integer.parseInt(config("snframework.test.retry.max.limit"))) {
			counter++;
			return true;
		}
		return false;
	}

}
