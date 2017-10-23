package com.vinay.fileupload;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UploadFile 
{
	
	public static void main(String[] args) {
		RestAssured.baseURI = "http://demo.websupergoo.com";
		RestAssured.basePath = "/abcocr/";
		
		Response res = RestAssured.given()
			.multiPart(new File("/home/vinay/Desktop/xpath.png"))
			.when()
			.post()
			.then()
			.extract().response();
		System.out.println(res.statusCode());
		System.out.println(res.body().prettyPrint());
	}

}
