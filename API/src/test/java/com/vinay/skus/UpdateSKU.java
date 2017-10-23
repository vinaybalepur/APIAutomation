package com.vinay.skus;

import org.hamcrest.Matchers;
import org.hamcrest.number.IsCloseTo;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.vinay.updatesku_pojo.UpdateSkuData;
import com.vinay.utils.RestUtilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateSKU 
{
	RequestSpecification rspec;
	@BeforeMethod
	public  void setup()
	{
		rspec = RestUtilities.buildRequest();
	}
	
	
	
	@Test(enabled = true)
	public void test2()
	{
		Object stock = 100;
		String title = "New title";
		Object weight = 10;
		
		UpdateSkuData skuData = new UpdateSkuData();
		skuData.setStock(stock);
		skuData.setTitle(title);
		skuData.setWeight(weight);
		
		rspec = RestUtilities.setPathParameter(rspec, "skuid", "1150");
		RestUtilities.setEndPoint("/skus/{skuid}");
		
		RestUtilities.setBody(rspec, skuData).toString();
		RestUtilities.buildResponseSpecification();
		
		Response response = RestUtilities.getResponse(rspec, "put");
		
		RestUtilities.validateResponseHeader(response,"Content-Length", "13");
		
		JsonPath jp = RestUtilities.getjsonpath(response);
		Assert.assertEquals(jp.get("done"), true);
		
		rspec = RestUtilities.setPathParameter(rspec, "skuid", "1150");
		RestUtilities.setEndPoint("/skus/{skuid}");
		RestUtilities.buildResponseSpecification();
		
		response = RestUtilities.getResponse(rspec, "get");
		
		jp = RestUtilities.getjsonpath(response);
		Assert.assertEquals(jp.get("stock"), stock);
		Assert.assertEquals(jp.get("title"), title);
		Assert.assertEquals(jp.get("weight"), weight);
		
		response.then().body("channel_skus.find {it.id=2311}.mrp",Matchers.equalTo("10"));
		response.then().body("channel_skus.findAll {it.id=2311}.mrp",Matchers.contains("10"));
		
		System.out.println( response.getCookies().keySet() + "\n"+response.getCookies().values().toString());
	}

}
