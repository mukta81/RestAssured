package services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.io.File;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JiraServices {
	private Response response;
	private Response createIssue(String payload) {
		return RestAssured.given()
					.header("Content-Type", "application/json")
					.log()
					.all()
					.when()
					.body(payload)
					.post("/issue");

			}
	private Response createIssueWithAttachment(String payload, String projectID) {
		return RestAssured.given()
			//	.header("Content-Type", "application/json")
				.header("X-Atlassian-Token", "no-check")
				.pathParam("projectId", projectID)
				.log()
				.all()
				.multiPart("file",new File("src/main/resources/data/IssueAttachmentJira.txt"))
				.when()
				//.body(payload)
				.post("/issue/{projectId}/attachments");
	}

	
	private Response getIssue() {
		return RestAssured.given()
					.header("Content-Type", "application/json")
					//.pathParam("projectId", projectID)
					.queryParam("jql", "project=10002")
					.log()
					.all()
					.when()
					.get("/search");

	}
	
	
	public JiraServices getIssueFromProject() {
		response = getIssue();
		return this;

	}
	
	
	public JiraServices createPostForAProject(String payload) {
		response = createIssue(payload);
		return this;

	}
	
	public JiraServices createPostForAProjectWithAttachment(String payload, String projectID) {
		response = createIssueWithAttachment(payload, projectID);
		return this;

	}
	


	public JiraServices validateStatusCode(int statusCode) {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(statusCode));
		System.out.println("Status Code "+ response.getStatusCode());
		return this;

	}
	
	public JiraServices validateStatusLine(String statusLine) {
		MatcherAssert.assertThat(response.getStatusLine(), Matchers.containsString(statusLine));
		System.out.println("Status Line "+ response.getStatusLine());
		return this;

	}
	
	public JiraServices validateContentType(String contentType) {
		assertThat(response.getContentType(), containsString(contentType));
		System.out.println("Content Type "+ response.getContentType());
		return this;
	}
	
	public JiraServices responseAsPrettyString() {
		System.out.println("Pretty String "+ response.asPrettyString());
		return this;
	}

	}
