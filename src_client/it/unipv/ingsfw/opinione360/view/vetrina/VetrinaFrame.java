package it.unipv.ingsfw.opinione360.view.vetrina;

import java.awt.*;
import javax.swing.*;

import it.unipv.ingsfw.opinione360.model.IConsultazioneC;
import it.unipv.ingsfw.opinione360.model.OpzioneC;

import java.util.ArrayList;

/**
 * Schermata della vetrina della consultazione selezionata
 * @see AreaContenutoFrame
 */
public class VetrinaFrame extends JFrame {

	private JLabel vetTit;
	private JPanel areeSchermo;
	private ArrayList<JButton> areeBottoni;
	private JPanel iSchermo;
	private JButton indBottone;
	
	private ArrayList<AreaContenutoFrame> acf;
	private IConsultazioneC consultazione;

	public VetrinaFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		setTitle("Opinione 360");
		ImageIcon icon = new ImageIcon("resources/Logo_Opinione360.png");
		setIconImage(icon.getImage());
		areeBottoni = new ArrayList<>();
		acf = new ArrayList<>();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		vetTit = new JLabel("Vetrina");
		vetTit.setPreferredSize(new Dimension(300, 70));
		vetTit.setHorizontalAlignment(SwingConstants.CENTER);
		vetTit.setFont(new Font("Arial", 1, 18));
		
		areeSchermo = new JPanel();
		areeSchermo.setLayout(new GridLayout(5, 2, 40, 40));
		areeSchermo.setBorder(BorderFactory.createEmptyBorder(20, 80, 0, 80));
		
		iSchermo = new JPanel();
		indBottone = new JButton("Indietro");
		iSchermo.add(indBottone);
		
		add(areeSchermo, BorderLayout.CENTER);
		add(vetTit, BorderLayout.NORTH);
		add(iSchermo, BorderLayout.SOUTH);
	}

	public void setAreeBottoni(OpzioneC[] opzioni) {
		areeBottoni.removeAll(areeBottoni);
		areeSchermo.removeAll();
		for(int i = 0; i < opzioni.length; i++) 
			areeBottoni.add(new JButton(opzioni[i].getDescription()));
		for(JButton ab : areeBottoni)
			areeSchermo.add(ab);	
	}

	public JLabel getVetTit() {
		return vetTit;
	}

	public JPanel getAreeSchermo() {
		return areeSchermo;
	}

	public ArrayList<JButton> getAreeBottoni() {
		return areeBottoni;
	}

	public JPanel getISchermo() {
		return iSchermo;
	}

	public JButton getIndBottone() {
		return indBottone;
	}

	public IConsultazioneC getConsultazione() {
		return consultazione;
	}

	public void setConsultazione(IConsultazioneC consultazione) {
		this.consultazione = consultazione;
	}

	public ArrayList<AreaContenutoFrame> getAcf() {
		return acf;
	}

	public void setAcf(ArrayList<AreaContenutoFrame> acf) {
		this.acf = acf;
	}
	
}
