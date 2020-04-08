package testCases;

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
public class TC001_GET_Request {
	
	@Test
	void getWeatherDetails() {
		
		//Specify the base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city/";
		
		//Request object
		RequestSpecification httprequest = RestAssured.given();

		//Response object
		Response response = httprequest.request(Method.GET, "Toronto");
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

}
