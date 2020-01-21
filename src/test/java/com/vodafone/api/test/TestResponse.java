package com.vodafone.api.test;
import org.junit.Test;

import com.vodafone.api.core.*;
import org.testng.Assert;

public class TestResponse {
    @Test
    public void testHttpResponseConvertToJson() {
        String responseSample = "{\"failed\":false,\"statusId\":5,\"messageId\":0} ";
        HttpResponse httpResponse = new HttpResponse();
        Assert.assertEquals(httpResponse.parse(responseSample).get("statusId"),5);
        Assert.assertEquals(httpResponse.parse(responseSample).get("failed"),false);
        Assert.assertEquals(httpResponse.parse(responseSample).get("messageId"),0);
    }
    
}
