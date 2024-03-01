package it.unipv.ingsfw.opinione360.model.exception;

/**
 * Eccezione lanciata quando la consultazione è terminata
 */


public class ConsultationExpiredException extends IllegalStateException {

	public ConsultationExpiredException() {
		super("La consultazione è terminata.");
	}

}
