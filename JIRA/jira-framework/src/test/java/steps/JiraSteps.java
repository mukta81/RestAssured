package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import services.JiraServices;
import utils.PropertiesHandler;

public class JiraSteps {
	private static JiraServices services = new JiraServices();

	@Given("Set a base URI and base path")
	public void set_a_base_uri_and_base_path() {
	    RestAssured.baseURI = PropertiesHandler.config("jira.issue.uri");
	    RestAssured.basePath = PropertiesHandler.config("jira.issue.basePath");
	}
	
	@Given("Set Authentication for the create operation")
	public void set_authentication_for_the_create_operation() {
	RestAssured.authentication= RestAssured.preemptive().basic(PropertiesHandler.config("jira.issue.username"), 
			PropertiesHandler.secret("jira.issues.password"));
	}
		
	@When("User hit the POST method with the request payload")
	public void user_hit_the_post_method_with_the_request_payload() {
		String payload = "{\n" +
			    "    \"fields\": {\n" +
			    "        \"summary\": \"New Issue from REST API Eclipse\",\n" +
			    "        \"issuetype\": {\n" +
			    "            \"id\": \"10012\"\n" +
			    "        },\n" +
			    "        \"project\": {\n" +
			    "            \"id\": \"10003\"\n" +
			    "        }\n" +
			    "    }\n" +
			    "}";
		
		services.createPostForAProject(payload);

	}
	
	@When("User hit the POST method with the request payload and attachment")
	public void user_hit_the_post_method_with_the_request_payload_and_attachment() {
		String payload = "{\n" +
			    "    \"fields\": {\n" +
			    "        \"summary\": \"New Issue from REST API Eclipse with attachment\",\n" +
			    "        \"issuetype\": {\n" +
			    "            \"id\": \"10012\"\n" +
			    "        },\n" +
			    "        \"project\": {\n" +
			    "            \"id\": \"10003\"\n" +
			    "        }\n" +
			    "    }\n" +
			    "}";
		
		services.createPostForAProjectWithAttachment(payload,"10003")
		.validateStatusCode(200)
		.validateStatusLine("OK")
		.validateContentType("application/json")
		.responseAsPrettyString();
	}
	
	@Then("New Issue should be created")
	public void new_issue_should_be_created() {
		//System.out.println("hre");
		services.validateStatusCode(201)
		.validateStatusLine("Created")
		.validateContentType("application/json")
		.responseAsPrettyString();
	}
	

	@Then("New Issue should be created  with attachment")
	public void new_issue_should_be_created_with_attachment() {
				services.validateStatusCode(200)
				.validateStatusLine("OK")
				.validateContentType("application/json")
				.responseAsPrettyString();
	}


	@When("User hit the GET method with the request payload")
	public void user_hit_the_get_method_with_the_request_payload() {
		services.getIssueFromProject();
	}
	@Then("All Issue in a project should be returned")
	public void all_issue_in_a_project_should_be_returned() {
		services.validateStatusCode(200)
		.validateStatusLine("OK")
		.validateContentType("application/json");
	}
}
