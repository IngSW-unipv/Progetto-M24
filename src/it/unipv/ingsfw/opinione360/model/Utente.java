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
	 * Costruttore
	 * @param id id dell'utente
	 * @param username l'username dell'utente
	 * @param password la password dell'utente
	 */
	public Utente(UUID id, String username, String password) {
		this.id = id;
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

	/**
	 * Costruttore che prende tutti i parametri
	 * @param username il nome utente
	 * @param password la password
	 * @param email l'email
	 * @param id_societario l'id univoco all'interno della società di appartenenza dell'utente
	 */
	public Utente(String username, String password, String email, String id_societario){
		id = UUID.randomUUID();
		this.username = username;
		this.password = password;
		this.email = email;
		this.id_societario = id_societario;
	}

	/**
	 * Costruttore con tutti i parametri
	 * @param id
	 * @param username
	 * @param password
	 * @param email
	 * @param id_societario
	 */
	public Utente(UUID id, String username, String password, String email, String id_societario){
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.id_societario = id_societario;
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

	@Override
	public boolean equals(Object obj) {
		Utente userInput = (Utente) obj;
		return this.id.equals(userInput.getId());
	}

	@Override
	public String toString() {
		return id + " " + username + " " + password + " " + email + " " + id_societario + "\n";
	}
	
	@Override
	public Utente clone(){
		return new Utente(id, username, password, email, id_societario);
	}
}
