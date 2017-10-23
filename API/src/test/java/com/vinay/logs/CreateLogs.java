package com.vinay.logs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;

public class CreateLogs 
{
	
static String key = "AIzaSyBM967BMeod7kYFObRewfO0tBdisrMeQ4Q";
	
@Test
public void requestLoggin()
{
	RestAssured.baseURI = "https://maps.googleapis.com";
	RestAssured.basePath= "/maps/api";
	Response response = given()
		.log()
//		.headers()
//		.all()
		.ifValidationFails(LogDetail.HEADERS)
		.param("origins", "Vancouver+BC")
		.param("destinations", "San+Francisco")
		.param("key", key)
	.when()
		.get("/distancematrix/xml")
	.then()
		.statusCode(200)
		.extract().response();
	
	
	
}

@Test
public void responseLoggin()
{
	RestAssured.baseURI = "https://maps.googleapis.com";
	RestAssured.basePath= "/maps/api";
	Response response = given()
		.param("origins", "Vancouver+BC")
		.param("destinations", "San+Francisco")
		.param("key", key)
	.when()
		.get("/distancematrix/xml")
	.then()
		.log()
//		.all()
//		.ifError()
		.ifValidationFails()
		.statusCode(200)
		.extract().response();
}

	

}
