package com.vodafone.api.test.core;
import com.vodafone.core.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class TestSheetHandle {

    @Test
    public void testReadFromExcelSheetWithOneRow() throws Exception {
        SheetHandle sheetHandleObj = new SheetHandle("offline-trigger-params-one-row.xlsx");
        String[][] excelSheetsParams = sheetHandleObj.read();
        String[] requestParamsValues = excelSheetsParams[1];
        Assert.assertEquals(requestParamsValues[0],
                "http://10.230.85.119:7005/cdl-trg-intg/trgsubmit?");
        Assert.assertEquals(Integer.valueOf(requestParamsValues[1]),
                Integer.valueOf(2));
        Assert.assertEquals(requestParamsValues[2],
                "TP1:7.000000;TP2:%2220200108235539%22;TP3:1723;TP4:8.340000;TP5:%22RC46%22;TP6:%22005817310803%22;TP7:%22%22;TP8:%221%22;TP9:%22%22");
        Assert.assertEquals(Integer.valueOf(requestParamsValues[3]),
                Integer.valueOf(1127));
        Assert.assertEquals(requestParamsValues[4],
                "1020201144");
    }

    @Test
    public void testReadFromExcelSheetWithMultipleRows() throws Exception {
        SheetHandle sheetHandleObj = new SheetHandle("offline-trigger-params-multiple-rows.xlsx");
        String[][] excelSheetsParams = sheetHandleObj.read();
        // test excel header
        String[] headerRowParamsValues = excelSheetsParams[0];
        Assert.assertEquals(headerRowParamsValues[0],
                "IP");
        Assert.assertEquals(headerRowParamsValues[1],
                "tsid");
        Assert.assertEquals(headerRowParamsValues[2],
                "trigger_params");
        Assert.assertEquals(headerRowParamsValues[3],
                "ttid");
        Assert.assertEquals(headerRowParamsValues[4],
                "msisdn");


        // test row #1 ( not header )
        String[] firstRowParamsValues = excelSheetsParams[1];
        Assert.assertEquals(firstRowParamsValues[0],
                "http://10.230.85.119:7005/cdl-trg-intg/trgsubmit?");
        Assert.assertEquals(Integer.valueOf(firstRowParamsValues[1]),
                Integer.valueOf(2));
        Assert.assertEquals(firstRowParamsValues[2],
                "TP1:7.000000;TP2:%2220200108235539%22;TP3:1723;TP4:8.340000;TP5:%22RC46%22;TP6:%22005817310803%22;TP7:%22%22;TP8:%221%22;TP9:%22%22");
        Assert.assertEquals(Integer.valueOf(firstRowParamsValues[3]),
                Integer.valueOf(1127));
        Assert.assertEquals(firstRowParamsValues[4],
                "1020201144");

        // test row #2
        String[] secondRowParamsValues = excelSheetsParams[2];
        Assert.assertEquals(secondRowParamsValues[0],
                "http://10.230.85.119:7005/cdl-trg-intg/trgsubmit?");
        Assert.assertEquals(Integer.valueOf(secondRowParamsValues[1]),
                Integer.valueOf(2));
        Assert.assertEquals(secondRowParamsValues[2],
                "TP1:7.000000;TP2:%2220200108235539%22;TP3:1723;TP4:8.340000;TP5:%22RC46%22;TP6:%22005817310803%22;TP7:%22%22;TP8:%221%22;TP9:%22%22");
        Assert.assertEquals(Integer.valueOf(secondRowParamsValues[3]),
                Integer.valueOf(1127));
        Assert.assertEquals(secondRowParamsValues[4],
                "1019577882");

        // test row #3
        String[] thirdRowParamsValues = excelSheetsParams[3];
        Assert.assertEquals(thirdRowParamsValues[0],
                "http://10.230.85.119:7005/cdl-trg-intg/trgsubmit?");
        Assert.assertEquals(Integer.valueOf(thirdRowParamsValues[1]),
                Integer.valueOf(2));
        Assert.assertEquals(thirdRowParamsValues[2],
                "TP1:7.000000;TP2:%2220200108235539%22;TP3:1723;TP4:8.340000;TP5:%22RC46%22;TP6:%22005817310803%22;TP7:%22%22;TP8:%221%22;TP9:%22%22");
        Assert.assertEquals(Integer.valueOf(thirdRowParamsValues[3]),
                Integer.valueOf(1127));
        Assert.assertEquals(thirdRowParamsValues[4],
                "1020201144");
    }


    @Test
    public void testSheetStructureIsNotValid() throws Exception {
        final SheetHandle sheetHandleObj = new SheetHandle("offline-trigger-params-invalid.xlsx");
        Assert.assertThrows(Exception.class, new ThrowingRunnable() {
            public void run() throws Throwable {
            	sheetHandleObj.read();
            }
        });

    }

    @Test
    public void testSheetHeaderIsValid() throws Exception{
        SheetHandle sheetHandleObj = new SheetHandle("offline-trigger-params-multiple-rows.xlsx");
        sheetHandleObj.read();
        String[] headerRowParamsValues = sheetHandleObj.getHeader();
        Assert.assertEquals(headerRowParamsValues[0],
                "IP");
        Assert.assertEquals(headerRowParamsValues[1],
                "tsid");
        Assert.assertEquals(headerRowParamsValues[2],
                "trigger_params");
        Assert.assertEquals(headerRowParamsValues[3],
                "ttid");
        Assert.assertEquals(headerRowParamsValues[4],
                "msisdn");

    }

    private FileInputStream getSheetStream(String s) throws FileNotFoundException {
        URL res = getClass().getClassLoader().getResource(s);
        assert res != null;
        return new FileInputStream(res.getPath());
    }
}
