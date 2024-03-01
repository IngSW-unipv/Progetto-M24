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
public class Sondaggio implements IConsultazione {
	final static long DEFAULT_END_DATE = 2524608; 
		
	private UUID id;
	private String quesito;
	private ArrayList<AreaOpzione> lista_opzioni;
	private ArrayList<Votante> votanti;
	private Calendar data_apertura;
	private Calendar data_chiusura;
	
	/**
	 * Costruttore
	 * @param id id della consultazione
	 */
	public Sondaggio(UUID id){
		this.id=id;
		quesito="";
		lista_opzioni = new ArrayList<AreaOpzione>();
		votanti=new ArrayList<Votante>();
		data_apertura=Calendar.getInstance();
		data_chiusura=Calendar.getInstance();
		data_chiusura.setTime(new Date(DEFAULT_END_DATE));
	}
	
	public Sondaggio(UUID id, String quesito, ArrayList<Votante> votanti, Calendar data_apertura, Calendar data_chiusura){
		this.id=id;
		this.quesito=quesito;
		this.lista_opzioni = new ArrayList<AreaOpzione>();
		this.votanti=votanti;
		this.data_apertura=data_apertura;
		this.data_chiusura=data_chiusura;
	}
	
	public Sondaggio(String quesito, ArrayList<Utente> votanti){
		this(UUID.randomUUID());
		this.quesito=quesito;
		lista_opzioni = new ArrayList<AreaOpzione>();
		for (Utente u: votanti) {
			this.votanti.add(new Votante(u));
		}
	}
	
	
	/**
	 * Costruttore che inizializza tutti gli attributi
	 * @param id id della consultazione
	 * @param quesito e' il quesito su cui si chiede agli utenti di decidere
	 * @param lista_opzioni lista contenente opzioni valide e candidati relativi
	 * @param votanti lista di Utenti che possono votare
	 * @param data_apertura la data prima della quale non è possibile votare
	 * @param data_chiusura la data oltre cui non è possibile votare
	 */
	public Sondaggio(UUID id, String quesito, ArrayList<AreaOpzione> lista_opzioni, ArrayList<Votante> votanti, Calendar data_apertura, Calendar data_chiusura) {
		this.id = id;
		this.quesito = quesito;
		this.lista_opzioni = lista_opzioni;;
		
		this.votanti=votanti;
		
		this.data_apertura = data_apertura;
		this.data_chiusura = data_chiusura;
	}
	
	/**Costruttore che crea il sondaggio con i parametri forniti assegnando un nuovo id*/
	public Sondaggio(String quesito, ArrayList<AreaOpzione> lista_opzioni, ArrayList<Utente> votanti, Calendar data_apertura, Calendar data_chiusura) {
		this.id = UUID.randomUUID();
		this.quesito = quesito;
		this.lista_opzioni = new ArrayList<AreaOpzione>(lista_opzioni.size());
		
		for (int n=0;n<lista_opzioni.size();n++) { //le opzioni vengono rinumerate per evitare conflitti successivi
			AreaOpzione a = lista_opzioni.get(n);
			a.getOpzione().setId(n);
			this.lista_opzioni.set(n, a);
		}
		
		this.votanti=new ArrayList<Votante>(votanti.size());
		for (Utente u: votanti) {
			this.votanti.add(new Votante(u));
		}
		
		this.data_apertura = data_apertura;
		this.data_chiusura = data_chiusura;
	}
	
	/**Costruttore che crea il sondaggio con i parametri forniti assegnando un nuovo id, creando una list di opzioni vuota*/
	public Sondaggio(String quesito, ArrayList<Utente> votanti, Calendar data_apertura, Calendar data_chiusura) {
		this.id = UUID.randomUUID();
		this.quesito = quesito;
		this.lista_opzioni = new ArrayList<AreaOpzione>();
		
		this.votanti=new ArrayList<Votante>(votanti.size());
		for (Utente u: votanti) {
			this.votanti.add(new Votante(u));
		}
		
		this.data_apertura = data_apertura;
		this.data_chiusura = data_chiusura;
	}

