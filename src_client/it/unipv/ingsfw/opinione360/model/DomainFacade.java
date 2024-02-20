package it.unipv.ingsfw.opinione360.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unipv.ingsfw.opinione360.ServerConnection;
import it.unipv.ingsfw.opinione360.exception.EmptyFieldException;
import it.unipv.ingsfw.opinione360.exception.ServerException;
import it.unipv.ingsfw.opinione360.exception.WrongEmailExpressionException;
import it.unipv.ingsfw.opinione360.exception.WrongPasswordExpressionException;

/**
 * Classe Facade del dominio del client
 */
public class DomainFacade {
	
	static private DomainFacade instance;
	private ServerConnection srvconn;
	private UtenteC sessioneUtente;
	
	/**
	 * Costruttore senza parametri
	 */
	private DomainFacade() { 
		srvconn = new ServerConnection();
	}

	/**
	 * Metodo per effettuare la registrazione
	 * @param utenteIn l'utente che si vuole registrare
	 * @throws EmptyFieldException
	 * @throws WrongEmailExpressionException
	 * @throws WrongPasswordExpressionException
	 * @throws ServerException
	 */
	public void signup(UtenteC utenteIn) throws EmptyFieldException, WrongEmailExpressionException, WrongPasswordExpressionException, ServerException {
		String regEx = "^(?=\\S+$).+$";
		String regExMail = "[a-zA-Z0-9_.+]+@.[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,3}+$";
		String regExPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}$";
		Matcher mb1 = Pattern.compile(regEx).matcher(utenteIn.getUsername());
		Matcher mb2 = Pattern.compile(regEx).matcher(utenteIn.getEmail());
		Matcher me = Pattern.compile(regExMail).matcher(utenteIn.getEmail());
		Matcher mb3 = Pattern.compile(regEx).matcher(utenteIn.getPassword());
		Matcher mp = Pattern.compile(regExPass).matcher(utenteIn.getPassword());
		Matcher mb4 = Pattern.compile(regEx).matcher(utenteIn.getId_societario());
		if(mb1.matches() && mb2.matches() && me.matches() && mb3.matches() && mp.matches() && mb4.matches())
			srvconn.signup(utenteIn);
		else if(!mb1.matches() || !mb2.matches() || !mb3.matches() || !mb4.matches())
			throw new EmptyFieldException();
		else if(!me.matches())
			throw new WrongEmailExpressionException();
		else
			throw new WrongPasswordExpressionException();
	}

	/**
	 * Metodo per avviare il login
	 * @param utenteIn l'utente che vuole accedere
	 * @throws ServerException
	 * @throws IOException
	 */
	public void login(UtenteC utenteIn) throws ServerException, IOException {
		sessioneUtente = srvconn.login(utenteIn);
	}
	
	public SondaggioC getConsultazione() {
		//return srvconn.getConsultazioni();
		return null;
	}
	
	public ArrayList<SondaggioC> getListaConsultazioni() {
		return srvconn.getConsultazioni();
	}

	public SondaggioC getSondaggio() {
		return srvconn.getSondaggio();
	}
	
	/**
	 * Metodo che permette di avviare una votazione
	 * @param consultazione
	 * @param id_opzioni
	 * @throws EmptyFieldException
	 */
	public void vota(IConsultazioneC consultazione, ArrayList<Integer> id_opzioni) throws EmptyFieldException {
		if(id_opzioni.size() == 0)
			throw new EmptyFieldException();
		String risposta;
		try {
			risposta = srvconn.vota(sessioneUtente, id_opzioni, consultazione);
		} catch ( Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("L'utente ha votato ");
		System.out.println(risposta);
		for(int i : id_opzioni)
			System.out.println(i + 1);
	}

	/**
	 * Metodo per ottenere un istanza della classe
	 * @return
	 */
	static public DomainFacade getInstance() {
		if (instance == null) {
			instance = new DomainFacade();
		}
		return instance;
	}
	
}

