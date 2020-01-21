package com.vodafone.api.test;

import org.junit.Assert;
import org.junit.Test;

import com.vodafone.api.core.HttpRequest;

public class TestRequest {

    @Test
    public void testCreateRequestURL() {
        String expectedRequestSample = "http://10.230.85.119:7005/cdl-trg-intg/trgsubmit?tsid=2&trigger_params=TP1:7.000000;TP2:%2220200108235539%22;TP3:1723;TP4:8.340000;TP5:%22RC46%22;TP6:%22005817310803%22;TP7:%22%22;TP8:%221%22;TP9:%22%22&ttid=1127&msisdn=1020201144";
        HttpRequest httpRequest = new HttpRequest();
        String[] params = {"http://10.230.85.119:7005/cdl-trg-intg/trgsubmit?",
                "2",
                "TP1:7.000000;TP2:%2220200108235539%22;TP3:1723;TP4:8.340000;TP5:%22RC46%22;TP6:%22005817310803%22;TP7:%22%22;TP8:%221%22;TP9:%22%22",
                "1127",
                "1020201144"};

        String actualRequestURL = httpRequest.createRequestURLFromParams(params);
        Assert.assertEquals(actualRequestURL, expectedRequestSample);
    }
}
