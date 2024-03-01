package it.unipv.ingsfw.opinione360.view.logreg;

import java.awt.*;
import javax.swing.*;

/**
 * Schermata relativa alla registrazione di un utente alla piattaforma
 */
public class RegistrazioneFrame extends LoginFrame {
	
	private JLabel regTit;
	private JTextField emailCampo;
	private JTextField idSocCampo;
	private JPanel regSchermo;
	private JPanel icSchermo;
	private JButton indBottone;
	private JButton confBottone;
	
	/**
	 * Costruttore senza parametri
	 */
	public RegistrazioneFrame() {
		super();
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout());
		
		regTit = new JLabel("Registrati con le tue credenziali");
		regTit.setPreferredSize(new Dimension(300, 90));
		regTit.setHorizontalAlignment(SwingConstants.CENTER);
		regTit.setFont(new Font("Arial", 1, 18));
		
		Dimension dim = new Dimension(300, 25);
		
		JPanel logSchermo3 = new JPanel();
		logSchermo3.add(new JLabel("E-Mail: "));
		emailCampo = new JTextField();
		emailCampo.setPreferredSize(dim);
		logSchermo3.add(emailCampo);
		
		JPanel logSchermo4 = new JPanel();
		logSchermo4.add(new JLabel("ID societario: "));
		idSocCampo = new JTextField();
		idSocCampo.setPreferredSize(dim);
		logSchermo4.add(idSocCampo);
		
		regSchermo = new JPanel();
		regSchermo.setLayout(new GridLayout(4, 1));
		regSchermo.add(super.getLogSchermo1());
		regSchermo.add(super.getLogSchermo2());
		regSchermo.add(logSchermo3);
		regSchermo.add(logSchermo4);
		
		icSchermo = new JPanel();	
		indBottone = new JButton("Indietro");
		icSchermo.add(indBottone);
		confBottone = new JButton("Conferma");
		icSchermo.add(confBottone);
		
		add(regTit, BorderLayout.NORTH);
		add(regSchermo, BorderLayout.CENTER);
		add(icSchermo, BorderLayout.SOUTH);
	}
	
	public Component getRegTit() {
		return regTit;
	}
	
	public JPanel getRegSchermo() {
		return regSchermo;
	}
	
	public JTextField getEmailCampo() {
		return emailCampo;
	}
	
	public JTextField getIdSocietarioCampo() {
		return idSocCampo;
	}
	
	public JPanel getICSchermo() {
		return icSchermo;
	}
	
	@Override
	public JButton getConfBottone() {
		return confBottone;
	}
	
	public JButton getIndBottone() {
		return indBottone;
	}
}
