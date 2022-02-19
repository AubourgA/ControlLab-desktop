package com.gui;


import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
//import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;

import connexion.BD.connexion;

public class Alerte extends prime{

	private static Connection connect; //creer varaible connect
	private Image img_Alerte;
	private JFrame win;
	private JLabel liste,designation,periode,date1,date2,message;
	private JButton ajout_but, supp_but;
	private JTextField txtdes,tdate1,tdate2,txmess;
	private JComboBox<String> cliste2, cperiode;
	
	
	
public Alerte() {
	    this.zonetxtsud2.setText("");
	    imgAlerte();
	    createGUI();
	    ChargeIntitule();
	  
	    
}

//methode pour afficher au début message - Alerte pour le controleur 
protected static void PrimeAlerte() {
	 
	 try {
			
			connect= connexion.getConnection(); //initialisation connection
			
			
			String sql = "SELECT COUNT(DESIGNATION) FROM LISTE_ALERTE  "; // selecton type de donne de la base
			PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
			
			ResultSet rs = pState.executeQuery(); // resultat de la selection
			//ajout element 1 a 1
			while(rs.next()) {
				JOptionPane.showMessageDialog(null, "Il y a : "+rs.getString(1)+" alerte(s) à surveiller !");			
			}
			//ferme les connexions
			
			rs.close();  
			pState.close();
		}catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		     }
	 
}


private void createGUI() {
	win = new JFrame("PARAMETRAGE D'UNE ALERTE");
	win.setSize(definition.SS_Large,definition.SS_Haut);
	win.setLocationRelativeTo(null);
    win.setResizable(true);
    
    //def panel
  
    JPanel pl = new PPanel();
     pl.setLayout(new GridBagLayout());
     
     GridBagConstraints gbc = new GridBagConstraints();
    
    //def composant
    liste = new JLabel("LISTE ACTUELLE : ");
    designation = new JLabel("INTITULE ALERTE : ");
  
    periode = new JLabel("PERIODE : ");
    date1 = new JLabel("DATE DEBUT : ");
    date2 = new JLabel("DATE DE FIN : ");
    message = new JLabel("MESSAGE : ");
    ajout_but = new JButton();
    
   
  //position
  		gbc.gridx =0;
  		gbc.gridy =0;
  		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
  		gbc.insets = new Insets(0,10,10,0);
  		pl.add(liste, gbc);
  		gbc.gridy=1;	
  		pl.add(designation,gbc);
  		gbc.gridy=2;
  		pl.add(periode,gbc);
  		gbc.gridy=3;
  		pl.add(date1,gbc);
  		gbc.gridy=4;
  		pl.add(date2,gbc);
  		gbc.gridy=5;
  		pl.add(message,gbc);
  		gbc.gridy=6;
  		gbc.insets = new Insets(20,10,0,0);
  		pl.add(ajout_but,gbc);
  		
  		
    //def 2em colonnes
    txtdes = new JTextField(10);
    tdate1 = new JTextField(5);
    tdate2 = new JTextField(5);
    txmess = new JTextField(15);
    cliste2 = new JComboBox<>();
    cliste2.setEditable(false);
    String[] cp = {"PERMANENT","TEMPORAIREMENT"};
    cperiode = new JComboBox<>(cp);
    supp_but = new JButton();
    
    //position
		gbc.gridx =1;
		gbc.gridy =0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0,10,10,0);
		pl.add(cliste2, gbc);
		gbc.gridy=1;
		pl.add(txtdes,gbc);
		gbc.gridy=2;
		pl.add(cperiode,gbc);
		gbc.gridy=3;
		pl.add(tdate1,gbc);
		gbc.gridy=4;
		pl.add(tdate2,gbc);
		gbc.gridy=5;
		pl.add(txmess,gbc);
		gbc.gridy=6;
		gbc.insets = new Insets(20,10,0,0);
		pl.add(supp_but,gbc);
    
    

		//ajout icon au bouton
		 ImageIcon add_icon = new ImageIcon(getClass().getResource(definition.URL_AJOUTER));
		 JLabel iconlab_add = new JLabel("AJOUTER",add_icon,JLabel.CENTER);
		 ajout_but.add(iconlab_add);
		
		 ImageIcon supp_icon = new ImageIcon(getClass().getResource(definition.URL_SUPPRIMER));
		 JLabel iconlab_supp = new JLabel("SUPPRIMER",supp_icon,JLabel.CENTER);
		 supp_but.add(iconlab_supp); 
		
    //ecoute sur compoment 
    ajout_but.addActionListener(new AddAlerte());
    supp_but.addActionListener(new SuppAlerte());
    cperiode.addItemListener(new ActiveDate());
    
    win.getContentPane().add(pl);
    win.setVisible(true);
}

