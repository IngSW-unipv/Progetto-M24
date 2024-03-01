package it.unipv.ingsfw.opinione360.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import it.unipv.ingsfw.opinione360.model.exception.ConsultationExpiredException;
import it.unipv.ingsfw.opinione360.model.exception.OptionNotFoundException;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

/**
 * Quest'interfaccia è implementata da tutte le classi che vogliono permettere di votare
 * @see Sondaggio
 */
public interface IConsultazione {
	
    /**
	 * Incrementa il contatore relativo alle opzioni selezionate di un'unita'
	 * @param scelta la scelta dell'utente
	 * @param u l'utente che vota
	 * @throws OptionNotFoundException
	 * @throws UserMissingAccessException
	 * @throws ConsultationExpiredException
	 */
    void vota(Opzione[] scelta, Utente u) throws OptionNotFoundException, UserMissingAccessException, ConsultationExpiredException;

    ArrayList<AreaOpzione> getListaOpzioni();

    /**Restituisce l'Id univoco della consultazione*/
    public UUID getId();
    
    /**Restituisce il quesito della consultazione*/
	public String getQuesito();
	
	public Opzione[] getAllOpzioni();
	
	@Deprecated
	public String[] getOpzioni();
	
	/**Restituisce la lista di contenuti caricati per questa opzione.*/
	public ArrayList<IContenuto> getContenuti(Opzione opzione);
	
	/**Carica un contenuto nella vetrina, se l'utente e' un candidato.*/
	public void caricaContenuto(Utente candidato, IContenuto contenuto) throws UserMissingAccessException;
	
	/**Carica un contenuto nella vetrina sulla base dell'opzione*/
	public void caricaContenuto(Opzione opzione, IContenuto contenuto) throws OptionNotFoundException;
	
	/**Restituisce il numero di opzioni*/
	public int getOpzioniCount();
	
	public HashMap<Opzione, Integer> getRisultati();
	
	public boolean hasCandidato(Utente candidato);
	public boolean hasVotante(Utente votante);
	
	/**
     * Incrementa il contatore relativo alle opzioni selezionate di un'unita'
     * @deprecated
     * metodo di compatibilita' con le versioni precedenti
     */
    @Deprecated
	public void vota(int scelta, Utente u);
	boolean isExpired();
}
