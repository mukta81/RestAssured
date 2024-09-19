package Serialization.payload.test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Serialization.payload.utils.DataReader;
import io.restassured.response.Response;
import week3.day2.payload.pojo.object.Config;
import week3.day2.payload.pojo.object.SNIncidentPOJO;

/* shouldUserAbleToCreateIncident() reads from Excel Sheet
 * 
 * shouldUserAbleToUpdateSingleIncident() updated the same sys_id generated above
 * 
 * shouldUserAbleToGetUpdatedIncident()) shows on screen the updated columns of the record
 */



public class IncidentTestNGServiceNow {
	public static List<String> sysIds = new ArrayList<>();

	@DataProvider
	public String[][] getExcelData() {
		return DataReader.readExcelData("create-incidents");

	}

	@DataProvider
	public String[][] getCSVData() {
		return DataReader.readCsvData("incidents");

	}


	@Test(priority=1, dataProvider="getExcelData")    //read from excel
	public void shouldUserAbleToCreateIncident(String shortDescription, String description, String urgency, String state, String callerID) {
		SNIncidentPOJO payload = new SNIncidentPOJO();
		payload.setDescription(shortDescription);
		payload.setShort_description(description);
		payload.setState(urgency);
		payload.setUrgency(state);
		payload.setCaller_id(callerID);
		String url ="https://dev282303.service-now.com/api/now/table/{table_name}";
		Response response=given()   // imported static RestAssured
				.auth()
				.basic(Config.getUserName(), Config.getPassword())
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
				.statusCode(201)
				.extract()
				.response();

		String sysId = response.jsonPath().getString("result.sys_id");
		sysIds.add(sysId);

		//System.out.println("Created Incident with sys_id: " + sysId);

	}



	@Test(priority=2, dependsOnMethods = "shouldUserAbleToCreateIncident", dataProvider="getCSVData")			//read from csv
	public void shouldUserAbleToUpdateSingleIncident(String shortDescription, String description, String urgency, String state, String callerID) 
	{
		SNIncidentPOJO payload = new SNIncidentPOJO();
		payload.setDescription(shortDescription);
		payload.setShort_description(description);
		payload.setState(urgency);
		payload.setUrgency(state);
		payload.setCaller_id(callerID);
		String url ="https://dev282303.service-now.com/api/now/table/{table_name}/{sys_id}";
		for(String sysid: sysIds)
		{
			given()   // imported static RestAssured
			.auth()
			.basic(Config.getUserName(), Config.getPassword())
			.header("Content-Type", "application/json")
			.pathParam("table_name", "incident")
			.pathParam("sys_id", sysid)
			.log()
			.all()	
			.when()
			.body(new File("src/main/resources/request-payload/update-incident.json"))
			.put(url)
			.then()
			.log()
			.all()
			.assertThat()
			.statusCode(200);
		}
	}

	
	@Test(priority=3, dependsOnMethods = "shouldUserAbleToUpdateSingleIncident")			//read from csv
	public void shouldUserAbleToGetUpdatedIncident() 
	{
		String url ="https://dev282303.service-now.com/api/now/table/{table_name}/{sys_id}";
		for(String sysid: sysIds)
		{
			given()   // imported static RestAssured
			.auth()
			.basic(Config.getUserName(), Config.getPassword())
			.header("Content-Type", "application/json")
			.pathParam("table_name", "incident")
			.pathParam("sys_id", sysid)
			.queryParam("sysparm_fields", "short_description,urgency,state,description,caller_id")
			.log()
			.all()	
			.when()
			.get(url)
			.then()
			.log()
			.all()
			.assertThat()
			.statusCode(200);
		}
	}
}
