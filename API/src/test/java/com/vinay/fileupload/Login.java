package com.vinay.fileupload;

import io.restassured.RestAssured;

public class Login {

	
	public static void main(String[] args) {
		RestAssured.basePath= "https://demo.actitime.com/login.do";
		
		RestAssured.given()
		.param("username", "admin")
		.param("pwd", "manager")
		.when()
		.post()
		.then()
		.extract().response().prettyPrint();
	}
}
