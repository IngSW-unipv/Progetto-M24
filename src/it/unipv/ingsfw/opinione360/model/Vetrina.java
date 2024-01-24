package it.unipv.ingsfw.opinione360.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

public class Vetrina {

    private Map<Utente, AreaCandidato> elencoAreaCandidato;
    
    public Vetrina(){
        elencoAreaCandidato = new Hashtable<>();
    }
    
    public Vetrina(ArrayList<Utente> candidati){
        elencoAreaCandidato = new Hashtable<>();
        
        for (Utente candidato: candidati){
        	elencoAreaCandidato.put(candidato, new AreaCandidato());
        }
    }

    public void aggiungiArea(Utente candidato, String [] contenuti){
        elencoAreaCandidato.put(candidato, new AreaCandidato(contenuti));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Vetrina:\n");
        for (Map.Entry<Utente, AreaCandidato> entry : elencoAreaCandidato.entrySet()) {
            result.append("\nArea candidato con i contenuti di ").append(entry.getKey().getUsername()).append(": \n").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    public AreaCandidato getArea(Utente candidato) throws UserMissingAccessException {
		if (elencoAreaCandidato.containsKey(candidato))
			return this.elencoAreaCandidato.get(candidato);
		else
			throw new UserMissingAccessException();
    }
    
}
