package it.unipv.ingsfw.opinione360.controller;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import it.unipv.ingsfw.opinione360.exception.*;
import it.unipv.ingsfw.opinione360.view.logreg.*;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;
import it.unipv.ingsfw.opinione360.view.voto.ConsultazioneBottone;
import it.unipv.ingsfw.opinione360.view.voto.ConsultazioniFrame;
import it.unipv.ingsfw.opinione360.view.voto.RisultatiFrame;
import it.unipv.ingsfw.opinione360.view.voto.VotoFrame;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.model.UtenteC;

/**
 * Controller che gestisce l'accesso alla piattaforma 
 * @see LogoFrame
 * @see LoginFrame
 * @see RegistrazioneFrame 
 * @see ConsultazioniFrame
 * @see VotoFrame
 * @see RisultatiFrame
 * @see MessaggioFrame
 * @see DomainFacade
 */
public class LogRegController {

	private final LogoFrame lf;
	private final LoginFrame logf;
	private final RegistrazioneFrame regf;
	private final ConsultazioniFrame cf;
	private final VotoFrame vf;
	private final RisultatiFrame rf;
	private final DomainFacade df;
	private final MessaggioFrame mf;
	
	/**
	 * Costruttore parametrizzato
	 * @param lf schermata iniziale 
	 * @param logf schermata di login 
	 * @param regf schermata di registrazione
	 * @param vf schermata del voto
	 * @param rf schermata dei risutati
	 * @param cf schermata di scelta della consultazione
	 * @param df facciata che rappresenta il dominio del client
	 */
	public LogRegController(LogoFrame lf, LoginFrame logf, RegistrazioneFrame regf, ConsultazioniFrame cf, DomainFacade df, VotoFrame vf, RisultatiFrame rf) {
		this.lf = lf;
		this.logf = logf;
		this.regf = regf;
		this.cf = cf;
		this.vf = vf;
		this.rf = rf;
		mf = MessaggioFrame.getInstance();
		this.df = df;
		setListeners();
	}

	/**
	 * Imposta i listener dei bottoni dell'interfaccia grafica per il login e per la registrazione
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
				regf.setVisible(true);
			}
		};
		logf.getRegBottone().addActionListener(regAl);
		
		ActionListener indRegAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				regf.setVisible(false);
				logf.setVisible(true);
			}
		};
		regf.getIndBottone().addActionListener(indRegAl); 
		
		ActionListener confLogAl = new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = logf.getUsernameCampo().getText();
				String password = new String(logf.getPasswordCampo().getPassword());
				UtenteC utente = new UtenteC(username, password);
				ArrayList<SondaggioC> consultazioniElenco = new ArrayList<>();
				ArrayList<String> consDesc = new ArrayList<>();
				manageActions(utente, consultazioniElenco, consDesc);
			}

			private void manageActions(UtenteC utente, ArrayList<SondaggioC> consultazioniElenco, ArrayList<String> consDesc) {
				try {
					df.login(utente);
					consultazioniElenco = df.getListaConsultazioni();
					cf.setConsBottoni(consultazioniElenco);
					ConsultazioniActionListener cal = new ConsultazioniActionListener(cf, vf, rf, df);
					for(ConsultazioneBottone cb : cf.getConsBottoni())
						cb.addActionListener(cal);
					logf.setVisible(false);
					cf.setVisible(true);
				}                                                  
				catch(IllegalArgumentException | ServerException e) {
					mf.setTitle("Errore");
					mf.getMess().setText(e.getMessage());
					mf.setVisible(true);
				}
				catch(IOException ioe) {
					mf.setTitle("Errore");
					mf.getMess().setText("Operazione non riuscita.");
					mf.setVisible(true);			
				}	
			}
		};
		logf.getConfBottone().addActionListener(confLogAl);
		
		ActionListener confRegAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = regf.getUsernameCampo().getText();
				String email = regf.getEmailCampo().getText();
				String password = new String(regf.getPasswordCampo().getPassword());
				String idSocietario = regf.getIdSocietarioCampo().getText();
				UtenteC utente = new UtenteC(username, email, password, idSocietario);
				ArrayList<SondaggioC> consultazioniElenco = new ArrayList<>();
				ArrayList<String> consDesc = new ArrayList<>();
				manageActions(utente, consultazioniElenco, consDesc);
			}

			private void manageActions(UtenteC utente, ArrayList<SondaggioC> consultazioniElenco, ArrayList<String> consDesc) {
				try {	
					df.signup(utente);
					regf.setVisible(false);
					logf.setVisible(true);
				}                                                  
				catch(AlreadyRegisteredUserException | IllegalArgumentException | ServerException e) {
					mf.setTitle("Errore");
					mf.getMess().setText(e.getMessage());
					mf.setVisible(true);
				}
				catch(IOException ioe) {
					mf.setTitle("Errore");
					mf.getMess().setText("Operazione non riuscita.");
					mf.setVisible(true);
				}
			}
		};
		regf.getConfBottone().addActionListener(confRegAl);
	}

}
