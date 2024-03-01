package it.unipv.ingsfw.opinione360.view.voto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import it.unipv.ingsfw.opinione360.model.IConsultazioneC;
import it.unipv.ingsfw.opinione360.model.OpzioneC;

public class StoricoFrame extends JFrame {
	
	private JLabel stoTit;
	private JPanel schermo;
	private JButton indBottone;
	
	private JTable storico;

	public StoricoFrame() {
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
		
		stoTit = new JLabel("Storico");
		stoTit.setPreferredSize(new Dimension(300, 70));
		stoTit.setHorizontalAlignment(SwingConstants.CENTER);
		stoTit.setFont(new Font("Arial", 1, 18));
		
		schermo = new JPanel();
		
		storico = new JTable(0, 0);
		JScrollPane sv = new JScrollPane(storico);		
		sv.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		storico.setBackground(Color.WHITE);
		schermo.add(sv);
		
		DefaultTableModel model = (DefaultTableModel) storico.getModel();
		model.addColumn("Consultazione");
		model.addColumn("Voto");
		
		JPanel iSchermo = new JPanel();
		indBottone = new JButton("Indietro");
		iSchermo.add(indBottone);
		
		add(schermo, BorderLayout.CENTER);
		add(iSchermo, BorderLayout.SOUTH);
	}

	public JTable getStorico() {
		return storico;
	}

	public void setStorico(IConsultazioneC consultazione, ArrayList<OpzioneC> voti) {
		DefaultTableModel model = (DefaultTableModel) storico.getModel();
		int i = 0;
		for(OpzioneC v : voti)  {
			model.insertRow(i, new String[] {consultazione.getQuesito(), v.getDescription()});
			i++;
		}
	}

	public JButton getIndBottone() {
		return indBottone;
	}

	public void setIndBottone(JButton indBottone) {
		this.indBottone = indBottone;
	}
	
}
