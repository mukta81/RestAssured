package com.snframework.test;

import java.io.File;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.testng.annotations.Test;

import com.snframework.general.utils.PropertiesHandler;
import com.snframework.pojo.serialization.CreateIncidentSerialization;
import com.snframework.services.IncidentService;
//import com.snframework.testing.api.Retry;
import com.snframework.testing.api.TestNGHooks;


public class IncidentServiceTest extends TestNGHooks{
	//IncidentService service = new IncidentService(); 
	CreateIncidentSerialization serviceSerial = new CreateIncidentSerialization();
	private String sysID = null;

	@Test(priority=1)
	public void userShouldAbleToCreateIncident() {
		//create an object of service

		//call method to create incident method
	//	Response response = service.create(new File("src/main/resources/request-payloads/create-incident.json"));  

//		assertThat(response.getStatusCode(), equalTo(201));
//		assertThat(response.getStatusLine(), containsString("Created"));
//		assertThat(response.getContentType(), containsString("application/json"));

//		System.out.println("Status Code "+ response.getStatusCode());
//		System.out.println("Status Line "+ response.getStatusLine());
//		System.out.println("Pretty String "+ response.asPrettyString());
//		sysID = response.body().jsonPath().getString("result.sys_id");
//		System.out.println("SYS_ID: "+response.body().jsonPath().getString("result.sys_id"));
//		System.out.println("short description is added: "+response.body().jsonPath().getString("result.short_description"));

	
		sysID = new IncidentService()
		.create(new File("src/main/resources/request-payloads/create-incident.json"))
		.validateContentType("application/json")
		.validateStatusCode(201)
		.validateStatusLine("Created")
		.responseAsPrettyString()
		.validateShortDescription("This is description by Mukta for chaining")
		.validateUrgency("1")
		.validateState("1")
		.validateDescription("description by Mukta")
		.getSysDomainValue()
		.extractValue("result.sys_id");
		
		System.out.println("************END OF POST**************");
	}



	@Test(priority=4)
	public void userShouldAbleToCreateIncidentSerializationNoCallerId() {

		serviceSerial.setDescription("Added Description using set method");
		serviceSerial.setShort_description("Added short Description using set method");
	//	serviceSerial.setCaller_id("681cdaf9c0a8016400b98a06818d11c7");
		serviceSerial.setUrgency("1");
		serviceSerial.setState("1");

		sysID = new IncidentService()
		.create(serviceSerial)
		.validateStatusCode(201)
		.validateContentType("application/json")
		.validateStatusLine("Created")
		.responseAsPrettyString()
		.validateDescription("Added Description using set method")
		.validateShortDescription("Added short Description using set method")
		.validateUrgency("1")
		.extractValue("result.sys_id");
		
		
//		Response response = service.create(serviceSerial);   
//
//		assertThat(response.getStatusCode(), equalTo(201));
//		assertThat(response.getStatusLine(), containsString("Created"));
//		assertThat(response.getContentType(), containsString("application/json"));
//
//		System.out.println("Status Code "+ response.getStatusCode());
//		System.out.println("Status Line "+ response.getStatusLine());
//		System.out.println("Pretty String "+ response.asPrettyString());
//		sysID = response.body().jsonPath().getString("result.sys_id");
//		System.out.println("SYS_ID: "+response.body().jsonPath().getString("result.sys_id"));
//		System.out.println("short description is added: "+response.body().jsonPath().getString("result.short_description"));
//

//			System.out.println("SYS_Domain: "+response.body().jsonPath().getString("result.sys_domain"));
		
		System.out.println("************END OF POST with Serialization/Deserialization without Caller ID **************");
	}

	@Test(priority=4)
	public void userShouldAbleToCreateIncidentSerializationWithCallerId() {

		serviceSerial.setDescription("Added Description using set method");
		serviceSerial.setShort_description("Added short Description using set method");
		serviceSerial.setCaller_id("681cdag9c0a8016400b98a06818d11c7");
	//  serviceSerial.setCaller_id("9ee1b13dc6112271007f9d0efdb69cd0");
		serviceSerial.setUrgency("1");
		serviceSerial.setState("1");

		sysID = new IncidentService()
		.createWithCallerId(serviceSerial)
		.validateStatusCode(201)
		.validateContentType("application/json")
		.validateStatusLine("Created")
		.responseAsPrettyString()
		.extractValue("result.sys_id");
		
		
//		Response response = service.create(serviceSerial);   
//
//		assertThat(response.getStatusCode(), equalTo(201));
//		assertThat(response.getStatusLine(), containsString("Created"));
//		assertThat(response.getContentType(), containsString("application/json"));
//
//		System.out.println("Status Code "+ response.getStatusCode());
//		System.out.println("Status Line "+ response.getStatusLine());
//		System.out.println("Pretty String "+ response.asPrettyString());
//		sysID = response.body().jsonPath().getString("result.sys_id");
//		System.out.println("SYS_ID: "+response.body().jsonPath().getString("result.sys_id"));
//		System.out.println("short description is added: "+response.body().jsonPath().getString("result.short_description"));
//

//			System.out.println("SYS_Domain: "+response.body().jsonPath().getString("result.sys_domain"));
		
		System.out.println("************END OF POST with Serialization/Deserialization with Caller ID **************");
	}


