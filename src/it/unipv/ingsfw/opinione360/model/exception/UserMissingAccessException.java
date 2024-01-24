package it.unipv.ingsfw.opinione360.model.exception;

public class UserMissingAccessException extends IndexOutOfBoundsException {
	
	public UserMissingAccessException() {
		super("L'utente non ha accesso al sondaggio.");
	}
	
}
