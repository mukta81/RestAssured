package com.snframework.steps;

import static com.snframework.general.utils.PropertiesHandler.*;
import com.snframework.pojo.serialization.CreateIncidentSerialization;
import com.snframework.services.IncidentService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

public class IncidentServiceSteps {
	
	private static IncidentService service = new IncidentService();
	
	@Given("Set a base URI and base path")
	public void set_a_base_uri_and_base_path() {
	   baseURI = config("service.now.instance.uri");
	   basePath = config("service.now.instance.basePath");
	}
	@Given("Set Authentication for the create operation")
	public void set_authentication_for_the_create_operation() {
		 authentication=
		    		basic(config("service.now.instance.username"), secret("service.now.instance.password"));
	}
	@When("User hit the post method with the request payload")
	public void user_hit_the_post_method_with_the_request_payload() {
		CreateIncidentSerialization serviceSerialPayload = new CreateIncidentSerialization();
		serviceSerialPayload.setDescription("Added Description using cucumber");
		serviceSerialPayload.setShort_description("Added short Description using cucumber");

		service.create(serviceSerialPayload);
	   }
	@Then("New Incident record should be created")
	public void new_incident_record_should_be_created() {
		service.validateStatusCode(201)
		.validateContentType("application/json")
		.validateStatusLine("Created")
		.responseAsPrettyString()
		.validateDescription("Added Description using cucumber")
		.validateShortDescription("Added short Description using cucumber")
		.extractValue("result.sys_id");
	}
	
	
	@When("User hit the post method with the multiple data payload {string},{string}")
	public void user_hit_the_post_method_with_the_multiple_data_payload(String shortDescription, String description) {
		CreateIncidentSerialization serviceSerialPayload = new CreateIncidentSerialization();
		serviceSerialPayload.setDescription(description);
		serviceSerialPayload.setShort_description(shortDescription);


		service.create(serviceSerialPayload);
	}
	@Then("New Incident records should be created with following {string},{string} details")
	public void new_incident_multiple_records_should_be_created(String shortDescription, String description) {
		service.validateStatusCode(201)
		.validateContentType("application/json")
		.validateStatusLine("Created")
		.responseAsPrettyString()
		.validateDescription(description)
		.validateShortDescription(shortDescription)
		.extractValue("result.sys_id");
	}

}
