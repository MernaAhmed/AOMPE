package com.vodafone.api.gift;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.vodafone.api.core.HttpRequest;
import com.vodafone.api.core.HttpResponse;
import com.vodafone.api.core.SheetHandle;

public class hj {

	public static void main(String[] args) throws FileNotFoundException, Exception {
		Gift offer = new Gift();
		SheetHandle sheet = new SheetHandle("offline-trigger-params-one-row.xlsx");
		sheet.read();
		String [] urlParams = sheet.getRows();
		String offerURL = offer.getURL(urlParams);
		System.out.println(offerURL);
	
		String url = offerURL;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		//print in String
		String str = response.toString();
		HttpResponse httpResponse = new HttpResponse();
		JSONObject responseObject = httpResponse.parse(str);
		System.out.println(responseObject);
		System.out.println(responseObject.get("statusId"));;
		System.out.println(responseObject.get("failed"));

	}
	

}
