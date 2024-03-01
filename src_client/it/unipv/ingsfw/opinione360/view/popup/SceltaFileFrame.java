package it.unipv.ingsfw.opinione360.view.popup;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Schermata che permette di scegliere un file dal file system
 */
public class SceltaFileFrame extends JFrame {

	private JFileChooser chooser;
	
	public SceltaFileFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);
		setLocation(screenWidth / 4, screenHeight / 4);
		setTitle("Opinione 360");
		ImageIcon icon = new ImageIcon("resources/Logo_Opinione360.png");
		setIconImage(icon.getImage());
		setResizable(false);
		initComponents();
	}

	private void initComponents() {
		FileFilter filter1 = new FileNameExtensionFilter("PNG & JPEG Images", "png", "jpeg");
		FileFilter filter2 = new FileNameExtensionFilter("TXT Texts", "txt");
		chooser = new JFileChooser();
		chooser.addChoosableFileFilter(filter1);
		chooser.setFileFilter(filter1);
		chooser.addChoosableFileFilter(filter2);
		chooser.setFileFilter(filter2);
	    chooser.setDialogTitle("Opinione360");
	}
	
	public File getFile() {
		int scelta = chooser.showOpenDialog(this);
		if(scelta == JFileChooser.APPROVE_OPTION) 
			return chooser.getSelectedFile();
		else
			return null;
	}

}
