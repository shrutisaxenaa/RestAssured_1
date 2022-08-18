package testCases;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ReadOneProduct {
	
	@Test
	public void readOneProduct() {
		
		
		/*
			given: all input details(baseURI,Headers,PayLoad/Body,QueryParamaeters,Authorization)
		    when: submit API requests(HTTP method, Endpoint/resource)
		     then: validate response(status code,Headers, responseTime, payLoad/Body)
			 
		 */
		
		Response response =
		
	given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.header("Content-Type","application/json")
		.queryParam("id", "5108")
		.auth().preemptive().basic("demo@techfios.com", "abc123").
		
    when()
         
         .get("/read_one.php?id=5108").
         
    then()
    
          .extract().response();
	
	int statusCode=	response.getStatusCode();
	System.out.println("Status Code :"+statusCode);
	Assert.assertEquals(statusCode, 200);
	
	

	long responseTime=response.getTimeIn(TimeUnit.MILLISECONDS);
	System.out.println("Response Time is :"+responseTime);
	
	if(responseTime<=2000) {
		System.out.println("Response time is within the Range");
	}
	       
	else {
		System.out.println("Response Time is not in the Range");
	}
		
	
	String responseHeader=response.getHeader("Content-Type");
	
	System.out.println("Response Header is:"+responseHeader);
	Assert.assertEquals(responseHeader, "application/json");
	
	String responseBody=response.getBody().asString();
	response.getBody().prettyPrint();
	
	
	JsonPath jsonPath= new JsonPath(responseBody);
	
	String ProductID=jsonPath.get("id");
	System.out.println("Product ID :"+ProductID);
	
	
	}

}
