package com.vinay.specifications;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetXmlResponse
{
	
	static String key = "AIzaSyBCxNnQ11ZsNntvCVbFvuI95hOOOqzfjxo";
	
	@Test
	public void validateStatusCode()
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
			.statusCode(200)
			.extract().response();
		System.out.println(response.prettyPrint());
		
		String duration = response.path("distancematrixresponse.row.element.duration.value");
		System.out.println(duration);
		
		String responsexml = response.asString();
		
		XmlPath xmlpath = new XmlPath(responsexml);
		duration = xmlpath.get("distancematrixresponse.row.element.duration.value");
		System.out.println(duration);
		
	}

}
