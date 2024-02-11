package it.unipv.ingsfw.opinione360.view;

import java.awt.*;
import javax.swing.*;

public class RegistrazioneFrame extends CampiFrame {
	
	private JLabel regTit;
	private JTextField idCampo;
	private JPanel icSchermo;
	private JButton indBottone;
	private JButton confBottone;
	
	/**
	 * Costruttore
	 */
	public RegistrazioneFrame() {
		super();
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout());
		
		regTit = new JLabel("Registrati con le tue credenziali");
		regTit.setPreferredSize(new Dimension(300, 70));
		regTit.setHorizontalAlignment(SwingConstants.CENTER);
		regTit.setFont(new Font("Arial", 1, 18));
		
		JPanel logSchermo4 = new JPanel();
		logSchermo4.add(new JLabel("ID societario: "));
		idCampo = new JTextField();
		idCampo.setPreferredSize(new Dimension(300, 25));
		logSchermo4.add(idCampo);
		
		super.getLogSchermo().setLayout(new GridLayout(4, 1));
		super.getLogSchermo().add(logSchermo4);
		
		icSchermo = new JPanel();
		
		indBottone = new JButton("Indietro");
		icSchermo.add(indBottone);
		confBottone = new JButton("Conferma");
		icSchermo.add(confBottone);
		
		add(regTit, BorderLayout.NORTH);
		add(super.getLogSchermo(), BorderLayout.CENTER);
		add(icSchermo, BorderLayout.SOUTH);
	}
	
	public Component getRegTit() {
		return regTit;
	}
	
	public JTextField getIdCampo() {
		return idCampo;
	}
	
	public JPanel getICSchermo() {
		return icSchermo;
	}
	
	public JButton getConfBottone() {
		return confBottone;
	}
	
	public JButton getIndBottone() {
		return indBottone;
	}
}
