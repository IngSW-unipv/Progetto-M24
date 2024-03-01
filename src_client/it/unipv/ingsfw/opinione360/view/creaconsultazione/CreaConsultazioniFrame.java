package it.unipv.ingsfw.opinione360.view.creaconsultazione;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import it.unipv.ingsfw.opinione360.model.UtenteC;

/**
 * Schermata che permette la creazione di una nuova consultazione
 * @see RiepilogoFrame
 */
public class CreaConsultazioniFrame extends JFrame {
	
	private JLabel creaTit;
	private JPanel schermo;
	private JList<String> sceltaVotanti;
	private JList<String> sceltaCandidati;
	private JSpinner sceltaDataApertura;
	private JSpinner sceltaDataChiusura;
	private JComboBox<String> sceltCons;
	private JTextField sceltaquesito;
	private JPanel sceltaOpzioni;
	private ArrayList<JTextField> opzioni;
	private JButton piùBottone;
	private JButton indBottone;
	private JPanel aiSchermo; 
	private JButton avantiBottone;

	public CreaConsultazioniFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		setTitle("Opinione 360");
		ImageIcon icon = new ImageIcon("resources/Logo_Opinione360.png");
		setIconImage(icon.getImage());
		opzioni = new ArrayList<>();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		creaTit = new JLabel("Crea la consultazione");
		creaTit.setPreferredSize(new Dimension(300, 70));
		creaTit.setHorizontalAlignment(SwingConstants.CENTER);
		creaTit.setFont(new Font("Arial", 1, 18));
		
		schermo = new JPanel();
		
		schermo.add(new JLabel("Scegli gli utenti che possono votare:"));
		sceltaVotanti = new JList<>();
		JScrollPane sv = new JScrollPane(sceltaVotanti);		
		sv.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sceltaVotanti.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		sceltaVotanti.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sv.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		schermo.add(sv);
		
		schermo.add(new JLabel("Scegli i candidati:"));
		sceltaCandidati = new JList<>();
		JScrollPane sc = new JScrollPane(sceltaCandidati);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sceltaCandidati.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		sceltaCandidati.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		schermo.add(sc);
		
		schermo.add(new JLabel("Imposta la data di apertura:"));
		sceltaDataApertura = new JSpinner(new SpinnerDateModel());
		schermo.add(sceltaDataApertura);
		
		schermo.add(new JLabel("Imposta la data di chiusura:"));
		sceltaDataChiusura = new JSpinner(new SpinnerDateModel());
		schermo.add(sceltaDataChiusura);
		
		schermo.add(new JLabel("Scegli il tipo di consultazione:"));
		sceltCons = new JComboBox<>(new String[] {"Sondaggio", "Votazione"});
		schermo.add(sceltCons);
		
		schermo.add(new JLabel("Imposta il quesito della consultazione:"));
		sceltaquesito = new JTextField();
		schermo.add(sceltaquesito);
		
		schermo.add(new JLabel("Imposta le opzioni:"));
		sceltaOpzioni = new JPanel();
		schermo.add(sceltaOpzioni);
		piùBottone = new JButton("+");
		schermo.add(piùBottone);
		
		aiSchermo = new JPanel();
		indBottone = new JButton("Indietro");
		aiSchermo.add(indBottone);
		avantiBottone = new JButton("Avanti");
		aiSchermo.add(avantiBottone);
		
		add(creaTit, BorderLayout.NORTH);
		add(schermo, BorderLayout.CENTER);
		add(aiSchermo, BorderLayout.SOUTH);
	}

	public void setListaUtenti(ArrayList<UtenteC> listaUtenti) {
		String[] usernameUtenti = new String[listaUtenti.size()];
		int i = 0;
		for(UtenteC utente : listaUtenti) {
			usernameUtenti[i] = utente.getUsername();
			i++;
		}
		sceltaVotanti.setListData(usernameUtenti);
		sceltaCandidati.setListData(usernameUtenti);
	}
	
	public void setOpzione() {
		sceltaOpzioni.removeAll();
		opzioni.add(new JTextField());
		for(JTextField o : opzioni)
			sceltaOpzioni.add(o);
		validate();
	}

	public List<String> getListaVotanti() {
     	return sceltaVotanti.getSelectedValuesList();
	}
	
	public List<String> getListaCandidati() {
		return sceltaCandidati.getSelectedValuesList();
	}
	
	public Date getDataApertura() {
		return (Date) sceltaDataApertura.getValue();
	}
	
	public Date getDataChiusura() {
		return (Date) sceltaDataChiusura.getValue();
	}
	
	public String getTipoConsultazione() {
		return (String) sceltCons.getSelectedItem();
	}
	
	public String getQuesito() {
		return sceltaquesito.getText();
	}
	
	public ArrayList<String> getOpzioni() {
		ArrayList<String> opz = new ArrayList<>();
		for(JTextField o : opzioni)
			opz.add(o.getText());
		return opz;
	}
	
	public JButton getPiùBottone() {
		return piùBottone;
	}
	
	public JButton getIndBottone() {
		return indBottone;
	}

	public JButton getAvantiBottone() {
		return avantiBottone;
	}

}
