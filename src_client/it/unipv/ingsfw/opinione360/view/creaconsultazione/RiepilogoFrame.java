package it.unipv.ingsfw.opinione360.view.creaconsultazione;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Schermata che riepiloga le informazioni sulla consultazione appena creata
 * @see CreaConsultazioniFrame
 */
public class RiepilogoFrame extends JFrame {
	
	private JLabel creaTit;
	private JTextArea votantiScelti;
	private JTextArea candidatiScelti;
	private JTextField verDataApertura;
	private JTextField verDataChiusura;
	private JTextField verTipoConsultazione;
	private JTextField verQuesito;
	private JPanel opzioniScelte;
	private JPanel schermo;
	private JPanel icSchermo; 
	private JButton indBottone;
	private JButton confBottone;

	public RiepilogoFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(new Dimension(screenWidth / 2, screenHeight / 2));
		setLocation(screenWidth / 4, screenHeight / 4);
		setTitle("Opinione 360");
		ImageIcon icon = new ImageIcon("resources/Logo_Opinione360.png");
		setIconImage(icon.getImage());
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		creaTit = new JLabel("Riepilogo della consultazione");
		creaTit.setPreferredSize(new Dimension(300, 70));
		creaTit.setHorizontalAlignment(SwingConstants.CENTER);
		creaTit.setFont(new Font("Arial", 1, 18));
		
		schermo = new JPanel();
		
		schermo.add(new JLabel("Utenti della consultazione"));
		votantiScelti = new JTextArea();
		JScrollPane sv = new JScrollPane(votantiScelti);		
		sv.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		votantiScelti.setBackground(Color.WHITE);
		votantiScelti.setEditable(false);
		schermo.add(sv);
		
		schermo.add(new JLabel("Candidati dellla consultazione"));
		candidatiScelti = new JTextArea();
		JScrollPane sc = new JScrollPane(candidatiScelti);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		candidatiScelti.setBackground(Color.WHITE);
		candidatiScelti.setEditable(false);
		schermo.add(sc);
		
		schermo.add(new JLabel("Data di apertura"));
		verDataApertura = new JTextField();
		verDataApertura.setBackground(Color.WHITE);
		verDataApertura.setEditable(false);
		schermo.add(verDataApertura);
		
		schermo.add(new JLabel("Data di chiusura"));
		verDataChiusura = new JTextField();
		verDataChiusura.setBackground(Color.WHITE);
		verDataChiusura.setEditable(false);
		schermo.add(verDataChiusura);
		
		schermo.add(new JLabel("Tipo della consultazione"));
		verTipoConsultazione = new JTextField();
		verTipoConsultazione.setBackground(Color.WHITE);
		verTipoConsultazione.setEditable(false);
		schermo.add(verTipoConsultazione);
		
		schermo.add(new JLabel("Quesito"));
		verQuesito = new JTextField();
		verQuesito.setBackground(Color.WHITE);
		verQuesito.setEditable(false);
		schermo.add(verQuesito);
		
		opzioniScelte = new JPanel();
		schermo.add(opzioniScelte);
		
		icSchermo = new JPanel();
		indBottone = new JButton("Indietro");
		icSchermo.add(indBottone);
		confBottone = new JButton("Conferma la creazione");
		icSchermo.add(confBottone);
		
		add(creaTit, BorderLayout.NORTH);
		add(schermo, BorderLayout.CENTER);
		add(icSchermo, BorderLayout.SOUTH);
	}
	
	public void setListaVotanti(List<String> listaVotanti) {
		Iterator<String> it = listaVotanti.iterator();
		while(it.hasNext()) {
			String lv = (String) it.next();	
			votantiScelti.append(lv);
			String sep = it.hasNext()? "\n" : null;
			votantiScelti.append(sep);
		}
	}
	
	public void setListaCandidati(List<String> listaCandidati) {
		Iterator<String> it = listaCandidati.iterator();
		while(it.hasNext()) {
			String lv = (String) it.next();	
			candidatiScelti.append(lv);
			String sep = it.hasNext()? "\n" : null;
			candidatiScelti.append(sep);
		}
	}
	
	public void setDataApertura(Date dataApertura) {
		verDataApertura.setText(dataApertura.toString());
	}
	
	public void setDataChiusura(Date dataChiusura) {
		verDataChiusura.setText(dataChiusura.toString());
	}
	
	public void setTipoConsultazione(String tipoConsultazione) {
		verTipoConsultazione.setText(tipoConsultazione);
	}
	
	public void setQuesito(String quesito) {
		verQuesito.setText(quesito);
	}
	
	public void setOpzioni(ArrayList<String> opzioni) {
		int i = 1;
		for(String o : opzioni) {
			opzioniScelte.add(new JLabel("Opzione" + i));
			opzioniScelte.add(new JTextField(o));
			i++;
		}
		validate();
	}
		
	public JButton getIndBottone() {
		return indBottone;
	}

	public JButton getConfBottone() {
		return confBottone;
	}
	
}
