package step.defitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

import org.hamcrest.Matchers;

public class IncidentSteps {
	
	RequestSpecBuilder builder = new RequestSpecBuilder();
	Response response;

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

	@When("user hit the get http method")
	public void user_hit_the_get_http_method() {
		response = given()
				    .spec(builder.build())
				    .log().all()
				   .when()
				    .get();
	}

	@Then("user recived the succesfully response")
	public void user_recived_the_succesfully_response() {
		response.then()
		        .log().all()
		        .assertThat()
		        .statusCode(200)
		        .statusLine(Matchers.containsString("OK"))
		        .contentType("application/json");
	}
	
	@Then("user recived the success response with following detail")
	public void user_recived_the_success_response_with_following_detail(DataTable dataTable) {
	    List<List<String>> asLists = dataTable.asLists();
	    for (int i = 0; i < asLists.size(); i++) {
	    	response.then()
	    	        .log().all()
	    	        .assertThat()
	    	        .statusCode(Integer.parseInt(asLists.get(i).get(0)))
	    	        .statusLine(Matchers.containsString(asLists.get(i).get(1)))
	    	        .contentType(asLists.get(i).get(2))
	    	        .body("result.sys_id", Matchers.equalTo(asLists.get(i).get(3)));
		}
	}

}