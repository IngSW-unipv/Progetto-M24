package it.unipv.ingsfw.opinione360.model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * Utente che può creare le consultazioni
 * @see Utente
 */
public class Amministratore extends Utente {

	Date data_selezione;

	/**
	 * Costruttore parametrizzato
	 * @param username lo username dell'amministratore
	 * @param password la propria password
	 */
	public Amministratore(String username, String password) {
		super(username, password);
		this.data_selezione=Date.from(Instant.now());
	}
	
	public Amministratore(UUID id, String username, String password) {
		super(username, password);
		super.setId(id);
		this.data_selezione=Date.from(Instant.now());
	}

	public Amministratore(UUID id, String username, String password, String email, String id_societario) {
		super(username, password, email, id_societario);
		super.setId(id);
	}
	
	/**
	 * Costruttore parametrizzato che permette di indicare la data di selezione
	 * @param username
	 * @param password
	 * @param data la data in cui è stato selezionato
	 */
	public Amministratore(String username, String password, Date data) {
		super(username, password);
		this.data_selezione = data;
	}

    public Amministratore(UUID id) {
		super(id);
    }

    public Amministratore(String username, String password, String email, String id_socetario) {
		super(username,password,email,id_socetario);
    }

    public Date getData_selezione() {
		return data_selezione;
	}

	public void setData_selezione(Date data_selezione) {
		this.data_selezione = data_selezione;
	}
	
	@Override
	public Utente clone(){
		return new Amministratore(getId(), getUsername(), getPassword(), getEmail(), getId_societario());
	}
}
