package it.unipv.ingsfw.opinione360.model;

public class Opzione implements IOpzione {

	private int id;
	private String descrizione;
	
	public Opzione(int id) {
		this.id=id;
		this.descrizione="";
	}
	
	public Opzione(String description) {
		this.id=-1; // avoid being able to use this for voting
		this.descrizione=description;
	}
	
	public Opzione(int id, String descrizione) {
		this.id=id;
		this.descrizione=descrizione;
	}
	
	@Override
	public String getDescription() {
		return descrizione;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void setId(int id) {
		this.id=id;
	}
	
	@Override
	public boolean equals(Object obj) {
		Opzione target = (Opzione)obj;
		return this.getId()==target.getId();
	}
	
	@Override
	public int hashCode() { // utile per utilizzarlo nelle hashmap
		return this.id;
	}
	
	@Override
	public String toString() {
		return String.format("%d) %s", id, descrizione);
	}

}
