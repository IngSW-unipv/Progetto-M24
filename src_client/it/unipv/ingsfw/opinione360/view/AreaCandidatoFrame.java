package it.unipv.ingsfw.opinione360.view;

import java.awt.*;
import javax.swing.*;

public class AreaCandidatoFrame extends JFrame {
	
	private JButton indBottone;

	public AreaCandidatoFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		setTitle("Opinione 360");
		setLayout(new BorderLayout());
		indBottone = new JButton("Indietro");
		add(indBottone, BorderLayout.SOUTH);
	}
	
	public void display(String contenuto) {
		
	}
	
	public void display(Image contenuto) {
		
	}

	public JButton getIndBottone() {
		return indBottone;
	}
	
}
