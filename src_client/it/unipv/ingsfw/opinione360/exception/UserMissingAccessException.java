package it.unipv.ingsfw.opinione360.exception;

/**
 * Eccezione lanciata quando un utente che non ha accesso alla risorsa tenta di effettuare delle operazioni
 */
public class UserMissingAccessException extends IndexOutOfBoundsException {
	
	public UserMissingAccessException() {
		super("Non puoi votare per questa consultazione.");
	}
	
}
