package it.unipv.ingsfw.opinione360.model;

import java.util.UUID;

/**
 * Classe base di Utente
 */

public class Utente {

	private UUID id;
	private String username;
	private String password;
	private String email;
	private String id_societario;

	/**
	 * Costruttore
	 * @param username l'username dell'utente
	 * @param password la password dell'utente
	 */
	public Utente(String username, String password) {
		this.id = UUID.randomUUID();
		this.username = username;
		this.password = password;
	}

	/**
	 * Costruttore che prende l'id dell'utente
	 * @param id id dell'utente che si vuole creare
	 */
	public Utente(UUID id) {
		this.id = id;
	}

	/**
	 * Costruttore senza parametri
	 */
	public Utente() {
		this.id = UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public boolean equals(Utente user) {
		return this.id.equals(user.getId());
	}
}
