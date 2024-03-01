package it.unipv.ingsfw.opinione360.model;

/**
 * L'AreaOpzione gestisce tutte e informazioni relative ad una specifica opzione di una 
 * consultazione: candidati, contenuti, identificazione, e descrizione.
 */
public class AreaOpzione {
	private Utente candidato;
	private AreaContenuti contenuti;
	private Opzione opzione;
	int count;
	
	public AreaOpzione(Utente candidato, AreaContenuti contenuti, Opzione opzione, int count) {
		super();
		this.candidato = candidato;
		this.contenuti = contenuti;
		this.opzione = opzione;
		this.count = count;
	}
	
	/**Costruttore che inizializza l'AreaOpzione con un'AreaContenuti vuota e conteggio a zero*/
	public AreaOpzione(Utente candidato, Opzione opzione) {
		super();
		this.candidato = candidato;
		this.contenuti = new AreaContenuti();
		this.opzione = opzione;
		this.count = 0;
	}
	
	/**Costruttore che inizializza l'AreaOpzione con un'AreaContenuti vuota*/
	public AreaOpzione(Utente candidato, Opzione opzione, int count) {
		super();
		this.candidato = candidato;
		this.contenuti = new AreaContenuti();
		this.opzione = opzione;
		this.count = count;
	}
	
	/**Costruttore che inizializza l'AreaOpzione con un'AreaContenuti vuota.*/
	public AreaOpzione(Opzione opzione) {
		super();
		this.opzione = opzione;
		this.contenuti = new AreaContenuti();
		this.count = 0;
	}	
	
	/**Restituisce il candidato relativo a quest'opzione, se assegnato.*/
	public Utente getCandidato() {
		return candidato;
	}
	public void setCandidato(Utente candidato) {
		this.candidato = candidato;
	}
	/**Restituisce true se è stato indicato un candidato per questa opzione.*/
	public boolean hasCandidato() {
		return candidato!=null;
	}
	public AreaContenuti getContenuti() {
		return contenuti;
	}
	public void setContenuti(AreaContenuti contenuti) {
		this.contenuti = contenuti;
	}
	public Opzione getOpzione() {
		return opzione;
	}
	public void setOpzione(Opzione opzione) {
		this.opzione = opzione;
	}
	
	/**Restituisce il numero di voti che ha ricevuto questa opzione*/
	public int getCount() {
		return count;
	}
	
	/**Incrementa di un'unità il conteggio relativo ai voti di questa opzione*/
	public void vota() {
		count += 1;
	}

	@Override
	public boolean equals(Object obj) {
		AreaOpzione dat = (AreaOpzione) obj;
		return opzione.equals(dat.getOpzione());
	}	
}
