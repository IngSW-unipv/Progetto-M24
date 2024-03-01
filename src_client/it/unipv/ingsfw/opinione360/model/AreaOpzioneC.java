package it.unipv.ingsfw.opinione360.model;

import it.unipv.ingsfw.opinione360.model.UtenteC;

/**
 * L'AreaOpzione gestisce tutte e informazioni relative ad una specifica opzione di una 
 * consultazione: candidati, contenuti, identificazione, e descrizione.
 */
public class AreaOpzioneC {
	private UtenteC candidato;
	private AreaContenutiC contenuti;
	private OpzioneC opzione;
	int count;
	
	public AreaOpzioneC(UtenteC candidato, AreaContenutiC contenuti, OpzioneC opzione, int count) {
		super();
		this.candidato = candidato;
		this.contenuti = contenuti;
		this.opzione = opzione;
		this.count = count;
	}
	
	/**Costruttore che inizializza l'AreaOpzione con un'AreaContenuti vuota e conteggio a zero*/
	public AreaOpzioneC(UtenteC candidato, OpzioneC opzione) {
		super();
		this.candidato = candidato;
		this.contenuti = new AreaContenutiC();
		this.opzione = opzione;
		this.count = 0;
	}
	
	/**Costruttore che inizializza l'AreaOpzione con un'AreaContenuti vuota*/
	public AreaOpzioneC(UtenteC candidato, OpzioneC opzione, int count) {
		super();
		this.candidato = candidato;
		this.contenuti = new AreaContenutiC();
		this.opzione = opzione;
		this.count = count;
	}
	
	/**Costruttore che inizializza l'AreaOpzione con un'AreaContenuti vuota.*/
	public AreaOpzioneC(OpzioneC opzione) {
		super();
		this.opzione = opzione;
		this.contenuti = new AreaContenutiC();
		this.count = 0;
	}	
	
	/**Restituisce il candidato relativo a quest'opzione, se assegnato.*/
	public UtenteC getCandidato() {
		return candidato;
	}
	public void setCandidato(UtenteC candidato) {
		this.candidato = candidato;
	}
	/**Restituisce true se è stato indicato un candidato per questa opzione.*/
	public boolean hasCandidato() {
		return candidato!=null;
	}
	public AreaContenutiC getContenuti() {
		return contenuti;
	}
	public void setContenuti(AreaContenutiC contenuti) {
		this.contenuti = contenuti;
	}
	public OpzioneC getOpzione() {
		return opzione;
	}
	public void setOpzione(OpzioneC opzione) {
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
		if (obj==null) return false;
		AreaOpzioneC dat = (AreaOpzioneC) obj;
		return opzione.equals(dat.getOpzione());
	}	
}
