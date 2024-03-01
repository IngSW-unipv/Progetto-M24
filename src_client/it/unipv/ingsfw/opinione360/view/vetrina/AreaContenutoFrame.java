package it.unipv.ingsfw.opinione360.view.vetrina;

import java.awt.*;
import javax.swing.*;

import it.unipv.ingsfw.opinione360.model.ContenutoImmagineC;
import it.unipv.ingsfw.opinione360.model.ContenutoTestoC;
import it.unipv.ingsfw.opinione360.model.OpzioneC;

/**
 * Schermata che visualizza i contenuti di uno specifico candidato
 */
public class AreaContenutoFrame extends JFrame {
	
	private JLabel acTit;
	private JTextArea contenutiSchermo;
	private JButton indBottone;	
	private JButton caricaBottone;
	
	private OpzioneC opzione;

	public AreaContenutoFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		setTitle("Opinione 360");
		ImageIcon icon = new ImageIcon("resources/Logo_Opinione360.png");
		setIconImage(icon.getImage());
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout());
		
		acTit = new JLabel();
		acTit.setPreferredSize(new Dimension(300, 70));
		acTit.setHorizontalAlignment(SwingConstants.CENTER);
		acTit.setFont(new Font("Arial", 1, 18));
		
		contenutiSchermo = new JTextArea();
		JScrollPane schermo = new JScrollPane(contenutiSchermo);
		contenutiSchermo.setBackground(Color.WHITE);
		contenutiSchermo.setEditable(false);
		schermo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JPanel icSchermo = new JPanel(); 
		indBottone = new JButton("Indietro");
		icSchermo.add(indBottone);
		caricaBottone = new JButton("Carica contenuto");
		icSchermo.add(caricaBottone);
		
		add(acTit, BorderLayout.NORTH);
		add(contenutiSchermo, BorderLayout.CENTER);
		add(icSchermo, BorderLayout.SOUTH);
	}
	
	/**
	 * Visualizza i contenuti che corrispondono a file testo
	 * @param contenuto
	 */
	public void displayTesto(ContenutoTestoC contenuto) {
		contenutiSchermo.add(new JLabel(contenuto.toString()));
	}
	
	/**
	 * Visualizza i contenuti che corrispondono a file immagine
	 * @param contenuto
	 */
	public void displayImmagine(ContenutoImmagineC contenuto) {
		contenutiSchermo.add(new JLabel(new ImageIcon(contenuto.toImage())));
	}
	
	public OpzioneC getOpzione() {
		return opzione;
	}

	public void setOpzione(OpzioneC opzione) {
		this.opzione = opzione;
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

	public JButton getCaricaBottone() {
		return caricaBottone;
	}
	
}
