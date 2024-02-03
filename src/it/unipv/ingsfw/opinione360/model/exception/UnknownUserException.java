package it.unipv.ingsfw.opinione360.model.exception;

/**
 * Eccezione lanciata quando un utente non riconosciuto tenta di effettuare delle operazioni
 */

public class UnknownUserException extends IndexOutOfBoundsException {

	public UnknownUserException() {
		super("Utente non riconosciuto.");
	}
	
}
