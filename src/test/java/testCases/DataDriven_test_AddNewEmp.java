package testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.TestData;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDriven_test_AddNewEmp {
	
	@Test
	void postNewEmployee() {
		
		//Fetching all the data required ----
		TestData testData = new TestData();
		testData.getTestData("TC_001", "Sheet1");
		String name = testData.getFieldValue("Name");
		String salary = testData.getFieldValue("Salary");
		String age = testData.getFieldValue("Age");
		
		//Specify the base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		//Request object
		RequestSpecification httprequest = RestAssured.given();

		//Request payload sent with parameters
		JSONObject requestParams = new JSONObject();

		requestParams.put("name", name);
		requestParams.put("salary", salary);
		requestParams.put("age", age);

		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestParams.toJSONString());

		//Response object
		Response response = httprequest.request(Method.POST, "/create");


		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
}
