package com.vodafone.schema.smrtprc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.vodafone.core.DBConnection;

public class CdlTriggers {
	
	private int MSISDN;
    private CdlTriggersIDs cdlTriggersIds;
	public String tableName = "CDL_TRIGGERS";
    private DBConnection dbConnection;
    private Statement statement;
    String SELECT_QUERY = "SELECT MSISDN,IDI,IDV,IDD,TYPE_ID,GP1,GP2,STATUS_ID from %s where MSISDN = %s and IDI=%s and IDD=%s and IDV = %s";
    private HashMap<String,String> map=new HashMap<String,String>();  
    
	public int getMSISDN() {
	    return MSISDN;
	}
	public void setMSISDN(int MSISDN) {
	    this.MSISDN = MSISDN;
	}

    public CdlTriggersIDs getId() {
        return cdlTriggersIds;
    }

    public void setId(CdlTriggersIDs cdlTriggersIds) {
        this.cdlTriggersIds = cdlTriggersIds;
    }
   
   // for application use, to create new persistent objects
   public CdlTriggers(int MSISDN,CdlTriggersIDs cdlTriggersIds) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException
   {
      this.MSISDN = MSISDN;
      this.cdlTriggersIds =cdlTriggersIds;
      initiateConnection();
   }
	private void initiateConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		dbConnection = new DBConnection();
	    statement = dbConnection.getStatment();
	}
	public void get() throws SQLException {
		executeSelectQuery(this.MSISDN);
	}
	private void executeSelectQuery(int testMSISDN) throws SQLException {
		ResultSet selectSet = statement.executeQuery(String.format(this.SELECT_QUERY,this.tableName,testMSISDN,this.cdlTriggersIds.getIDI(),this.cdlTriggersIds.getIDD(),this.cdlTriggersIds.getIDV()));
		selectSet.next();
		map.put("MSISDN",selectSet.getString("MSISDN"));
		map.put("IDI",selectSet.getString("IDI"));
		map.put("IDV",selectSet.getString("IDV"));
		map.put("IDD",selectSet.getString("IDD"));
		map.put("GP1",selectSet.getString("GP1"));
		map.put("GP2",selectSet.getString("GP2"));
		map.put("STATUS_ID",selectSet.getString("STATUS_ID"));
	}
	public String getValueOf(String column) {
		return map.get(column);
	}
	
}