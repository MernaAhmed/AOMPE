package com.vodafone.api;

import org.json.JSONObject;

public class HttpResponse {
    public JSONObject parse(String body) {
        return new JSONObject(body);
    }
}
