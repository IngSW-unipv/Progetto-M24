package it.unipv.ingsfw.opinione360.model;

import java.util.UUID;

public class UtenteC implements IUtenteC {
	private UUID id;
	private String username;
	private String password;
	private String email;
	private String id_societario;
	
	public UtenteC(UUID id, String username, String password, String email, String id_societario) {
		//super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.id_societario = id_societario;
	}

	/**
	 * Costruttore parametrizzato
	 * @param username lo username dell'utente
	 * @param password la password dell'utente
	 * @param email l'email dell'utente
	 * @param id_societario l'idi relativo all'ambito socetario in cui è inserito l'utente
	 */
	public UtenteC(String username, String password, String email, String id_societario) {
		id = null;
		this.username = username;
		this.password = password;
		this.email = email;
		this.id_societario = id_societario;
	}

	/**
	 * Costruttore parametrizzato
	 * @param password
	 * @param email
	 */
	public UtenteC(String password, String email) {
		this.password = password;
		this.email = email;
	}

	public UtenteC(UUID id) {
		//super();
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId_societario() {
		return id_societario;
	}
	public void setId_societario(String id_societario) {
		this.id_societario = id_societario;
	}
	
	
}
