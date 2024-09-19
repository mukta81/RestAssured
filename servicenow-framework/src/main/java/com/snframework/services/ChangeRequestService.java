package com.snframework.services;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import com.snframework.pojo.serialization.CreateCRSerialization;

import io.restassured.response.Response;

public class ChangeRequestService {
	
	private Response response;
	
	public Response createChangeRequest(CreateCRSerialization crPayload) {
		return given()
				.header("Content-Type", "application/json")
				.log()
				.all()
				.when()
				.body(crPayload)
				.post("/change_request");

	}
	
	
	public Response createChangeRequestQueryParam() {
		return given()
				.header("Content-Type", "application/json")
				.log()
				.all()
				//.queryParams(map)
				.queryParam("sysparm_fields", "number, sys_id, short_description, opened_at, description, category")
				.queryParam("sysparm_query", "category=Network")
				.queryParam("sysparm_limit", "3")
				.when()
				.get("/change_request");

	}
	public ChangeRequestService createCR(CreateCRSerialization payload) {
		response = createChangeRequest(payload);
		//root = response.as(Root.class);
		return this;
	}
	
	public ChangeRequestService getCRQueryParam() {
		response = createChangeRequestQueryParam();
		return this;

	}
	
	public ChangeRequestService validateStatusCode(int statusCode) {
		assertThat(response.getStatusCode(), equalTo(statusCode));
		System.out.println("Status Code "+ response.getStatusCode());
		return this;
	}
	
	public ChangeRequestService validateStatusLine(String statusLine) {
		assertThat(response.getStatusLine(), containsString(statusLine));
		System.out.println("Status Line "+ response.getStatusLine());
		return this;

	}

	public ChangeRequestService validateContentType(String contentType) {
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

	public ChangeRequestService responseAsPrettyString() {
		System.out.println("Pretty String "+ response.asPrettyString());
		return this;
	}
	
}
