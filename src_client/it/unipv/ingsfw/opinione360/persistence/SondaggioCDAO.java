package it.unipv.ingsfw.opinione360.persistence;

import java.util.ArrayList;

import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.model.UtenteC;

public class SondaggioCDAO implements ISondaggioCDAO {

	@Override
	public ArrayList<SondaggioC> selectById(SondaggioC pollInput) {
		ArrayList<SondaggioC> poll_list = new ArrayList<SondaggioC>();
		poll_list.add(new SondaggioC("chi voti?", new String[]{"opz1", "opz2"}));
		poll_list.add(new SondaggioC("chi è il miglior pilota?", new String[]{"opz1", "opz2"}));
		poll_list.add(new SondaggioC("quale colore è meglio?", new String[]{"opz1", "opz2"}));
		return poll_list;
	}

	@Override
	public ArrayList<SondaggioC> selectByUser(UtenteC utenteInput) {
		ArrayList<SondaggioC> poll_list = new ArrayList<SondaggioC>();
		poll_list.add(new SondaggioC("chi voti?", new String[]{"opz1", "opz2"}));
		poll_list.add(new SondaggioC("chi è il miglior pilota?", new String[]{"opz1", "opz2"}));
		poll_list.add(new SondaggioC("quale colore è meglio?", new String[]{"opz1", "opz2"}));
		return poll_list;
	}

	@Override
	public ArrayList<SondaggioC> selectByCandidato(UtenteC candidatoInput) {
		ArrayList<SondaggioC> poll_list = new ArrayList<SondaggioC>();
		poll_list.add(new SondaggioC("chi voti?", new String[]{"opz1", "opz2"}));
		poll_list.add(new SondaggioC("chi è il miglior pilota?", new String[]{"opz1", "opz2"}));
		poll_list.add(new SondaggioC("quale colore è meglio?", new String[]{"opz1", "opz2"}));
		return poll_list;
	}

}
