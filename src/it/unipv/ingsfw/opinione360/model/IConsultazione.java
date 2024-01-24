package it.unipv.ingsfw.opinione360.model;

import it.unipv.ingsfw.opinione360.model.exception.OptionNotFoundException;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

public interface IConsultazione {
	
    void vota(int scelta, Utente u) throws OptionNotFoundException, UserMissingAccessException;
    
    void stampaRisultati();
    
}
