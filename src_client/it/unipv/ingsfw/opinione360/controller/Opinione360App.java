package it.unipv.ingsfw.opinione360.controller;

import javax.swing.JFrame;

import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.view.VotoFrame;

public class Opinione360App {

	public static void main(String[] args) {
		VotoFrame vf = new VotoFrame();
		DomainFacade df = DomainFacade.getInstance(); 
		CVotoController c = new CVotoController(vf, df);
		vf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vf.setVisible(true);
	}

}
