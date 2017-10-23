package com.vinay.utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.vinay.auth.Auth;
import com.vinay.path.Path;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestUtilities 
{
	
	
	private static  String ENDPOINT = "";
	
	public static RequestSpecBuilder REQUEST_BUILDER;
	public static RequestSpecification REQUEST_SPEC;
	public static ResponseSpecBuilder RESPONSE_BUILDER;
	public static ResponseSpecification RESPONSE_SPEC;
	
	public static void  setEndPoint(String endpoint)
	{
		RestUtilities.ENDPOINT = endpoint;
	}
	
	public static RequestSpecification buildRequest()
	{
		REQUEST_BUILDER = new RequestSpecBuilder();
		REQUEST_BUILDER.setBaseUri(Path.BASE_URI);
		REQUEST_BUILDER.setBasePath(Path.BASE_PATH);
		REQUEST_BUILDER.addHeader("Authorization", "Bearer " + Auth.ACCESS_TOKEN);
		
		REQUEST_SPEC = REQUEST_BUILDER.build();
		return REQUEST_SPEC;
	}
	
	public static ResponseSpecification buildResponseSpecification()
	{
		RESPONSE_BUILDER = new ResponseSpecBuilder();
		RESPONSE_BUILDER.expectStatusCode(200);
		RESPONSE_BUILDER.expectResponseTime(lessThan(20L), TimeUnit.SECONDS);
		RESPONSE_SPEC = RESPONSE_BUILDER.build();
		return RESPONSE_SPEC;
	}
	
	public static RequestSpecification createQueryParameter(RequestSpecification rspec, String param, String value)
	{
		return rspec.queryParam(param, value);
	}
	
	public static RequestSpecification createQueryParameter(RequestSpecification rspec, Map<String, String> map)
	{
		return rspec.queryParams(map);
	}
	
	public static RequestSpecification setPathParameter(RequestSpecification rspec, String param, String value)
	{
		return rspec.pathParam(param, value);
	}
	
	public static RequestSpecification setBody(RequestSpecification rspec, Object body)
	{
		return rspec.body(body);
	}
	
	public static Response getResponse(RequestSpecification reqspec, String type)
	{
		
		Response response = null;
		
		if(type.equalsIgnoreCase("get"))
		{
			response = given().log().ifValidationFails().spec(reqspec).get(ENDPOINT);
		}else if(type.equalsIgnoreCase("post"))
		{
			response = given().spec(reqspec).post(ENDPOINT);
		}else if(type.equalsIgnoreCase("put"))
		{
			response = given().log().ifValidationFails().spec(reqspec).put(ENDPOINT);
		}
		response.then().log().all();
		
		response.then().spec(RESPONSE_SPEC);
		return response;
	}
	
	
	public static void validateResponseHeader(Response response, String key, String expected)
	{
		response.then().header(key, expected);
	}
	
	public static JsonPath getjsonpath(Response res)
	{
		JsonPath jp = new JsonPath(res.asString());
		return jp;
	}
	
	

}
