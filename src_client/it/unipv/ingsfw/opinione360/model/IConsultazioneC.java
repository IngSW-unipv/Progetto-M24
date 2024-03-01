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
public interface IConsultazioneC {
	
    /**
	 * Incrementa il contatore relativo alle opzioni selezionate di un'unita'
	 * @param scelta la scelta dell'UtenteC
	 * @param u l'UtenteC che vota
	 * @throws OptionNotFoundException
	 * @throws UserMissingAccessException
	 * @throws ConsultationExpiredException
	 */
    void vota(OpzioneC[] scelta, UtenteC u) throws OptionNotFoundException, UserMissingAccessException, ConsultationExpiredException;

    ArrayList<AreaOpzioneC> getListaOpzioni();

    /**Restituisce l'Id univoco della consultazione*/
    public UUID getId();
    
    /**Restituisce il quesito della consultazione*/
	public String getQuesito();
	
	public OpzioneC[] getAllOpzioni();
	
	@Deprecated
	public String[] getOpzioni();
	
	/**Restituisce la lista di contenuti caricati per questa opzione.*/
	public ArrayList<IContenutoC> getContenuti(OpzioneC opzione);
	
	/**Carica un contenuto nella vetrina, se l'UtenteC e' un candidato.*/
	public void caricaContenuto(UtenteC candidato, IContenutoC contenuto) throws UserMissingAccessException;
	
	/**Carica un contenuto nella vetrina sulla base dell'opzione*/
	public void caricaContenuto(OpzioneC opzione, IContenutoC contenuto) throws OptionNotFoundException;
	
	/**Restituisce il numero di opzioni*/
	public int getOpzioniCount();
	
	public HashMap<OpzioneC, Integer> getRisultati();
	
	public boolean hasCandidato(UtenteC candidato);
	public boolean hasVotante(UtenteC votante);
	
	/**
     * Incrementa il contatore relativo alle opzioni selezionate di un'unita'
     * @deprecated
     * metodo di compatibilita' con le versioni precedenti
     */
    @Deprecated
	public void vota(int scelta, UtenteC u);
	boolean isExpired();
}
