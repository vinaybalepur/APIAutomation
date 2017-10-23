package com.vinay.rootpath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;

public class RootPath 
{
	
static String key = "AIzaSyBM967BMeod7kYFObRewfO0tBdisrMeQ4Q";
	

	
	@Test
	public void requestLoggin()
	{
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath= "/maps/api";
		RestAssured.rootPath = "rows.elements.distance";
		given()
			.param("origins", "Vancouver+BC")
			.param("destinations", "San+Francisco")
			.param("key", key)
		.when()
			.get("/distancematrix/json")
		.then()
//			.rootPath("rows.elements.distance")
			.body("text", Matchers.containsInAnyOrder("1,527 km"))
			.statusCode(200)
			.body("origin_addresses", hasItem("Vancouver, BC, Canada"))
			.body("origin_addresses.size()", lessThanOrEqualTo(1));
		
	}


}
