package it.unipv.ingsfw.opinione360.view;

import java.awt.*;
import javax.swing.*;

import it.unipv.ingsfw.opinione360.model.IContenuto;

/**
 * Schermata che visualizza i contenuti di uno specifico candidato
 */
public class AreaContenutoFrame extends JFrame {
	
	private JLabel acTit;
	private JButton indBottone;	

	/**
	 * Costruttore senza parametri
	 */
	public AreaContenutoFrame() {
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
		
		acTit = new JLabel();
		acTit.setPreferredSize(new Dimension(300, 70));
		acTit.setHorizontalAlignment(SwingConstants.CENTER);
		acTit.setFont(new Font("Arial", 1, 18));
		
		JScrollPane cSchermo = new JScrollPane();
		
		JViewport vp = cSchermo.getViewport();
		vp.setBackground(Color.WHITE);
		
		cSchermo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//cSchermo.setVerticalScrollBar(sb);
		//cSchermo.setCorner(JScrollPane.LOWER_RIGHT_CORNER, sb);
		//cSchermo.setCorner(JScrollPane.UPPER_RIGHT_CORNER, sb);
		
		JPanel iSchermo = new JPanel(); 
		
		indBottone = new JButton("Indietro");
		iSchermo.add(indBottone);
		
		add(acTit, BorderLayout.NORTH);
		add(cSchermo, BorderLayout.CENTER);
		add(iSchermo, BorderLayout.SOUTH);
	}

	public void display(IContenuto contenuto) {
		
	}
	
	public void setTit(String tit) {
		acTit.setText(tit);
	}

	public JLabel getAcTit() {
		return acTit;
	}

	public JButton getIndBottone() {
		return indBottone;
	}
	
}
