package Serialization.payload.base;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basic;

import org.testng.annotations.BeforeMethod;

import Serialization.payload.Config.ConfigPayload;

public class BasePayload {
	
	protected String sysid;
	@BeforeMethod
	public void BeforeMethod() {
		baseURI="https://dev282303.service-now.com";
		basePath="/api/now/table/{table_name}";
		authentication = basic(ConfigPayload.getUserName(), ConfigPayload.getPassword());   //read config file using Config.java

	}

}
