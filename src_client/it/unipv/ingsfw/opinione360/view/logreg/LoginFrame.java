package it.unipv.ingsfw.opinione360.view.logreg;

import java.awt.*;
import javax.swing.*;

/**
 * Schermata relativa al login di un utente alla piattaforma 
 */
public class LoginFrame extends JFrame {

	private JLabel logTit;
	private JTextField usernameCampo;
	private JPasswordField passwordCampo;
	private JPanel logSchermo;
	private JPanel logSchermo1;
	private JPanel logSchermo2;
	private JPanel rcSchermo;
	private JButton regBottone;
	private JButton confBottone;

	/**
	 * Costruttore senza parametri
	 */
	public LoginFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		ImageIcon icon = new ImageIcon("resources/Logo_Opinione360.png");
		setIconImage(icon.getImage());
		setTitle("Opinione 360");
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		logTit = new JLabel("Accedi alla piattaforma");
		logTit.setPreferredSize(new Dimension(300, 90));
		logTit.setHorizontalAlignment(SwingConstants.CENTER);
		logTit.setFont(new Font("Arial", 1, 18));
		
		Dimension dim = new Dimension(300, 25);
		
		logSchermo1 = new JPanel();
		logSchermo1.add(new JLabel("Username: "));
		usernameCampo = new JTextField();
		usernameCampo.setPreferredSize(dim);
		logSchermo1.add(usernameCampo);
		
		logSchermo2 = new JPanel();
		logSchermo2.add(new JLabel("Password: "));
		passwordCampo = new JPasswordField();
		passwordCampo.setPreferredSize(dim);
		logSchermo2.add(passwordCampo);
		
		logSchermo = new JPanel();
		logSchermo.setLayout(new GridLayout(2, 1));
		logSchermo.add(logSchermo1);
		logSchermo.add(logSchermo2);
		
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
	
	public JTextField getUsernameCampo() {
		return usernameCampo;
	}

	public JPasswordField getPasswordCampo() {
		return passwordCampo;
	}

	public JPanel getLogSchermo() {
		return logSchermo;
	}
	
	public JPanel getLogSchermo1() {
		return logSchermo1;
	}
	
	public JPanel getLogSchermo2() {
		return logSchermo2;
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
