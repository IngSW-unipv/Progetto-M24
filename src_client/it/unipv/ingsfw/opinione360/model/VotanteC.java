package it.unipv.ingsfw.opinione360.model;

public class VotanteC extends UtenteC {
	private boolean voted;
	
	// copy constructor
	public VotanteC(UtenteC u){
		super(u.getId(), u.getUsername(), u.getPassword(), u.getEmail(), u.getId_societario());
		this.voted=false;	
	}
	
	public VotanteC(String username, String password, String email, String id_societario){
		super(username, password, email, id_societario);
	}

	public boolean getHaVotato() {
		return voted;
	}
}
