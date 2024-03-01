package it.unipv.ingsfw.opinione360.view.voto;

import java.awt.*;
import javax.swing.*;

import it.unipv.ingsfw.opinione360.model.SondaggioC;

import java.util.ArrayList;

/**
 * Schermata che visualizza tutte le consultazioni a cui il candidato può partecipare
 */
public class ConsultazioniFrame extends JFrame {
	
	private JLabel consTit;
	private JPanel consSchermo;                     // cons = consultazione
	private JPanel csSchermo;
	private ArrayList<ConsultazioneBottone> consBottoni;
	private JButton creaBottone;  
	private JButton storicoBottone;
	
	/**
	 * Costruttore senza parametri
	 */
	public ConsultazioniFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		setTitle("Opinione 360");
		ImageIcon icon = new ImageIcon("resources/Logo_Opinione360.png");
		setIconImage(icon.getImage());
		consBottoni = new ArrayList<>();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		consTit = new JLabel("Scegli la consultazione che ti interessa");
		consTit.setPreferredSize(new Dimension(300, 70));
		consTit.setHorizontalAlignment(SwingConstants.CENTER);
		consTit.setFont(new Font("Arial", 1, 18));
		
		consSchermo = new JPanel();
		consSchermo.setLayout(new GridLayout(5, 2, 40, 40));
		consSchermo.setBorder(BorderFactory.createEmptyBorder(20, 80, 0, 80));
		
		csSchermo = new JPanel();
		creaBottone = new JButton("Crea consultazione");
		csSchermo.add(creaBottone);
		storicoBottone = new JButton("Storico");
		csSchermo.add(storicoBottone);
		
		add(consTit, BorderLayout.NORTH);
		add(consSchermo, BorderLayout.CENTER);
		add(csSchermo, BorderLayout.SOUTH);
	}
	
	public JLabel getConsTit() {
		return consTit;
	}

	public JPanel getConsSchermo() {
		return consSchermo;
	}
	
	public void setConsBottoni(ArrayList<SondaggioC> consultazioniElenco) {
		consBottoni.removeAll(consBottoni);
		consSchermo.removeAll();
		for(SondaggioC ce : consultazioniElenco) {
			ConsultazioneBottone cb = new ConsultazioneBottone(ce.getId(), ce.getQuesito());
			cb.setText(ce.getQuesito());
			consBottoni.add(cb);
		}
		for(ConsultazioneBottone ab : consBottoni)
			consSchermo.add(ab);	
	}

	public ArrayList<ConsultazioneBottone> getConsBottoni() {
		return consBottoni;
	}

	public JButton getCreaBottone() {
		return creaBottone;
	}
	
	public JButton getStoricoBottone() {
		return storicoBottone;
	}
	
}
