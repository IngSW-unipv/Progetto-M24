package it.unipv.ingsfw.opinione360.view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class VotoFrame extends JFrame {
	
	private JPanel consSchermo;                     // cons = consultazione
	private JPanel opSchermo;                       // op = opzioni
	private JPanel icSchermo;                       // ic = indietro-conferma
	private JTextField consTit;
	private JTextField descTit;                     // desc = descrizione
	private ArrayList<JButton> consBottoni;         
	private ArrayList<JRadioButton> opBottoni;
	private JButton indBottone;                     // ind = indietro
	private JButton confBottone;                    // conf = conferma
	
	private String b1Tit = null;

	public VotoFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		Image img = kit.getImage("icon.gif");
		setIconImage(img);
		setTitle("Opinione 360");
		consBottoni = new ArrayList<>();
		opBottoni = new ArrayList<>();
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		JButton b1 = new JButton("Miglior pilota di Formula1?");
		consBottoni.add(b1);
		
		consTit = new JTextField("Scegli la consultazione che ti interessa");
		consTit.setBackground(this.getBackground());
		consTit.setBorder(null);
		consTit.setHorizontalAlignment(SwingConstants.CENTER);
		consTit.setFont(new Font(null, 1, 18));
		consTit.setEditable(false);
		
		consSchermo = new JPanel();
		//consSchermo.setLayout(new GridLayout(5, 2, 40, 40));
		consSchermo.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));
		for(JButton cb : consBottoni)
			consSchermo.add(cb);
		
		add(consTit, BorderLayout.NORTH);
		add(consSchermo, BorderLayout.CENTER);
		
		descTit = new JTextField();
		descTit.setBackground(this.getBackground());
		descTit.setBorder(null);
		descTit.setHorizontalAlignment(SwingConstants.CENTER);
		descTit.setFont(new Font(null, 1, 18));
		descTit.setEditable(false);
	
		opSchermo = new JPanel(new GridLayout(5, 2));
		
		icSchermo = new JPanel();
	
		indBottone = new JButton("Indietro");
		icSchermo.add(indBottone);
		confBottone = new JButton("Conferma");
		icSchermo.add(confBottone);
		
	}
	
	public class errOpFrame extends JFrame {
		
		public errOpFrame() {
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;
			setSize(screenWidth / 4, screenHeight / 4);
			setLocation(screenWidth / 3, screenHeight / 3);
			Image img = kit.getImage("icon.gif");
			setIconImage(img);
			setTitle("Errore");
			JLabel l = new JLabel("Seleziona almeno un'opzione");
			l.setHorizontalAlignment(SwingConstants.CENTER);
			add(l);
		}
	}
	
	public void setB1Tit(String b1Tit) {
		this.b1Tit = b1Tit;
		descTit.setText(b1Tit);
	}

	public void setOpButtons(String[] scelte) {
		opBottoni.removeAll(opBottoni);
		opSchermo.removeAll();
		for(int i = 0; i < scelte.length; i++)
			opBottoni.add(new JRadioButton(scelte[i]));
		for(JRadioButton ob : opBottoni)
			opSchermo.add(ob);	
	}

	public ArrayList<JButton> getConsButtons() {
		return consBottoni;
	}
	
	public ArrayList<JRadioButton> getOpButtons() {
		return opBottoni;
	}
	
	public JButton getIndButton() {
		return indBottone; 
	}
	
	public JButton getConfButton() {
		return confBottone;
	}
	
	public JTextField getConsTit() {
		return consTit;
	}

	public JPanel getConsScreen() {
		return consSchermo;
	}
	
	public JTextField getDescScreen() {
		return descTit;
	}

	public JPanel getOpScreen() {
		return opSchermo;
	}
	
	public JPanel getICScreen() {
		return icSchermo;
	}
	
}
