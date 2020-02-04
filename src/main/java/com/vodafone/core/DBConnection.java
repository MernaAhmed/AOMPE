package com.vodafone.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class DBConnection {

	/*variables definition*/
	/*DB Connection variables*/
	private String URL ;
    private String USER;
    private String PASSWORD;
    private String DRIVER;
    private final String DB_CONFIG_FILE="database.properties";
    private Connection connection;
    private Statement statement;
    public DBConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
    	Properties props = new Properties();
        URL res = getClass().getClassLoader().getResource(this.DB_CONFIG_FILE);
        props.load( new FileInputStream(res.getPath()));
        readDBConfigurations(props);
        this.initiateConnection();
    }

	private void readDBConfigurations(Properties props) {
		this.DRIVER = props.getProperty("testing.driver");
        this.USER = props.getProperty("testing.username");
        this.PASSWORD = props.getProperty("testing.password");
        this.URL = props.getProperty("testing.url");
	}
    
    private void initiateConnection() throws SQLException{
    	try {
			Class.forName(this.DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Connection connection = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
    	this.connection = connection;
    	statement = this.connection.createStatement();
    }
    
    public Connection getConnectionInstance() {
    	return this.connection;
    }
    
    public Statement getStatment() {
    	return this.statement;
    }
    public void close() throws SQLException {
    	this.connection.close();
    }

	public static String getTimeMinutesAgo(int minutes) {
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yy hh:mm:ss a");
		return formatter.format(System.currentTimeMillis() - (minutes * 60 * 1000));
	}
}
