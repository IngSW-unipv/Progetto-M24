package it.unipv.ingsfw.opinione360.controller;

import javax.swing.JFrame;

import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.view.*;

/**
 * Classe che permette di avviare il software Opinione360
 */
public class Opinione360App {

	public static void main(String[] args) {
		LogoFrame lf = new LogoFrame();
		lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginFrame logf = new LoginFrame();
		logf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RegistrazioneFrame rf = new RegistrazioneFrame();
		rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ConsultazioniFrame cf = new ConsultazioniFrame();
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VotoFrame vf = new VotoFrame();
		vf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VetrinaFrame vetf = new VetrinaFrame();
		vetf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AreaCandidatoFrame acf = new AreaCandidatoFrame();
		acf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DomainFacade df = DomainFacade.getInstance(); 
		CLogRegController clr = new CLogRegController(lf, logf, rf, cf, df);
		CVotoController cv = new CVotoController(logf, cf, vf, df);
		CVetrinaController cvet = new CVetrinaController(vf, vetf, acf, df); 
		lf.setVisible(true);
	}

}
