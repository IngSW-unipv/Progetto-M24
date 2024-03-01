package it.unipv.ingsfw.opinione360.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.JDBC;

import it.unipv.ingsfw.opinione360.exception.UserNotFoundException;
import it.unipv.ingsfw.opinione360.model.UtenteC;
import it.unipv.ingsfw.opinione360.persistence.PersistenceFacade;
import it.unipv.ingsfw.opinione360.persistence.PersistenceFacadeC;
import it.unipv.ingsfw.opinione360.persistence.UtenteCDAO;

/**
 * Permette la connessione al database
 */

public class DBConnectionC {
//	
//	private static final String PROPERTYDBDRIVER = "DBDRIVER";
//	private static final String PROPERTYDBURL = "DBURL";
//	private static final String PROPERTYNAME = "db_usn"; 
//	private static final String PROPERTYPSW = "db_psw"; 
//	
//	private static String username;
//	private static String password;
	private static String dbDriver;
	private static String dbURL;
	private static DBConnectionC conn;

	public static void main(String[] args) throws SQLException, UserNotFoundException{
		
		PersistenceFacadeC dao = PersistenceFacadeC.getInstance();
//		dao.resetAllUsers();
//		dao.insertUtente(new UtenteC("fra", "a@b.c", "1234"));
		
		System.out.println(dao.selectLocal());
//		dao.insertUtente(new UtenteC("dude", "password"));
//		Connection conn = DBConnectionC.startConnection(null);
//		System.out.println(conn.getMetaData());
//		conn.close();
//		DBConnectionC.initializeDB(conn);
//		System.out.println(conn.getSchema));
	}
	private static void init() {
		dbDriver = "org.sqlite.JDBC";
		dbURL = "jdbc:sqlite:Client/db/storico.db";
	}

	/**
	 * Metodo che permette di avviare la connessione al database
	 * @param conn
	 * @param schema lo schema a cui ci si vuole connettere
	 * @return un oggetto di tipo {@link java.sql.Connection}
	 */
	public static Connection startConnection(Connection conn)
	{
		init();
		
		if ( isOpen(conn) )
			closeConnection(conn);
		try {
			Class.forName(dbDriver);
			
			System.out.println(dbURL);
			conn = DriverManager.getConnection(dbURL);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
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
}
