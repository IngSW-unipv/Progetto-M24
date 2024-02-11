package it.unipv.ingsfw.opinione360.view.popup;

import java.awt.*;
import javax.swing.*;

/**
 * Schermata che viene visualizzata in risposta a un particolare evento generato da un bottone
 */

public class MessaggioFrame extends JFrame {
	
	private static MessaggioFrame instance = null;	
	private JLabel l; 
	
	/**
	 * Costruttore
	 */
	private MessaggioFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 5, screenHeight / 5);
		setLocation(screenWidth / 5 * 2, screenHeight / 5 * 2);
		l = new JLabel();
		l.setHorizontalAlignment(SwingConstants.CENTER);
		add(l);
	}
	
	public static MessaggioFrame getInstance() {
		if(instance == null) 
			instance = new MessaggioFrame();
		return instance;
	}

	public JLabel getLabel() {
		return l;
	}
	
}
