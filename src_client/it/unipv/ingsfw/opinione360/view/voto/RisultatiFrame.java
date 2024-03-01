package it.unipv.ingsfw.opinione360.view.voto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;

import it.unipv.ingsfw.opinione360.model.OpzioneC;

/**
 * Schermata che visualizza i risultati di una consultazione terminata
 * @see ConsultazioniFrame
 */
public class RisultatiFrame extends JFrame {
	
	private DefaultPieDataset<String> pieDataSet;
	private JFreeChart pieChart;
	private PiePlot<String> piePlot;
	private ChartPanel chartPanel;
	private JPanel risSchermo;
	private JButton indBottone;

	/**
	 * Costruttore senza parametri
	 */
	public RisultatiFrame() {
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
		risSchermo = new JPanel();
		
		JPanel iSchermo = new JPanel();
		indBottone = new JButton("Torna all'elenco consultazioni");
		iSchermo.add(indBottone);
		
		add(risSchermo, BorderLayout.CENTER);
		add(iSchermo, BorderLayout.SOUTH);
	}

	/**
	 * Visualizza i risultati della consultazione mediante un grafico a torta
	 * @param opzioni lista di opzioni
	 * @param contatore insieme dei voti per ogni opzione
	 */
	public void setPieChart(HashMap<OpzioneC, Integer> risultati) {
		pieDataSet = new DefaultPieDataset<>();

		for(OpzioneC r : risultati.keySet())
			pieDataSet.setValue(r.getDescription(), risultati.get(r));

		pieChart = ChartFactory.createPieChart("Risultati", pieDataSet, true, true, false);
		
		piePlot = (PiePlot<String>) pieChart.getPlot();
		piePlot.setStartAngle(290);
		piePlot.setDirection(Rotation.CLOCKWISE);
		//PiePlot.setForegroundAlpha(Window.opacity);
	     
		chartPanel = new ChartPanel(pieChart);
		
		risSchermo.setLayout(new BorderLayout());
		
		chartPanel.removeAll();
		risSchermo.add(chartPanel, BorderLayout.CENTER);
		chartPanel.validate();
	}

	public JButton getIndBottone() {
		return indBottone;
	}
	
}
