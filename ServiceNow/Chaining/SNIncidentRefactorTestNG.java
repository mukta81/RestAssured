package Chaining;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.io.File;

public class SNIncidentRefactorTestNG extends BaseRequest{
	
	@Test(priority=1)
	public void shouldUserAbleToCreateIncident() {
	//	String url ="https://dev282303.service-now.com/api/now/table/{table_name}";
			sysid = given()   // imported static RestAssured
		//	.auth()
		//	.basic("admin", "Nrk80CWWxz*/")
			.header("Content-Type", "application/json")
			.pathParam("table_name", "incident")
			.log()
			.all()
			.when()
			.body(new File("src/main/resources/request-payload/create-incident.json"))
			.post()
			.then()
			.log()
			.all()
			.assertThat()
			.statusCode(201)
			//.statusLine(Matchers.equalTo("HTTP/1.1 201 Created"))
			.statusLine(equalTo("HTTP/1.1 201 Created"))
			.statusLine(containsString("Created"))
			.body("result.short_description", containsString("Mukta"))
			.body("result.knowledge", equalTo("true"))
			.extract()
			.jsonPath()
			.getString("result.sys_id");
			
	}
	
	
	@Test(priority=2, enabled=false)
	public void shouldUserAbleToGetAllIncident() {
	//	String url ="https://dev282303.service-now.com/api/now/table/{table_name}";
			given()   // imported static RestAssured
		//	.auth()
		//	.basic("admin", "Nrk80CWWxz*/")
			.pathParam("table_name", "incident")
			.log()
			.all()
			.when()
			.get()
			.then()
			.log()
			.all()
			.assertThat()
			.statusCode(200)
			//.statusLine(Matchers.equalTo("HTTP/1.1 200 OK"));
			.statusLine(equalTo("HTTP/1.1 200 OK"))
			.statusLine(containsString("OK"));

	}
	
	
	@Test(priority=3, enabled=false)
	public void shouldUserAbleToGetSingleIncident() {
	//	String url ="https://dev282303.service-now.com/api/now/table/{table_name}/{sys_id}";
			given()   // imported static RestAssured
		//	.auth()
		//	.basic("admin", "Nrk80CWWxz*/")
			.pathParam("table_name", "incident")
			.pathParam("sys_id", sysid)
			.log()
			.all()
			.when()
			.get("/{sys_id}")
			.then()
			.log()
			.all()
			.assertThat()
			.statusCode(200)
			//.statusLine(Matchers.equalTo("HTTP/1.1 200 OK"));
			.statusLine(equalTo("HTTP/1.1 200 OK"))
			.statusLine(containsString("OK"));
	}
	
	@Test(priority=4, enabled=false)
	public void shouldUserAbleToUpdateSingleIncident() {
	//	String url ="https://dev282303.service-now.com/api/now/table/{table_name}/{sys_id}";
			given()   // imported static RestAssured
		//	.auth()
		//	.basic("admin", "Nrk80CWWxz*/")
			.header("Content-Type", "application/json")
			.pathParam("table_name", "incident")
			.pathParam("sys_id", sysid)
			.log()
			.all()	
			.when()
			.body(new File("src/main/resources/request-payload/update-incident.json"))
			.put("/{sys_id}")
			.then()
			.log()
			.all()
			.assertThat()
			.statusCode(200)
			//.statusLine(Matchers.equalTo("HTTP/1.1 200 OK"));
			.statusLine(equalTo("HTTP/1.1 200 OK"))
			.statusLine(containsString("OK"));

	}

	
	
	@Test(priority=5, enabled=false)
	public void shouldUserAbleToUpdateSingleIncidentByPatch() {
	//	String url ="https://dev282303.service-now.com/api/now/table/{table_name}/{sys_id}";
			given()   // imported static RestAssured
		//	.auth()
		//	.basic("admin", "Nrk80CWWxz*/")
			.header("Content-Type", "application/json")
			.pathParam("table_name", "incident")
			.pathParam("sys_id", sysid)
			.log()
			.all()	
			.when()
			.body(new File("src/main/resources/request-payload/update-incident-patch.json"))
			.patch("/{sys_id}")
			.then()
			.log()
			.all()
			.assertThat()
			.statusCode(200)
			//.statusLine(Matchers.equalTo("HTTP/1.1 200 OK"));
			.statusLine(equalTo("HTTP/1.1 200 OK"))
			.statusLine(containsString("OK"));
}
	
	
	@Test(priority=6, enabled=false)
	public void shouldUserAbleToDeleteSingleIncident() {
	//	String url ="https://dev282303.service-now.com/api/now/table/{table_name}/{sys_id}";
			given()   // imported static RestAssured
		//	.auth()
		//	.basic("admin", "Nrk80CWWxz*/")
			.pathParam("table_name", "incident")
			.pathParam("sys_id", sysid)
			.log()
			.all()
			.when()
			.delete("/{sys_id}")
			.then()
			.log()
			.all()
			.assertThat()
			.statusCode(204)
			//.statusLine(Matchers.equalTo("HTTP/1.1 204 No Content"));
			.statusLine(equalTo("HTTP/1.1 204 No Content"))
			.statusLine(containsString("No Content"));

	}
}
