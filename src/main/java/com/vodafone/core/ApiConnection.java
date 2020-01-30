package com.vodafone.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {
	
	private int statusCode ;
	private String requestURL ;
	private HttpURLConnection requestConnection ;
	public ApiConnection(String requestURL) {
		this.requestURL = requestURL;
	}
	public int getStatusCode(){
		return this.statusCode;
	}
	
	public void setRequestURL(String URL){
		this.requestURL = URL;
	}
	
	public void get() throws IOException {
		URL obj = new URL(this.requestURL);
		requestConnection = (HttpURLConnection) obj.openConnection();
		requestConnection.setRequestMethod("GET");
		requestConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
		this.statusCode = requestConnection.getResponseCode();
	}
	public String getResponseAsString() throws IOException{
		BufferedReader in = new BufferedReader(
				new InputStreamReader(this.requestConnection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
	public Object getConnectionObject() {
		return this.requestConnection;
	}

}
