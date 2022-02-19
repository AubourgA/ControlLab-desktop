package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import connexion.BD.connexion;

public class Liste_Mortar extends Habillage{

	private static Connection connect; //creer varaible connect
	private JFrame win;
	private JPanel prime, buttonPane,ContentPane,ContentPane2;
	private JLabel lab1,lchoix, addbut_label, supbut_label;
	private JButton ajout_but, supp_but;
	private JComboBox<String> cliste;
	private JRadioButton m1,m2;
	public Liste_Mortar() {
		createGUI();
		ChargeListe();
	}
	
	
private void createGUI() {
	win = new JFrame("LISTE  MORTIER POUR ESSAIS COMPLEMENTAIRES");
	win.setSize(definition.TP_Large,definition.TP_Haut);
	win.setLocationRelativeTo(null);
    win.setResizable(false);
    
    //creation panel
    prime = new SmallWindow();
    prime.setLayout(new BorderLayout());
    ContentPane = new JPanel();
    ContentPane2 = new JPanel();
    buttonPane = new JPanel();
    
    //option panel
    ContentPane.setOpaque(false);
    ContentPane2.setOpaque(false);
    buttonPane.setOpaque(false);
   
    
    
    //creation composant
    lab1 = new JLabel("MODIFIER LISTE ACTUELLE : ");
    lab1.setForeground(Color.BLACK);
    cliste = new JComboBox<>();
    cliste.setEditable(true);
    lchoix = new JLabel("Choix type de suivi des Essai Mecanique : ");
    lchoix.setForeground(Color.BLACK);
    ajout_but = new JButton();
    supp_but = new JButton();
    ButtonGroup bg = new ButtonGroup();
    m1 = new JRadioButton("CE/NF");
    m2 = new JRadioButton("Autre");
    m1.setOpaque(false); m2.setOpaque(false);
    //assocaition des Jradiobutton
    bg.add(m1);
    bg.add(m2);
    
    
    //image sur bouton
    ImageIcon addbut_icon = new ImageIcon(getClass().getResource(definition.URL_AJOUTER));
    addbut_label = new JLabel("AJOUER",addbut_icon,JLabel.CENTER);
    ajout_but.add(addbut_label); 
    ImageIcon supbut_icon = new ImageIcon(getClass().getResource(definition.URL_SUPPRIMER));
    supbut_label = new JLabel("SUPPRIMER",supbut_icon,JLabel.CENTER);
    supp_but.add(supbut_label); 
    
    //association composant - panel
    ContentPane.add(lab1);
    ContentPane.add(cliste);
    ContentPane2.add(lchoix);
    ContentPane2.add(m1);
    ContentPane2.add(m2);
    buttonPane.add(ajout_but);
    buttonPane.add(supp_but);
    
    //Ecouteur
    ajout_but.addActionListener(new AddListe());
    supp_but.addActionListener(new SuppListe());
    prime.addMouseListener(new myMouseListener());
    
    //option frame
    win.getContentPane().add(prime);
    prime.add(ContentPane, BorderLayout.NORTH);
    prime.add(ContentPane2, BorderLayout.CENTER);
    prime.add(buttonPane, BorderLayout.SOUTH);
    win.setVisible(true);
}
	
//*** CLASS BOUTON ***
public class AddListe implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
	try {
		 connect= connexion.getConnection();
		 String str = "INSERT INTO MORTAR_LISTE (NOM,TYPE) VALUES (?,?)";
		 PreparedStatement ps = connect.prepareStatement(str);
		 ps.setString(1, (String) cliste.getSelectedItem());
		
		 //en fonction du choix des JRadoButton
		if(m1.isSelected() ) {
			  ps.setString(2, "C");
			 
		   } else if(m2.isSelected() ) {
			     ps.setString(2, "A");
		       } 
		 ps.executeUpdate();
	     ps.close();
			
			JOptionPane.showMessageDialog(null,"La Liste a ete modifié");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
		
		
	}
}

public class SuppListe implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
		try {
			 connect= connexion.getConnection();
			 String str = "DELETE FROM  MORTAR_LISTE WHERE NOM = ?";
			 PreparedStatement ps = connect.prepareStatement(str);
			 ps.setString(1, (String) cliste.getSelectedItem());
			 ps.executeUpdate();
		     ps.close();
				
				JOptionPane.showMessageDialog(null,"La Liste a ete modifié");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
	}
}

private void ChargeListe() {
	
	try {
		 cliste.removeAllItems(); //supprime element residuel
		connect= connexion.getConnection(); //initialisation connection
		
		String sql = "SELECT NOM FROM MORTAR_LISTE ORDER BY NOM"; // selecton type de donne de la base
		PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
		
		ResultSet rs = pState.executeQuery(); // resultat de la selection
		//ajout element 1 a 1
		while(rs.next()) {
			cliste.addItem(rs.getString("NOM"));
		}
		//ferme les connexions
		cliste.setSelectedIndex(-1);
		rs.close();  
		pState.close();
	}catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	}}

}
