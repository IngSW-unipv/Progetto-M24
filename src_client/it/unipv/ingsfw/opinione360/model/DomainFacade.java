package it.unipv.ingsfw.opinione360.model;

import java.util.ArrayList;

public class DomainFacade {
	static private DomainFacade instance;

	private DomainFacade() { //Classe creata solo per avere la sua istanza nel controller

	}

	public Sondaggio getConsultazione() {
		return null;
	}

	public SondaggioC getSondaggio() {
		String[] opzioni = {"Pilota uno", "Pilota due"};
		return new SondaggioC("Vota il miglior pilota di formula uno", opzioni); //temporary
	}
	
	public void vota(IConsultazioneC consultazione, ArrayList<Integer> id_opzioni) {
		System.out.println("L'utente ha votato " + id_opzioni.get(0));
	}

	static public DomainFacade getInstance() {
		if (instance == null) {
			instance = new DomainFacade();
		}
		return instance;
	}
}

