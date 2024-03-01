package it.unipv.ingsfw.opinione360.model;

import it.unipv.ingsfw.opinione360.model.IContenuto;
import it.unipv.ingsfw.opinione360.model.UtenteC;
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
public class SondaggioC implements IConsultazioneC {
	
	private UUID id;
	private String quesito;
	private ArrayList<AreaOpzioneC> listaOpzioni;
	private ArrayList<VotanteC> votanti;
	private Calendar data_apertura;
	private Calendar data_chiusura;
	
	/**
	 * Costruttore
	 * @param id id della consultazione
	 */
	public SondaggioC(UUID id){
		this.id=id;
		quesito="";
		listaOpzioni = new ArrayList<AreaOpzioneC>();
		votanti=new ArrayList<VotanteC>();
		data_apertura=Calendar.getInstance();
		data_chiusura=Calendar.getInstance();
		data_chiusura.setTime(new Date(Long.MAX_VALUE));
	}
	
	public SondaggioC(UUID id, String quesito, ArrayList<VotanteC> votanti, Calendar data_apertura, Calendar data_chiusura){
		this.id=id;
		this.quesito=quesito;
		this.listaOpzioni = new ArrayList<AreaOpzioneC>();
		this.votanti=votanti;
		this.data_apertura=data_apertura;
		this.data_chiusura=data_chiusura;
	}
	
