package it.unipv.ingsfw.opinione360.model.exception;

public class OptionNotFoundException extends IndexOutOfBoundsException {
	
	public OptionNotFoundException() {
		super("L'opzione selezionata non è valida.");
	}
	
}
