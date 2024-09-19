package Chaining;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class SNChangeRequestRefactorTestNG extends BaseRequest{

	@Test
	public void shouldUserAbleToCreateChangeRequest() {
				Response response = given()   // imported static RestAssured
				.header("Content-Type", "application/json")
				.pathParam("table_name", "change_request")
				.log()
				.all()
				.when()
				.body(new File("src/main/resources/request-payload/create-changeRequest.json"))
				.post()
				.then()
				.log()
				.all()
				.assertThat()
				.statusCode(201)
				//.statusLine(Matchers.equalTo("HTTP/1.1 201 Created"))
				.statusLine(equalTo("HTTP/1.1 201 Created"))
				.statusLine(containsString("Created"))
				.extract()
				.response();
				
				System.out.println("sysid of response: "+response.jsonPath().getString("result.sys_id"));
				System.out.println("active  of response: "+response.jsonPath().getString("result.active"));
				System.out.println("close_notes  of response: "+response.jsonPath().getString("result.close_notes"));
		}
	
}
