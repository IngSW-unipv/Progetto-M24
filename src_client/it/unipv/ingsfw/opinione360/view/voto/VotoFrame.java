package it.unipv.ingsfw.opinione360.view.voto;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import it.unipv.ingsfw.opinione360.model.IConsultazioneC;
import it.unipv.ingsfw.opinione360.model.OpzioneC;

/**
 * Schermata che permette di votare per la consultazione scelta
 */
public class VotoFrame extends JFrame {
	
	private JLabel quesitoTit;                        
	private JPanel opzioniSchermo;                     
	private JPanel ivcSchermo;                          // ic = indietro-vetrina-conferma

	private ArrayList<OpzioneBottone> opzioniBottoni;
	private JButton indBottone;                     // ind = indietro
	private JButton vetrinaBottone;                    
	private JButton confBottone;                    // conf = conferma
	
	private IConsultazioneC consultazione;
	
	/**
	 * Costruttore senza parametri
	 */
	public VotoFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		setTitle("Opinione 360");
		ImageIcon icon = new ImageIcon("resources/Logo_Opinione360.png");
		setIconImage(icon.getImage());
		opzioniBottoni = new ArrayList<>();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		quesitoTit = new JLabel();
		quesitoTit.setPreferredSize(new Dimension(300, 70));
		quesitoTit.setHorizontalAlignment(SwingConstants.CENTER);
		quesitoTit.setFont(new Font("Arial", 1, 18));

		opzioniSchermo = new JPanel(new GridLayout(5, 2));
		
		ivcSchermo = new JPanel();
	
		indBottone = new JButton("Indietro");
		ivcSchermo.add(indBottone);
		vetrinaBottone = new JButton("Vetrina");
		ivcSchermo.add(vetrinaBottone);
		confBottone = new JButton("Conferma");
		ivcSchermo.add(confBottone);
		
		add(quesitoTit, BorderLayout.NORTH);
		add(opzioniSchermo, BorderLayout.CENTER);
		add(ivcSchermo, BorderLayout.SOUTH);
	}
	
	/**
	 * Imposta il quesito della consultazione 
	 * @param b1Tit la stringa che contiene il quesito
	 */
	public void setQuesitoTit(String b1Tit) {
		quesitoTit.setText(b1Tit);
	}

	/**
	 * Imposta i bottoni con le opzioni della consultazione
	 * @param scelte un array di stringhe che contengono le opzioni
	 */
	public void setOpzioniBottoni(OpzioneC[] opzioni) {
		opzioniBottoni.removeAll(opzioniBottoni);
		opzioniSchermo.removeAll();
		for(int i = 0; i < opzioni.length; i++)
			opzioniBottoni.add(new OpzioneBottone(opzioni[i]));
		for(OpzioneBottone ob : opzioniBottoni)
			opzioniSchermo.add(ob);
	}
	
	public IConsultazioneC getConsultazione() {
		return consultazione;
	}

	public void setConsultazione(IConsultazioneC consultazione) {
		this.consultazione = consultazione;
	}

	public ArrayList<OpzioneBottone> getOpzioniBottoni() {
		return opzioniBottoni;
	}
	
	public JButton getIndBottone() {
		return indBottone; 
	}
	
	public JButton getVetrinaBottone() {
		return vetrinaBottone;
	}

	public JButton getConfBottone() {
		return confBottone;
	}

	public JLabel getQuesitoSchermo() {
		return quesitoTit;
	}

	public JPanel getOpzioniSchermo() {
		return opzioniSchermo;
	}
	
	public JPanel getIVCSchermo() {
		return ivcSchermo;
	}
	
}
