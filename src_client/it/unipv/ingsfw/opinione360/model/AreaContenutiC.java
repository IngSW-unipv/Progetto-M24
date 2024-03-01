package it.unipv.ingsfw.opinione360.model;

import java.util.ArrayList;
import java.util.Arrays;

import it.unipv.ingsfw.opinione360.model.IContenutoC;

/**
 * Rappresenta le specifiche aree della vetrina in cui un candidato carica i contenuti
 */
public class AreaContenutiC {
	
    private ArrayList<IContenutoC> contenuti;

    /**
     * Costruttore che crea un'AreaCandidato con dei contenuti di tipo stringa
     * @param contenuti i contenuti
     */
    public AreaContenutiC(IContenutoC[] contenuti){
        this.contenuti = new ArrayList<>(Arrays.asList(contenuti));
    }

    /**
     * Costruttore senza parametri
     */
    public AreaContenutiC(){
        this.contenuti = new ArrayList<>();
    }

    /**
     * Override del metodo toString
     * @return i contenuti dell'area
     */
    @Override
    public String toString() {
    	StringBuilder result = new StringBuilder("AreaCandidato\n");
    	for (IContenutoC cont: contenuti) {
    		result.append(cont.toString()).append('\n');
    	}
        return result.toString();
    }

    /**
     * Metodo che permette di aggiungere contenuti ad un AreaCandidato
     * @param contenuto i contenuti
     */
    public void aggiungiContenuto(IContenutoC contenuto){
    	contenuti.add(contenuto);
    }
    
    public ArrayList<IContenutoC> getContenuti() {
    	return new ArrayList<IContenutoC>(this.contenuti);
    }
    
}
