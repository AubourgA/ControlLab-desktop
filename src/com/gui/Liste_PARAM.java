package com.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.jdbc.PreparedStatement;

import connexion.BD.connexion;

public class Liste_PARAM extends Habillage {

	 private static Connection connect; //creer varaible connect
	private JFrame win;
	private JPanel pane, pane1,pane3;
	private JLabel lab1, lab2, lab_eau,lab_res, lab_poudre, lab_dmin,lab_dmax,lab_rcmin;
	private JLabel add_lab, mod_lab, sup_lab;
	private JTextField txt_eau,txt_res, txt_poudre, txt_dmin,txt_dmax,txt_rcmin;
	private JTextArea are_gach;
	private JComboBox<String> cprod;
	private JButton add_but, mod_but, supp_but;
	private JScrollPane areaScroll;
	
	public Liste_PARAM() {
		createGUI();
		ChargerCombox();
	}
	


private void createGUI() {
	win = new JFrame("DEFINITION DES CRITERES POUR ESSAIS SUPPLEMENATAIRES");
    win.setResizable(false);
    
    //def des panel
    pane = new SmallWindow();
    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
   
    pane1 = new JPanel(new GridBagLayout()); 
    pane3 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
    
    
    //desfinition des composant
    lab1 = new JLabel("CHOIX DU PRODUIT : ");
    lab2 = new JLabel("METHODE DE GACHAGE : ");
    lab_eau = new JLabel("QUANTITE EAU (g) : ");
    lab_res = new JLabel("QUANTITE RESINE (g) : ");
    lab_poudre = new JLabel("QUANTITE POUDRE (g) : ");
    lab_dmin = new JLabel("DENSITE MIN : ");
    lab_dmax = new JLabel("DENSITE MAX : ");
    lab_rcmin = new JLabel("RESISTANCE COMPRESSION MIN (MPa) : ");
    
    cprod = new JComboBox<>();
    are_gach = new JTextArea(4,15);
    areaScroll = new JScrollPane(are_gach);
    
    txt_eau  = new JTextField(5);
    txt_res  = new JTextField(5); 
    txt_poudre  = new JTextField(5);
    txt_dmin =   new JTextField(5);
    txt_dmax  = new JTextField(5);
    txt_rcmin  = new JTextField(5);
    
  
    
    //def pane1
    GridBagConstraints c1 = new GridBagConstraints();
    
    c1.gridx =0;
    c1.gridy=2;
    c1.anchor = GridBagConstraints.FIRST_LINE_END;
    c1.insets = new Insets(0,0,5,5);
    pane1.add(lab_eau,c1);
    c1.gridy=3;
    pane1.add(lab_res,c1);
    c1.gridy=4;
    pane1.add(lab_poudre,c1);
     
    c1.gridx =1;
    c1.gridy=0;
    c1.insets = new Insets(5,0,0,0);
    pane1.add(lab1,c1);
    c1.gridy=1;
    c1.insets = new Insets(20,0,30,0);
    pane1.add(lab2,c1);
    
    c1.gridwidth=1;
    c1.fill = GridBagConstraints.NONE;
     c1.gridy=2;
     c1.anchor = GridBagConstraints.FIRST_LINE_START;
     c1.insets = new Insets(0,0,5,5);
     pane1.add(txt_eau,c1);
     c1.gridy=3;
     pane1.add(txt_res,c1);
     c1.gridy=4;
     pane1.add(txt_poudre,c1);
     
     
     c1.gridx=2;
     c1.gridy=0;
     c1.insets = new Insets(5,5,0,0);
     pane1.add(cprod,c1);
     c1.gridy=1;
     c1.insets = new Insets(20,5,30,0);
     pane1.add(areaScroll,c1);
     c1.gridy=2;
     c1.anchor = GridBagConstraints.FIRST_LINE_END;
     c1.insets = new Insets(0,0,0,0);
     pane1.add(lab_dmin,c1);
     c1.gridy=3;
     pane1.add(lab_dmax,c1);
     c1.gridy=4;
     pane1.add(lab_rcmin,c1);
     
     c1.gridx=3;
     c1.gridy=2;
     c1.anchor = GridBagConstraints.FIRST_LINE_START;
     pane1.add(txt_dmin,c1);
     c1.gridy=3;
      pane1.add(txt_dmax,c1);
     c1.gridy=4;
    pane1.add(txt_rcmin,c1);
   

     //panel3 ===> definition bouton et creation icon
      
     add_but = new JButton();
	 ImageIcon add_icon = new ImageIcon(getClass().getResource(definition.URL_AJOUTER));
	 add_lab = new JLabel("AJOUTER",add_icon,SwingConstants.CENTER);
	 add_lab.setVerticalTextPosition(JLabel.BOTTOM);
	 add_lab.setHorizontalTextPosition(JLabel.CENTER);
	 add_but.setPreferredSize(new Dimension(90,60));
	 add_but.add(add_lab);
	
	 mod_but = new JButton();
	 ImageIcon mod_icon = new ImageIcon(getClass().getResource(definition.URL_MODIFIER));
	 mod_lab = new JLabel("MODIFIER",mod_icon,SwingConstants.CENTER);
	 mod_lab.setVerticalTextPosition(JLabel.BOTTOM);
	 mod_lab.setHorizontalTextPosition(JLabel.CENTER);
	 mod_but.setPreferredSize(new Dimension(90,60));
	 mod_but.add(mod_lab);
	 
	 supp_but = new JButton();
	 ImageIcon histo_icon = new ImageIcon(getClass().getResource(definition.URL_SUPPRIMER));
	 sup_lab = new JLabel("SUPPRIMER",histo_icon,SwingConstants.CENTER);
	 sup_lab.setVerticalTextPosition(JLabel.BOTTOM);
	 sup_lab.setHorizontalTextPosition(JLabel.CENTER);
	supp_but.setPreferredSize(new Dimension(110,60));
	supp_but.add(sup_lab);
    
    
    
    
    pane3.add(add_but);
    pane3.add(mod_but);
    pane3.add(supp_but);
    
    //association des panel
    pane.add(pane1);
    pane.add(pane3);
     
    //opacite des panel pour affiche image de fond
    pane1.setOpaque(false);
    pane3.setOpaque(false);
    
    //attribution des bouton & composant
    cprod.addItemListener(new AppelTable());
   add_but.addActionListener(new ActionAjout());
    mod_but.addActionListener(new ActionMod());
    supp_but.addActionListener(new ActionSupp());
    
    win.getContentPane().add(pane);
    win.pack();
    win.setLocationRelativeTo(null);
    win.setVisible(true);
}

// ***  FONCTION DIVERS
private void ChargerCombox() {
	
	try {
		 cprod.removeAllItems(); //supprime element residuel
		connect= connexion.getConnection(); //initialisation connection
		
		String sql = "SELECT NOM FROM MORTAR_LISTE ORDER BY NOM"; // selecton type de donne de la base
		PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
		
		ResultSet rs = pState.executeQuery(); // resultat de la selection
		//ajout element 1 a 1
		while(rs.next()) {
			cprod.addItem(rs.getString("NOM"));
		}
		//ferme les connexions
		cprod.setSelectedIndex(-1);
		rs.close();  
		pState.close();
	}catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	}
}

