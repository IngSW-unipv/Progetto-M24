package it.unipv.ingsfw.opinione360.persistence.exception;

import java.io.FileNotFoundException;

/**
 * Eccezione lanciata se viene cercata una vetrina relativa ad una consultazione che non ha vetrine registrate.
 */
public class MissingVetrinaException extends FileNotFoundException {

	public MissingVetrinaException() {
		super("Impossibile trovare la vetrina specificata.");
	}

}
