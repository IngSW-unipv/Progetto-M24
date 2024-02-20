package it.unipv.ingsfw.opinione360.controller;

import java.awt.event.*;
import java.io.IOException;

import it.unipv.ingsfw.opinione360.exception.*;
import it.unipv.ingsfw.opinione360.view.ConsultazioniFrame;
import it.unipv.ingsfw.opinione360.view.LoginFrame;
import it.unipv.ingsfw.opinione360.view.LogoFrame;
import it.unipv.ingsfw.opinione360.view.RegistrazioneFrame;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.UtenteC;

/**
 * Controller che gestisce l'accesso alla piattaforma 
 * @see LogoFrame
 * @see LoginFrame
 * @see RegistrazioneFrame
 * @see VotoFrame
 * @see DomainFacade
 */
public class CLogRegController {

	private final LogoFrame lf;
	private final LoginFrame logf;
	private final RegistrazioneFrame rf;
	private final ConsultazioniFrame cf;
	private final DomainFacade df;
	private final MessaggioFrame mf;
	
	/**
	 * Costruttore parametrizzato
	 * @param lf schermata iniziale 
	 * @param logf schermata di login 
	 * @param rf schermata di registrazione
	 * @param vf schermata di voto
	 * @param df facciata che rappresenta il dominio del client
	 */
	public CLogRegController(LogoFrame lf, LoginFrame logf, RegistrazioneFrame rf, ConsultazioniFrame cf, DomainFacade df) {
		this.lf = lf;
		this.logf = logf;
		this.rf = rf;
		this.df = df;
		this.cf = cf;
		mf = MessaggioFrame.getInstance();
		setListeners();
	}

	/**
	 * Imposta i listener dei bottoni dell'interfaccia grafica
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
				String username = logf.getUserCampo().getText();
				String email = logf.getEmailCampo().getText();
				String password = new String(logf.getPassCampo().getPassword());
				try {
					df.login(new UtenteC(username, email, password));
					logf.setVisible(false);
					cf.setVisible(true);
				}                                                  
				catch(ServerException se) {
					mf.setTitle("Errore");
					mf.getMess().setText("Server al momento non raggiungibile.");
					mf.setVisible(true);
				}
				catch(IOException ioe) {
					mf.setTitle("Errore");
					mf.getMess().setText("Operazione di IO fallita.");
					mf.setVisible(true);
				}
			}
		};
		logf.getConfBottone().addActionListener(conf1Al);
		
		ActionListener conf2Al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = rf.getUserCampo().getText();
				String email = rf.getEmailCampo().getText();
				String password = new String(rf.getPassCampo().getPassword());
				String idSocietario = rf.getIdSocietarioCampo().getText();
				try {	
					df.signup(new UtenteC(username, email, password, idSocietario));
					rf.setVisible(false);
					cf.setVisible(true);
				}                                                  
				catch(EmptyFieldException efe) {
					mf.setTitle("Errore");
					mf.getMess().setText("C'è almeno un campo vuoto.");
					mf.setVisible(true);
				}
				catch(WrongEmailExpressionException weee) {
					mf.setTitle("Errore");
					mf.getMess().setText("L'email non è scritta correttamente. \nUsa una forma del tipo: \nnome@dominio");
					mf.setVisible(true);
				}
				catch(WrongPasswordExpressionException wpee) {
					mf.setTitle("Errore");
					mf.getMess().setText("La password non è scritta correttamente. \nControlla se la password ha almeno: \n1 numero, 1 lowercase, 1 uppercase, 1 simbolo tra @#$%^&+=, nessuno spazio vuoto, tra i 5 e i 10 caratteri");
					mf.setVisible(true);
				}
				catch(ServerException se) {
					mf.setTitle("Errore");
					mf.getMess().setText("Server al momento non raggiungibile.");
					mf.setVisible(true);
				}
			}
		};
		rf.getConfBottone().addActionListener(conf2Al);
	}

}
