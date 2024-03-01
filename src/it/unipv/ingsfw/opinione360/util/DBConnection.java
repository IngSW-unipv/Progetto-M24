package it.unipv.ingsfw.opinione360.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.mysql.jdbc.Driver;

/**
 * Permette la connessione al database
 */

public class DBConnection {
	
	private static final String PROPERTYDBDRIVER = "DBDRIVER";
	private static final String PROPERTYDBURL = "DBURL";
	private static final String PROPERTYNAME = "db_usn"; 
	private static final String PROPERTYPSW = "db_psw"; 
	
	private static final String RELEASE_SCHEMA = "db_schema"; 
	private static final String TEST_SCHEMA = "db_test_schema"; 
	
	private static String username;
	private static String password;
	private static String dbDriver;
	private static String dbURL;
	
	private static String db_schema;
	private static String db_test_schema;
	
	private static DBConnection conn;
	
	private static void init() {
		Properties p = new Properties(System.getProperties());
		try {
			p.load(new FileInputStream("Server/properties/properties.properties"));
			
			username = p.getProperty(PROPERTYNAME);
			password = p.getProperty(PROPERTYPSW);
			dbDriver = p.getProperty(PROPERTYDBDRIVER);
			dbURL = p.getProperty(PROPERTYDBURL);
			
			db_schema = p.getProperty(RELEASE_SCHEMA);
			db_test_schema = p.getProperty(TEST_SCHEMA);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo che permette di avviare la connessione al database
	 * @param conn
	 * @param schema lo schema a cui ci si vuole connettere
	 * @return un oggetto di tipo {@link java.sql.Connection}
	 */
	public static Connection startConnection(Connection conn, String schema)
	{
		init();
		System.out.println(db_schema);
		if ( isOpen(conn) )
			closeConnection(conn);	
//			System.out.println("DBConnection: connessione lasciata aperta chiusa.");
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
	
	public static Connection startConnection(Connection conn, boolean release)
	{
		init();
		
		if ( isOpen(conn) )
			closeConnection(conn);	
		try {			
			dbURL=String.format(dbURL, (release)?db_schema:db_test_schema);
		
			Class.forName(dbDriver);
			
			conn = DriverManager.getConnection(dbURL, username, password);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	
	public static Connection startConnection(Connection conn) {
		return startConnection(conn, true);
	}


	/**Verifica se la connessione è stata chiusa correttamente.*/
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
//			System.out.println("closing connection");
			conn.close();
			conn = null;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	
	public static Connection rollback(Connection conn) {
		try {
			conn.rollback();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	public static String getReleaseSchema() {
		return db_schema;
	}
	public static String getTestSchema() {
		return db_schema;
	}
}
