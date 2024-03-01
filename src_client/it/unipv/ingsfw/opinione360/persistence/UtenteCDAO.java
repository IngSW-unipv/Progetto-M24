package it.unipv.ingsfw.opinione360.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import it.unipv.ingsfw.opinione360.exception.UserNotFoundException;
import it.unipv.ingsfw.opinione360.model.AmministratoreC;
import it.unipv.ingsfw.opinione360.model.IUtenteC;
import it.unipv.ingsfw.opinione360.model.UtenteC;
import it.unipv.ingsfw.opinione360.util.DBConnection;
import it.unipv.ingsfw.opinione360.util.DBConnectionC;

public class UtenteCDAO implements IUtenteCDAO {
	
	 private Connection conn;
	 
	 static protected UtenteC parseUtente(ResultSet input) throws SQLException {
    	if (input.getString("ID")==null) {
    		return null;
    	}
    	if (input.getBoolean("IS_ADMIN")) {
    		return new AmministratoreC(UUID.fromString(input.getString("ID")), input.getString("USERNAME"), null, input.getString("EMAIL"), input.getString("COMP_ID"));
    	}
    	else {
    		return new UtenteC(UUID.fromString(input.getString("ID")), input.getString("USERNAME"), null, input.getString("EMAIL"), input.getString("COMP_ID"));
    	}
    }
	 
	@Override
	public UtenteC selectLocal() throws SQLException, UserNotFoundException {
		UtenteC user;
		
		conn=DBConnectionC.startConnection(conn);
		
		String query="SELECT * from UTENTE";
		
		Statement st1 = conn.createStatement();
		ResultSet rs1 = st1.executeQuery(query);

		if (rs1.isBeforeFirst()) {
			rs1.next();
			user = UtenteCDAO.parseUtente(rs1);				
		}
		else {
			throw new UserNotFoundException();
		}
		
		DBConnectionC.closeConnection(conn);
		
		return user;
	}

	@Override
	public void insertUtente(UtenteC u) throws SQLException {
		conn=DBConnectionC.startConnection(conn);
    	
		PreparedStatement st;

		String query="INSERT INTO `UTENTE` (`ID`, `USERNAME`, `COMP_ID`, `EMAIL`, `IS_ADMIN`) VALUES(?,?,?,?,0)";
		st = conn.prepareStatement(query);
		
		conn.setAutoCommit(false);
		
		st.setString(1, u.getId().toString());
		st.setString(2, u.getUsername());
		st.setString(3, u.getId_societario());
		st.setString(4, u.getEmail());
		
		st.executeUpdate();
		
		conn.commit();
		
		DBConnectionC.closeConnection(conn);
	}

	@Override
	public void updateUtente(IUtenteC u) throws SQLException {
		conn=DBConnectionC.startConnection(conn);
		PreparedStatement st;
		
		String query="UPDATE UTENTE SET USERNAME=?, PASSWORD=?, EMAIL=? WHERE ID=? AND IS_ADMIN=0";
		
		st = conn.prepareStatement(query);
		
		st.setString(1, u.getUsername());
		st.setString(3, u.getEmail());
		st.setString(4, u.getId().toString());
		
		st.executeUpdate(query);

		DBConnection.closeConnection(conn);
	}
	
	@Override
	public void dropUtente(IUtenteC u) throws SQLException {
		conn=DBConnectionC.startConnection(conn);
		PreparedStatement st;
		
		String query="DELETE UTENTE WHERE ID=?";
		
		st = conn.prepareStatement(query);
		
		st.setString(1, u.getId().toString());
		
		st.executeUpdate(query);

		DBConnection.closeConnection(conn);
	}
	
	/**Inizializza il database locale del client creando le tables relative a UtenteC*/
	public void initialize() throws SQLException {
		conn=DBConnectionC.startConnection(conn);
		
		String query2="CREATE TABLE UTENTE (ID CHAR(36) NOT NULL PRIMARY KEY, USERNAME VARCHAR(50) NOT NULL, COMP_ID VARCHAR(50) NULL, EMAIL TEXT NULL, IS_ADMIN TINYINT NOT NULL DEFAULT 0)";
		Statement st2 = conn.createStatement();
		st2.executeUpdate(query2);
		
		DBConnection.closeConnection(conn);
	}
	
	/**Elimina le tables relative a UtenteC per il client*/
	public void drop() throws SQLException {
		conn=DBConnectionC.startConnection(conn);
		
		String query2="DROP TABLE UTENTE";
		Statement st2 = conn.createStatement();
		st2.executeUpdate(query2);
		
		DBConnection.closeConnection(conn);
	}
	
	@Override
	public boolean reset() {
    	conn=DBConnectionC.startConnection(conn);
		
		String query="DELETE FROM UTENTE";
			
		boolean result=true;
		
		try {
			Statement st1 = conn.createStatement();	
			st1.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			result=false;
		}

		DBConnection.closeConnection(conn);
		
		return result;
    }

	@Override
	public UtenteC selectById(UtenteC utente) {
		// TODO Auto-generated method stub
		return null;
	}
}
