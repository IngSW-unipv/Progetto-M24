package it.unipv.ingsfw.opinione360.exception;

/**
 * Eccezione lanciata quando si verifica un errore lato server
 */
public class ServerException extends Exception {

    public ServerException() {
        super("Errore del server.");
    }
    
}
