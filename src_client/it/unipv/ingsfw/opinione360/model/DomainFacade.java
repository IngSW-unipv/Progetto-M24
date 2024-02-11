package it.unipv.ingsfw.opinione360.model;

import java.util.ArrayList;

import it.unipv.ingsfw.opinione360.exception.EmptyFieldException;

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
	
	public void vota(IConsultazioneC consultazione, ArrayList<Integer> id_opzioni) throws EmptyFieldException {
		if(id_opzioni.size() == 0)
			throw new EmptyFieldException();
		System.out.println("L'utente ha votato ");
		for(int i : id_opzioni)
			System.out.println(i + 1);
	}

	static public DomainFacade getInstance() {
		if (instance == null) {
			instance = new DomainFacade();
		}
		return instance;
	}
}

