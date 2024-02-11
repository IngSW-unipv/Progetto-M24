package it.unipv.ingsfw.opinione360.exception;

import java.io.FileNotFoundException;

/**
 * Eccezione lanciata quando le credenziali inserite non corrispondono a quelle di un utente registrato
 */
public class UserNotFoundException extends FileNotFoundException {

	public UserNotFoundException() {
		super("Impossibile trovare l'utente specificato.");
	}

}
