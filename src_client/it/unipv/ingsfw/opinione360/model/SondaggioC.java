package it.unipv.ingsfw.opinione360.model;

public class SondaggioC implements IConsultazioneC {
	private final String descrizione;
	private final String[] opzioni;
	
	public SondaggioC(String descrizione, String[] opzioni) {
		this.descrizione = descrizione;
		this.opzioni = opzioni;
	}
	
	@Override
	public String getDescrizione() {
		// TODO Auto-generated method stub
		return descrizione;
	}

	@Override
	public String[] getOpzioni() {
		// TODO Auto-generated method stub
		return opzioni;
	}

}
