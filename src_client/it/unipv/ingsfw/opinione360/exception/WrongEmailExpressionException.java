package it.unipv.ingsfw.opinione360.exception;

public class WrongEmailExpressionException extends Exception {

	public WrongEmailExpressionException() {
		super("L'email non è scritta correttamente.");
	}
	
}
