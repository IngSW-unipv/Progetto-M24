package it.unipv.ingsfw.opinione360.view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Schermata che permette di votare per la consultazione scelta
 */
public class VotoFrame extends JFrame {
	
	private JLabel descTit;                         // desc = descrizione  
	private JPanel opSchermo;                       // op = opzioni
	private JPanel ivcSchermo;                      // ic = indietro-conferma

	private ArrayList<JRadioButton> opBottoni;
	private JButton indBottone;                     // ind = indietro
	private JButton vetBottone;                     // vet = vetrina
	private JButton confBottone;                    // conf = conferma

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
		opBottoni = new ArrayList<>();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		descTit = new JLabel();
		descTit.setPreferredSize(new Dimension(300, 70));
		descTit.setHorizontalAlignment(SwingConstants.CENTER);
		descTit.setFont(new Font("Arial", 1, 18));

		opSchermo = new JPanel(new GridLayout(5, 2));
		
		ivcSchermo = new JPanel();
	
		indBottone = new JButton("Indietro");
		ivcSchermo.add(indBottone);
		vetBottone = new JButton("Vetrina");
		ivcSchermo.add(vetBottone);
		confBottone = new JButton("Conferma");
		ivcSchermo.add(confBottone);
		
		add(descTit, BorderLayout.NORTH);
		add(opSchermo, BorderLayout.CENTER);
		add(ivcSchermo, BorderLayout.SOUTH);
	}
	
	/**
	 * Imposta il quesito della consultazione 
	 * @param b1Tit la stringa che contiene il quesito
	 */
	public void setDescTit(String b1Tit) {
		descTit.setText(b1Tit);
	}

	/**
	 * Imposta i bottoni con le opzioni della consultazione
	 * @param scelte un array di stringhe che contengono le opzioni
	 */
	public void setOpBottoni(String[] scelte) {
		opBottoni.removeAll(opBottoni);
		opSchermo.removeAll();
		for(int i = 0; i < scelte.length; i++)
			opBottoni.add(new JRadioButton(scelte[i]));
		for(JRadioButton ob : opBottoni)
			opSchermo.add(ob);	
	}
	
	public ArrayList<JRadioButton> getOpBottoni() {
		return opBottoni;
	}
	
	public JButton getIndBottone() {
		return indBottone; 
	}
	
	
	public JButton getVetBottone() {
		return vetBottone;
	}

	public JButton getConfBottone() {
		return confBottone;
	}

	public JLabel getDescSchermo() {
		return descTit;
	}

	public JPanel getOpSchermo() {
		return opSchermo;
	}
	
	public JPanel getIVCSchermo() {
		return ivcSchermo;
	}
	
}
