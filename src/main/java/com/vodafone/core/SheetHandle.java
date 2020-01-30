package com.vodafone.core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetHandle {
	private String EXCEL_SHEET_NAME = "Sheet1";
	private String path ;
	private String [][] rows ;
	public SheetHandle(String sheetPath){
		this.path = sheetPath;
	}
    public String[][] read() throws Exception {
        XSSFSheet myExcelSheet = open(this.getStream(this.path));
        if(isAValidStructure(myExcelSheet)){
             throw new Exception("File structure is not valid \" it should be 5 columns \" ");
        }
        String[][] sheetRows = new String[myExcelSheet.getPhysicalNumberOfRows()][5];
        int rowNumberIterator = 0;
        DataFormatter formatter = new DataFormatter();
        for (Iterator iterator = myExcelSheet.rowIterator(); iterator.hasNext(); ) {
            XSSFRow row = (XSSFRow) iterator.next();
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                XSSFCell cell = row.getCell(i);
                sheetRows[rowNumberIterator][i] = formatter.formatCellValue(cell);
            }
            rowNumberIterator++;
        }
        this.rows = sheetRows;
        return sheetRows;
    }
    
    public String [] getRows(){
    	return this.rows[1];
    }
    private boolean isAValidStructure(XSSFSheet myExcelSheet) {
        return myExcelSheet.getRow(0).getPhysicalNumberOfCells() != 5;
    }

    private XSSFSheet open(FileInputStream sheet) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(sheet);
        return myExcelBook.getSheet(this.EXCEL_SHEET_NAME);
    }
    
    public String[] getHeader() throws Exception {
        return this.rows[0];
    }
    
    private FileInputStream getStream(String s) throws FileNotFoundException {
        URL streamURL = getClass().getClassLoader().getResource(s);
        assert streamURL != null;
        return new FileInputStream(streamURL.getPath());
    }

}
