package com.vinay.specifications;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vinay.updatesku_pojo.UpdateSkuData;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UnderstandingPUT 
{
	RequestSpecBuilder requestSpecification;
	static RequestSpecification requestSpec;
	ResponseSpecBuilder reponseSpecBuilder;
	static ResponseSpecification responseSpec;
	final String accessToken = "diBwUWxCi9Kh7Hg75a3nCYSDYq2qvXvWzlFCQ55y"; 
	UpdateSkuData updateData = new UpdateSkuData();
	
	@BeforeMethod 
	public void setup()
	{
		requestSpecification = new RequestSpecBuilder();
		requestSpecification.setBaseUri("https://sandbox-connect.sellerworx.com");
		requestSpecification.setBasePath("/api/v7");
		requestSpecification.addHeader("Authorization", "Bearer " + accessToken);
		
		requestSpec = requestSpecification.build();
		
		reponseSpecBuilder = new ResponseSpecBuilder();
		reponseSpecBuilder.expectStatusCode(200);
		responseSpec = reponseSpecBuilder.build();
	}
	
	@Test(enabled = false)
	public void updateData()
	{
		updateData.setStock(10);
		updateData.setTitle("Some Title");
		updateData.setWeight(100);
		
		Response response = RestAssured
			.given()
			.spec(requestSpec)
			.body(updateData)
			.when()
			.put("/skus/1150")
			.then()
			.spec(responseSpec)
			.extract()
			.response();
		System.out.println( response.prettyPrint());
		
		
		
		
	}

	@Test
	public void updateDataAndValidate()
	{
		updateData.setStock(10);
		updateData.setTitle("Some Title");
		updateData.setWeight(100);
		
		
			given()
			.spec(requestSpec)
			.body(updateData)
			.when()
			.put("/skus/1150")
			.then()
			.spec(responseSpec)
			.and()
			.body("done", is(true));
		
		
		
	}
}
