package it.unipv.ingsfw.opinione360.model;

import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Questa è la vetrina relativa a una consultazione in cui i candidati possono caricare contenuti
 * @see IConsultazione
 * @see Sondaggio
 */
@Deprecated
public class Vetrina {

    private Map<Utente, AreaContenuti> elencoAreaCandidato;

    /**
     * Costruttore senza parametri
     */
    @Deprecated
    public Vetrina(){
        elencoAreaCandidato = new Hashtable<>();
    }

    /**
     * Costruttore che accetta un ArrayList di utenti
     * @param candidati lista di utenti che hanno accesso alla vetrina
     */
    @Deprecated
    public Vetrina(ArrayList<Utente> candidati){
        elencoAreaCandidato = new Hashtable<>();
        
        for (Utente candidato: candidati){
        	elencoAreaCandidato.put(candidato, new AreaContenuti());
        }
    }

    /**
     * Metodo che permette ad un utente di creare una nuova AreaCandidato con dei contenuti
     * @param candidato l'utente che carica i contenuti
     * @param contenuti i contenuti testuali
     */
    @Deprecated
    public void aggiungiArea(Utente candidato, IContenuto [] contenuti){
        elencoAreaCandidato.put(candidato, new AreaContenuti(contenuti));

    }

    
    /**Aggiunge un contenuto alla vetrina per un candidato registrato.*/
   @Deprecated
    public void aggiungiContenuto(Utente candidato, IContenuto contenuto) {
    	if (elencoAreaCandidato.containsKey(candidato)) {
    		elencoAreaCandidato.get(candidato).aggiungiContenuto(contenuto);
    	}
    	else {
    		elencoAreaCandidato.put(candidato, new AreaContenuti(new IContenuto[]{contenuto}));    		
    	}
    }

    /**
     * Override del metodo toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Vetrina:\n");
        for (Map.Entry<Utente, AreaContenuti> entry : elencoAreaCandidato.entrySet()) {
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
    @Deprecated
    public AreaContenuti getArea(Utente candidato) throws UserMissingAccessException {
		if (elencoAreaCandidato.containsKey(candidato))
			return this.elencoAreaCandidato.get(candidato);
		else
			throw new UserMissingAccessException();
    }
    
}
