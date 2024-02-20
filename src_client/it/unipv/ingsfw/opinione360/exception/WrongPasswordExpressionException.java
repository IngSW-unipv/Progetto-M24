package it.unipv.ingsfw.opinione360.exception;

public class WrongPasswordExpressionException extends Exception {

	public WrongPasswordExpressionException() {
		super("La password non è scritta correttamente");
	}
	
}
