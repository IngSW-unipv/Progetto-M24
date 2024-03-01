package it.unipv.ingsfw.opinione360.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import it.unipv.ingsfw.opinione360.model.AreaOpzione;
import it.unipv.ingsfw.opinione360.model.IConsultazioneC;
import it.unipv.ingsfw.opinione360.model.IOpzioneC;
import it.unipv.ingsfw.opinione360.model.IUtenteC;
import it.unipv.ingsfw.opinione360.model.OpzioneC;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.model.UtenteC;
import it.unipv.ingsfw.opinione360.model.Votante;
import it.unipv.ingsfw.opinione360.util.DBConnection;
import it.unipv.ingsfw.opinione360.util.DBConnectionC;

public class StoricoCDAO implements IStoricoCDAO {
	private Connection conn;
	
	public StoricoCDAO () {
		super();
	}
	
	/**Inizializza il database locale del client creando le tables relative a SOndaggioC*/
	public void initialize() throws SQLException {
		conn=DBConnectionC.startConnection(conn);
		
		String query1="CREATE TABLE STORICO (ID_CONSULTAZIONE CHAR(36) NOT NULL, ID_UTENTE CHAR(36) NOT NULL, ID_OPZIONE INT NOT NULL, PRIMARY KEY (ID_CONSULTAZIONE, ID_UTENTE, ID_OPZIONE))";
		Statement st1 = conn.createStatement();
		st1.executeUpdate(query1);
		
		DBConnection.closeConnection(conn);
	}
	
	/**Elimina le tables relative a SOndaggioC per il client*/
	public void drop() throws SQLException {
		conn=DBConnectionC.startConnection(conn);
		
		String query1="DROP TABLE STORICO";
		Statement st1 = conn.createStatement();
		st1.executeUpdate(query1);
		
		DBConnection.closeConnection(conn);
	}

	@Override
	public ArrayList<IConsultazioneC> selectByUser(IUtenteC utenteInput) throws SQLException {
		ArrayList<IConsultazioneC> sondaggi = new ArrayList<>();
		
		conn=DBConnectionC.startConnection(conn);
		
		String query="SELECT UNIQUE ID_CONSULTAZIONE FROM STORICO WHERE ID_UTENTE=?";
		
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, utenteInput.getId().toString());
		
		ResultSet rs = st1.executeQuery();
		
		if (rs.isBeforeFirst()) { //check if there is any data so to avoid error 
			while(rs.next()) {
				SondaggioC poll = new SondaggioC(UUID.fromString(rs.getString("ID")));
				
				sondaggi.add(poll);
			}		
		}
		
		DBConnection.closeConnection(conn);
		
		return sondaggi;
	}

	@Override
	public ArrayList<OpzioneC> selectChoice(IConsultazioneC pollInput) throws SQLException {
		ArrayList<OpzioneC> opzioni = new ArrayList<>();
		
		conn=DBConnectionC.startConnection(conn);
		
		String query="SELECT ID_OPZIONE FROM STORICO WHERE ID_CONSULTAZIONE=?";
		
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, pollInput.getId().toString());
		
		ResultSet rs = st1.executeQuery();
		
		if (rs.isBeforeFirst()) { //check if there is any data so to avoid error 
			while(rs.next()) {

				opzioni.add(new OpzioneC(rs.getInt("ID_OPZIONE")));
			}		
		}
		
		DBConnection.closeConnection(conn);
		
		return opzioni;
	}

	@Override
	public void insertChoice(IOpzioneC opzione, IConsultazioneC consultazione, IUtenteC votante) throws SQLException {
		conn=DBConnectionC.startConnection(conn);
    	
		PreparedStatement st;

		String query="INSERT INTO STORICO (ID_CONSULTAZIONE,ID_UTENTE,ID_OPZIONE) VALUES (?,?,?)";
		st = conn.prepareStatement(query);
		
		conn.setAutoCommit(false);
		
		st.setString(1, consultazione.getId().toString());
		st.setString(2, votante.getId().toString());
		st.setInt(3, opzione.getId());
		
		st.executeUpdate();
		
		conn.commit();
		
		DBConnectionC.closeConnection(conn);		
	}
	
	@Override
	public boolean reset() {
    	conn=DBConnectionC.startConnection(conn);
		
		String query="DELETE FROM STORICO";
			
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
}
