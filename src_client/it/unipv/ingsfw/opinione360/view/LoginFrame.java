package it.unipv.ingsfw.opinione360.view;

import java.awt.*;
import javax.swing.*;

/**
 * Schermata relativa al login di un utente alla piattaforma 
 */
public class LoginFrame extends CampiFrame {

	private JLabel logTit;
	private JPanel logSchermo;
	private JPanel rcSchermo;
	private JButton regBottone;
	private JButton confBottone;

	/**
	 * Costruttore senza parametri
	 */
	public LoginFrame() {
		super();
		ImageIcon icon = new ImageIcon("Materiale utile/Logo/LogoV4.png");
		setIconImage(icon.getImage());
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		logTit = new JLabel("Accedi alla piattaforma");
		logTit.setPreferredSize(new Dimension(300, 70));
		logTit.setHorizontalAlignment(SwingConstants.CENTER);
		logTit.setFont(new Font("Arial", 1, 18));
		
		logSchermo = super.getCampiSchermo();
		logSchermo.setLayout(new GridLayout(3, 1));
		
		rcSchermo = new JPanel();
		
		regBottone = new JButton("Registrati");
		rcSchermo.add(regBottone);
		confBottone = new JButton("Conferma");
		rcSchermo.add(confBottone);
		
		add(logTit, BorderLayout.NORTH);
		add(logSchermo, BorderLayout.CENTER);
		add(rcSchermo, BorderLayout.SOUTH);
	}

	public JLabel getLogTit() {
		return logTit;
	}
	
	public JPanel getLogSchermo() {
		return logSchermo;
	}

	public JPanel getRCSchermo() {
		return rcSchermo;
	}

	public JButton getRegBottone() {
		return regBottone;
	}

	public JButton getConfBottone() {
		return confBottone;
	}
	
}
