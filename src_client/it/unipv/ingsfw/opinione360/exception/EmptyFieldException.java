package it.unipv.ingsfw.opinione360.exception;

/**
 * Eccezione lanciata quando un utente non riempie almeno uno dei campi 
 */
public class EmptyFieldException extends Exception {
	
    public EmptyFieldException() {
        super("C'è almeno un campo vuoto");
    }
    
}
