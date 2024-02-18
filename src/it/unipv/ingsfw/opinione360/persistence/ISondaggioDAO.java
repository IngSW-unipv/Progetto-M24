package it.unipv.ingsfw.opinione360.persistence;

import java.util.ArrayList;

import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.Utente;

public interface ISondaggioDAO {
	
	/**Restituisce l'elenco di tutti i sondaggi salvati.*/
	public ArrayList<Sondaggio> selectAll();
	
	/**Restituisce l'elenco di tutti i sondaggi attivi al momento in cui viene eseguito il metodo.*/
	public ArrayList<Sondaggio> selectActive();
	
	/**Restituisce l'elenco di tutti i sondaggi terminati al momento in cui viene eseguito il metodo.*/
	public ArrayList<Sondaggio> selectExpired();
	
	/**Restituisce il sondaggio con l'id fornito*/
	public Sondaggio selectById(Sondaggio pollInput);
	
	/**
	 * Restituisce una lista contenente tutti i sondaggi a cui ha accesso l'utente
	 * @param utenteInput Utente <b>completo</b> (con id valido) di cui si vuole trovare la lista dei sondaggi accessibili.
	 */
	public ArrayList<Sondaggio> selectByUser(Utente utenteInput);
	
	/**Restituisce l'elenco dei sondaggi per cui l'utente indicato puo' caricare contenuti nella vetrina (candidato)*/
	public ArrayList<Sondaggio> selectByCandidato(Utente candidatoInput);
	
	public boolean insertSondaggio(Sondaggio s);
	
	/**
	 * Aggiorna il record relativo al sondaggio fornito
	 * @param s Sondaggio valido che si vuole aggiornare
	 */
	public boolean updateSondaggio(Sondaggio s);
}
