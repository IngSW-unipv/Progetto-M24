package it.unipv.ingsfw.opinione360.persistence.exception;

import java.io.FileNotFoundException;

/**
 * Eccezione lanciata se l'utente cercato non esiste.
 */
public class UserNotFoundException extends FileNotFoundException {

	public UserNotFoundException() {
		super("Impossibile trovare l'utente specificato.");
	}

}
