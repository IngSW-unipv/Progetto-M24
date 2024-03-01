package it.unipv.ingsfw.opinione360.model;

import java.util.UUID;

/**
 * Tipo base di utente
 */
public class UtenteC implements IUtenteC {
	private UUID id;
	private String username;
	private String email;
	private String password;
	private String id_societario;

	/**
	 * Costruttore con tutti i parametri
	 * @param id
	 * @param username
	 * @param email
	 * @param password
	 * @param id_societario
	 */
	public UtenteC(UUID id, String username, String email, String password, String id_societario) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.id_societario = id_societario;
	}

	/**
	 * Costruttore parametrizzato
	 * @param username lo username dell'utente
	 * @param email l'email dell'utente
	 * @param password la password dell'utente
	 * @param id_societario l'id relativo all'ambito socetario in cui è inserito l'utente
	 */
	public UtenteC(String username, String email, String password, String id_societario) {
		id = UUID.randomUUID();
		this.username = username;
		this.email = email;
		this.password = password;
		this.id_societario = id_societario;
	}

	/**
	 * Costruttore parametrizzato
	 * @param username
	 * @param password
	 */
	public UtenteC(String username, String password) {
		id = UUID.randomUUID();
		this.username = username;
		this.password = password;
	}

	/**Costruttore con solo l'id
	 * @param id
	 */
	public UtenteC(UUID id) {
		this.id = id;
	}

	/**
	 * Costruttore con solo lo username
	 * @param username
	 */
	public UtenteC(String username) {
		id = UUID.randomUUID();
		this.username = username;
	}

	/**
	 * Costruttore
	 * @param username
	 * @param email
	 * @param password
	 */
    public UtenteC(String username, String email, String password) {
    	this.id = UUID.randomUUID();
		this.username = username;
		this.email = email;
		this.password = password;
    }
	@Override
    public UUID getId() {
		return id;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getId_societario() {
		return id_societario;
	}
	@Override
	public void setId_societario(String id_societario) {
		this.id_societario = id_societario;
	}
	@Override
	public boolean equals(Object obj) {
		UtenteC userInput = (UtenteC) obj;
		return this.id.equals(userInput.getId());
	}

	@Override
	public String toString() {
		return id + " " + username + " " + password + " " + email + " " + id_societario + "\n";
	}

	@Override
	public UtenteC clone(){
		return new UtenteC(id, username, password, email, id_societario);
	}
}
