package test.it.unipv.ingsfw.opinione360.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.model.Utente;
import it.unipv.ingsfw.opinione360.model.IUtenteC;
import it.unipv.ingsfw.opinione360.model.OpzioneC;
import it.unipv.ingsfw.opinione360.model.Sondaggio;
import it.unipv.ingsfw.opinione360.model.UtenteC;
import it.unipv.ingsfw.opinione360.persistence.PersistenceFacadeC;
import it.unipv.ingsfw.opinione360.controller.VotoController;
import it.unipv.ingsfw.opinione360.exception.ServerException;
import it.unipv.ingsfw.opinione360.model.Amministratore;
import it.unipv.ingsfw.opinione360.model.AmministratoreC;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.view.voto.*;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;

public class VotoControllerTest {

	ConsultazioniFrame cf;
	VotoFrame vf;
	MessaggioFrame mf;
	RisultatiFrame rf;
	StoricoFrame sf;
	DomainFacade df;
	VotoController vc;
	PersistenceFacadeC pf;
	SondaggioC consultazione;
	OpzioneC opzione;
	UtenteC u1;
	UtenteC u2;
	UtenteC u3;
	UtenteC u4;
	AmministratoreC amm;
	
	@Before
	public void inTest() {
		cf = new ConsultazioniFrame();
		vf = new VotoFrame();
		rf = new RisultatiFrame();
		sf = new StoricoFrame();
		mf = MessaggioFrame.getInstance();
		df = DomainFacade.getInstance();
		vc = new VotoController(cf, vf, rf, sf, df);
		df.setSessioneUtente(new UtenteC("Luigi")); 
		u1 = new UtenteC("n", "o");
		u2 = new UtenteC("g", "e");
		u3 = new UtenteC("n", "o");
		u4 = new UtenteC("g", "e");
		amm = new AmministratoreC("g", "e");
		ArrayList<UtenteC> v = new ArrayList<>();
		v.add(u1);
		v.add(u2);
        v.add(u3);
        v.add(u4);
		ArrayList<UtenteC> c = new ArrayList<>();
		c.add(u4);
		String[] o = {"Michael Schumacher", "Lewis Hamilton", "Ayrton Senna", "Sebastian Vettel", "Alain Prost"};
		Calendar data1 = new GregorianCalendar(2024, Calendar.JANUARY,1, 0, 0,0);
        Calendar data2 = new GregorianCalendar(2024, Calendar.FEBRUARY,30, 0, 0,0);
        consultazione = new SondaggioC("Miglior pilota di Formula 1?", v, c, o, data1, data2);
	}
	
	@Test
	public void testIndBottoneOK() {
		vf.getIndBottone().doClick();
		assertFalse(vf.isVisible());
		assertTrue(cf.isVisible());
	}
	
	@Test
	public void testConsBottoniOK() {
		ArrayList<SondaggioC> consultazioni = new ArrayList<>();
		consultazioni.add(consultazione);
		cf.setConsBottoni(consultazioni);
		cf.getConsBottoni().get(0).doClick();
		assertFalse(cf.isVisible());
		assertTrue(vf.isVisible());
	}
		
	@Test
	public void testConfBottoneOK() { 
		vf.setOpzioniBottoni(consultazione.getAllOpzioni());
		vf.getOpzioniBottoni().get(0).setSelected(true);
		vf.getConfBottone().doClick();
		try {
			opzione = pf.getChoice(consultazione).get(0);
		} catch (SQLException e) {
			fail();
		}
		assertEquals(opzione, consultazione.getOpzioni()[0]);
		assertTrue(mf.isVisible());
		assertEquals(mf.getMess().getText(), "Il tuo voto è stato registrato!");
	}
	
	@Test
	public void testConfBottoneKO() {
		vf.getConfBottone().doClick();
		assertThrows(IllegalArgumentException.class, () -> df.vota(null, consultazione));
	}
	
}
