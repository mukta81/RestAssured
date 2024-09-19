package com.snframework.services;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.snframework.pojo.deserialization.Root;
import com.snframework.pojo.deserialization.RootCallerId;
import com.snframework.pojo.serialization.CreateIncidentSerialization;

//import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;

public class IncidentService {
	private Response response;
	private Root root;
	private RootCallerId rootCallerId;
	
	private Response createIncident(File file) {
		//	String url ="https://dev282303.service-now.com/api/now/table/{table_name}";
		return given()
				.header("Content-Type", "application/json")
				//	.auth()
				//	.basic("admin", "Nrk80CWWxz*/")
				.log()
				.all()
				//	.pathParam("table_name", "incident")
				.when()
				.body(file)
				.post("/incident");
		//			.then()
		//				.log()
		//				.all()
		//				.assertThat()
		//				.statusCode(201);
	}

	private Response createIncident(String payload) {
		return given()
				.header("Content-Type", "application/json")
				.log()
				.all()
				.when()
				.body(payload)
				.post("/incident");
	}

	private Response createIncident(CreateIncidentSerialization payload) {
		return given()
				.header("Content-Type", "application/json")
				.log()
				.all()
				.when()
				.body(payload)
				.post("/incident");
	}


	private Response getAllIncident() {
		return given()
				.header("Content-Type", "application/json")
				.log()
				.all()
				.when()
				.get("/incident");


	}


	private Response getSingleIncident(String sys_id) {
		return given()
				.header("Content-Type", "application/json")
				.log()
				.all()
				.when()
				.get("/incident/"+sys_id);


	}

	
	private Response getAllIncidentWithQueryParam(Map map) {
		return given()
				.header("Content-Type", "application/json")
				.queryParams(map)
//				.queryParam("sysparm_fields", "number, sys_id, short_description, opened_at, caller_id, description, category")
//				.queryParam("sysparm_query", "caller_id!=''")
//				.queryParam("sysparm_limit", "3")
				.log()
				.all()
				.when()
				.get("/incident");


	}

	
	private Response getSingleIncidentWithQueryParm(String sys_id, Map map) {
		return given()
			//	.queryParam("sysparm_fields", "number, sys_id, short_description, opened_at, caller_id, description, category")
				.queryParams(map)
				.header("Content-Type", "application/json")
				.log()
				.all()
				.when()
				.get("/incident/"+sys_id);


	}

	private Response updateIncident(File file, String sys_id) {
		return given()
				.header("Content-Type", "application/json")
				.log()
				.all()
				.when()
				.body(file)
				.put("/incident/"+sys_id);


	}

	private Response updateIncident(String payload, String sys_id) {
		return given()
				.header("Content-Type", "application/json")
				.log()
				.all()
				.when()
				.body(payload)
				.put("/incident/"+sys_id);


	}


	private Response updateIncident(CreateIncidentSerialization payload, String sys_id) {
		return given()
				.header("Content-Type", "application/json")
				.log()
				.all()
				.when()
				.body(payload)
				.put("/incident/"+sys_id);


	}


	public IncidentService create(CreateIncidentSerialization payload) {
		response = createIncident(payload);
		root = response.as(Root.class);
		return this;
	}

	
	public IncidentService createWithCallerId(CreateIncidentSerialization payload) {
		response = createIncident(payload);
		System.out.println("response as pretty string"+response.asPrettyString());
		rootCallerId = response.as(RootCallerId.class);
		return this;

	}
	
	public IncidentService create(File file) {
		response = createIncident(file);
		root = response.as(Root.class);
		return this;
	}

	public IncidentService create(String payload) {
		response = createIncident(payload);
		return this;
	}


	public IncidentService getAll() {
		response = getAllIncident();
		return this;
	}
	
	public IncidentService getAllQueryParam(Map<String, String> map) {
		response = getAllIncidentWithQueryParam(map);
		return this;
	}

	public IncidentService getSingle(String sysID) {
		response = getSingleIncident(sysID);
		return this;

	}
	
	public IncidentService getSingleQueryParm(String sysID, Map map) {
		response = getSingleIncidentWithQueryParm(sysID, map);
		return this;

	}


	public IncidentService update(CreateIncidentSerialization payload, String sysID) {
		response=updateIncident(payload, sysID);
		return this;
	}

	public IncidentService update(File file, String sysID) {
		response=updateIncident(file, sysID);
		return this;
	}

	public IncidentService update(String payload, String sysID) {
		response=updateIncident(payload, sysID);
		return this;
	}

	public IncidentService validateStatusCode(int statusCode) {
		assertThat(response.getStatusCode(), equalTo(statusCode));
		System.out.println("Status Code "+ response.getStatusCode());
		return this;

	}

	public IncidentService validateStatusLine(String statusLine) {
		assertThat(response.getStatusLine(), containsString(statusLine));
		System.out.println("Status Line "+ response.getStatusLine());
		return this;

	}

	public IncidentService validateContentType(String contentType) {
		assertThat(response.getContentType(), containsString(contentType));
		System.out.println("Content Type "+ response.getContentType());
		return this;
	}

	public Response getResponse() {
		System.out.println("Response: "+response);
		return response;
	}


	public String getSysID() {
		System.out.println("SysID: "+response.body().jsonPath().getString("result.sys_id"));
		return response.body().jsonPath().getString("result.sys_id");
		//return sysID;
	}

	public String extractValue(String jsonpath) {
		System.out.println("Extracted Value of "+jsonpath+" is:"+response.body().jsonPath().getString(jsonpath));
		return response.body().jsonPath().getString(jsonpath);
		//return sysID;
	}

	public IncidentService responseAsPrettyString() {
		System.out.println("Pretty String "+ response.asPrettyString());
		return this;
	}
	
	
	public IncidentService validateShortDescription(String expectedValue) {
		assertThat(root.getResult().getShort_description(), equalTo(expectedValue));
		return this;
	}
	
	public IncidentService validateDescription(String expectedValue) {
		assertThat(root.getResult().getDescription(), equalTo(expectedValue));
		return this;
	}
	
	public IncidentService validateUrgency(String expectedValue) {
		assertThat(root.getResult().getUrgency(), equalTo(expectedValue));
		return this;
	}
	
	public IncidentService validateState(String expectedValue) {
		assertThat(root.getResult().getState(), equalTo(expectedValue));
		return this;
	}
	

	public IncidentService getSysDomainValue() {
		System.out.println("SysDomain Value: "+root.getResult().getSys_domain().getValue());
		return this;
	}

	public IncidentService validateIncidentCategoryWithJsonPathFetchNumber(String jsonPath, String expected, String fetch) {
		List<String> list = response.body().jsonPath().getList(jsonPath);
		System.out.println("Total No. of results obtained "+list.size());
		for (String item : list) {
		//	System.out.println(item+","+expected);
			assertThat(item, equalTo(expected));
			
		}
		System.out.println("All Request with "+expected+" cateory is: "+ response.body().jsonPath().getString(fetch));	
		return this;
	}
}
