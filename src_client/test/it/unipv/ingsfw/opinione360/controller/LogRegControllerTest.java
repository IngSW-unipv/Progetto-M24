package test.it.unipv.ingsfw.opinione360.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.opinione360.controller.LogRegController;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.UtenteC;
import it.unipv.ingsfw.opinione360.view.logreg.*;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;
import it.unipv.ingsfw.opinione360.view.voto.ConsultazioniFrame;

public class LogRegControllerTest {

	LogoFrame lf;
	LoginFrame logf;
	RegistrazioneFrame regf;
	ConsultazioniFrame cf;
	MessaggioFrame mf;
	DomainFacade df;
	LogRegController clrc;
	

	@Before
	public void inTest() {
		lf = new LogoFrame();
		logf = new LoginFrame();
		regf = new RegistrazioneFrame();
		cf = new ConsultazioniFrame();
		mf = MessaggioFrame.getInstance();
		df = DomainFacade.getInstance();
		clrc = new LogRegController(lf, logf, regf, cf, df, null, null);
	}

	@Test
	public void testEnBottoneOK() {
		lf.getEnBottone().doClick();
		assertFalse(lf.isVisible());
		assertTrue(logf.isVisible());
	}
	
	@Test
	public void testRegBottoneOK() {
		logf.getRegBottone().doClick();
		assertFalse(logf.isVisible());
		assertTrue(regf.isVisible());
	}
	
	@Test
	public void testIndRegBottoneOK() {
		regf.getIndBottone().doClick();
		assertFalse(regf.isVisible());
		assertTrue(logf.isVisible());
	}
	
	@Test
	public void testConfLogBottoneOK() {
		logf.getUsernameCampo().setText("Luigi");
		logf.getPasswordCampo().setText("Luigi#456");
		logf.getConfBottone().doClick();
		assertFalse(logf.isVisible());
		assertTrue(cf.isVisible());
	}
	
	@Test
	public void testConfLogBottoneKO() {
		logf.getConfBottone().doClick();
		assertThrows(IllegalArgumentException.class, () -> df.signup(new UtenteC("Luigi", "luigi@gmail.com", "Luigi#456", "")));
		assertThrows(IllegalArgumentException.class, () -> df.signup(new UtenteC("Luigi", "luigi@gmail.com", "Luigi456", "123")));
	}
	
	@Test
	public void testConfRegBottoneOK() {
		regf.getUsernameCampo().setText("Luigi");
		regf.getEmailCampo().setText("luigi@gmail.com");
		regf.getPasswordCampo().setText("Luigi#456");
		regf.getIdSocietarioCampo().setText("123");
		regf.getConfBottone().doClick();
		assertFalse(regf.isVisible());
		assertTrue(logf.isVisible());
	}
	
	@Test
	public void testConfRegBottoneKO() {
		regf.getConfBottone().doClick();
		assertThrows(IllegalArgumentException.class, () -> df.signup(new UtenteC("Luigi", "luigi@gmail.com", "Luigi#456", "")));
		assertThrows(IllegalArgumentException.class, () -> df.signup(new UtenteC("Luigi", "luigi@gmail.commo", "Luigi#456", "123")));
		assertThrows(IllegalArgumentException.class, () -> df.signup(new UtenteC("Luigi", "luigi@gmail.com", "Luigi456", "123")));
	}

}
