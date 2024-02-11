package it.unipv.ingsfw.opinione360.view;

import java.awt.*;
import javax.swing.*;

/**
 * Classe astratta che contiene i campi principali del login e della registrazione
 */

public abstract class CampiFrame extends JFrame {
	
	private JPanel logSchermo;
	
	private JTextField userCampo;
	private JTextField emailCampo;
	private JPasswordField passCampo;

	/**
	 * Costruttore
	 */
	public CampiFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		setTitle("Opinione 360");
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		logSchermo = new JPanel();
		
		Dimension dim = new Dimension(300, 25);
		
		JPanel logSchermo1 = new JPanel();
		logSchermo1.add(new JLabel("Username: "));
		userCampo = new JTextField();
		userCampo.setPreferredSize(dim);
		logSchermo1.add(userCampo);
		
		JPanel logSchermo2 = new JPanel();
		logSchermo2.add(new JLabel("E-Mail: "));
		emailCampo = new JTextField();
		emailCampo.setPreferredSize(dim);
		logSchermo2.add(emailCampo);
		
		JPanel logSchermo3 = new JPanel();
		logSchermo3.add(new JLabel("Password: "));
		passCampo = new JPasswordField();
		passCampo.setPreferredSize(dim);
		logSchermo3.add(passCampo);
		
	
		logSchermo.add(logSchermo1);
		logSchermo.add(logSchermo2);
		logSchermo.add(logSchermo3);
	}
	
	public JPanel getLogSchermo() {
		return logSchermo;
	}
	
	public JTextField getUserCampo() {
		return userCampo;
	}

	public JTextField getEmailCampo() {
		return emailCampo;
	}

	public JPasswordField getPassCampo() {
		return passCampo;
	}

}
