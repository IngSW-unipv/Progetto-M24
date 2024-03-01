package it.unipv.ingsfw.opinione360.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unipv.ingsfw.opinione360.ServerConnection;
import it.unipv.ingsfw.opinione360.exception.ServerException;
import it.unipv.ingsfw.opinione360.exception.AlreadyRegisteredUserException;
import it.unipv.ingsfw.opinione360.exception.UserNotFoundException;

/**
 * Classe Facade del dominio del client
 */
public class DomainFacade {
	
	static private DomainFacade instance;
	private ServerConnection srvconn;
	private IUtenteC sessioneUtente;
	
	/**
	 * Costruttore senza parametri
	 */
	private DomainFacade() { 
		srvconn = new ServerConnection();
	}

	/**
	 * Metodo per effettuare la registrazione
	 * @param utenteIn l'utente che si vuole registrare
	 * @throws AlreadyRegisteredUserException
	 * @throws IllegalArgumentException
	 * @throws ServerException
	 * @throws IOException
	 */
	public void signup(UtenteC utenteIn) throws AlreadyRegisteredUserException, IllegalArgumentException, ServerException, IOException {
		String regEx = "^(?=\\S+$).+$";
		String regExPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}$";
		String regExMail = "[a-zA-Z0-9_.+]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,3}+$";
		Matcher mb1 = Pattern.compile(regEx).matcher(utenteIn.getUsername());
		Matcher mb2 = Pattern.compile(regEx).matcher(utenteIn.getEmail());
		Matcher me = Pattern.compile(regExMail).matcher(utenteIn.getEmail());
		Matcher mb3 = Pattern.compile(regEx).matcher(utenteIn.getPassword());
		Matcher mp = Pattern.compile(regExPass).matcher(utenteIn.getPassword());
		Matcher mb4 = Pattern.compile(regEx).matcher(utenteIn.getId_societario());
		if(mb1.matches() && mb2.matches() && me.matches() && mb3.matches() && mp.matches() && mb4.matches()) 
				sessioneUtente = srvconn.signup(utenteIn);
		else if(!mb1.matches() || !mb2.matches() || !mb3.matches() || !mb4.matches())
			throw new IllegalArgumentException("C'è almeno un campo vuoto");
		else if(!me.matches())
			throw new IllegalArgumentException("L'email non è scritta correttamente. \nUsa una forma del tipo: \nnome@dominio");
		else
			throw new IllegalArgumentException("La password non è scritta correttamente. \nControlla se la password ha almeno: \n1 numero, 1 lowercase, 1 uppercase, 1 simbolo tra @#$%^&+=, nessuno spazio vuoto, tra i 5 e i 10 caratteri");  
	}
	
	/**
	 * Metodo per avviare il login
	 * @param utenteIn l'utente che vuole accedere
	 * @throws IllegalArgumentException
	 * @throws ServerException
	 * @throws IOException
	 */
	public void login(UtenteC utenteIn) throws IllegalArgumentException, ServerException, IOException {
		String regEx = "^(?=\\S+$).+$";
		String regExPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}$";
		Matcher mb1 = Pattern.compile(regEx).matcher(utenteIn.getUsername());
		Matcher mb3 = Pattern.compile(regEx).matcher(utenteIn.getPassword());
		Matcher mp = Pattern.compile(regExPass).matcher(utenteIn.getPassword());
		if(mb1.matches() && mb3.matches() && mp.matches()) 
				sessioneUtente = srvconn.login(utenteIn);
		else if(!mb1.matches() || !mb3.matches())
			throw new IllegalArgumentException("C'è almeno un campo vuoto");
		else
			throw new IllegalArgumentException("La password non è scritta correttamente. \nControlla se la password ha almeno: \n1 numero, 1 lowercase, 1 uppercase, 1 simbolo tra @#$%^&+=, nessuno spazio vuoto, tra i 5 e i 10 caratteri");  
	}

	/**
	 * Metodo che consente di avviare una richiesta di una lista di consultazioni
	 * @return un oggetto di tipo ArrayList<SondaggioC>
	 * @throws IOException
	 */
	public ArrayList<SondaggioC> getListaConsultazioni() throws IOException {
		return srvconn.getConsultazioni(sessioneUtente);
	}

	/**
	 * Metodo che consente di avviare una richiesta di una specifica consultazione
	 * @param consIn la consultazione che si vuole ricevere
	 * @return un oggetto di tipo {@link IConsultazioneC}
	 * @throws IOException
	 * @throws ServerException
	 */
	public IConsultazioneC getConsultazione(IConsultazioneC consIn) throws IOException, ServerException{
		return srvconn.getConsultazione(consIn);
	}

	/**
	 * Metodo che permette di avviare una votazione
	 * @param consultazione
	 * @throws IllegalArgumentException
	 */
	public void vota(OpzioneC[] voti, IConsultazioneC consultazione) throws IllegalArgumentException, ServerException, IOException {
		if(voti.length == 0)
			throw new IllegalArgumentException("C'è almeno un campo vuoto");
		srvconn.vota(sessioneUtente, voti, consultazione);
	}

	/**
	 * Metodo che permette a un amministratore di avviare la richiesta della lista di utenti
	 * @return un oggetto di tipo ArrayList<UtenteC>
	 * @throws ServerException
	 * @throws IOException
	 * @throws UserNotFoundException
	 */
	public ArrayList<UtenteC> getUtenti() throws ServerException, UserNotFoundException, IOException {
		return srvconn.getUtenti(sessioneUtente);
	}

	/**
	 * Metodo che permette a un amministratore di avviare la richiesta di creazione di una consultazione
	 * @param cons la consultazione che deve essere creata
	 * @throws ServerException
	 * @throws IOException
	 */
	public void creaConsultazione(IConsultazioneC cons) throws ServerException, IOException, IllegalArgumentException {
		srvconn.creaConsultazione(sessioneUtente, cons);
	}

	/**
	 * Metodo che consente di avviare il caricamento di contenuti
	 * @param cons la consultazione nella quale si vuole caricare il contenuto
	 * @param contenuto il contenuto stesso
	 * @param tipoContenuto il tipo del contenuto
	 * @throws ServerException
	 * @throws IOException
	 */
	public void caricaVetrina(IConsultazioneC cons, IContenutoC contenuto, String tipoContenuto) throws ServerException, IOException {
		srvconn.caricaVetrina(sessioneUtente, cons, contenuto, tipoContenuto );
	}	
	
	/**
	 * Metodo per ottenere un istanza della classe
	 * @return un istanza di {@link DomainFacade}
	 */
	static public DomainFacade getInstance() {
		if (instance == null) {
			instance = new DomainFacade();
		}
		return instance;
	}

	public void setSessioneUtente(IUtenteC sessioneUtente) {
		this.sessioneUtente = sessioneUtente;
	}

	public IUtenteC getSessioneUtente(){
		return sessioneUtente;
	}
	
}

