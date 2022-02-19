package com.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class apropos {

	
	private final static String newline = "\n";

	
	public apropos() {
		cadre();
	}
	
	private void cadre() {
		JFrame window = new JFrame("A PROPOS DE");
		JPanel panel = new JPanel(new GridLayout());
		window.getContentPane().add(panel);
		window.setSize(250, 150);
		window.setResizable(false);
		JTextArea jtxt = new JTextArea();
		jtxt.setEditable(false);
		jtxt.append("**********************************************"+newline+"          APPLICATION  QWANT-LAB"+newline+"**********************************************"+newline+"             Version 1.02 /  2018-2020"+newline+newline+"Programme Conçu & Développé par : "+newline+"Adrien AUBOURG");
		
		
		panel.add(jtxt);
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
