package test.it.unipv.ingsfw.opinione360.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.opinione360.controller.VetrinaController;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.view.voto.VotoFrame;
import it.unipv.ingsfw.opinione360.view.popup.SceltaFileFrame;
import it.unipv.ingsfw.opinione360.view.vetrina.*;

public class VetrinaControllerTest {
	
	VotoFrame vf;
	VetrinaFrame vetf;
	AreaContenutoFrame acf;
	SceltaFileFrame sff;
	DomainFacade df;
	VetrinaController cvc; 
	
	@Before
	public void inTest() {
		vf = new VotoFrame();
		vetf = new VetrinaFrame();
		acf = new AreaContenutoFrame();
		sff = new SceltaFileFrame();
		df = DomainFacade.getInstance();
		cvc = new VetrinaController(vf, vetf, acf, sff, df); 
	}

	@Test
	public void testAreeBottoniOK() {
		vf.getVetrinaBottone().doClick();
		vetf.getAreeBottoni().get(0).doClick();
		assertFalse(vetf.isVisible());
		assertTrue(acf.isVisible());
	}
	
	@Test
	public void testVetBottoneOK() {
		vf.getVetrinaBottone().doClick();
		assertFalse(vf.isVisible());
		assertTrue(vetf.isVisible());
	}

	@Test
	public void testIndVetBottoneOK() {
		vetf.getIndBottone().doClick();
		assertFalse(vetf.isVisible());
		assertTrue(vf.isVisible());
	}
	
	@Test
	public void testIndAcBottoneOK() {
		acf.getIndBottone().doClick();
		assertFalse(acf.isVisible());
		assertTrue(vetf.isVisible());
	}
	
}
