package testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


/**
 * @author ajay
 *
 */
public class TC002_POST_Request {

	
	@Test
	void registerCustomer() {
		
		//Specify the base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer/";
		
		//Request object
		RequestSpecification httprequest = RestAssured.given();

		//Request payload sent with parameters
		JSONObject requestParams = new JSONObject();

		requestParams.put("FirstName", "Mack");
		requestParams.put("LastName", "Doe");
		requestParams.put("UserName", "mack05");
		requestParams.put("Password", "mack_05");
		requestParams.put("Email", "mack05@gmail.com");

		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestParams.toJSONString());

		//Response object
		Response response = httprequest.request(Method.POST, "register");


		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");

	}
}
