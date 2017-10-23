package com.vinay.assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class SoftAssertions {

	static String key = "AIzaSyBM967BMeod7kYFObRewfO0tBdisrMeQ4Q";

	@Test
	public void requestLoggin() {
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
		given().param("origins", "Vancouver+BC").param("destinations", "San+Francisco").param("key", key).when()
				.get("/distancematrix/json").then().statusCode(200)
				.body("origin_addresses.size()", lessThanOrEqualTo(0),
					  "origin_addresses", hasItem("Vancouver, BC, Canada")
					 );
				

	}

}
