package com.vodafone.api.gift;
//package ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.vodafone.api.HttpRequest;
import com.vodafone.core.SheetHandle;

public class Gift {

	public String getURL(String [] urlParams) throws FileNotFoundException, Exception{
		HttpRequest httpRequest = new HttpRequest();
		return httpRequest.createRequestURLFromParams(urlParams);
	}
}