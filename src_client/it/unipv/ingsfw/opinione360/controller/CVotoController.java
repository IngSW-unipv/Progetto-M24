package it.unipv.ingsfw.opinione360.controller;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import it.unipv.ingsfw.opinione360.model.DomainFacade;
import it.unipv.ingsfw.opinione360.model.SondaggioC;
import it.unipv.ingsfw.opinione360.view.VotoFrame;

public class CVotoController {

	private final VotoFrame vf;
	private final DomainFacade df;
	
	public CVotoController(VotoFrame vf, DomainFacade df) {
		this.vf = vf;
		this.df = df;
		setListeners();
	}

	private void setListeners() {
		ActionListener consAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vf.remove(vf.getConsTit());
				vf.remove(vf.getConsScreen());
				vf.add(vf.getDescScreen(), BorderLayout.NORTH);
				vf.add(vf.getOpScreen(), BorderLayout.CENTER);
				vf.add(vf.getICScreen(), BorderLayout.SOUTH);
				SondaggioC s = df.getSondaggio();
				vf.setB1Tit(s.getDescrizione());
				vf.setOpButtons(s.getOpzioni());
				vf.repaint();
				vf.validate();
			}
		};
		for(JButton b : vf.getConsButtons())
			b.addActionListener(consAl);
		
		ActionListener indAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vf.remove(vf.getDescScreen());
				vf.remove(vf.getOpScreen());
				vf.remove(vf.getICScreen());
				vf.add(vf.getConsTit(), BorderLayout.NORTH);
				vf.add(vf.getConsScreen(), BorderLayout.CENTER);
				vf.repaint();
				vf.validate();
			}
		};
		vf.getIndButton().addActionListener(indAl);
		
		ActionListener confAl = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<JRadioButton> b = vf.getOpButtons();
				ArrayList<Integer> id_opzioni = new ArrayList<Integer>();
				for(int i = 0; i < b.size(); i++)                              // Spostare nel domainfacade?
					if(b.get(i).isSelected())
						id_opzioni.add(i);
				try {
					df.vota(df.getSondaggio(), id_opzioni);
				}
				catch(IndexOutOfBoundsException er) {     
					VotoFrame.errOpFrame eof = vf.new errOpFrame();
					eof.setVisible(true);
				}
			}
		};
		vf.getConfButton().addActionListener(confAl);
	}
	
}
