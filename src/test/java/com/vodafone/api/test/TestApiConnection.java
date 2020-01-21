package com.vodafone.api.test;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vodafone.api.core.ApiConnection;

public class TestApiConnection {
	private String requestURL="http://10.230.85.119:7005/cdl-trg-intg/trgsubmit";
	private ApiConnection apiConnectionObj;
	@Before
	public void setUp(){
		apiConnectionObj = new ApiConnection(this.requestURL);
	}
	
	@Test
	public void testCreateApiConnection() throws IOException{
		this.apiConnectionObj.get();
		Assert.assertNotNull(this.apiConnectionObj.getConnectionObject());
	}
	@Test
	public void testSendGetRequestSucceeds() throws IOException{
		this.apiConnectionObj.get();
		Assert.assertEquals(200, this.apiConnectionObj.getStatusCode());
	}
	
	@Test
	public void testGetResponseisNotNull() throws IOException{
		this.apiConnectionObj.get();
		Assert.assertEquals("{\"failed\":true,\"statusId\":1,\"messageId\":3}", this.apiConnectionObj.getResponseAsString());
	}
	@After
	public void tearDown(){
		this.apiConnectionObj = null;
		System.gc();
	}
}

