package it.unipv.ingsfw.opinione360.view;

import java.awt.*;
import javax.swing.*;

/**
 * Schermata iniziale della piattaforma Opinione360
 */
public class LogoFrame extends JFrame {
	
	private JButton enBottone;

	/**
	 * Costruttore
	 */
	public LogoFrame() {
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
		JLabel logoEt = new JLabel(new ImageIcon("Materiale utile/Logo/LogoV4.png"));
		JPanel ep = new JPanel();
		ep.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		enBottone = new JButton("Entra");
		ep.add(enBottone);
		add(logoEt, BorderLayout.CENTER);
		add(ep, BorderLayout.SOUTH);	
	}

	public JButton getEnBottone() {
		return enBottone;
	}

}
