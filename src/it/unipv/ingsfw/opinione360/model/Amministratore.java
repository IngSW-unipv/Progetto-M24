package it.unipv.ingsfw.opinione360.model;

import java.time.Instant;
import java.util.Date;

public class Amministratore extends Utente {

	Date data_selezione;
	
	public Amministratore(String username, String password) {
		super(username, password);
		this.data_selezione=Date.from(Instant.now());
	}
	
	public Amministratore(String username, String password, Date data) {
		super(username, password);
		this.data_selezione = data;
	}

	public Date getData_selezione() {
		return data_selezione;
	}

	public void setData_selezione(Date data_selezione) {
		this.data_selezione = data_selezione;
	}

}
