package it.unipv.ingsfw.opinione360.persistence;

import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.persistence.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUtenteDAO {
	
	ArrayList <Utente> selectAll() throws SQLException;
	
	Utente selectById(Utente userInput) throws UserNotFoundException, SQLException;
	Utente selectByUsrPw(Utente userInput) throws UserNotFoundException, SQLException;
	
    Utente selectUtente(Utente u) throws UserNotFoundException, SQLException; //to be changed?
    

    boolean insertUtente(Utente u) throws UserNotFoundException, SQLException;
    
    boolean updateUtente(Utente u) throws UserNotFoundException, SQLException;
}
