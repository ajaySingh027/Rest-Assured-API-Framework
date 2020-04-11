package testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utility.TestData;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDriven_test_AddNewEmp extends TestData {
	
	
	public TestData testData = null;
	public String name;
	public String salary;
	public String age;
	RequestSpecification httprequest;
	Response response;
	
	
	@BeforeTest
	public void fetchData() {
		
		//Fetching all the data required ----
		TestData testData = new TestData();
		testData.getTestData("TC_001", "Sheet1");
		name = testData.getFieldValue("Name");
		salary = testData.getFieldValue("Salary");
		age = testData.getFieldValue("Age");
		
	}
	
	@BeforeClass
	void postNewEmployee() {
		
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
		response = httprequest.request(Method.POST, "/create");

		
	}
	
	
	@Test
	void validateResponse() {
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
	}
	
	@Test
	void validateStatusCode() {
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
 	
}
