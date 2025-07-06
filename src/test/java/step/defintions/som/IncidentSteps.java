package step.defintions.som;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.IncidentPayLoad;
import som.IncidentService;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

public class IncidentSteps {
	
	IncidentPayLoad requestPayload = new IncidentPayLoad();
	RequestSpecBuilder builder = new RequestSpecBuilder();
	Response response;
	
	IncidentService incidentService = new IncidentService();

	@Given("user set base uri {string} of the service now instance")
	public void user_set_base_uri_of_the_service_now_instance(String baseUri) {
		builder.setBaseUri(baseUri);
	}

	@Given("user set the base path {string} of the service now table api")
	public void user_set_the_base_path_of_the_service_now_table_api(String basePath) {
		builder.setBasePath(basePath);
	}

	@Given("user set the path parameter for {string} key as {string} value")
	public void user_set_the_path_parameter_for_key_as_value(String key, String value) {
		builder.addPathParam(key, value);
	}

	@Given("user set the basic authencation username as {string} and password as {string}")
	public void user_set_the_basi_authencation_username_as_and_password_as(String username, String password) {
		builder.setAuth(basic(username, password));
	}
	
	@Given("user set path parameters key and value")
	public void user_set_path_parameters_key_and_value(DataTable dataTable) {
		List<List<String>> asLists = dataTable.asLists();
		for (int i = 0; i < asLists.size(); i++) {
			builder.addPathParam(asLists.get(i).get(0), asLists.get(i).get(1));
		}
	}
	
	@Given("user set the {string} as a key and {string} as value in the header")
	public void user_set_the_as_a_key_and_as_value_in_the_header(String key, String value) {
	    builder.addHeader(key, value);
	}

	@When("user hit the get http method")
	public void user_hit_the_get_http_method() {
		response = incidentService.retrieveRecords(builder.build());
	}
	
	@When("user set the description {string} in the request body")
	public void user_set_the_description_in_the_request_body(String description) {
		requestPayload.setDescription(description);
	}
	
	@When("user set the short description {string} in the request body")
	public void user_set_the_short_description_in_the_request_body(String shortDescription) {
	    requestPayload.setShort_description(shortDescription);
	}
	
	@When("user hit the post http method")
	public void user_hit_the_post_http_method() {	   
	   response = incidentService.createRecord(builder.build(), requestPayload);   
	}

	@Then("user recived the succesfully response")
	public void user_recived_the_succesfully_response() {
		incidentService.validateResponse(response, 200, "OK", ContentType.JSON);
	}
	
	@Then("user recived the success response with following detail")
	public void user_recived_the_success_response_with_following_detail(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		incidentService.validateResponse(response, 
				Integer.parseInt(map.get("statusCode")), 
				map.get("statusLine"), 
				map.get("contentType"));
		incidentService.validateResponseBody(response, "result.sys_id", map.get("sysId"));
	}
	
	@Then("user finds in a single record in the incident table")
	public void user_finds_in_a_single_record_in_the_incident_table() {
		incidentService.validateResponse(response, 201, "Created", ContentType.JSON);
	}
	
	@Then("user able to create the mulitple records successfully")
	public void user_able_to_create_the_mulitple_records_successfully() {
		incidentService.validateResponse(response, 201, "Created", ContentType.JSON);
	}

}