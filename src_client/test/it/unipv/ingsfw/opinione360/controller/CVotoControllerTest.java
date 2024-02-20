package test.it.unipv.ingsfw.opinione360.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.opinione360.controller.CVotoController;
import it.unipv.ingsfw.opinione360.exception.EmptyFieldException;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.view.ConsultazioniFrame;
import it.unipv.ingsfw.opinione360.view.LoginFrame;
import it.unipv.ingsfw.opinione360.view.VotoFrame;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;

public class CVotoControllerTest {

	LoginFrame logf;
	ConsultazioniFrame cf;
	VotoFrame vf;
	DomainFacade df;
	CVotoController cvc;
	SondaggioC s;
	MessaggioFrame mf;
	
	@Before
	public void inTest() {
		logf = new LoginFrame();
		cf = new ConsultazioniFrame();
		vf = new VotoFrame();
		df = DomainFacade.getInstance();
		cvc = new CVotoController(logf, cf, vf, df);
		s = df.getSondaggio();
		mf = MessaggioFrame.getInstance();
	}
	
	@Test
	public void testInd1Bottone() {
		cf.getIndBottone().doClick();
		assertFalse(cf.isVisible());
		assertTrue(logf.isVisible());
	}
	
	@Test
	public void testConsBottoni () {
		cf.getConsBottoni().get(0).doClick();
		assertFalse(cf.isVisible());
		assertTrue(vf.isVisible());
	}
	
	@Test
	public void testInd2Bottone() {
		vf.getIndBottone().doClick();
		assertFalse(vf.isVisible());
		assertTrue(cf.isVisible());
	}
	
	@Test
	public void testConfBottoneEq1() { 
		vf.setOpBottoni(new String[] {"0"});
		vf.getOpBottoni().get(0).setSelected(true);
		vf.getConfBottone().doClick();
		assertTrue(mf.isVisible());
		assertEquals(mf.getMess().getText(), "Il tuo voto è stato registrato!");
	}
	
	@Test
	public void testConfBottoneEq2() {
		vf.getConfBottone().doClick();
		assertThrows(EmptyFieldException.class, () -> df.vota(s, new ArrayList<Integer>()));
	}
	
}
