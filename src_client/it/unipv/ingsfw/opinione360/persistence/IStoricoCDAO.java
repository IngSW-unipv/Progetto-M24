package it.unipv.ingsfw.opinione360.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.ingsfw.opinione360.model.IConsultazioneC;
import it.unipv.ingsfw.opinione360.model.IUtenteC;
import it.unipv.ingsfw.opinione360.model.OpzioneC;
import it.unipv.ingsfw.opinione360.model.IOpzioneC;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.model.UtenteC;

public interface IStoricoCDAO {
	/**Restituisce l'elenco di consultazioni (oggetto opaco) a cui l'utente (locale) ha partecipato
	 * @throws SQLException */
	public ArrayList<IConsultazioneC> selectByUser(IUtenteC u) throws SQLException;
	
	/**Restituisce la scelta effettuata dall'utente locale per la consultazione fornita
	 * @throws SQLException */
	public ArrayList<OpzioneC> selectChoice(IConsultazioneC pollInput) throws SQLException;
	
	/**Aggiunge al database l'opzione selezionata per questa consultazione
	 * @throws SQLException */
	public void insertChoice(IOpzioneC opzione, IConsultazioneC consultazione, IUtenteC votante) throws SQLException;

	boolean reset();
}
