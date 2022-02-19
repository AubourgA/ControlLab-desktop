package com.gui;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import connexion.BD.connexion;

public class SuiviVal {
	private static Connection connect; //creer varaible connect
	
	JTable tab_hist;
	
	public SuiviVal() {
		createView();
	}
	
	
	private void createView() {
		    //creation de la fenetre
		   
				JFrame boxH = new JFrame("Suivi Modification Valeur");
				boxH.setSize(400,400);
				boxH.setLocationRelativeTo(null);
			
				JPanel paneBox = new JPanel();
				paneBox.setLayout(new BoxLayout(paneBox, BoxLayout.Y_AXIS));
				JButton Exporter = new JButton("Exporter");
				Exporter.setAlignmentX(Component.CENTER_ALIGNMENT);
				
			
				
			   //affiche du Tableau
				 tab_hist = new JTable();
				Object [] hist_col = {"NOM PF","LOT","DATE MODIFICATION", "CRITERE" };
				DefaultTableModel mod_hist = new DefaultTableModel();
				mod_hist.setColumnIdentifiers(hist_col);
				tab_hist.setModel(mod_hist);
				JScrollPane tableHist = new JScrollPane(tab_hist);
				tableHist.setPreferredSize(new Dimension(30,50));
				tab_hist.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,10));
				Object[] row_hist = new Object[4];
		
				
				
				
				//insertion info BDD dans le tableau
				
				try {
					connect= connexion.getConnection(); //fait appel a la classe connexion
					String histo = "SELECT NOM_PF,DATE,N_LOT,CRITERE FROM tale_HISTO ORDER BY NOM_PF";
					PreparedStatement pshisto = (PreparedStatement) connect.prepareStatement(histo);
					
					ResultSet rshisto = pshisto.executeQuery(); // resultat de la selection
					while(rshisto.next()) {
						//ajout element dans tableau
						row_hist[0] = rshisto.getString("NOM_PF");
						row_hist[1] = rshisto.getString("N_LOT");
						row_hist[2] = rshisto.getString("DATE");
						row_hist[3] = rshisto.getString("CRITERE");
						mod_hist.addRow(row_hist);		
				     	}
					
					rshisto.close();
					pshisto.close();
					
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				   }
				 finally {
			    	  try {
						connect.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			      }

	//fonction bouton
	Exporter.addActionListener(new EXPORTER());
				
				
	 //affichage fenetre
	
	paneBox.add(tableHist);
	paneBox.add(Exporter);
	boxH.getContentPane().add(paneBox);
	boxH.setVisible(true);	
				
	}
	
	public class EXPORTER implements ActionListener {
		  public void actionPerformed(ActionEvent e) {	 
			  SaveData PrintExcel = new SaveData();
			  PrintExcel.TableToExcel(tab_hist, new File(definition.getPathDesktop+File.separator+"Suivi Modif.xls"), "Fichier créé sur le bureau : Suivi Modif.xls");
		  }
	}
	
}
