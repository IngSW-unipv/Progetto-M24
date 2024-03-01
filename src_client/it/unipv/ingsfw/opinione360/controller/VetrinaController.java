package it.unipv.ingsfw.opinione360.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JButton;

import it.unipv.ingsfw.opinione360.exception.ServerException;
import it.unipv.ingsfw.opinione360.model.ContenutoImmagineC;
import it.unipv.ingsfw.opinione360.model.ContenutoTestoC;
import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.Formato;
import it.unipv.ingsfw.opinione360.model.IConsultazioneC;
import it.unipv.ingsfw.opinione360.model.IContenutoC;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.view.voto.VotoFrame;
import it.unipv.ingsfw.opinione360.view.popup.MessaggioFrame;
import it.unipv.ingsfw.opinione360.view.popup.SceltaFileFrame;
import it.unipv.ingsfw.opinione360.view.vetrina.*;

/**
 * Controller che gestisce la visualizzazione della vetrina
 * @see VotoFrame
 * @see VetrinaFrame
 * @see AreaContenutoFrame
 * @see SceltaFileFrame
 * @see DomainFacade
 */
public class VetrinaController {
	
	private final VotoFrame vf;
	private final VetrinaFrame vetf;
	private final AreaContenutoFrame acf;
	private final MessaggioFrame mf;
	private final SceltaFileFrame sff;
	private final DomainFacade df;
	private IConsultazioneC cons;

	/**
	 * Costruttore parametrizzato
	 * @param vf schermata di voto
	 * @param vetf schermata della vetrina
	 * @param acf schermata della specifica area candidato 
	 * @param sff schermata di scelta del contenuto da caricare
	 * @param df facciata che rappresenta il dominio del client
	 */
	public VetrinaController(VotoFrame vf, VetrinaFrame vetf, AreaContenutoFrame acf, SceltaFileFrame sff, DomainFacade df) {
		this.vf = vf;
		this.vetf = vetf;
		this.acf = acf;
		mf = MessaggioFrame.getInstance();
		this.sff = sff;
		this.df = df;
		setListeners();
	}

	private void setListeners() {
		ActionListener areeAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acf.getAcTit().setText(acf.getOpzione().getDescription());
				ArrayList<IContenutoC> contenuti = cons.getContenuti(acf.getOpzione());
				manageActions(contenuti);
			}

			private void manageActions(ArrayList<IContenutoC> contenuti) {
				for(IContenutoC c : contenuti)	{
					Formato formato = Formato.valueOf(c.getExtension());
					switch(formato) {
						case TXT:
							ContenutoTestoC ct = (ContenutoTestoC) c;
							acf.displayTesto(ct);
							break;
						case PNG:
							ContenutoImmagineC ci = (ContenutoImmagineC) c;
							acf.displayImmagine(ci);
							break;
					}
				}
				acf.setTit(cons.getQuesito());
				vetf.setVisible(false);
				acf.setVisible(true);
			}
		};
		
		ActionListener vetAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cons = df.getConsultazione(vf.getConsultazione()); 
					manageActions(cons);
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
			}

			private void manageActions(IConsultazioneC cons) {
				vetf.setAreeBottoni(cons.getAllOpzioni());
				for(JButton b : vetf.getAreeBottoni())
					b.addActionListener(areeAl);
				vetf.setConsultazione(cons);
				vf.setVisible(false);
				vetf.setVisible(true);
			}
		};
		vf.getVetrinaBottone().addActionListener(vetAl);
		
		ActionListener indVetAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vetf.setVisible(false);
				vf.setVisible(true);
			}
		};
		vetf.getIndBottone().addActionListener(indVetAl);
		
		ActionListener indAcAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acf.setVisible(false);
				vetf.setVisible(true);
			}
		};
		acf.getIndBottone().addActionListener(indAcAl);
		
		ActionListener carContAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = sff.getFile();
				int i = file.getAbsolutePath().lastIndexOf('.');
			    IContenutoC cont = null;
			    String fe = (file.getAbsolutePath().substring(i + 1)).toUpperCase();
			    Formato formato = Formato.valueOf(fe);
			    try 
			    {
			    	switch(formato) {
			    		case TXT:
			    			ContenutoTestoC ct = new ContenutoTestoC();
			    			cont = ct.fromFile(file);
			    			break;
			    		case PNG:
			    			ContenutoImmagineC ci = new ContenutoImmagineC();
			    			cont = ci.fromFile(file);
			    			break;
			    	}
			    	manageActions(cont);
			    }
			    catch(IOException ioe) {
			    	mf.setTitle("Errore");
			    	mf.getMess().setText("Errore nell'apertura del file.");
			    	mf.setVisible(true);
			    }
			}

			private void manageActions(IContenutoC cont) {
				try {
			    	df.caricaVetrina(cons, cont, cont.getExtension());
				}
				catch(ServerException se) {
					mf.setTitle("Errore");
					mf.getMess().setText(se.getMessage());
					mf.setVisible(true);
				}
				catch(IOException ioe) {
			    	mf.setTitle("Errore");
			    	mf.getMess().setText("Errore nell'apertura del file.");
			    	mf.setVisible(true);
			    }
			}
		};
		acf.getCaricaBottone().addActionListener(carContAl);
	}
	
}
