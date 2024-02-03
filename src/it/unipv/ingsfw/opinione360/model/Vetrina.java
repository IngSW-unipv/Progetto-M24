package it.unipv.ingsfw.opinione360.model;

import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Questa è la vetrina relativa a una consultazione in cui i candidati possono caricare contenuti
 * @see IConsultazione
 * @see Sondaggio
 * @see AreaCandidato
 */

public class Vetrina {

    private Map<Utente, AreaCandidato> elencoAreaCandidato;

    /**
     * Costruttore senza parametri
     */
    public Vetrina(){
        elencoAreaCandidato = new Hashtable<>();
    }

    /**
     * Costruttore che accetta un ArrayList di utenti
     * @param candidati lista di utenti che hanno accesso alla vetrina
     */
    public Vetrina(ArrayList<Utente> candidati){
        elencoAreaCandidato = new Hashtable<>();
        
        for (Utente candidato: candidati){
        	elencoAreaCandidato.put(candidato, new AreaCandidato());
        }
    }

    /**
     * Metodo che permette ad un utente di creare una nuova AreaCandidato con dei contenuti
     * @param candidato l'utente che carica i contenuti
     * @param contenuti i contenuti testuali
     */
    public void aggiungiArea(Utente candidato, String [] contenuti){
        elencoAreaCandidato.put(candidato, new AreaCandidato(contenuti));
    }

    /**
     * Override del metodo toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Vetrina:\n");
        for (Map.Entry<Utente, AreaCandidato> entry : elencoAreaCandidato.entrySet()) {
            result.append("\nArea candidato con i contenuti di ").append(entry.getKey().getUsername()).append(": \n").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    /**
     * Metodo che restituisce l'area candidato relativa ad un Utente
     * @param candidato l'utente di cui si vuole ottenere l'area associata
     * @return l' AreaCandidato relativa all' utente
     * @throws UserMissingAccessException
     */
    public AreaCandidato getArea(Utente candidato) throws UserMissingAccessException {
		if (elencoAreaCandidato.containsKey(candidato))
			return this.elencoAreaCandidato.get(candidato);
		else
			throw new UserMissingAccessException();
    }
    
}
