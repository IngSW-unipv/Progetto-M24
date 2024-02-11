package it.unipv.ingsfw.opinione360.controller;

import java.awt.event.*;

import it.unipv.ingsfw.opinione360.exception.*;
import it.unipv.ingsfw.opinione360.view.ConsultazioniFrame;
import it.unipv.ingsfw.opinione360.view.LoginFrame;
import it.unipv.ingsfw.opinione360.view.LogoFrame;
import it.unipv.ingsfw.opinione360.view.RegistrazioneFrame;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;
import it.unipv.ingsfw.opinione360.model.DomainFacade;

/**
 * Controller che gestisce l'accesso alla piattaforma 
 * @see LogoFrame
 * @see LoginFrame
 * @see VotoFrame
 * @see DomainFacade
 */
public class CLogRegController {

	private final LogoFrame lf;
	private final LoginFrame logf;
	private final RegistrazioneFrame rf;
	private final ConsultazioniFrame cf;
	private final DomainFacade df;
	
	/**
	 * Costruttore parametrizzato
	 * @param lf schermata iniziale 
	 * @param logf schermata di login 
	 * @param rf schermata di registrazione
	 * @param vf schermata di voto
	 * @param df 
	 */
	public CLogRegController(LogoFrame lf, LoginFrame logf, RegistrazioneFrame rf, ConsultazioniFrame cf, DomainFacade df) {
		this.lf = lf;
		this.logf = logf;
		this.rf = rf;
		this.df = df;
		this.cf = cf;
		setListeners();
	}

	/**
	 * Imposta i listener dei bottoni dell'interfaccia grafica
	 * @throws EmptyFieldException
	 */
	private void setListeners() {
		
		ActionListener eAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lf.setVisible(false);
				logf.setVisible(true);
			}
		};
		lf.getEnBottone().addActionListener(eAl);
		
		ActionListener regAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logf.setVisible(false);
				rf.setVisible(true);
			}
		};
		logf.getRegBottone().addActionListener(regAl);
		
		ActionListener indAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rf.setVisible(false);
				logf.setVisible(true);
			}
		};
		rf.getIndBottone().addActionListener(indAl); 
		
		ActionListener conf1Al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(logf.getUserCampo().getText().equals("") || logf.getEmailCampo().getText().equals("") || logf.getPassCampo().getPassword().equals(""))
						throw new EmptyFieldException();
					logf.setVisible(false);
					cf.setVisible(true);
				}                                                  
				catch(EmptyFieldException er) {
					MessaggioFrame mf = MessaggioFrame.getInstance();
					mf.setTitle("Errore");
					mf.getLabel().setText("C'è almeno un campo vuoto.");
					mf.setVisible(true);
				}
			}
		};
		logf.getConfBottone().addActionListener(conf1Al);
		
		ActionListener conf2Al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(rf.getUserCampo().getText().equals("") || rf.getEmailCampo().getText().equals("") || rf.getPassCampo().getPassword().equals("") || rf.getIdCampo().getText().equals(""))
						throw new EmptyFieldException();
					rf.setVisible(false);
					cf.setVisible(true);
				}                                                  
				catch(EmptyFieldException er) {
					MessaggioFrame mf = MessaggioFrame.getInstance();
					mf.setTitle("Errore");
					mf.getLabel().setText("C'è almeno un campo vuoto");
					mf.setVisible(true);
				}
			}
		};
		rf.getConfBottone().addActionListener(conf2Al);
	}

}
