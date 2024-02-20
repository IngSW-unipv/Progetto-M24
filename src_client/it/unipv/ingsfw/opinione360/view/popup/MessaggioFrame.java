package it.unipv.ingsfw.opinione360.view.popup;

import java.awt.*;
import javax.swing.*;

/**
 * Schermata che viene visualizzata in risposta a un particolare evento generato da un bottone
 */
public class MessaggioFrame extends JFrame {
	
	private static MessaggioFrame instance = null;	
	private JTextArea l; 
	
	/**
	 * Costruttore senza parametri
	 */
	private MessaggioFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 5, screenHeight / 5);
		setLocation(screenWidth / 5 * 2, screenHeight / 5 * 2);
		setResizable(false);
		initComponents();
	}
	
	private void initComponents() {
		l = new JTextArea();
		l.setPreferredSize(new Dimension(300, 70));
		l.setFont(new Font("Arial", 1, 14));
		l.setBackground(this.getForeground());
		l.setEditable(false);
		l.setLineWrap(true);
		l.setWrapStyleWord(true);
		add(l);
	}

	public static MessaggioFrame getInstance() {
		if(instance == null) 
			instance = new MessaggioFrame();
		return instance;
	}

	public JTextArea getMess() {
		return l;
	}
	
}
