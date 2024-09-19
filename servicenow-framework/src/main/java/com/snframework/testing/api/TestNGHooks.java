package com.snframework.testing.api;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basic;

import org.testng.annotations.BeforeMethod;

import static com.snframework.general.utils.PropertiesHandler.*;

public class TestNGHooks {
			@BeforeMethod
			public void setUp() throws Exception {
				
				baseURI="https://dev282303.service-now.com";
				basePath="/api/now/table";
				authentication = basic(config("service.now.instance.username"), secret("service.now.instance.password"));
				
			}
//			@DataProvider
//			public String[][] excel() {
//				return DataFactory.engine(DataEngine.EXCEL).getData("create-incidents");
//			}
//			
//			@DataProvider
//			public String[][] csv() {
//				return DataFactory.engine(DataEngine.CSV).getData("incidents");
//			}

}
