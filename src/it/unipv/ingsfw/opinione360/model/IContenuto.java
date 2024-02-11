package it.unipv.ingsfw.opinione360.model;

import java.util.UUID;

public interface IContenuto {
	
	/**Restituisce l'identificativo del contenuto.*/
	public UUID getId();
	
	/**Restituisce il contenuto dell'oggetto.*/
	public char[] getData();
	
	/**Restituisce il contenuto dell'oggetto.*/
	public void setData(char[] data);
	
	/**Restituisce l'estensione del contanuto (formato)*/
	public String getExtension();
}