	/**
	 * Costruttore per compatibilita' con le versioni precedenti di questa classe
	 * @deprecated 
	 * @param quesito e il quesito su cui si chiede agli utenti di decidere
	 * @param votanti lista di Utenti che possono votare
	 * @param candidati lista di Utenti che possono caricare contenuti nella vetrina
	 * @param opzioni lista di opzioni
	 * @param data_chiusura la data oltre cui non è possibile votare
	 */
	@Deprecated
	public Sondaggio(String quesito, ArrayList<Utente> votanti, ArrayList<Utente> candidati, String[] opzioni, Calendar data_chiusura){
		this.id = UUID.randomUUID();
		this.quesito = quesito;
		
		this.lista_opzioni = new ArrayList<AreaOpzione>(opzioni.length);
		for (int n_opz=0;n_opz<opzioni.length;n_opz++) {
			if (n_opz<candidati.size())
				this.lista_opzioni.add(new AreaOpzione(candidati.get(n_opz), new Opzione(n_opz, opzioni[n_opz])));
			else {
				this.lista_opzioni.add(new AreaOpzione(new Opzione(n_opz, opzioni[n_opz])));
			}
		}
		
		this.votanti=new ArrayList<Votante>(votanti.size());
		for (Utente u: votanti) {
			this.votanti.add(new Votante(u));
		}
		
		this.data_apertura = Calendar.getInstance();
		this.data_chiusura = data_chiusura;
	}
	
	@Override
	public void vota(Opzione[] scelte, Utente u) throws OptionNotFoundException, UserMissingAccessException, ConsultationExpiredException {
  		int votante_idx = votanti.indexOf(u);
		
		if(this.isExpired()||!this.isOpen()) {
			throw new ConsultationExpiredException();
		}
		
		if (votante_idx < 0 || votanti.get(votante_idx).getHaVotato())
			throw new UserMissingAccessException();
		
		// removing option duplicates
		HashSet<Opzione> opzioni = new HashSet<Opzione>();
		for (Opzione o: scelte) {
			opzioni.add(o);
		}
		
		try {
			for (Opzione o: opzioni) {
				int op_index = lista_opzioni.indexOf(new AreaOpzione(o));
				lista_opzioni.get(op_index).vota();
			}
		}
		catch(IndexOutOfBoundsException e) {
			throw new OptionNotFoundException();
		}
		
		votanti.get(votante_idx).setHaVotato(true);
	}
	
	public void vota(Opzione scelta, Utente u) throws OptionNotFoundException, UserMissingAccessException, ConsultationExpiredException {
		this.vota(new Opzione[]{scelta}, u);
	}
	
	@Override @Deprecated
	public void vota(int scelta, Utente u) throws OptionNotFoundException, UserMissingAccessException, ConsultationExpiredException{
		this.vota(new Opzione[]{new Opzione(scelta)}, u);
	}

	/**
	 * Restituisce true se la data corrente e' successiva alla data di chiusura della consultazione
	 */
	@Override
	public boolean isExpired() {
		return Calendar.getInstance().compareTo(data_chiusura) > 0;
	}
	
	/**
	 * Restituisce true se il sondaggio e' aperto, ovvero se la data corrente e' successiva all'apertura e anteriore alla chiusura
	 */
	public boolean isOpen() {
		return Calendar.getInstance().compareTo(data_apertura) > 0 && Calendar.getInstance().compareTo(data_chiusura) < 0;
	}
	@Override
	public ArrayList<AreaOpzione> getListaOpzioni() {
		return lista_opzioni;
	}
	@Override
	public UUID getId() {
		return this.id;
	}
	@Override
	public String getQuesito() {
		return this.quesito;
	}
	@Override
	public Opzione[] getAllOpzioni() {
		Opzione[] result = new Opzione[lista_opzioni.size()];
		for (int n=0;n<lista_opzioni.size();n++) {
			result[n]=lista_opzioni.get(n).getOpzione();
		}		
		return result;
	}
	
	@Override
	public String[] getOpzioni() {
		String[] str_op_list = new String[this.lista_opzioni.size()];
		
		for (int n=0;n<this.lista_opzioni.size();n++) {
			str_op_list[n]=this.lista_opzioni.get(n).getOpzione().getDescription();
		}

		return str_op_list;
	}

