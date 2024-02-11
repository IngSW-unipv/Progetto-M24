package it.unipv.ingsfw.opinione360.model;

import java.util.ArrayList;
import java.util.UUID;

import it.unipv.ingsfw.opinione360.model.exception.OptionNotFoundException;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

/**
 * Quest'interfaccia è implementata da tutte le classi che vogliono permettere di votare
 * @see Sondaggio
 */
public interface IConsultazione {
	
    void vota(int scelta, Utente u) throws OptionNotFoundException, UserMissingAccessException;
    
    void stampaRisultati();
    
    public UUID getId();
	public String getQuesito();
	public String[] getOpzioni();
	public Vetrina getVetrina();
	public ArrayList<Utente> getCandidati();
	public ArrayList<Utente> getVotanti();
}
