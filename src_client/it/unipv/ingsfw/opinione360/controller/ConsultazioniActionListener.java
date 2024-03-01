package it.unipv.ingsfw.opinione360.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;

import it.unipv.ingsfw.opinione360.exception.ServerException;
import it.unipv.ingsfw.opinione360.exception.UserMissingAccessException;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.model.IConsultazioneC;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;
import it.unipv.ingsfw.opinione360.view.voto.ConsultazioneBottone;
import it.unipv.ingsfw.opinione360.view.voto.ConsultazioniFrame;
import it.unipv.ingsfw.opinione360.view.voto.RisultatiFrame;
import it.unipv.ingsfw.opinione360.view.voto.VotoFrame;

/**
 * Listener che ascolta quando un utente esegue il login o la registrazione
 * @see ConsultazioniFrame
 * @see VotoFame
 * @see RisultatiFrame
 * @see DomainFacade
 */
public class ConsultazioniActionListener implements ActionListener {
	
	private final ConsultazioniFrame cf;
	private final VotoFrame vf;
	private final RisultatiFrame rf;
	private final MessaggioFrame mf;
	private final DomainFacade df;
	private IConsultazioneC cons;

	/**
	 * Costruttore parametrizzato
	 * @param cf schermata di scelta della consultazione
	 * @param vf schermata del voto
	 * @param rf schermata dei risultati
	 * @param df facciata che rappresenta il dominio del client
	 */
	public ConsultazioniActionListener(ConsultazioniFrame cf, VotoFrame vf, RisultatiFrame rf, DomainFacade df) {
		this.cf = cf;
		this.vf = vf;
		this.rf = rf;
		mf = MessaggioFrame.getInstance();
		this.df = df;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ConsultazioneBottone cb = (ConsultazioneBottone) e.getSource();
			cons = df.getConsultazione(new SondaggioC(cb.getId()));
			if(cons.isExpired()) 
				manageActions1();
			else
				manageActions2(cons);
		}
		catch(UserMissingAccessException | ServerException er) {
			mf.setTitle("Errore");
			mf.getMess().setText(er.getMessage());
			mf.setVisible(true);
		}
		catch(IOException ioe) {
			mf.setTitle("Errore");
			mf.getMess().setText("Operazione non riuscita.");
			mf.setVisible(true);
		}
	}

	private void manageActions1() {
		mf.setTitle("Avviso");
		mf.getMess().setText("La consultazione è terminata. Ecco i risultati.");
		rf.setPieChart(cons.getRisultati());
		vf.setVisible(false);
		mf.setVisible(true);
		rf.setVisible(true);
	}

	private void manageActions2(IConsultazioneC cons) {
		vf.setQuesitoTit(cons.getQuesito());
		vf.setOpzioniBottoni(cons.getAllOpzioni());
		vf.setConsultazione(cons);
		cf.setVisible(false);
		vf.setVisible(true);
	}

}