	@Test(priority=2)
	public void userShouldAbleToGetAllIncidents() {
//		Response response = service.getAllIncident();   
//
//		assertThat(response.getStatusCode(), equalTo(200));
//		assertThat(response.getStatusLine(), containsString("OK"));
//		assertThat(response.getContentType(), containsString("application/json"));
//
//		System.out.println("Status Code "+ response.getStatusCode());
//		System.out.println("Status Line "+ response.getStatusLine());
		
		new IncidentService()
		.getAll()
		.validateContentType("application/json")
		.validateStatusCode(200)
		.validateStatusLine("OK");
		
		System.out.println("************END OF GET ALL**************");
	}
	
	@Test(priority=2)
	public void userShouldAbleToGetAllIncidentsWithQueryParam() {
//		Response response = service.getAllIncident();   
//
//		assertThat(response.getStatusCode(), equalTo(200));
//		assertThat(response.getStatusLine(), containsString("OK"));
//		assertThat(response.getContentType(), containsString("application/json"));
//
//		System.out.println("Status Code "+ response.getStatusCode());
//		System.out.println("Status Line "+ response.getStatusLine());
//		Map<String,String> map = new HashedMap<String, String>();
//		map.put("sysparm_fields", "number, sys_id, short_description, opened_at, caller_id, description, category");
//		map.put("sysparm_query", "caller_id!=''");
//		map.put("sysparm_limit", "3");
		
		new IncidentService()
		.getAllQueryParam(PropertiesHandler.queryParamRead("incident-service", new String[]{"sysparm_limit","sysparm_fields","sysparm_query"}))
		.validateContentType("application/json")
		.validateStatusCode(200)
		.validateStatusLine("OK").responseAsPrettyString();
		
		System.out.println("************END OF GET ALL WITH QUERY PARAM **************");
	}


	
	@Test(priority=2)
	public void userShouldAbleToGetAllIncidentNumberForHardwareCategory() {

		new IncidentService()
		.getAllQueryParam(PropertiesHandler.queryParamRead("incident-service", new String[]{"sysparm_fields","sysparm_query"}))
		.validateContentType("application/json")
		.validateStatusCode(200)
		.validateStatusLine("OK")
		.validateIncidentCategoryWithJsonPathFetchNumber("result.category", "hardware", "result.number")
		.responseAsPrettyString();
		
		System.out.println("************END OF GET ALL WITH QUERY PARAM and CATEGORY AS HARDWARE and Fetch Number**************");
	}
	
	
	
	@Test(priority=3, dependsOnMethods = "userShouldAbleToCreateIncident")
	public void userShouldAbleToGetSingleIncident() {
//		Response response = service.getSingleIncident(sysID);   
//
//		assertThat(response.getStatusCode(), equalTo(200));
//		assertThat(response.getStatusLine(), containsString("OK"));
//		assertThat(response.getContentType(), containsString("application/json"));
//
//		System.out.println("Status Code "+ response.getStatusCode());
//		System.out.println("Status Line "+ response.getStatusLine());
		
		new IncidentService()
		.getSingle(sysID)
		.validateContentType("application/json")
		.validateStatusCode(200)
		.validateStatusLine("OK")
		.responseAsPrettyString();
		System.out.println("************END OF GET SINGLE**************");
	}

	
	@Test(priority=5, dependsOnMethods = "userShouldAbleToCreateIncident")
	public void userShouldAbleToGetSingleIncidentWithQueryParam() {
//		Response response = service.getSingleIncident(sysID);   
//
//		assertThat(response.getStatusCode(), equalTo(200));
//		assertThat(response.getStatusLine(), containsString("OK"));
//		assertThat(response.getContentType(), containsString("application/json"));
//
//		System.out.println("Status Code "+ response.getStatusCode());
//		System.out.println("Status Line "+ response.getStatusLine());
		Map<String, String> map = new HashedMap<String, String>();
		map.put("sysparm_fields", "sys_id, category, short_description, number, opened_at, caller_id,value, description");
		
		new IncidentService()
		.getSingleQueryParm(sysID, map)
		.validateContentType("application/json")
		.validateStatusCode(200)
		.validateStatusLine("OK")
		.responseAsPrettyString();
		System.out.println("************END OF GET SINGLE WITH QUERY PARAM **************");
	}

	@Test(priority=6, dependsOnMethods = "userShouldAbleToCreateIncidentSerializationWithCallerId")
	public void userShouldAbleToUpdateIncident() {
		String payload = "{"
				+ "\"short_description\": \"Updated description by Mukta for chaining\","
				+ "\"urgency\":\"2\","
				+ "\"state\":\"2\","
				+ "\"description\":\"updated description by Mukta\","
				+ "\"caller_id\": \"281cdaf2c0a8016400b98a06218d11c7\""
				+ "}";


//		Response response = service.updateIncident(payload, sysID);   
//
//		assertThat(response.getStatusCode(), equalTo(200));
//		assertThat(response.getStatusLine(), containsString("OK"));
//		assertThat(response.getContentType(), containsString("application/json"));
//
//		System.out.println("Status Code "+ response.getStatusCode());
//		System.out.println("Status Line "+ response.getStatusLine());
//		System.out.println("short description is updated: "+response.body().jsonPath().getString("result.short_description"));
//		System.out.println("knowledge is updated: "+response.body().jsonPath().getString("result.knowledge"));
//		System.out.println("Pretty String "+ response.asPrettyString());

		
		new IncidentService()
		.update(payload, sysID)
		.validateContentType("application/json")
		.validateStatusCode(200)
		.validateStatusLine("OK")
		.responseAsPrettyString();
		System.out.println("************END OF PUT**************");
	}
}
