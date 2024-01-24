package it.unipv.ingsfw.opinione360.model;

import java.util.ArrayList;
import java.util.Arrays;

public class AreaCandidato {
	
    private ArrayList<String> contenuti;
    
    public AreaCandidato(String [] contenuti){
        this.contenuti = new ArrayList<>(Arrays.asList(contenuti));
    }
    
    public AreaCandidato(){
        this.contenuti = new ArrayList<>();
    }

    @Override
    public String toString() {
    	return String.join("\n", contenuti);
    }

    public void aggiungiContenuto(String contenuto){
    	contenuti.add(contenuto);
    }
    
}
