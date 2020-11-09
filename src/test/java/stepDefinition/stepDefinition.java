package stepDefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResource;
import resources.InputMyData;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class stepDefinition extends Utils {

	RequestSpecification request;
	ResponseSpecification responseBuilder;
	Response response;
	InputMyData input = new InputMyData();
	public static String place_id;

	@Given("required input payload with {string}, {string} and {string}")
	public void required_input_payload_with_and(String name, String language, String address) throws IOException {

	

		
		request = given().log().all().spec(requestSpecification()).body(input.inputData(name, language, address));

	}

	@When("users calls {string} using {string} request")
	public void users_calls_using_request(String resource, String method) {
		
		APIResource resourceAPI = APIResource.valueOf(resource);
		responseBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST")) {
		response = request.when().post(resourceAPI.getResource());
		
		//response = request.when().post(resourceAPI.getResource()).then().spec(responseBuilder).extract().response();
		}else if(method.equalsIgnoreCase("GET")){
			response = request.when().get(resourceAPI.getResource());
		}
	}
	
	@Then("API call is successful with status code")
	public void api_call_is_successful_with_status_code() {

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
		
		//assertEquals(jp.get(parameter).toString(),value);
		assertEquals(getJsonPath(response,keyValue), ExpectedValue);
		

	}
	
	@Then("validate {string} using place_id generated mapping to {string}")
	public void validate_using_place_id_generated_mapping_to(String resource, String ExpectedName) throws IOException {
		place_id=getJsonPath(response,"place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", place_id);
		users_calls_using_request(resource, "GET");
		String ActualName=getJsonPath(response,"name");
		assertEquals(ActualName, ExpectedName);
	}

	@Given("DeletePlaceAPI payload")
	public void delete_place_api_payload() throws IOException {
		request = given().spec(requestSpecification()).body(InputMyData.deletePlacePayload(place_id));
	    
	}



}
