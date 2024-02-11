package it.unipv.ingsfw.opinione360.model;

import it.unipv.ingsfw.opinione360.model.exception.ConsultationExpiredException;
import it.unipv.ingsfw.opinione360.model.exception.OptionNotFoundException;
import it.unipv.ingsfw.opinione360.model.exception.UserMissingAccessException;

import java.util.*;

/**
 * Questa classe è il tipo base di consultazione
 * @see UserMissingAccessException
 * @see OptionNotFoundException
 * @see ConsultationExpiredException
 * */
public class Sondaggio implements IConsultazione{
	
	private UUID id;
	private int[] contatore;
	private String quesito;
	private String[] opzioni;	
	private ArrayList<Utente> votanti;
	private ArrayList<Utente> candidati;
	private Calendar data_apertura;
	private Calendar data_chiusura;
	private Vetrina vetrina;


	public Sondaggio(Utente u) {
		id = UUID.randomUUID();
		opzioni = new String[]{"0", "1", "2", "3", "4"};
		contatore = new int[opzioni.length];
		ArrayList<Utente> v = new ArrayList<>();
		v.add(u);
		this.votanti = v;
		data_chiusura = new GregorianCalendar(2024, Calendar.FEBRUARY,10, 0, 0,0);
	}
	
	public Sondaggio(UUID id) {
		this.id = id;
	}


	/**
	 * Costruttore
	 * @param quesito e il quesito su cui si chiede agli utenti di decidere
	 * @param v lista di Utenti che possono votare
	 * @param c lista di Utenti che possono caricare contenuti nella vetrina
	 * @param opzioni lista di opzioni
	 * @param data_chiusura la data oltre cui non è possibile votare
	 */
	public Sondaggio(String quesito, ArrayList<Utente> v, ArrayList<Utente> c, String[] opzioni, Calendar data_chiusura){
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

	/**
	 * Override del metodo vota dichiarato in IConsultazione
	 * @param scelta la scelta dell'utente
	 * @param u l'utente che vota
	 * @throws OptionNotFoundException
	 * @throws UserMissingAccessException
	 * @throws ConsultationExpiredException
	 */
	@Override
	public void vota(int scelta, Utente u) throws OptionNotFoundException, UserMissingAccessException, ConsultationExpiredException{
		int posizione = votanti.indexOf(u);
		
		if(this.isExpired()) {
			throw new ConsultationExpiredException();
		}
		
		if (posizione < 0)
			throw new UserMissingAccessException();
		
		if(scelta >= 0 && scelta < opzioni.length) {
			this.contatore[scelta]++;
			this.votanti.remove(posizione);
		}
		else
			throw new OptionNotFoundException();
	}

	/**
	 * Metodo che controlla se la consultazione è terminata
	 * @return true se la consultazione è conclusa, false altrimenti
	 */
	public boolean isExpired(){
		return this.data_chiusura.compareTo(Calendar.getInstance()) < 0;
	}

	/**
	 * Metodo che controlla se la consultazione è già aperta
	 * @return true se la consultazione è aperta, false altrimenti
	 */
	public boolean isOpen(){
		return this.data_apertura.compareTo(Calendar.getInstance()) < 0;
	}

	/**
	 * Metodo che stampa i risultati di un sondaggio concluso
	 */
	@Override
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

	/**
	 * Metodo che restituisce i risultati
	 * @return i risultati contenuti in un vettore di interi
	 */
	public int [] getRisultati(){
		return contatore.clone();
	}

	/**
	 * Metodo che permette il caricamento di contenuti testuali sulla vetrina relativa al sondaggio
	 * @param candidato l'utente presente nella lista di candidati che carica i contenuti
	 * @param contenuto il contenuto che l'utente carica
	 * @throws UserMissingAccessException
	 */
	public void caricaContenuto(Utente candidato, String contenuto) throws UserMissingAccessException{
		vetrina.getArea(candidato).aggiungiContenuto(new ContenutoTesto(contenuto));
	}
	
	public void caricaContenuto(Utente candidato, IContenuto contenuto) throws UserMissingAccessException{
		vetrina.getArea(candidato).aggiungiContenuto(contenuto);
	}
	
	@Override
	public boolean equals(Object obj) {
		Sondaggio pollInput = (Sondaggio) obj;
		return this.id.equals(pollInput.getId());
	}

	@Override
	public UUID getId(){
		return id;
	}
	@Override
	public String getQuesito(){
		return quesito;
	}
	@Override
	public String[] getOpzioni() {
		return opzioni;
	}

	@Override
	public Vetrina getVetrina() {
		return vetrina;
	}

	@Override
	public ArrayList<Utente> getCandidati() {
		return new ArrayList<>(candidati);
	}

	@Override
	public ArrayList<Utente> getVotanti() {
		return new ArrayList<>(votanti);
	}
}