	public SondaggioC(String quesito, ArrayList<UtenteC> votanti){
		this(UUID.randomUUID());
		this.quesito=quesito;
		listaOpzioni = new ArrayList<AreaOpzioneC>();
		for (UtenteC u: votanti) {
			this.votanti.add(new VotanteC(u));
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
	public SondaggioC(UUID id, String quesito, ArrayList<AreaOpzioneC> lista_opzioni, ArrayList<VotanteC> votanti, Calendar data_apertura, Calendar data_chiusura) {
		this.id = id;
		this.quesito = quesito;
		this.listaOpzioni = lista_opzioni;;
		
		this.votanti=votanti;
		
		this.data_apertura = data_apertura;
		this.data_chiusura = data_chiusura;
	}
	
	/**Costruttore che crea il sondaggio con i parametri forniti assegnando un nuovo id*/
	public SondaggioC(String quesito, ArrayList<AreaOpzioneC> lista_opzioni, ArrayList<UtenteC> votanti, Calendar data_apertura, Calendar data_chiusura) {
		this.id = UUID.randomUUID();
		this.quesito = quesito;
		this.listaOpzioni = new ArrayList<AreaOpzioneC>(lista_opzioni.size());
		
		for (int n=0;n<lista_opzioni.size();n++) { //le opzioni vengono rinumerate per evitare conflitti successivi
			AreaOpzioneC a = lista_opzioni.get(n);
			a.getOpzione().setId(n);
			this.listaOpzioni.set(n, a);
		}
		
		this.votanti=new ArrayList<VotanteC>(votanti.size());
		for (UtenteC u: votanti) {
			this.votanti.add(new VotanteC(u));
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
	public SondaggioC(String quesito, ArrayList<UtenteC> votanti, ArrayList<UtenteC> candidati, String[] opzioni, Calendar data_chiusura){
		this.id = UUID.randomUUID();
		this.quesito = quesito;
		
		this.listaOpzioni = new ArrayList<AreaOpzioneC>(opzioni.length);
		for (int n_opz=0;n_opz<opzioni.length;n_opz++) {
			if (n_opz<candidati.size())
				this.listaOpzioni.add(new AreaOpzioneC(candidati.get(n_opz), new OpzioneC(n_opz, opzioni[n_opz])));
			else {
				this.listaOpzioni.add(new AreaOpzioneC(new OpzioneC(n_opz, opzioni[n_opz])));
			}
		}
		
		this.votanti=new ArrayList<VotanteC>(votanti.size());
		for (UtenteC u: votanti) {
			this.votanti.add(new VotanteC(u));
		}
		
		this.data_apertura = Calendar.getInstance();
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
	public SondaggioC(String quesito, ArrayList<UtenteC> votanti, ArrayList<UtenteC> candidati, String[] opzioni, Calendar data_apertura, Calendar data_chiusura){
		this.id = UUID.randomUUID();
		this.quesito = quesito;
		
		this.listaOpzioni = new ArrayList<AreaOpzioneC>(opzioni.length);
		for (int n_opz=0;n_opz<opzioni.length;n_opz++) {
			if (n_opz<candidati.size())
				this.listaOpzioni.add(new AreaOpzioneC(candidati.get(n_opz), new OpzioneC(n_opz, opzioni[n_opz])));
			else {
				this.listaOpzioni.add(new AreaOpzioneC(new OpzioneC(n_opz, opzioni[n_opz])));
			}
		}
		
		this.votanti=new ArrayList<VotanteC>(votanti.size());
		for (UtenteC u: votanti) {
			this.votanti.add(new VotanteC(u));
		}
		
		this.data_apertura = data_apertura;
		this.data_chiusura = data_chiusura;
	}
	
	@Override
	public void vota(OpzioneC[] scelte, UtenteC u) throws OptionNotFoundException, UserMissingAccessException, ConsultationExpiredException {
  		int votante_idx = votanti.indexOf(u);
		
		if(this.isExpired()||!this.isOpen()) {
			throw new ConsultationExpiredException();
		}
		
		if (votante_idx < 0 || votanti.get(votante_idx).getHaVotato())
			throw new UserMissingAccessException();
		
		// removing option duplicates
		HashSet<OpzioneC> opzioni = new HashSet<OpzioneC>();
		for (OpzioneC o: scelte) {
			opzioni.add(o);
		}
		
		try {
			for (OpzioneC o: opzioni) {
				int op_index = listaOpzioni.indexOf(new AreaOpzioneC(o));
				listaOpzioni.get(op_index).vota();
			}
		}
		catch(IndexOutOfBoundsException e) {
			throw new OptionNotFoundException();
		}
	}
	
	public void vota(OpzioneC scelta, UtenteC u) throws OptionNotFoundException, UserMissingAccessException, ConsultationExpiredException {
		this.vota(new OpzioneC[]{scelta}, u);
	}
	
	@Override @Deprecated
	public void vota(int scelta, UtenteC u) throws OptionNotFoundException, UserMissingAccessException, ConsultationExpiredException{
		this.vota(new OpzioneC[]{new OpzioneC(scelta)}, u);
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
	public ArrayList<AreaOpzioneC> getListaOpzioni() {
		return listaOpzioni;
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
	public OpzioneC[] getAllOpzioni() {
		OpzioneC[] result = new OpzioneC[listaOpzioni.size()];
		for (int n=0;n<listaOpzioni.size();n++) {
			result[n]=listaOpzioni.get(n).getOpzione();
		}		
		return result;
	}
	
	@Override
	public String[] getOpzioni() {
		String[] str_op_list = new String[this.listaOpzioni.size()];
		
		for (int n=0;n<this.listaOpzioni.size();n++) {
			str_op_list[n]=this.listaOpzioni.get(n).getOpzione().getDescription();
		}

		return str_op_list;
	}

	@Override
	public int getOpzioniCount() {
		return this.listaOpzioni.size();
	}
	@Override
	public boolean hasCandidato(UtenteC candidato) {
		for (AreaOpzioneC a: this.listaOpzioni) {
			if (a.hasCandidato() && candidato.equals(a.getCandidato())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean hasVotante(UtenteC votante) {
		return this.votanti.contains(votante);
	}
	@Override
	public ArrayList<IContenutoC> getContenuti(OpzioneC opzione) throws OptionNotFoundException {
		AreaContenutiC area;
		try {
			area = listaOpzioni.get(listaOpzioni.indexOf(new AreaOpzioneC(opzione))).getContenuti();		
		}
		catch(IndexOutOfBoundsException e) {
			throw new OptionNotFoundException();
		}
		return area.getContenuti();
	}
	
	public void caricaContenuto(UtenteC candidato, IContenutoC contenuto) throws UserMissingAccessException{
		for (AreaOpzioneC o: listaOpzioni) {
			if (o.getCandidato().equals(candidato)) {
				o.getContenuti().aggiungiContenuto(contenuto);
				return;
			}
		}
		throw new UserMissingAccessException();
	}
	
	public ArrayList<UtenteC> getCandidati(){
		ArrayList<UtenteC> result = new ArrayList<UtenteC>();
		for (AreaOpzioneC a: this.listaOpzioni) {
			if (a.hasCandidato()) {
				result.add(a.getCandidato());
			}
		}
		return result;
	}
	public ArrayList<UtenteC> getVotanti(){
		ArrayList<UtenteC> result = new ArrayList<UtenteC>();
		for(UtenteC u : this.votanti) {
			result.add(u.clone());
		}
		return result;
	}

	@Override
	public HashMap<OpzioneC, Integer> getRisultati() {
		HashMap<OpzioneC, Integer> risultati = new HashMap<OpzioneC, Integer>(this.listaOpzioni.size());
		for (AreaOpzioneC o: listaOpzioni) {
			risultati.put(o.getOpzione(), o.getCount());
		}
		return risultati;
	}
	
	/**Restituisce il numero di voti ricevuti per l'opzione indicata*/
	public int getRisultato(OpzioneC opzione) throws OptionNotFoundException {
		try {
			return listaOpzioni.get(listaOpzioni.indexOf(new AreaOpzioneC(opzione))).getCount();
		}
		catch(IndexOutOfBoundsException err) {
			throw new OptionNotFoundException();
		}
	}
	
	public void addOpzione(OpzioneC opzione) {
		int id=listaOpzioni.size();
		opzione.setId(id); //verify no duplicate ids
		
		listaOpzioni.add(new AreaOpzioneC(opzione));
	}
	
	public void addOpzione(OpzioneC opzione, UtenteC candidato) {
		int id=listaOpzioni.size();
		opzione.setId(id); //verify no duplicate ids
		
		listaOpzioni.add(new AreaOpzioneC(candidato, opzione));
	}

	@Override
	public void caricaContenuto(OpzioneC opzione, IContenutoC contenuto) throws OptionNotFoundException {
		try {
			listaOpzioni.get(listaOpzioni.indexOf(new AreaOpzioneC(opzione))).getContenuti().aggiungiContenuto(contenuto);
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
		
		for (AreaOpzioneC a: listaOpzioni) {
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