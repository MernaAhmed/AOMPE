package com.vodafone.api.core;

import org.json.JSONObject;

public class HttpResponse {
    public JSONObject parse(String body) {
        return new JSONObject(body);
    }
}
