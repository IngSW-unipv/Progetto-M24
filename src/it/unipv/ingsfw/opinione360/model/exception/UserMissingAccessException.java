package it.unipv.ingsfw.opinione360.model.exception;

/**
 * Eccezione lanciata quando un utente che non ha accesso alla risorsa tenta di effettuare delle operazioni
 */

public class UserMissingAccessException extends IndexOutOfBoundsException {
	
	public UserMissingAccessException() {
		super("L'utente non ha accesso al sondaggio.");
	}
	
}
