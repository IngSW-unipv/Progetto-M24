package it.unipv.ingsfw.opinione360.persistence;

import it.unipv.ingsfw.opinione360.model.IConsultazioneC;
import it.unipv.ingsfw.opinione360.model.IOpzioneC;
import it.unipv.ingsfw.opinione360.model.IUtenteC;
import it.unipv.ingsfw.opinione360.model.OpzioneC;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.model.UtenteC;
import it.unipv.ingsfw.opinione360.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Facade del livello di persistenza
 */
public class PersistenceFacadeC {
	
	private static PersistenceFacadeC instance;
	private IStoricoCDAO storicoCDAO;
	private IUtenteCDAO utenteCDAO;
	
	private PersistenceFacadeC() {
		storicoCDAO = new StoricoCDAO();
		utenteCDAO = new UtenteCDAO();
	}
	
	public static PersistenceFacadeC getInstance() {
		if (instance == null) {
			instance = new PersistenceFacadeC();
		}
		return instance;
	}
	
	public UtenteC selectById(UtenteC utente) throws SQLException {
       return utenteCDAO.selectById(utente);
    }
	
	public UtenteC selectLocal() throws UserNotFoundException, SQLException {
		return utenteCDAO.selectLocal();
	}
	
	public ArrayList<OpzioneC> getChoice(IConsultazioneC pollInput) throws SQLException {
		return storicoCDAO.selectChoice(pollInput);
	}
	
	public void insertChoice(OpzioneC opzione, IConsultazioneC consultazione, IUtenteC votante) throws SQLException {
		storicoCDAO.insertChoice(opzione, consultazione, votante);
	}
	
	public boolean haVotato(IConsultazioneC consultazione, IUtenteC votante) throws SQLException {
		return true;	
	}

	public void insertUtente(UtenteC u) throws SQLException{
		utenteCDAO.insertUtente(u);
	}
	
	public ArrayList<IConsultazioneC> selectByUser(IUtenteC u) throws SQLException {
		return storicoCDAO.selectByUser(u);
	}
	
	public void updateUtente(IUtenteC u) throws SQLException{
    	utenteCDAO.updateUtente(u);
    }
	
	public void dropUtente(IUtenteC u) throws SQLException{
    	utenteCDAO.dropUtente(u);
    }
    
    /**Deletes all users in local database*/
	public void resetAllUsers() {
    	utenteCDAO.reset();
    }
    
    /**Elimina tutti i dati salvati nello storico*/
	public void resetStorico() {
    	storicoCDAO.reset();
    }
	
}
