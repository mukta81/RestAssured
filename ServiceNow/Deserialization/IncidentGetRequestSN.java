package Deserialization;

import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.annotations.Test;

import Deserialization.pojo.ResultModalIncidentPOJOSN;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import week3.day2.payload.pojo.object.Config;

public class IncidentGetRequestSN {
		
	@Test
		public void shouldUserAbleToFetchAllIncidentRecords() {
			String url ="https://dev282303.service-now.com/api/now/table/{table_name}";
			 List<ResultModalIncidentPOJOSN> response = given()
			.auth()
			.basic(Config.getUserName(), Config.getPassword())
			.pathParam("table_name", "incident")
			.queryParam("sysparm_limit", 3)
			.queryParam("sysparm_query", "category=hardware")
			.queryParam("sysparm_fields", "number, sys_id, short_description, opened_at, caller_id, description, category")
			.when()
			.get(url)
			.then()
			.extract()
			.jsonPath()
			.getList("result", ResultModalIncidentPOJOSN.class);
			 System.out.println("Size of List:"+response.size());
			
			for (ResultModalIncidentPOJOSN resultModal : response) {
				System.out.println("NUMBER:"+resultModal.getNumber());
				System.out.println("Short Description:"+resultModal.getDescription());
				System.out.println("Description:"+resultModal.getShort_description());
				System.out.println("Category:"+resultModal.getCategory());
				System.out.println("Opened At:"+resultModal.getOpened_at());
				System.out.println("Sysid:"+resultModal.getSys_id());
				System.out.println("CallerId.Value:"+resultModal.getCaller_id().getValue());
				System.out.println("CallerId.link:"+resultModal.getCaller_id().getLink());
				System.out.println("");
				
				assertThat(resultModal.getCategory(), anyOf(equalTo("hardware"), equalTo("Hardware")));
				
			}
			
			
			 
		}
}
