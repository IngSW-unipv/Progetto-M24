package it.unipv.ingsfw.opinione360.model;

import java.util.UUID;

/**
 * Utente che può creare consultazioni
 * @see UtenteC
 */
public class AmministratoreC extends UtenteC implements IUtenteC {
	/**
	 * Costruttore parametrizzato
	 * @param id
	 * @param username
	 * @param password
	 * @param email
	 * @param id_societario
	 */
	public AmministratoreC(UUID id, String username, String password, String email, String id_societario) {
		super(id,username,password,email,id_societario);
	}

	/**
	 * Costruttore parametrizzato
	 * @param username lo username dell'amministratore
	 * @param password la password dell'amministratore
	 * @param email l'email dell'amministratore
	 * @param id_societario l'idi relativo all'ambito socetario in cui è inserito l'amministratore
	 */
	public AmministratoreC(String username, String password, String email, String id_societario) {
		super(username,password,email,id_societario);
	}

	/**
	 * Costruttore parametrizzato
	 * @param password
	 * @param email
	 */
	public AmministratoreC(String password, String email) {
		super(password,email);
	}

	/**
	 * Costruttore con id
	 * @param id
	 */
	public AmministratoreC(UUID id) {
		super(id);
	}

	/**
	 * Costruttore
	 * @param username
	 * @param password
	 * @param email
	 */
    public AmministratoreC(String username, String password, String email) {
		super(username,password,email, null);
    }

	
}
