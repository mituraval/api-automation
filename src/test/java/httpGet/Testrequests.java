package httpGet;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Testrequests {
	@BeforeClass
	public void setBaseUri() {
		RestAssured.baseURI = "http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/";
	}
	
	
	@Test(priority=1)
	public void checkResponse() {
		Response resp = RestAssured.get("http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/users");
		//RestAssured.baseURI = "http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		Response response = httpRequest.request(Method.GET,"/users/1");
		JsonPath jsonPathEvaluator = response.jsonPath();
		String username = jsonPathEvaluator.get("username");
		Assert.assertEquals("Bret", username);
		System.out.println("username received from response "+ username);
		int code = resp.getStatusCode();
		Assert.assertEquals(code, 200);
		System.out.println("Get call response time in MS: " + resp.getTime());			
	}
	@Test(priority=2)

	public void postCheck()
	{		
		RestAssured.baseURI ="http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/todos";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("userId", 1);
		requestParams.put( "id", 1);
		requestParams.put( "title","test title");
		requestParams.put("completed", true);
		request.body(requestParams.toJSONString());
		Response response = request.post("/1");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, "201");
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
	}

}
