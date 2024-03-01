package it.unipv.ingsfw.opinione360.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.unipv.ingsfw.opinione360.exception.ServerException;
import it.unipv.ingsfw.opinione360.exception.UserNotFoundException;
import it.unipv.ingsfw.opinione360.model.AmministratoreC;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.model.UtenteC;
import it.unipv.ingsfw.opinione360.view.creaconsultazione.CreaConsultazioniFrame;
import it.unipv.ingsfw.opinione360.view.creaconsultazione.RiepilogoFrame;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;
import it.unipv.ingsfw.opinione360.view.voto.ConsultazioniFrame;

/**
 * Controller che gestisce la creazione di una nuova consultazione 
 * @see ConsultazioniFrame
 * @see CreaConsultazioniFrame
 * @see RiepilogoFrame
 * @see MessaggioFrame
 * @see DomainFacade
 */
public class CreaConsultazioniController {
	
	private final ConsultazioniFrame cf;
	private final CreaConsultazioniFrame ccf;
	private final RiepilogoFrame verf;
	private final MessaggioFrame mf;
	private final DomainFacade df;

	/**
	 * Costruttore parametrizzato
	 * @param cf schermata di scelta della consultazione
	 * @param ccf schermata di creazione di una nuova consultazione
	 * @param verf schermata per il riepilogo della consultazione appena creata
	 * @param df facciata che rappresenta il dominio del client
	 */
	public CreaConsultazioniController(ConsultazioniFrame cf, CreaConsultazioniFrame ccf, RiepilogoFrame verf, DomainFacade df) {
		this.cf = cf;
		this.ccf = ccf;
		this.verf = verf;
		mf = MessaggioFrame.getInstance();
		this.df = df;	
		setListeners();
	}

	/**
	 * Imposta i listener dei bottoni dell'interfaccia grafica
	 */
	private void setListeners() {
		ActionListener creaAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AmministratoreC amministratore = (AmministratoreC) df.getSessioneUtente();
					ArrayList<UtenteC> listaUtenti = df.getUtenti();
					manageActions(listaUtenti);
				}	
				catch(ClassCastException cce) {
					mf.setTitle("Errore");
					mf.getMess().setText("Non sei un amministratore.");
					mf.setVisible(true);
				}
				catch(ServerException | UserNotFoundException er) {
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

			private void manageActions(ArrayList<UtenteC> listaUtenti) {
				cf.setVisible(false);
				ccf.setListaUtenti(listaUtenti);
				ccf.setVisible(true);
			}
		};
		cf.getCreaBottone().addActionListener(creaAl);
		
		ActionListener indCreaAl = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ccf.setVisible(false);
				cf.setVisible(true);
			}
		};
		ccf.getIndBottone().addActionListener(indCreaAl);
		
		ActionListener avantiAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> listaVotanti = ccf.getListaVotanti();
				List<String> listaCandidati = ccf.getListaCandidati();
				String tipoConsultazione = ccf.getTipoConsultazione();
				Date dataApertura = ccf.getDataApertura();
				Date dataChiusura = ccf.getDataChiusura();
				String quesito = ccf.getQuesito();
				ArrayList<String> opzioni = ccf.getOpzioni();
				try {
					if(dataApertura.compareTo(dataChiusura) >= 0)
						throw new IllegalArgumentException();
					manageActions(listaVotanti, listaCandidati, dataApertura, dataChiusura, tipoConsultazione, quesito, opzioni);
				}
				catch(IllegalArgumentException iae) {
					mf.setTitle("Errore");
					mf.getMess().setText("Il periodo in cui la consultazione è attiva non è valido.");
					mf.setVisible(true);
				}
			}

			private void manageActions(List<String> listaVotanti, List<String> listaCandidati, Date dataApertura, Date dataChiusura, String tipoConsultazione, String quesito, ArrayList<String> opzioni) {
				verf.setListaVotanti(listaVotanti);
				verf.setListaCandidati(listaCandidati);
				verf.setDataApertura(dataApertura);
				verf.setDataChiusura(dataChiusura);
				verf.setTipoConsultazione(tipoConsultazione);
				verf.setQuesito(quesito);
				verf.setOpzioni(opzioni);
				ccf.setVisible(false);
				verf.setVisible(true);
			}
		};
		ccf.getAvantiBottone().addActionListener(avantiAl);
		
		ActionListener piùAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ccf.setOpzione();
			}
		};
		ccf.getPiùBottone().addActionListener(piùAl);
		
		ActionListener indAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verf.setVisible(false);
				ccf.setVisible(true);
			}
		};
		verf.getIndBottone().addActionListener(indAl);
		
		ActionListener confConsAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> votanti = ccf.getListaVotanti();
				List<String> candidati = ccf.getListaCandidati();
				String tipoConsultazione = ccf.getTipoConsultazione();
				Calendar dataApertura = Calendar.getInstance();
				Date da = ccf.getDataApertura();
				dataApertura.setTime(da);
				Calendar dataChiusura = Calendar.getInstance();
				Date dc = ccf.getDataChiusura();
				dataChiusura.setTime(dc);
				String quesito = ccf.getQuesito();
				ArrayList<String> opzioni = ccf.getOpzioni();
				manageActions(votanti, candidati, dataApertura, dataChiusura, tipoConsultazione, quesito, opzioni);
			}

			private void manageActions(List<String> votanti, List<String> candidati, Calendar dataApertura, Calendar dataChiusura, String tipoConsultazione, String quesito, ArrayList<String> opzioni) {
				try {
					ArrayList<UtenteC> listaUtenti = df.getUtenti();
					ArrayList<UtenteC> listaVotanti = new ArrayList<>();
					ArrayList<UtenteC> listaCandidati = new ArrayList<>();
					for(UtenteC u : listaUtenti) {
						for(String v : votanti) 
							if(u.getUsername().equals(v))
								listaVotanti.add(u);
						for(String c : candidati)
							if(u.getUsername().equals(c))
								listaCandidati.add(u);
					}
					String[] opz = opzioni.toArray(new String[opzioni.size()]);
					SondaggioC consultazione = new SondaggioC(quesito, listaVotanti, listaCandidati, opz, dataApertura, dataChiusura);
					df.creaConsultazione(consultazione);
					mf.setTitle("Avviso");
					mf.getMess().setText("La consultazione è stata creata con successo.");
					mf.setVisible(true);
				}
				catch(ServerException | UserNotFoundException|IllegalArgumentException e) {
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
		verf.getConfBottone().addActionListener(confConsAl);
	}
	
}
