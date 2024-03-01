package it.unipv.ingsfw.opinione360.controller;

import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.ingsfw.opinione360.exception.ConsultationExpiredException;
import it.unipv.ingsfw.opinione360.exception.ServerException;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.IConsultazioneC;
import it.unipv.ingsfw.opinione360.model.OpzioneC;
import it.unipv.ingsfw.opinione360.persistence.PersistenceFacadeC;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;
import it.unipv.ingsfw.opinione360.view.voto.*;

/**
 * Controller che gestisce le operazioni di voto 
 * @see ConsultazioniFrame
 * @see VotoFrame
 * @see RisultatiFrame
 * @see MessaggioFrame
 * @see DomainFacade
 */
public class VotoController {

	private final ConsultazioniFrame cf;
	private final VotoFrame vf;
	private final RisultatiFrame rf;
	private final StoricoFrame sf;
	private final MessaggioFrame mf;
	private final DomainFacade df;
	private final PersistenceFacadeC pf;
	private IConsultazioneC cons;
	
	/**
	 * Costruttore parametrizzato
	 * @param cf schermata di scelta della consultazione
	 * @param vf schermata di voto
	 * @param rf schermata che visualizza i risultati della consultazione
	 * @param df facciata che rappresenta il dominio del client
	 */
	public VotoController(ConsultazioniFrame cf, VotoFrame vf, RisultatiFrame rf, StoricoFrame sf, DomainFacade df) {
		this.cf = cf;
		this.vf = vf;
		this.rf = rf;
		this.sf = sf;
		mf = MessaggioFrame.getInstance();
		this.df = df;	
		pf = PersistenceFacadeC.getInstance();
		setListeners();
	}

	private void setListeners() {
		ActionListener indVotoAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vf.setVisible(false);
				cf.setVisible(true);
			}
		};
		vf.getIndBottone().addActionListener(indVotoAl);
		
		ActionListener confAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int l = 0;
				int i = 0;
				for(OpzioneBottone rb : vf.getOpzioniBottoni())
					if(rb.isSelected()) 
						l++;
				OpzioneC[] voti = new OpzioneC[l];
				for(OpzioneBottone rb : vf.getOpzioniBottoni())
					if(rb.isSelected()) {
						voti[i] = rb.getOpzione();
						i++;
					}		
				cons = vf.getConsultazione();
				manageActions(cons, voti);
			}

			private void manageActions(IConsultazioneC cons, OpzioneC[] voti) {
				try {
					df.vota(voti, cons);
					for(int i = 0; i < voti.length; i++)
						pf.insertChoice(voti[i], cons, df.getSessioneUtente());
					mf.setTitle("Esito positivo");
					mf.getMess().setText("Voto effettuato!");
					mf.setVisible(true);
				}
				catch(IllegalArgumentException | ServerException er) {
					mf.setTitle("Errore");
					mf.getMess().setText(er.getMessage());           
					mf.setVisible(true);
				}
				catch(IOException ioe) {
					mf.setTitle("Errore");
					mf.getMess().setText("Operazione non riuscita.");
					mf.setVisible(true);
				}
				catch(SQLException sqle) {
					mf.setTitle("Errore");
					mf.getMess().setText("Errore nell'inserimento del voto nel registro");
					mf.setVisible(true);
				}
				catch(ConsultationExpiredException cee) {
					mf.setTitle("Avviso");
					mf.getMess().setText(cee.getMessage());
					rf.setPieChart(cons.getRisultati());
					vf.setVisible(false);
					mf.setVisible(true);
					rf.setVisible(true);
										
				}
			}
		};
		vf.getConfBottone().addActionListener(confAl);
		
		ActionListener indRisAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rf.setVisible(false);
				cf.setVisible(true);
			}
		};
		rf.getIndBottone().addActionListener(indRisAl);
		
		ActionListener StoricoAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<IConsultazioneC> consVotate = pf.selectByUser(df.getSessioneUtente());
					for(IConsultazioneC c : consVotate) {
						c = df.getConsultazione(c);
						ArrayList<OpzioneC> voti = pf.getChoice(c);
						sf.setStorico(c, voti);
					}	
					cf.setVisible(false);
					sf.setVisible(true);
				}
				catch(ServerException er) {
					mf.setTitle("Errore");
					mf.getMess().setText(er.getMessage());           
					mf.setVisible(true);
				}
				catch(IOException ioe) {
					mf.setTitle("Errore");
					mf.getMess().setText("Operazione non riuscita.");
					mf.setVisible(true);
				}
				catch(SQLException sqle) {
					mf.setTitle("Errore");
					mf.getMess().setText("Errore nell'apertura del registro");
					mf.setVisible(true);
				}
			}
		};
		cf.getStoricoBottone().addActionListener(StoricoAl);
		
		ActionListener IndStoAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sf.setVisible(false);
				cf.setVisible(true);
			}
		};
		sf.getIndBottone().addActionListener(IndStoAl);
	}
	
}
