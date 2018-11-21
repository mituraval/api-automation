package httpget;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Testrequests {
	
	@BeforeClass
	public void setBaseUri() {
		RestAssured.baseURI = "http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/";
	}
	
	
	@Test(priority=1)
	public void checkResponse() {
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		
		// svc end-points separate
		Response response = httpRequest.request(Method.GET,"/users/1");
		Response respcomments = httpRequest.request(Method.GET,"/comments/1");
		Response respposts = httpRequest.request(Method.GET,"/posts/1");
		
		// user check
		JsonPath jsonPathEvaluator = response.jsonPath();
		String username = jsonPathEvaluator.get("username");
		Assert.assertEquals("Bret", username);
		System.out.println("username returned from response "+ username);
		
		// comments check
		JsonPath jsonpathComments = respcomments.jsonPath();
		String comments = jsonpathComments.get("name");
		Assert.assertEquals("test put update", comments);
		System.out.println("comment name returned from response: "+ comments);
		
		//posts check
		JsonPath jsonpathPosts = respposts.jsonPath();
		String posts = jsonpathPosts.get("title");
		Assert.assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", posts);
		System.out.println("post title reurned from response: "+ posts);
		
		// response code check
		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);	
		//response time check
		System.out.println("Get requests time: "+response.getTime());
	}
	@Test(priority=2)

	public void postCheck()
	{	
		 try {
				HttpResponse<String> response = Unirest.post("http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/posts")
						  .header("Content-Type", "application/json")
						  .header("cache-control", "no-cache")
						  .body("{\n        \"userId\": 1,\n        \"title\": \"mit test post automated for title\",\n        \"body\": \"mit test post for automated body\"\n    }")
						  .asString();
				int postcode = response.getStatus();
				System.out.println(response.getStatus());
				Assert.assertEquals(postcode, 201);
				response.getBody();
				System.out.println("this is posts automated :"+ response.getBody());
				
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		 
		
	}
	@Test(priority=3)
	public void commentsCheck() {
		
		 try {
			 HttpResponse<String> response = Unirest.post("http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/comments/")
					  .header("Content-Type", "application/json")
					  .header("cache-control", "no-cache")
					  .body("{\n        \"postId\": 7009,\n        \"name\": \"mit test comment automated\",\n        \"email\": \"yaddi@yopmail.com\",\n        \"body\": \"Post request comment automated\"\n    }")
					  .asString();
				int postcode = response.getStatus();
				System.out.println(response.getStatus());
				Assert.assertEquals(postcode, 201);
				response.getBody();
				System.out.println("this is comments automated :"+ response.getBody());
				
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		 
		
	}
	@Test(priority=4)
	
	public void userCheck() {
		
		 try {
			 HttpResponse<String> response = Unirest.post("http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/users")
					  .header("Content-Type", "application/json")
					  .header("cache-control", "no-cache")
					  .body("{\n        \"name\": \"mit test\",\n        \"username\": \"mittest\",\n        \"email\": \"mittest@yopmail.com\",\n        \"address\": {\n            \"street\": \"Dayna Park\",\n            \"suite\": \"Suite 449\",\n            \"city\": \"Bartholomebury\",\n            \"zipcode\": \"76495-3109\",\n            \"geo\": {\n                \"lat\": \"24.6463\",\n                \"lng\": \"-168.8889\"\n            }\n        },\n        \"phone\": \"(770)976-6794\",\n        \"website\": \"conrad.com\",\n        \"company\": {\n            \"name\": \"mit and Sons\",\n            \"catchPhrase\": \"Switchable contextually-based project\",\n            \"bs\": \"aggregate real-time technologies\"\n        }\n    }")
					  .asString();
				int postcode = response.getStatus();
				System.out.println(response.getStatus());
				Assert.assertEquals(postcode, 201);
				response.getBody();
				System.out.println("this is user post automated :"+ response.getBody());
				
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		 
		
	}

}

