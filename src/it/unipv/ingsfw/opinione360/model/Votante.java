package it.unipv.ingsfw.opinione360.model;

import java.util.UUID;

import it.unipv.ingsfw.opinione360.model.Utente;

public class Votante extends Utente {
	private boolean voted;
	
	// copy constructor
	public Votante(Utente u){
		super(u.getId(), u.getUsername(), u.getPassword(), u.getEmail(), u.getId_societario());
		this.voted=false;	
	}
	
	public Votante(Utente u, boolean ha_votato){
		this(u);
		this.voted=ha_votato;	
	}
	
	public Votante(String username, String password, String email, String id_societario){
		super(username, password, email, id_societario);
	}
	public Votante(UUID id, String username, String password, String email, String id_societario, boolean hasVoted){
		super(id, username, password, email, id_societario);
		this.voted=hasVoted;
	}
	public boolean getHaVotato() {
		return voted;
	}
	
	public void setHaVotato(boolean value) {
		this.voted=value;
	}
}
