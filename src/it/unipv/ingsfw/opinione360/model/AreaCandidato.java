package it.unipv.ingsfw.opinione360.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Rappresenta le specifiche aree della vetrina in cui un candidato carica i contenuti
 * @see Vetrina
 */
public class AreaCandidato {
	
    private ArrayList<String> contenuti;

    /**
     * Costruttore che crea un'AreaCandidato con dei contenuti di tipo stringa
     * @param contenuti i contenuti
     */
    public AreaCandidato(String [] contenuti){
        this.contenuti = new ArrayList<>(Arrays.asList(contenuti));
    }

    /**
     * Costruttore senza parametri
     */
    public AreaCandidato(){
        this.contenuti = new ArrayList<>();
    }

    /**
     * Override del metodo toString
     * @return i contenuti dell'area
     */
    @Override
    public String toString() {
    	return String.join("\n", contenuti);
    }

    /**
     * Metodo che permette di aggiungere contenuti ad un AreaCandidato
     * @param contenuto i contenuti
     */
    public void aggiungiContenuto(String contenuto){
    	contenuti.add(contenuto);
    }
    
}
