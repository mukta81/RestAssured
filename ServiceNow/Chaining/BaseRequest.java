package Chaining;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basic;

import org.testng.annotations.BeforeMethod;

public class BaseRequest {
	
	protected String sysid;
	@BeforeMethod
	public void BeforeMethod() {
		baseURI="https://dev282303.service-now.com";
		basePath="/api/now/table/{table_name}";
		authentication = basic("admin", "Nrk80CWWxz*/");

	}

}
