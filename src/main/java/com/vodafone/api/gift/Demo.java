package com.vodafone.api.gift;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.vodafone.api.core.ApiConnection;
import com.vodafone.api.core.HttpResponse;
import com.vodafone.api.core.SheetHandle;

public class Demo {

	public static void main(String[] args) throws FileNotFoundException, Exception {
		Gift gift = new Gift();
		SheetHandle sheet = new SheetHandle("offline-trigger-params-one-row.xlsx");
		sheet.read();
		String [] urlParams = sheet.getRows();
		String offerURL = gift.getURL(urlParams);
		System.out.println(offerURL);
	
		String url = offerURL;
		ApiConnection apiConnectionObj = new ApiConnection(url);
		apiConnectionObj.get();
		String str = apiConnectionObj.getResponseAsString();
		HttpResponse httpResponse = new HttpResponse();
		JSONObject responseObject = httpResponse.parse(str);
		System.out.println(responseObject);
		System.out.println(responseObject.get("statusId"));;
		System.out.println(responseObject.get("failed"));

	}
	

}
