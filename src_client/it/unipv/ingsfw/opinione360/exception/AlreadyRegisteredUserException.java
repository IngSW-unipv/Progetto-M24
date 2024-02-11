package it.unipv.ingsfw.opinione360.exception;

/**
 * Eccezione lanciata quando un utente già registrato cerca di fare la registrazione
 */
public class AlreadyRegisteredUserException extends Exception {
	
    public AlreadyRegisteredUserException() {
        super("Utente già registrato.");
    }
    
}
