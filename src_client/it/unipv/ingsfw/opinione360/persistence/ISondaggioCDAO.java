package it.unipv.ingsfw.opinione360.persistence;

import java.util.ArrayList;

import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.model.UtenteC;

public interface ISondaggioCDAO {
	public ArrayList<SondaggioC> selectById(SondaggioC pollInput);
	public ArrayList<SondaggioC> selectByUser(UtenteC utenteInput);
	public ArrayList<SondaggioC> selectByCandidato(UtenteC candidatoInput);
}
