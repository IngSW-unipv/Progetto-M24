package it.unipv.ingsfw.opinione360;

import javax.swing.JFrame;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import it.unipv.ingsfw.opinione360.controller.*;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.view.creaconsultazione.CreaConsultazioniFrame;
import it.unipv.ingsfw.opinione360.view.creaconsultazione.RiepilogoFrame;
import it.unipv.ingsfw.opinione360.view.logreg.*;
import it.unipv.ingsfw.opinione360.view.popup.SceltaFileFrame;
import it.unipv.ingsfw.opinione360.view.voto.StoricoFrame;
import it.unipv.ingsfw.opinione360.view.voto.*;
import it.unipv.ingsfw.opinione360.view.vetrina.*;

/**
 * Classe che permette di avviare il software Opinione360
 */
public class Opinione360AppC {

	public static void main(String[] args) {
		FlatMacLightLaf.setup();
		LogoFrame lf = new LogoFrame();
		lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginFrame logf = new LoginFrame();
		logf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RegistrazioneFrame regf = new RegistrazioneFrame();
		regf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ConsultazioniFrame cf = new ConsultazioniFrame();
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VotoFrame vf = new VotoFrame();
		vf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RisultatiFrame rf = new RisultatiFrame();
		rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StoricoFrame sf = new StoricoFrame();
		sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VetrinaFrame vetf = new VetrinaFrame();
		vetf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AreaContenutoFrame acf = new AreaContenutoFrame();
		acf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SceltaFileFrame sff = new SceltaFileFrame();
		sff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CreaConsultazioniFrame ccf = new CreaConsultazioniFrame();
		ccf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RiepilogoFrame verf = new RiepilogoFrame();
		verf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DomainFacade df = DomainFacade.getInstance(); 
		
		LogRegController clr = new LogRegController(lf, logf, regf, cf, df, vf, rf);
		VotoController cv = new VotoController(cf, vf, rf, sf, df);
		VetrinaController cvet = new VetrinaController(vf, vetf, acf, sff, df); 
		CreaConsultazioniController ccc = new CreaConsultazioniController(cf, ccf, verf, df);
		
		lf.setVisible(true);
	}

}
