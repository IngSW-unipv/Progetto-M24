package it.unipv.ingsfw.opinione360.persistence;

import java.util.ArrayList;

import it.unipv.ingsfw.opinione360.model.UtenteC;

public interface IUtenteCDAO {
	/**Restituisce l'utente attualmente registrato nel dispositivo*/
	UtenteC selectLocal();
	
	
	UtenteC selectById(UtenteC userInput);
	
	/**Ottiene le informazioni relative ad un utente tramite https, se ha accesso a tale informazione*/
	UtenteC selectByUsrPw(UtenteC userInput);    
    
    boolean insertUtente(UtenteC u);
    boolean updateUtente(UtenteC u);
}
