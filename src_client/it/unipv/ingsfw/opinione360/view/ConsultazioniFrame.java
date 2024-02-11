package it.unipv.ingsfw.opinione360.view;

import java.awt.*;
import javax.swing.*;

import it.unipv.ingsfw.opinione360.exception.UserMissingAccessException;

import java.util.ArrayList;

public class ConsultazioniFrame extends JFrame {
	
	private JLabel consTit;
	private JPanel consSchermo;                     // cons = consultazione
	private ArrayList<JButton> consBottoni;
	private JButton indBottone;  
	
	/**
	 * Costruttore
	 */
	public ConsultazioniFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		setTitle("Opinione 360");
		consBottoni = new ArrayList<>();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		consTit = new JLabel("Scegli la consultazione che ti interessa");
		consTit.setPreferredSize(new Dimension(300, 70));
		consTit.setHorizontalAlignment(SwingConstants.CENTER);
		consTit.setFont(new Font("Arial", 1, 18));
		
		JButton b1 = new JButton("Chi voti?");
		consBottoni.add(b1);
		JButton b2 = new JButton("Miglior pilota di Formula1?");              // temporaneo
		consBottoni.add(b2);  
		JButton b3 = new JButton("Quale colore è meglio?");
		consBottoni.add(b3);
		
		consSchermo = new JPanel();
		consSchermo.setLayout(new GridLayout(5, 2, 40, 40));
		consSchermo.setBorder(BorderFactory.createEmptyBorder(20, 80, 0, 80));
		for(JButton cb : consBottoni) 
			consSchermo.add(cb);
		
		JPanel iSchermo = new JPanel();
		
		indBottone = new JButton("Indietro");
		iSchermo.add(indBottone);
		
		add(consTit, BorderLayout.NORTH);
		add(consSchermo, BorderLayout.CENTER);
		add(iSchermo, BorderLayout.SOUTH);
	}
	
	public JLabel getConsTit() {
		return consTit;
	}

	public JPanel getConsSchermo() {
		return consSchermo;
	}
	
	public ArrayList<JButton> getConsBottoni() {
		return consBottoni;
	}

	public JButton getIndBottone() {
		return indBottone;
	}
	
}
