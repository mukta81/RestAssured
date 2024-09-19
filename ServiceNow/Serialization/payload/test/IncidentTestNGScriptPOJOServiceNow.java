package Serialization.payload.test;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import com.google.gson.Gson;

import Serialization.payload.Config.ConfigPayload;
import Serialization.payload.pojo.IncidentPOJOServiceNow;

public class IncidentTestNGScriptPOJOServiceNow {
	
	@Test
	public void shouldUserBeAbleToCreateIncident()
	{
		
		IncidentPOJOServiceNow payload = new IncidentPOJOServiceNow();
		payload.setDescription("This is description by Mukta for chaining");
		payload.setShort_description("description by Mukta");
		payload.setState("1");
		payload.setUrgency("1");
		
		String url ="https://dev282303.service-now.com/api/now/table/{table_name}";
		given()   // imported static RestAssured
		.auth()
		.basic(ConfigPayload.getUserName(), ConfigPayload.getPassword())
		.header("Content-Type", "application/json")
		.pathParam("table_name", "incident")
		.log()
		.all()
		.when()
		.body(payload)
		.post(url)
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(201);

	}

}
