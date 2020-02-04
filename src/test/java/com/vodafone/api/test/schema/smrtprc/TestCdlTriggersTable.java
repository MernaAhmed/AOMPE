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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class TestCdlTriggersTable {

	int testMSISDN = 1020201144;
	int testIDI = 4280;
	int testIDD = 200204;
	int testIDV = 34;
	int testTYPE_ID = 1174;

	CdlTriggers cdlTrigger;
	DBConnection dbConnection;
	Statement statement ;
    String SELECT_QUERY = "SELECT MSISDN,IDI,IDV,IDD,TYPE_ID,TIME,GP1,GP2,STATUS_ID from %s where MSISDN = %s and TYPE_ID=%d and TIME > \'%s\'";
    String SELECT_QUERY_WITH_MULTIPLE_CONDITIONS = "SELECT MSISDN,IDI,IDV,IDD,TYPE_ID,TIME,GP1,GP2,STATUS_ID from %s where MSISDN = %s  and IDI=%d and IDV=%d and IDD=%d";
    String UPDATE_QUERY_SET_TIME_BEFORE = "UPDATE %s set TIME = TO_TIMESTAMP(\'%s\') where MSISDN = %d and IDI = %d and IDV = %d and IDD=%d";

	@Before
	public void setUp() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		cdlTrigger = new CdlTriggers(this.testMSISDN,this.testTYPE_ID,DBConnection.getTimeMinutesAgo(4));
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
		updateTestRecordTimeBy(4);
		String query = String.format(this.SELECT_QUERY, this.cdlTrigger.tableName,this.testMSISDN,this.testTYPE_ID,DBConnection.getTimeMinutesAgo(5));
		ResultSet testCdlTriggersRS = getResultSetByQuery(query);
		Assert.assertTrue("record not inserted before running the test",testCdlTriggersRS.next()); // if wasn't true thus there is not record on the database with the data above inserted
		cdlTrigger.get();
		Assert.assertEquals(cdlTrigger.getValueOf("MSISDN"), testCdlTriggersRS.getString("MSISDN"));
		Assert.assertEquals(cdlTrigger.getValueOf("IDD"), testCdlTriggersRS.getString("IDD"));
	}
	
	@Test
	public void testIfNoRecordsExistsItShouldNotThrowException() throws SQLException {
		
		Assert.assertThrows(SQLException.class, () -> {
			cdlTrigger.get();
		  });
	}
	private void updateTestRecordTimeBy(int minutes) throws SQLException {
		String testRecordQuery = String.format(this.SELECT_QUERY_WITH_MULTIPLE_CONDITIONS, this.cdlTrigger.tableName,this.testMSISDN,this.testIDI,this.testIDV,this.testIDD);
		ResultSet testCdlTriggersRS = getResultSetByQuery(testRecordQuery);
		Assert.assertTrue("record not inserted before running the test",testCdlTriggersRS.next()); // if wasn't true thus there is not record on the database with the data above inserted
		String updateQuery = String.format(this.UPDATE_QUERY_SET_TIME_BEFORE, this.cdlTrigger.tableName,
																			DBConnection.getTimeMinutesAgo(minutes),
																			this.testMSISDN,
																			this.testIDI,
																			this.testIDV,
																			this.testIDD);
		getResultSetByQuery(updateQuery);
		String afterUpdateTestRecordQuery = String.format(this.SELECT_QUERY, this.cdlTrigger.tableName,this.testMSISDN,this.testTYPE_ID,cdlTrigger.dbConnection.getTimeMinutesAgo(minutes+1));
		ResultSet testCdlTriggersRSAfterUpdate = getResultSetByQuery(afterUpdateTestRecordQuery);
		Assert.assertTrue("record not inserted before running the test",testCdlTriggersRSAfterUpdate.next());
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
		String testSEEN_TIME = this.getTimeNow();
		String testPROCESSING_TIME = this.getTimeNow();
		int testSTATUS_ID = 11;
		String testSTATUS_TIME = this.getTimeNow();
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
		preparedStatement.setString(7, testSEEN_TIME);
		preparedStatement.setString(8, testPROCESSING_TIME);
		preparedStatement.setInt(9, testSTATUS_ID);
		preparedStatement.setString(10, testSTATUS_TIME);
		int row = preparedStatement.executeUpdate();
		this.dbConnection.getConnectionInstance().commit();
		this.dbConnection.close();
	}
	private ResultSet getResultSetByQuery(String query) throws SQLException {
		return this.statement.executeQuery(query);
	}
	private String getTimeNow() {
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yy HH:mm:ss a");
		return formatter.format(System.currentTimeMillis());
	}

}
