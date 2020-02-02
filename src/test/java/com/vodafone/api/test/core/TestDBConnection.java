package com.vodafone.api.test.core;
import com.vodafone.core.DBConnection;

import java.sql.Statement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class TestDBConnection {
	
	@Test
	public void testCreateConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		DBConnection dbconnection = new DBConnection();
		Assert.assertNotNull(dbconnection.getConnectionInstance());
	}
	
	
}
