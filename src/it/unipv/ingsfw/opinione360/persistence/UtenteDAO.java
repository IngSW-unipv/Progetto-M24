package it.unipv.ingsfw.opinione360.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import it.unipv.ingsfw.opinione360.model.Amministratore;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;
import it.unipv.ingsfw.opinione360.util.DBConnection;

public class UtenteDAO implements IUtenteDAO {
    private String schema;
    private Connection conn;
    
    public UtenteDAO(){
        super();
        schema = "OPINIONE360";
    }

    /**
     * Restituisce l'utente ottenuto a partire dal ResultSet fornito
     * @param input ResultSet
     * @param offset indice della colonna da cui partono i dati relativi all'utente
     */
    static protected Utente parseUtente(ResultSet input, int offset) throws SQLException {
    	if (input.getBoolean(offset+6)) {
    		return new Amministratore(UUID.fromString(input.getString(offset+1)), input.getString(offset+2), input.getString(offset+3), input.getString(offset+4), input.getString(offset+5));
    	}
    	else {
    		return new Utente(UUID.fromString(input.getString(offset+1)), input.getString(offset+2), input.getString(offset+3), input.getString(offset+4), input.getString(offset+5));
    	}
    }
    
    static protected Utente parseUtente(ResultSet input) throws SQLException {
    	if (input.getBoolean("IS_ADMIN")) {
    		return new Amministratore(UUID.fromString(input.getString("ID")), input.getString("USERNAME"), input.getString("PASSWORD"), input.getString("COMP_ID"), input.getString("EMAIL"));
    	}
    	else {
    		return new Utente(UUID.fromString(input.getString("ID")), input.getString("USERNAME"), input.getString("PASSWORD"), input.getString("COMP_ID"), input.getString("EMAIL"));
    	}
    }
    
    @Override
    public boolean insertUtente(Utente userInput) {
    	conn=DBConnection.startConnection(conn,schema);
    	
		PreparedStatement st;
		
		boolean esito=true;

		try {
			String query="INSERT INTO `UTENTE` (`ID`, `USERNAME`, `PASSWORD`, `COMP_ID`, `EMAIL`, `IS_ADMIN`) VALUES(?,?,?,?,?,0)";
			st = conn.prepareStatement(query);
			conn.setAutoCommit(false);
			
			st.setString(1, userInput.getId().toString());
			st.setString(2, userInput.getUsername());
			st.setString(3, userInput.getPassword());
			st.setString(4, userInput.getId_societario());
			st.setString(5, userInput.getEmail());
			
			int results = st.executeUpdate();
			
			if (results > 0) {
		        esito = true;
		        conn.commit();
		        conn.setAutoCommit(true); 
		    }  

		} catch (SQLException e){
			esito=false;
		}
		
		DBConnection.closeConnection(conn);
		
		return esito;
    }

	@Override
	public ArrayList<Utente> selectAll() throws SQLException {
		ArrayList<Utente> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn, schema);
		
		String query="SELECT * from UTENTE";
		
		Statement st1 = conn.createStatement();
		ResultSet rs1 = st1.executeQuery(query);

		
		while(rs1.next()) {
			Utente user = UtenteDAO.parseUtente(rs1, 0);
			result.add(user);			
		}
		
		DBConnection.closeConnection(conn);
		
		return result;
	}

	@Override
	public Utente selectById(Utente userInput) throws UserNotFoundException, SQLException {
		Utente user = null;
		
		conn=DBConnection.startConnection(conn, schema);
		
		String query="SELECT * from UTENTE WHERE ID=?";
		
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, userInput.getId().toString());
		
		ResultSet rs1=st1.executeQuery();
		try {
			rs1.next();
			user = UtenteDAO.parseUtente(rs1, 0);
		}
		catch(SQLException e) {
			throw new UserNotFoundException();
		}	

		DBConnection.closeConnection(conn);
		return user;
	}

	@Override
	public Utente selectByUsrPw(Utente userInput) throws UserNotFoundException, SQLException {

		Utente user = null;
		
		conn=DBConnection.startConnection(conn, schema);
		
		String query="SELECT * from UTENTE WHERE USERNAME=? AND PASSWORD=?";
		
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, userInput.getUsername());
		st.setString(2, userInput.getPassword());

		ResultSet rs=st.executeQuery();

		try {
			rs.next();
			user = UtenteDAO.parseUtente(rs, 0);
		}
		catch(SQLException e) {
			throw new UserNotFoundException();
		}	

		DBConnection.closeConnection(conn);
		
		return user;
	}

	@Override
	public boolean updateUtente(Utente userInput) {
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st;
		
		boolean esito=true;

		try {
			String query="UPDATE UTENTE SET USERNAME=?, PASSWORD=?, EMAIL=? WHERE ID=? AND IS_ADMIN=0";
			
			st = conn.prepareStatement(query);
			
			st.setString(1, userInput.getUsername());
			st.setString(2, userInput.getPassword());
			st.setString(3, userInput.getEmail());
			st.setString(4, userInput.getId().toString());
			
			st.executeUpdate(query);

		} catch (Exception e){
			esito=false;
		}

		DBConnection.closeConnection(conn);
		
		return esito;
	}

	@Override
	public Utente selectUtente(Utente u) throws UserNotFoundException, SQLException {
    	Utente user = this.selectById(u);
    	return user;
	}
	
	/**Elimina tutti gli utenti del sistema*/
	public boolean reset() {
		conn=DBConnection.startConnection(conn, schema);
		
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
	
	public String getSchema() {
		return this.schema;
	}
}
