package test.it.unipv.ingsfw.opinione360.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.opinione360.controller.CVetrinaController;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.view.AreaContenutoFrame;
import it.unipv.ingsfw.opinione360.view.VetrinaFrame;
import it.unipv.ingsfw.opinione360.view.VotoFrame;

public class CVetrinaControllerTest {
	
	VotoFrame vf;
	VetrinaFrame vetf;
	AreaContenutoFrame acf;
	DomainFacade df;
	CVetrinaController cvc; 
	
	@Before
	public void inTest() {
		vf = new VotoFrame();
		vetf = new VetrinaFrame();
		acf = new AreaContenutoFrame();
		df = DomainFacade.getInstance();
		cvc = new CVetrinaController(vf, vetf, acf, df); 
	}

	@Test
	public void testAreeBottoni() {
		vf.getVetBottone().doClick();
		vetf.getAreeBottoni().get(0).doClick();
		assertFalse(vetf.isVisible());
		assertTrue(acf.isVisible());
	}
	
	@Test
	public void testVetBottone() {
		vf.getVetBottone().doClick();
		assertFalse(vf.isVisible());
		assertTrue(vetf.isVisible());
	}

	@Test
	public void testInd1Bottone() {
		vetf.getIndBottone().doClick();
		assertFalse(vetf.isVisible());
		assertTrue(vf.isVisible());
	}
	
	@Test
	public void testInd2Bottone() {
		acf.getIndBottone().doClick();
		assertFalse(acf.isVisible());
		assertTrue(vetf.isVisible());
	}
	
}
