package test.it.unipv.ingsfw.opinione360.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.opinione360.controller.CLogRegController;
import it.unipv.ingsfw.opinione360.exception.EmptyFieldException;
import it.unipv.ingsfw.opinione360.exception.WrongEmailExpressionException;
import it.unipv.ingsfw.opinione360.exception.WrongPasswordExpressionException;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.UtenteC;
import it.unipv.ingsfw.opinione360.view.ConsultazioniFrame;
import it.unipv.ingsfw.opinione360.view.LoginFrame;
import it.unipv.ingsfw.opinione360.view.LogoFrame;
import it.unipv.ingsfw.opinione360.view.RegistrazioneFrame;

public class CLogRegControllerTest {

	LogoFrame lf;
	LoginFrame logf;
	RegistrazioneFrame rf;
	ConsultazioniFrame cf;
	DomainFacade df;
	CLogRegController clrc;

	@Before
	public void inTest() {
		lf = new LogoFrame();
		logf = new LoginFrame();
		rf = new RegistrazioneFrame();
		cf = new ConsultazioniFrame();
		df = DomainFacade.getInstance();
		clrc = new CLogRegController(lf, logf, rf, cf, df);
	}

	@Test
	public void testEnBottone() {
		lf.getEnBottone().doClick();
		assertFalse(lf.isVisible());
		assertTrue(logf.isVisible());
	}
	
	@Test
	public void testRegBottone() {
		logf.getRegBottone().doClick();
		assertFalse(logf.isVisible());
		assertTrue(rf.isVisible());
	}
	
	@Test
	public void testIndBottone() {
		rf.getIndBottone().doClick();
		assertFalse(rf.isVisible());
		assertTrue(logf.isVisible());
	}
	
	@Test
	public void testConf1BottoneEq1() {
		logf.getUserCampo().setText("Luigi");
		logf.getEmailCampo().setText("luigi@gmail.com");
		logf.getPassCampo().setText("Luigi#456");
		logf.getConfBottone().doClick();
		assertFalse(logf.isVisible());
		assertTrue(cf.isVisible());
	}
	
	@Test
	public void testConf1BottoneEq2() {
		logf.getConfBottone().doClick();
		// Verifica dell'eccezione che l'utente inserito non è registrato
	}
	
	@Test
	public void testConf2BottoneEq1() {
		rf.getUserCampo().setText("Luigi");
		rf.getEmailCampo().setText("luigi@gmail.com");
		rf.getPassCampo().setText("Luigi#456");
		rf.getIdSocietarioCampo().setText("123");
		rf.getConfBottone().doClick();
		assertFalse(rf.isVisible());
		assertTrue(cf.isVisible());
	}
	
	@Test
	public void testConf2BottoneEq2() {
		rf.getConfBottone().doClick();
		assertThrows(EmptyFieldException.class, () -> df.signup(new UtenteC("Luigi", "luigi@gmail.com", "Luigi#456", "")));
		assertThrows(WrongEmailExpressionException.class, () -> df.signup(new UtenteC("Luigi", "luigi@gmail.commo", "Luigi#456", "123")));
		assertThrows(WrongPasswordExpressionException.class, () -> df.signup(new UtenteC("Luigi", "luigi@gmail.com", "Luigi456", "123")));
	}

}
