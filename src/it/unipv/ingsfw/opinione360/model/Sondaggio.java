package it.unipv.ingsfw.opinione360.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.unipv.ingsfw.opinione360.model.exception.OptionNotFoundException;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

public class Sondaggio implements IConsultazione{
	
	private UUID id;
	private int[] contatore;
	private String quesito;
	private String[] opzioni;	
	private List<Utente> votanti, candidati;
	private Date data_chiusura;
	private Vetrina vetrina;
	
	public Sondaggio(String quesito, ArrayList<Utente> v, ArrayList<Utente> c, String[] opzioni, Date data_chiusura){
		id = UUID.randomUUID();
		contatore = new int[opzioni.length];
		for(int i = 0; i < opzioni.length; i++)
			contatore[i] = 0;
		this.quesito = quesito;
		this.votanti = v;
		this.candidati = c;
		this.opzioni = opzioni;
		this.data_chiusura = data_chiusura;
		vetrina = new Vetrina(c);
	}
	
	public void vota(int scelta, Utente u) throws OptionNotFoundException, UserMissingAccessException{
		int posizione = votanti.indexOf(u);
		if (posizione < 0)
			throw new UserMissingAccessException();
		
		if(scelta >= 0 && scelta < opzioni.length) {
			this.contatore[scelta]++;
			this.votanti.remove(posizione);
		}
		else
			throw new OptionNotFoundException();
	}
	
	public boolean isExpired(){
		return this.data_chiusura.compareTo(Date.from(Instant.now())) < 0;
	}
	
	public void stampaRisultati(){
		String risultato = "\nQuesito:\n" + quesito + "\n\n\nRisultati:\n";
		if(this.isExpired())
		{
			for(int n = 0; n < opzioni.length; n++)
				risultato += this.opzioni[n] + ": " + this.contatore[n] + "\n";
			int nmax = 0;
			for(int n = 0; n < opzioni.length - 1; n++)
				if(this.contatore[n] > this.contatore[nmax])
					nmax = n;
			risultato += "\nIl vincitore è " + opzioni[nmax] + ".";
			System.out.println(risultato);
		}
	}
	
	public void stampaVetrina(){
		System.out.println(this.vetrina);
	}

	public int [] getRisultati(){
		return contatore.clone();
	}

	public void caricaContenuto(Utente candidato, String contenuto) throws UserMissingAccessException{
		vetrina.getArea(candidato).aggiungiContenuto(contenuto);
	}

}
