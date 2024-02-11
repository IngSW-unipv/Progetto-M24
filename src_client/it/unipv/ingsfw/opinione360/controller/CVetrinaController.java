package it.unipv.ingsfw.opinione360.controller;

import java.awt.event.*;
import javax.swing.JButton;

import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.view.AreaCandidatoFrame;
import it.unipv.ingsfw.opinione360.view.VetrinaFrame;
import it.unipv.ingsfw.opinione360.view.VotoFrame;

/**
 * Controller che gestisce la vetrina
 * @see VotoFrame
 * @see VetrinaFrame
 * @see DomainFacade
 */
public class CVetrinaController {
	
	private final VotoFrame vf;
	private final VetrinaFrame vetf;
	private final DomainFacade df;
	private final AreaCandidatoFrame acf;

	/**
	 * Costruttore parametrizzato
	 * @param vf schermata di voto
	 * @param vetf schermata della vetrina
	 * @param df 
	 */
	public CVetrinaController(VotoFrame vf, VetrinaFrame vetf, AreaCandidatoFrame acf, DomainFacade df) {
		this.vf = vf;
		this.vetf = vetf;
		this.acf = acf;
		this.df = df;
		setListeners();
	}

	/**
	 * Imposta i listener dei bottoni dell'interfaccia grafica
	 */
	private void setListeners() {
		ActionListener areeAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vetf.setVisible(false);
				acf.setVisible(true);
			}
		};
		
		ActionListener vetAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vf.setVisible(false);
				SondaggioC s = df.getSondaggio();
				vetf.setAreeBottoni(s.getOpzioni());
				for(JButton b : vetf.getAreeBottoni())
					b.addActionListener(areeAl);
				vetf.setVisible(true);
			}
		};
		vf.getVetBottone().addActionListener(vetAl);
		
		ActionListener ind1Al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vetf.setVisible(false);
				vf.setVisible(true);
			}
		};
		vetf.getIndBottone().addActionListener(ind1Al);
		
		ActionListener ind2Al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acf.setVisible(false);
				vetf.setVisible(true);
			}
		};
		acf.getIndBottone().addActionListener(ind2Al);
	}
	
}
