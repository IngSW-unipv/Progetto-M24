package it.unipv.ingsfw.opinione360.controller;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import it.unipv.ingsfw.opinione360.exception.EmptyFieldException;
import it.unipv.ingsfw.opinione360.exception.UserMissingAccessException;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.view.ConsultazioniFrame;
import it.unipv.ingsfw.opinione360.view.LoginFrame;
import it.unipv.ingsfw.opinione360.view.VotoFrame;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;

/**
 * Controller che gestisce le operazioni di voto 
 * @see LoginFrame
 * @see ConsultazioniFrame
 * @see VotoFrame
 * @see DomainFacade
 */
public class CVotoController {

	private final LoginFrame logf;
	private final ConsultazioniFrame cf;
	private final VotoFrame vf;
	private final DomainFacade df;
	private final MessaggioFrame mf;
	
	/**
	 * Costruttore parametrizzato
	 * @param logf schermata di login 
	 * @param cf schermata di scelta della consultazione
	 * @param vf schermata di voto
	 * @param df facciata che rappresenta il dominio del client
	 */
	public CVotoController(LoginFrame logf, ConsultazioniFrame cf, VotoFrame vf, DomainFacade df) {
		this.logf = logf;
		this.cf = cf;
		this.vf = vf;
		this.df = df;
		mf = MessaggioFrame.getInstance();
		setListeners();
	}

	/**
	 * Imposta i listener dei bottoni dell'interfaccia grafica
	 */
	private void setListeners() {
		ActionListener ind1Al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cf.setVisible(false);
				logf.setVisible(true);
			}
		};
		cf.getIndBottone().addActionListener(ind1Al);
		
		ActionListener consAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// logica per verificare che l'utente può accedere
					SondaggioC s = df.getSondaggio();
					vf.setDescTit(s.getDescrizione());
					vf.setOpBottoni(s.getOpzioni());
					cf.setVisible(false);
					vf.setVisible(true);
				}
				catch(UserMissingAccessException err) {
					mf.setTitle("Errore");
					mf.getMess().setText("L'utente inserito non esiste.");
					mf.setVisible(true);
				}
			}
		};
		for(JButton b : cf.getConsBottoni())
			b.addActionListener(consAl);
		
		ActionListener ind2Al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vf.setVisible(false);
				cf.setVisible(true);
			}
		};
		vf.getIndBottone().addActionListener(ind2Al);
		
		ActionListener confAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<JRadioButton> b = vf.getOpBottoni();
				ArrayList<Integer> id_opzioni = new ArrayList<Integer>();
				for(int i = 0; i < b.size(); i++)                              
					if(b.get(i).isSelected())
						id_opzioni.add(i);
				try {
					df.vota(df.getSondaggio(), id_opzioni);
					mf.setTitle("Esito positivo");
					mf.getMess().setText("Il tuo voto è stato registrato!");
					mf.setVisible(true);
				}
				catch(EmptyFieldException er) {
					mf.setTitle("Errore");
					mf.getMess().setText("Seleziona almeno un'opzione.");           
					mf.setVisible(true);
				}
			}
		};
		vf.getConfBottone().addActionListener(confAl);
	}
	
}
