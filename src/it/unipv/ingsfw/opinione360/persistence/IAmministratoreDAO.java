package it.unipv.ingsfw.opinione360.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.ingsfw.opinione360.model.Amministratore;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;

public interface IAmministratoreDAO {
	/**Restituisce tutti gli amministratori registrati nel sistema.*/
	public ArrayList<Amministratore> selectAll() throws SQLException;
	
	/**
	 * Restituisce l'amministratore con l'Id fornito, se esiste.
	 * @exception UserNotFoundException generata se l'amministratore non esiste.
	 */
	public Amministratore selectById(Utente adminInput) throws UserNotFoundException, SQLException;
	
	/**
	 * Restituisce l'amministratore con username e password specificati.
	 * @exception UserNotFoundException generata se l'amministratore non esiste.
	 */
	public Amministratore selectByUserPw(Utente adminInput) throws UserNotFoundException, SQLException;
	
	/**Registra un nuovo amministratore*/
	public boolean insertAmministratore(Amministratore admin);
	
	/**
	 * Aggiorna i dati dell'amministratore indicato.
	 * @exception UserNotFoundException generata se l'amministratore non esiste.
	 */
	public boolean updateAmministratore(Amministratore admin) throws UserNotFoundException, SQLException;
}
