package com.vodafone.api;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class HttpRequest {
    private String[] paramsKeys = {
            "IP",
            "tsid",
            "trigger_params",
            "ttid",
            "msisdn"
    };
    private String EXCEL_SHEET_NAME = "Sheet1";

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
