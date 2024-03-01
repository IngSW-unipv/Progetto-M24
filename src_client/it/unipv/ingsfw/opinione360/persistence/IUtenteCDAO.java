package it.unipv.ingsfw.opinione360.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.ingsfw.opinione360.exception.UserNotFoundException;
import it.unipv.ingsfw.opinione360.model.IUtenteC;
import it.unipv.ingsfw.opinione360.model.UtenteC;

public interface IUtenteCDAO {
	
	/**Restituisce l'utente attualmente registrato nel dispositivo
	 * @throws SQLException 
	 * @throws UserNotFoundException */
	UtenteC selectLocal() throws SQLException, UserNotFoundException;
    
    void insertUtente(UtenteC u) throws SQLException;
    void updateUtente(IUtenteC u) throws SQLException;

    /**Cancella l'utente dal database locale*/
	void dropUtente(IUtenteC u) throws SQLException;


	boolean reset();

	UtenteC selectById(UtenteC utente);
}
