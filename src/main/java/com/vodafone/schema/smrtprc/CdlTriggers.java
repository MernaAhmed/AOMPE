package com.vodafone.schema.smrtprc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.vodafone.core.DBConnection;

public class CdlTriggers {
	
	private int MSISDN;
	private int TYPE_ID;
	private String TIME;
	public String tableName = "CDL_TRIGGERS";
    public DBConnection dbConnection;
    private Statement statement;
    String SELECT_QUERY = "SELECT MSISDN,IDI,IDV,IDD,TYPE_ID,GP1,GP2,STATUS_ID from %s where MSISDN = %d and TYPE_ID=%d and TIME > \'%s\'";
    private HashMap<String,String> resultMap=new HashMap<String,String>();  
    
	public int getMSISDN() {
	    return MSISDN;
	}
	public void setMSISDN(int MSISDN) {
	    this.MSISDN = MSISDN;
	}
	public int getTYPE_ID() {
		return this.TYPE_ID;
	}
	public void setTYPE_ID(int typeID) {
		this.TYPE_ID = typeID;
	}
	public String getSEEN_TIME() {
		return this.TIME;
	}
	public void setSEEN_TIME(String time) {
		this.TIME = time;
	}
   // for application use, to create new persistent objects
   public CdlTriggers(int MSISDN,int typeID,String time) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException
   {
      this.MSISDN = MSISDN;
      this.TYPE_ID=typeID;
      this.TIME = time;
      initiateConnection();
   }
	private void initiateConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		dbConnection = new DBConnection();
	    statement = dbConnection.getStatment();
	}
	public void get() throws SQLException {
		executeSelectQuery(String.format(this.SELECT_QUERY,this.tableName,this.MSISDN,this.TYPE_ID,this.TIME));
	}
	
	private void executeSelectQuery(String query) throws SQLException {
		ResultSet selectSet = statement.executeQuery(query);
		selectSet.next();
		try {
		setQueryResultValuesToMap(selectSet);
		}catch(SQLException sqlexcp) {
			throw new SQLException("No Records found");
		}
	}
	private void setQueryResultValuesToMap(ResultSet selectSet) throws SQLException {
		for(int columnNumber=1;columnNumber<=selectSet.getMetaData().getColumnCount();columnNumber++) {
			resultMap.put(selectSet.getMetaData().getColumnName(columnNumber).toString(),selectSet.getString(columnNumber));
		}
	}
	
	public String getValueOf(String column) {
		return resultMap.get(column);
	}
	
}