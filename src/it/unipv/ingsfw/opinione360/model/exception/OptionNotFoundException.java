package it.unipv.ingsfw.opinione360.model.exception;

/**
 * Eccezione lanciata quando l'opzione non esiste
 */

public class OptionNotFoundException extends IndexOutOfBoundsException {
	
	public OptionNotFoundException() {
		super("L'opzione selezionata non è valida.");
	}
	
}