//
public class ActiveDate implements ItemListener {
	public void itemStateChanged(ItemEvent arg) {
		 switch ((String)cperiode.getSelectedItem()) {
	            case "PERMANENT":	
               tdate1.setEnabled(false);
                tdate2.setEnabled(false);
               
             break;
	          case "TEMPORAIREMENT":
	        	
                 tdate1.setEnabled(true);
                 tdate2.setEnabled(true);
           break;
}}}


//*** CLASS BOUTON ***
public class AddAlerte implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
		
		// en fonction de la periodicite => affichage du formulaire a remplir
	switch((String)cperiode.getSelectedItem())	{
	case "PERMANENT":
		try {
			 connect= connexion.getConnection();
			 String str = "INSERT INTO LISTE_ALERTE (DESIGNATION,PERIODE,MESSAGE) VALUES (?,?,?)";
			 PreparedStatement ps = connect.prepareStatement(str);
			 ps.setString(1, txtdes.getText());
			 ps.setString(2, (String)cperiode.getSelectedItem());
			 ps.setString(3, txmess.getText());
			 
			 ps.executeUpdate();
		     ps.close();
				
				JOptionPane.showMessageDialog(null,"La Liste a ete modifié");
				
				win.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		break;
		
	case "TEMPORAIREMENT" :
		try {
			 connect= connexion.getConnection();
			 String str = "INSERT INTO LISTE_ALERTE (DESIGNATION,PERIODE,DATE_DEBUT,DATE_FIN,MESSAGE) VALUES (?,?,?,?,?)";
			 PreparedStatement ps = connect.prepareStatement(str);
			 ps.setString(1, txtdes.getText());
			 ps.setString(2, (String)cperiode.getSelectedItem());
			 ps.setString(3, tdate1.getText());
			 ps.setString(4, tdate2.getText());
			 ps.setString(5, txmess.getText());
			 
			 ps.executeUpdate();
		     ps.close();
				
				JOptionPane.showMessageDialog(null,"La Liste a ete modifié");
				
				win.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		break;
		
		
		}}
}
public class SuppAlerte implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
		try {
			 connect= connexion.getConnection();
			 String str = "DELETE FROM LISTE_ALERTE WHERE DESIGNATION =?";
			 PreparedStatement ps = connect.prepareStatement(str);
			 ps.setString(1, (String)cliste2.getSelectedItem());
			 ps.executeUpdate();
		     ps.close();
				
				JOptionPane.showMessageDialog(null,"La Liste a ete modifié");
				
				win.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		
		
}}
	

private void ChargeIntitule() {
		 cliste2.removeAllItems(); //supprime element residuel
		 cperiode.setSelectedIndex(1);
		 try {
			
			connect= connexion.getConnection(); //initialisation connection
			
			String sql = "SELECT DESIGNATION FROM LISTE_ALERTE ORDER BY DESIGNATION"; // selecton type de donne de la base
			PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
			
			ResultSet rs = pState.executeQuery(); // resultat de la selection
			//ajout element 1 a 1
			while(rs.next()) {
				cliste2.addItem(rs.getString(1));
			}
			//ferme les connexions
			cliste2.setSelectedIndex(-1);
			rs.close();  
			pState.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		      }	}

private void imgAlerte() {
	//affichage image de fond
	 Toolkit kit = Toolkit.getDefaultToolkit();
	 img_Alerte = kit.getImage(getClass().getResource(definition.URL_FONDLISTE));
	 img_Alerte = img_Alerte.getScaledInstance(800, -1, Image.SCALE_SMOOTH); 
 }


 
		//class fond
private class PPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g)
		        {
		            //  **** don't forget to call the super method first
		            super.paintComponent(g);
		            g.drawImage(img_Alerte,0,0,this);
  } } 

 


}