// *** FONCTION BOUTON

public class ActionAjout implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
		 try {
			 connect= connexion.getConnection();
			 String str = "INSERT into LISTE_PARA (NOM,METHODE,QTITE_EAU,QTITE_RES,QTITE_POUDRE,D_MIN,D_MAX,RC_MIN) VALUES (?,?,?,?,?,?,?,?)";
			 PreparedStatement ps = (PreparedStatement) connect.prepareStatement(str);
			 ps.setString(1, (String) cprod.getSelectedItem());
			 ps.setString(2, are_gach.getText());
			 ps.setString(3,  txt_eau.getText());
			 ps.setString(4,  txt_res.getText());
			 ps.setString(5,  txt_poudre.getText());
			 ps.setString(6,  txt_dmin.getText());
			 ps.setString(7,  txt_dmax.getText());
			 ps.setString(8,  txt_rcmin.getText());
			 
			 ps.executeUpdate();
			ps.close();
			 
			JOptionPane.showMessageDialog(null,"Les parametres pour le : "+cprod.getSelectedItem()+" ont été ajoutée");
			 win.dispose();
		 }catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			   }
		 
}}

public class ActionMod implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
		 try {
			 connect= connexion.getConnection();
			 String str = "UPDATE  LISTE_PARA  SET  METHODE = ? ,QTITE_EAU = ? ,QTITE_RES = ? ,QTITE_POUDRE = ?, D_MIN = ? ,D_MAX = ? , RC_MIN = ? WHERE NOM=?";
			 PreparedStatement ps = (PreparedStatement) connect.prepareStatement(str);
			
			 ps.setString(1, are_gach.getText());
			 ps.setString(2,  txt_eau.getText());
			 ps.setString(3,  txt_res.getText());
			 ps.setString(4,  txt_poudre.getText());
			 ps.setString(5,  txt_dmin.getText());
			 ps.setString(6,  txt_dmax.getText());
			 ps.setString(7,  txt_rcmin.getText());
			 ps.setString(8, (String) cprod.getSelectedItem());
			 ps.executeUpdate();
			ps.close();
			 
			JOptionPane.showMessageDialog(null,"Les parametres pour le : "+cprod.getSelectedItem()+" ont été modifiés");
			 win.dispose();
		 }catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			   }		 
}}

public class ActionSupp implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
		//code a ecrire
	}}

public class AppelTable implements ItemListener {
	@Override
	public void itemStateChanged(ItemEvent arg) {
		// TODO Auto-generated method stub
		   //reset donnee
		    are_gach.setText("");
			txt_eau.setText("");
			txt_res.setText("");
			txt_poudre.setText("");
			txt_dmin.setText("");
			txt_dmax.setText("");
			txt_rcmin.setText("");
		 
		if(arg.getStateChange() == ItemEvent.SELECTED){
            
             try {
            	 connect= connexion.getConnection(); //initialisation connection
 				
 				String sql = "SELECT * FROM LISTE_PARA  WHERE NOM = ?"; // selecton type de donne de la base
 				PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
 				
 				pState.setString(1, (String) cprod.getSelectedItem());
 				ResultSet rs = pState.executeQuery(); // resultat de la selection
 				//ajout element 1 a 1
 				       while(rs.next()) {
 				   
 					//ajout element donne
 					 
 					are_gach.setText(rs.getString(3));
 					txt_eau.setText(rs.getString(4));
 					txt_res.setText(rs.getString(5));
 					txt_poudre.setText(rs.getString(6));
 					txt_dmin.setText(rs.getString(7));
 					txt_dmax.setText(rs.getString(8));
 					txt_rcmin.setText(rs.getString(9));				
 					
 				//ferme les connexions
 				       }
 				    rs.close();  
 				   pState.close();
			
                  } catch (Exception e) {
            	 e.printStackTrace();
                    }	
	}}}
}
