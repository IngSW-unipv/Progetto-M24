package it.unipv.ingsfw.opinione360.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import it.unipv.ingsfw.opinione360.util.DBConnection;
import it.unipv.ingsfw.opinione360.model.Amministratore;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;

public class AmministratoreDAO implements IAmministratoreDAO {

	private final String schema;
	private Connection conn;
	
	public AmministratoreDAO() {
		super();
		schema = "OPINIONE360";
	}

	@Override
	public ArrayList<Amministratore> selectAll() throws SQLException {
		
		ArrayList<Amministratore> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn, schema);
		
		String query="SELECT * from UTENTE WHERE IS_ADMIN=1;";
		
		Statement st1 = conn.createStatement();
		ResultSet rs1 = st1.executeQuery(query);

		
		while(rs1.next()) {
			Amministratore admin = new Amministratore(UUID.fromString(rs1.getString(1)), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5));

			result.add(admin);
		}
		
		DBConnection.closeConnection(conn);
		
		return result;
	}

	@Override
	public Amministratore selectById(Utente adminInput) throws UserNotFoundException, SQLException {
		Amministratore admin = null;
		
		conn=DBConnection.startConnection(conn, schema);
		
		String query="SELECT * from UTENTE WHERE IS_ADMIN=1 AND ID=?";
		
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, adminInput.getId().toString());
		
		ResultSet rs1=st1.executeQuery();
		
		if (rs1.next()){
			admin = new Amministratore(UUID.fromString(rs1.getString(1)), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5));		}
		else {
			throw new UserNotFoundException(); // leads to error 401 otherwise 500
		}		

		DBConnection.closeConnection(conn);
		return admin;
	}

	@Override
	public Amministratore selectByUserPw(Utente adminInput) throws UserNotFoundException, SQLException {
		
		Amministratore admin = null;
		
		conn=DBConnection.startConnection(conn, schema);
		
		String query="SELECT * from UTENTE WHERE IS_ADMIN=1 AND USERNAME=? AND PASSWORD=?";
		
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, adminInput.getUsername());
		st.setString(2, adminInput.getPassword());

		ResultSet rs=st.executeQuery();

		if (rs.next()){
			admin = new Amministratore(UUID.fromString(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		}
		else {
			throw new UserNotFoundException(); // leads to error 401 otherwise 500
		}		

		DBConnection.closeConnection(conn);
		
		return admin;
	}

	@Override
	public boolean insertAmministratore(Amministratore admin) {

		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st;
		
		boolean esito=true;

		try {
			String query="INSERT INTO `UTENTE` (`ID`, `USERNAME`, `PASSWORD`, `COMP_ID`, `EMAIL`, `IS_ADMIN`) VALUES(?,?,?,?,?,1)";
			st = conn.prepareStatement(query);
			
			st.setString(1, admin.getId().toString());
			st.setString(2, admin.getUsername());
			st.setString(3, admin.getPassword());
			st.setString(4, admin.getId_societario());
			st.setString(5, admin.getEmail());
			
			conn.setAutoCommit(false);
			int results = st.executeUpdate();

			if (results > 0) {
		        esito = true;
		        conn.commit();
		        conn.setAutoCommit(true); 
		    }  

		} catch (Exception e){
//			e.printStackTrace();
			esito=false;
		}
		
		DBConnection.closeConnection(conn);
		
		return esito;
	}

	@Override
	public boolean updateAmministratore(Amministratore admin) {
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st;
		
		boolean esito=true;

		try {
			String query="UPDATE UTENTE SET USERNAME=?, PASSWORD=?, EMAIL=? WHERE ID=? AND IS_ADMIN=1";
			
			st = conn.prepareStatement(query);
			
			st.setString(1, admin.getUsername());
			st.setString(2, admin.getPassword());
			st.setString(3, admin.getEmail());
			st.setString(4, admin.getId().toString());
			
			st.executeUpdate(query);

		} catch (Exception e){
			esito=false;
		}

		DBConnection.closeConnection(conn);
		
		return esito;
	}
	
	/**Elimina tutti gli amministratori del sistema*/
	public boolean reset() {
		conn=DBConnection.startConnection(conn, schema);
		
		String query="DELETE FROM UTENTE WHERE IS_ADMIN=1";
			
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
	
	public String getSchema() {
		return this.schema;
	}
}
