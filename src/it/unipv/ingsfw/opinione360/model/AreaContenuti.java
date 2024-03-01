package it.unipv.ingsfw.opinione360.model;

import java.util.ArrayList;
import java.util.Arrays;

import it.unipv.ingsfw.opinione360.model.IContenuto;

/**
 * Rappresenta le specifiche aree della vetrina in cui un candidato carica i contenuti
 * @see Vetrina
 */
public class AreaContenuti {
	
    private ArrayList<IContenuto> contenuti;

    /**
     * Costruttore che crea un'AreaCandidato con dei contenuti di tipo stringa
     * @param contenuti i contenuti
     */
    public AreaContenuti(IContenuto[] contenuti){
        this.contenuti = new ArrayList<>(Arrays.asList(contenuti));
    }

    /**
     * Costruttore senza parametri
     */
    public AreaContenuti(){
        this.contenuti = new ArrayList<>();
    }

    /**
     * Override del metodo toString
     * @return i contenuti dell'area
     */
    @Override
    public String toString() {
    	StringBuilder result = new StringBuilder("AreaCandidato\n");
    	for (IContenuto cont: contenuti) {
    		result.append(cont.toString()).append('\n');
    	}
        return result.toString();
    }

    /**
     * Metodo che permette di aggiungere contenuti ad un AreaCandidato
     * @param contenuto i contenuti
     */
    public void aggiungiContenuto(IContenuto contenuto){
    	contenuti.add(contenuto);
    }
    
    public ArrayList<IContenuto> getContenuti() {
    	return new ArrayList<IContenuto>(this.contenuti);
    }
    
}
