package com.vodafone.api.test.schema.smrtprc;

import com.vodafone.core.DBConnection;
import com.vodafone.schema.smrtprc.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class TestCdlTriggersTable {

	int testMSISDN = 1020201144;
	int testIDI = 4280;
	int testIDD = 200204;
	int testIDV = 34;
	
	CdlTriggers cdlTrigger;
	DBConnection dbConnection;
	Statement statement ;
    String SELECT_QUERY = "SELECT MSISDN,IDI,IDV,IDD,TYPE_ID from %s where MSISDN = %s and IDI=%s and IDD=%s and IDV = %s";

	@Before
	public void setUp() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		CdlTriggersIDs cdlTIds = new CdlTriggersIDs(this.testIDI, this.testIDD, this.testIDV);
		cdlTrigger = new CdlTriggers(this.testMSISDN,cdlTIds);
		dbConnection = new DBConnection();
		statement = dbConnection.getStatment();
	}
	@Test
	public void testModelCreateObject() {
		Assert.assertNotNull(cdlTrigger);	
	}
	
	@Test
	public void testGet() throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
		//this.insertIntoTableByQuery();
		// assume that we could have inserted a record to the database by a test
		String query = String.format(this.SELECT_QUERY, this.cdlTrigger.tableName,this.testMSISDN,this.testIDI,this.testIDD,this.testIDV);
		ResultSet testCdlTriggersRS = getResultSetByQuery(query);
		Assert.assertTrue("record not inserted before running the test",testCdlTriggersRS.next()); // if wasn't true thus there is not record on the database with the data above inserted
		cdlTrigger.get();
		Assert.assertEquals(cdlTrigger.getValueOf("MSISDN"), testCdlTriggersRS.getString("MSISDN"));
		Assert.assertEquals(cdlTrigger.getValueOf("IDD"), testCdlTriggersRS.getString("IDD"));
	}
	@After
	public void tearDown() throws SQLException {
		this.dbConnection.close();
	}
	private void insertIntoTableByQuery() throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
		int testMSISDN = 1020201144;
		int testIDI = 4280;
		int testIDD = 200204;
		int testIDV = 34;
		int testTYPE_ID = 1174;
		int testSYS_ID = 2;
		Date testSEEN_TIME = this.getTimeNow();
		Date testPROCESSING_TIME = this.getTimeNow();
		int testSTATUS_ID = 11;
		Date testSTATUS_TIME = this.getTimeNow();
		String INSERT_QUERY = "INSERT INTO CDL_TRIGGERS(IDI,IDD,IDV,MSISDN,TYPE_ID,SYS_ID,SEEN_TIME,PROCESSING_TIME,STATUS_ID,STATUS_TIME) "
											  + "VALUES(?,?,?,?,?,?,?,?,?,?)";
		dbConnection = new DBConnection();
		statement = dbConnection.getStatment();
		PreparedStatement preparedStatement = this.dbConnection.getConnectionInstance().prepareStatement(INSERT_QUERY);
		preparedStatement.setInt(1,testIDI);
		preparedStatement.setInt(2, testIDD);
		preparedStatement.setInt(3, testIDV);
		preparedStatement.setInt(4, testMSISDN);
		preparedStatement.setInt(5, testTYPE_ID);
		preparedStatement.setInt(6, testSYS_ID);
		preparedStatement.setObject(7, testSEEN_TIME);
		preparedStatement.setObject(8, testPROCESSING_TIME);
		preparedStatement.setInt(9, testSTATUS_ID);
		preparedStatement.setObject(10, testSTATUS_TIME);
		int row = preparedStatement.executeUpdate();
		System.out.println(row);
		this.dbConnection.getConnectionInstance().commit();
		this.dbConnection.close();
	}
	
	private ResultSet getResultSetByQuery(String query) throws SQLException {
		return this.statement.executeQuery(query);
	}
	
	private Date getTimeNow() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		return new Date(System.currentTimeMillis());
	}
}
