package com.vinay.specifications;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UnderstandingSpecifications 
{
	
	RequestSpecBuilder requestSpecification;
	static RequestSpecification requestSpec;
	ResponseSpecBuilder reponseSpecBuilder;
	static ResponseSpecification responseSpec;
	final String accessToken = "u6szGrxU8pFRLF4IW7r3SUEaXlYlAFZVWFsjOaJ2"; 
	
	@BeforeMethod
	public void setup()
	{
//		RestAssured.baseURI = "https://sandbox-connect.sellerworx.com";
//		RestAssured.basePath = "/api/v7";
		
		requestSpecification = new RequestSpecBuilder();
		requestSpecification.setBaseUri("https://sandbox-connect.sellerworx.com");
		requestSpecification.setBasePath("/api/v7");
		
//		AuthenticationScheme auth = RestAssured.oauth2("YKd9I6WiFE78sCaobCO8iC79b8r60nw3rSZg9Y4l",OAuthSignature.HEADER);
//		requestSpecification.setAuth(auth);
		requestSpecification.addHeader("Authorization", "Bearer " + accessToken);
		requestSpec = requestSpecification.build();
		
		reponseSpecBuilder = new ResponseSpecBuilder();
		reponseSpecBuilder.expectStatusCode(200);
		responseSpec = reponseSpecBuilder.build();
		
	}
	
	@Test
	public void test()
	{
		Response response = 
		given()
		.spec(requestSpec)
		.log()
		.all()
		.get("/skus")
		
		.then()
		.spec(responseSpec)
		.extract().response();
		System.out.println(response.asString());
		
	}

}
