package com.snframework.steps;

import java.util.Map;

import com.snframework.pojo.serialization.CreateCRSerialization;
import com.snframework.services.ChangeRequestService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ChangeRequestSteps {

	private static ChangeRequestService crService = new ChangeRequestService();
	private CreateCRSerialization crPayLoadSerialization = new CreateCRSerialization();
	
	@When("User hit the post change request method with the request payload")
	public void user_hit_the_post_change_request_method_with_the_request_payload() {
		
		crPayLoadSerialization.setDescription("Added Description using cucumber");
		crPayLoadSerialization.setShort_description("Added short Description using cucumber");
		crPayLoadSerialization.setClose_notes("Close Notes By Mukta");
		crPayLoadSerialization.setActive("true");
		crPayLoadSerialization.setUrgency("2");

		crService.createCR(crPayLoadSerialization);
		
	}
	@Then("New change request record should be created")
	public void new_change_request_record_should_be_created() {
		crService.validateStatusCode(201)
		.validateStatusLine("Created")
		.validateContentType("application/json")
		.responseAsPrettyString()
		.extractValue("result.sys_id");
	}
	
	
	@When("User hit the get change request method with the request payload and query params")
	public void user_hit_the_get_change_request_method_with_the_request_payload_and_query_params() {
		
		crService.getCRQueryParam()
		.validateContentType("application/json")
		.validateStatusCode(200)
		.validateStatusLine("OK");
	    
	}
	@Then("All change request record should be created with those query param")
	public void all_change_request_record_should_be_created_with_those_query_param() {
	    
	}

}
