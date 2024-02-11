package it.unipv.ingsfw.opinione360.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.mysql.jdbc.Driver;


public class DBConnection {
	
	private static final String PROPERTYDBDRIVER = "DBDRIVER";
	private static final String PROPERTYDBURL = "DBURL";
	private static final String PROPERTYNAME = "db_usn"; 
	private static final String PROPERTYPSW = "db_psw"; 
	
	private static String username;
	private static String password;
	private static String dbDriver;
	private static String dbURL;
	private static DBConnection conn;
	
	private static void init() {
		Properties p = new Properties(System.getProperties());
		try {
			p.load(new FileInputStream("Server/properties/properties.properties"));
			
			username = p.getProperty(PROPERTYNAME);
			password = p.getProperty(PROPERTYPSW);
			dbDriver = p.getProperty(PROPERTYDBDRIVER);
			dbURL = p.getProperty(PROPERTYDBURL);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection startConnection(Connection conn, String schema)
	{
		init();
		
		if ( isOpen(conn) )
			closeConnection(conn);	
		try {			
			dbURL=String.format(dbURL, schema); 
		
			Class.forName(dbDriver);
			
			conn = DriverManager.getConnection(dbURL, username, password);
			// conn.setAutoCommit(true);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	/**Verirfica se la connessione è stata chiusa correttamente.*/
	public static boolean isOpen(Connection conn)
	{
		if (conn == null)
			return false;
		else
			return true;
	}
	/**Chiude la connessione con il database, se aperta, e imposta la connessione a null.*/
	public static Connection closeConnection(Connection conn)
	{
		if ( !isOpen(conn) )
			return null;
		try {
			conn.close();
			conn = null;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
}