	@Override
	public int getOpzioniCount() {
		return this.lista_opzioni.size();
	}
	@Override
	public boolean hasCandidato(Utente candidato) {
		for (AreaOpzione a: this.lista_opzioni) {
			if (a.hasCandidato() && candidato.equals(a.getCandidato())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean hasVotante(Utente votante) {
		return this.votanti.contains(votante);
	}
	@Override
	public ArrayList<IContenuto> getContenuti(Opzione opzione) throws OptionNotFoundException {
		AreaContenuti area;
		try {
			area = lista_opzioni.get(lista_opzioni.indexOf(new AreaOpzione(opzione))).getContenuti();
			
		}
		catch(IndexOutOfBoundsException e) {
			throw new OptionNotFoundException();
		}
		
		return area.getContenuti();
	}
	
	public void caricaContenuto(Utente candidato, IContenuto contenuto) throws UserMissingAccessException{
		for (AreaOpzione o: lista_opzioni) {
			if (o.hasCandidato() && o.getCandidato().equals(candidato)) {
				o.getContenuti().aggiungiContenuto(contenuto);
				return;
			}
		}
		throw new UserMissingAccessException();
	}
	
	public ArrayList<Utente> getCandidati(){
		ArrayList<Utente> result = new ArrayList<Utente>();
		for (AreaOpzione a: this.lista_opzioni) {
			if (a.hasCandidato()) {
				result.add(a.getCandidato());
			}
		}
		return result;
	}
	public ArrayList<Votante> getVotanti(){
		return this.votanti;
	}
	
//	public void setVotanti(ArrayList<Votante> votanti){
//		this.votanti=votanti;
//	}
//	public void setAreaOpzioni(ArrayList<Votante> votanti){
//		this.votanti=votanti;
//	}

	@Override
	public HashMap<Opzione, Integer> getRisultati() {
		HashMap<Opzione, Integer> risultati = new HashMap<Opzione, Integer>(this.lista_opzioni.size());
		for (AreaOpzione o: lista_opzioni) {
			risultati.put(o.getOpzione(), o.getCount());
		}
		return risultati;
	}
	
	/**Restituisce il numero di voti ricevuti per l'opzione indicata*/
	public int getRisultato(Opzione opzione) throws OptionNotFoundException {
		try {
			return lista_opzioni.get(lista_opzioni.indexOf(new AreaOpzione(opzione))).getCount();
		}
		catch(IndexOutOfBoundsException err) {
			throw new OptionNotFoundException();
		}
	}
	
	public void addOpzione(Opzione opzione) {
		int id=lista_opzioni.size();
		opzione.setId(id); //verify no duplicate ids
		
		lista_opzioni.add(new AreaOpzione(opzione));
	}
	
	public void addOpzione(Opzione opzione, Utente candidato) {
		int id=lista_opzioni.size();
		opzione.setId(id); //verify no duplicate ids
		
		lista_opzioni.add(new AreaOpzione(candidato, opzione));
	}

	@Override
	public void caricaContenuto(Opzione opzione, IContenuto contenuto) throws OptionNotFoundException {
		try {
			lista_opzioni.get(lista_opzioni.indexOf(new AreaOpzione(opzione))).getContenuti().aggiungiContenuto(contenuto);
		}
		catch(IndexOutOfBoundsException e) {
			throw new OptionNotFoundException();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(String.format("Sondaggio: %s [active: %s]\n", quesito, isOpen()));
		result.append(String.format("\tApertura: %1$tc\n", this.data_apertura));
		result.append(String.format("\tChiusura: %1$tc\n", this.data_chiusura));
		
		result.append("\nOpzioni:\n");
		
		for (AreaOpzione a: lista_opzioni) {
			result.append("\t").append(a.getOpzione());
			if (a.hasCandidato()) {
				result.append("(").append(a.getCandidato().getUsername()).append(")");
			}
			result.append("\n");
		}
		
		return result.toString();
		
	}
	
	public Calendar getData_apertura() {
		return data_apertura;
	}

	public Calendar getData_chiusura() {
		return data_chiusura;
	}
}