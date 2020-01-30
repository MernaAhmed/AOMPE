package com.vodafone.api.core;


public class HttpRequest {
    private String[] paramsKeys = {
            "IP",
            "tsid",
            "trigger_params",
            "ttid",
            "msisdn"
    };
    public String createRequestURLFromParams(String[] params) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            if (i != 0) {
                builder.append(this.paramsKeys[i]);
                builder.append("=");
            }
            builder.append(params[i]);
            if (i != params.length - 1 && i != 0) {
                builder.append("&");
            }
        }
        return builder.toString();
    }

}
