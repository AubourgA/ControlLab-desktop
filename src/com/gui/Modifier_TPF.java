package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;

import connexion.BD.connexion;

public class Modifier_TPF extends Habillage{

	//declaration des variables
	
		private static Connection connect;
		
			private JFrame fen_MTPF;
			Image img_Modifier_TPF;
			private JPanel panel_MTPF, panel_titre,panel_O, panel_E, panel_E1, panel_E2, panel_E3;
			private JLabel  ltrame, lnom, llotfictif,lver,lveract, lfreq, lqtitep, lmod, lprep,  lpart, lnb, ldate, lqtite, lcarac, ltype,lmin, lmax,crit_max,crit2_max;
			private JLabel print_label,trans_label,histo_label,lVrai_Faux;
			private JTextField  ctrame,tver, tfreq, tqtitep, tqtite, tmod, tpart, tnb, tdate, tmin, tmax;
		    private JCheckBox Box_lot,Vrai_Faux;
		    private JTextArea tprep;
		    private JButton b_ajout, b_suppr, b_visualtrame, b_record, histo;
		    private JTable tab_crit;
		    private DefaultTableModel mod;
		    private JComboBox <String>  cnom,ctype,typever,ccrit;
		    private Object[] tabcrit_Row;
		   private String Categorie;
		   public static String chemin ="\\";
		    int i=25;
		    
		public Modifier_TPF() {	
			fen_Modifier_TPF();
			AfficheComboPF();	
			ChargeListeCritere();
		}

		private void fen_Modifier_TPF() {
			fen_MTPF = new JFrame("MODIFICATION : Trame PF");
			
			fen_MTPF.setSize(definition.SLarge,definition.SHaut);
			fen_MTPF.setLocationRelativeTo(null);

		    fen_MTPF.setResizable(false);
		  
		    panel_MTPF = new ModifierPF_Panel();
		    panel_MTPF.setLayout(new BorderLayout());
		    panel_titre = new JPanel();
		    panel_titre.setOpaque(false);
		    panel_O = new JPanel(new GridBagLayout()); // partie info
		    panel_O.setOpaque(false);
		    panel_E = new JPanel(new BorderLayout()); // partie controle
		    panel_E.setOpaque(false);
		    panel_E.setPreferredSize(new Dimension(400,0));
		    panel_E1 = new JPanel(new GridBagLayout());
		    panel_E1.setOpaque(false);
		    panel_E2 = new JPanel(new FlowLayout(FlowLayout.LEFT,30,30));
		    panel_E2.setOpaque(false);
		    panel_E3 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,50));
		    panel_E3.setOpaque(false);
		    panel_E.add(panel_E1, BorderLayout.NORTH);
		    panel_E.add(panel_E2, BorderLayout.CENTER);
		    panel_E.add(panel_E3, BorderLayout.SOUTH);
		    
		    panel_MTPF.add(panel_titre, BorderLayout.NORTH);
		    panel_MTPF.add(panel_O, BorderLayout.WEST);
		    panel_MTPF.add(panel_E, BorderLayout.EAST);
		
		    //charger methodes
		    info_PF(); // methode remplissage info MP
		    carac_PF(); // methode pour caracteristique MP
		    
		    fen_MTPF.getContentPane().add(panel_MTPF);
		    fen_MTPF.setVisible(true);
		}



		private void info_PF() {

			
			//panel O
			GridBagConstraints c1 = new GridBagConstraints();
			GridBagConstraints c2 = new GridBagConstraints();
			
		   //def  des composants
			ltrame = new JLabel("Type de trame : ");
			lnom = new JLabel("Nom du Produit : ");
			llotfictif = new JLabel("Si trame avec lot : ");
			lver = new JLabel("Choix Type de Version :");
			lveract = new JLabel("Version Actuelle : ");
			
			lfreq = new JLabel("Frequence Prélévement : ");
			lqtitep = new JLabel("Quantité à prélever : ");
			lmod = new JLabel("Modop : ");
			lprep = new JLabel("Préparation : ");
			lpart = new JLabel("Particularité : ");
			lnb = new JLabel("Dernier Numero de Lot : ");
			ldate = new JLabel("Date de nouvelle Version : ");
			lqtite = new JLabel("Choix de l'unité : ");
			
			//affichage + sombre des label
			ltrame.setForeground(Color.BLACK);
			llotfictif.setForeground(Color.BLACK);
			lnom.setForeground(Color.BLACK);
			lver.setForeground(Color.BLACK);
			lveract.setForeground(Color.BLACK);
			lfreq.setForeground(Color.BLACK);
			lqtitep.setForeground(Color.BLACK);
			lmod.setForeground(Color.BLACK);
			lprep.setForeground(Color.BLACK);
			lpart.setForeground(Color.BLACK);
			lnb.setForeground(Color.BLACK);
			ldate.setForeground(Color.BLACK);
			lqtite.setForeground(Color.BLACK);
			
			//placement label
			c1.gridx =0;
			c1.gridy =0;
			c1.anchor = GridBagConstraints.FIRST_LINE_END;
			c1.insets = new Insets(18,50,0,0);
			panel_O.add(lnom, c1);
			c1.gridy=1;
			panel_O.add(ltrame, c1);
			c1.gridy=2;
			panel_O.add(llotfictif, c1);
			c1.gridy=3;
			panel_O.add(lver, c1);
			c1.gridy=4;
			panel_O.add(lveract, c1);
			c1.gridy=5;
			panel_O.add(lfreq, c1);
			c1.gridy=6;
			panel_O.add(lqtitep, c1);
			c1.gridy=7;
			panel_O.add(lmod, c1);
			c1.gridy=8;
			panel_O.add(lprep, c1);
			c1.gridy=9;
			panel_O.add(lpart, c1);
			c1.gridy=10;
			panel_O.add(lnb, c1);
			c1.gridy=11;
			panel_O.add(ldate, c1);
			c1.gridy=12;
			panel_O.add(lqtite, c1);
			
			
			String[] TypeVer = {"NOUVELLE VERSION","SOUS VERSION"};
			ctrame = new JTextField(8);
			cnom = new JComboBox<>();
			cnom.setPreferredSize(new Dimension(200,20));
			cnom.setEditable(true);
			Box_lot = new JCheckBox("Lot Fictif");
			Box_lot.setOpaque(false);
			typever = new JComboBox<>(TypeVer);
			tver = new JTextField(10);
			tfreq = new JTextField(15);
			tqtitep = new JTextField(10);
			tmod = new JTextField(20);
			tprep = new JTextArea(5,20);
			JScrollPane tprep_sp = new JScrollPane(tprep);
			
			tpart = new JTextField(20);
			tnb = new JTextField(5);
			tdate = new JTextField(11);
			tqtite = new JTextField(11);
			
			
			c2.gridx =1;
			c2.gridy =0;
			c2.anchor = GridBagConstraints.FIRST_LINE_START;
			c2.insets = new Insets(16,10,0,0);
			panel_O.add(cnom, c2);
			c2.gridy=1;
			panel_O.add(ctrame, c2);
			c2.gridy=2;
			panel_O.add(Box_lot, c2);
			c2.gridy=3;
			panel_O.add(typever, c2);
			c2.gridy=4;
			panel_O.add(tver, c2);
			c2.gridy=5;
			panel_O.add(tfreq, c2);
			c2.gridy=6;
			panel_O.add(tqtitep, c2);
			c2.gridy=7;
			panel_O.add(tmod, c2);
			c2.gridy=8;
			panel_O.add(tprep_sp, c2);
			c2.gridy=9;
			panel_O.add(tpart, c2);
			c2.gridy=10;
			panel_O.add(tnb, c2);
			c2.gridy=11;
			panel_O.add(tdate, c2);
			c2.gridy=12;
			panel_O.add(tqtite, c2);
			
			//Action sur les combobox
			cnom.addItemListener(new AppelTramePF()); // class appel la trame a modifier
			typever.addItemListener(new ChoixVersion()); //class active/desactive formulaire
			
			
			
			//option sur CheckBox LOT FICTIF en fonction liste de choix du type de trame
			ctrame.addActionListener(new ActionListener() {
	           
	            public void actionPerformed(ActionEvent e) {
	               if(ctrame.getText()=="SANS LOT") {
	            	   Box_lot.setEnabled(false);
	                                                        }
	               else if(ctrame.getText()=="AVEC LOT") {
	            	   Box_lot.setEnabled(true);
	               }}
	            });	
		}

		private void carac_PF() {
			//panel E
			GridBagConstraints c3 = new GridBagConstraints();
			GridBagConstraints c4 = new GridBagConstraints();
			
			lcarac = new JLabel("Nouveau Critére : ");
			ltype = new JLabel("Type du Critere (?) : ");
			lVrai_Faux = new JLabel("Inserer Boolean : ");
			lmin = new JLabel(" Valeur MIN : ");
			lmax = new JLabel("Valeur MAX : ");
			b_ajout = new JButton("AJOUTER");
			crit_max = new JLabel("Nb Criteres disponibles : ");
			
			//couloueur des composant
			lcarac.setForeground(Color.BLACK);
			ltype.setForeground(Color.BLACK);
			lmin.setForeground(Color.BLACK);
			lmax.setForeground(Color.BLACK);
			crit_max.setForeground(Color.BLACK);
			lVrai_Faux.setForeground(Color.BLACK);
			
			//info bulle
			ltype.setToolTipText("<html> A : pour Autre critere<br/> CS : pour Controle de Secours<br/>AL : pour Autre Critere avec Liste de choix<br/> S : pour controle Supplemantaire uniquement<br/>L : pour Listing<br/>G  : pour Controle de secours, mode dégradé<br/>SG : Controle Suplémantaire et de secours</html>");
			
			
			//placement des labels
			c3.gridx =0;
			c3.gridy =0;
			c3.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
			c3.insets = new Insets(60,0,0,0);
			panel_E1.add(ltype,c3);
			c3.insets = new Insets(10,0,0,0);
			c3.gridy = 1;
			panel_E1.add(lcarac,c3);
			c3.gridy = 2;
			panel_E1.add(lVrai_Faux,c3);
			c3.gridy=3;
			panel_E1.add(lmin,c3);
			c3.gridy=4;
			panel_E1.add(lmax,c3);
			c3.gridy=5;
			panel_E1.add(b_ajout,c3);
			c3.gridy=6;
			panel_E1.add(crit_max,c3);
			
			// def et placement des enregistrements
			
			ccrit = new JComboBox<>(); 
			ccrit.setEditable(true);
			ccrit.setPreferredSize(new Dimension(150,25));
			String[] T = {"A","AL","CS","L","G","SG","S"};
			ctype = new JComboBox<>(T);
			Vrai_Faux = new JCheckBox(" VRAI/FAUX "); 
			Vrai_Faux.setOpaque(false);
			tmin = new JTextField(5);
			tmax = new JTextField(5);
			b_suppr = new JButton("SUPPRIMER");
			crit2_max = new JLabel("25");  //a modifer dnas code final
			
			c4.gridx =1;
			c4.gridy =0;
			c4.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
			c4.insets = new Insets(60,10,0,40);
			panel_E1.add(ctype, c4);
			c4.insets = new Insets(10,10,0,40);
			c4.gridy=1;
			panel_E1.add(ccrit,c4);
			c4.gridy=2;
			panel_E1.add(Vrai_Faux,c4);
			c4.gridy=3;
			panel_E1.add(tmin,c4);
			c4.gridy=4;
			panel_E1.add(tmax,c4);
			c4.gridy=5;
			panel_E1.add(b_suppr,c4);
			c4.gridy=6;
			panel_E1.add(crit2_max,c4);
			
			//creation du tableau pour afficher les criteres
			tab_crit = new JTable();
			Object [] col1 = {"TYPE", "CRITERES", "MIN", "MAX" };
			mod = new DefaultTableModel();
			mod.setColumnIdentifiers(col1);
			tab_crit.setModel(mod);
			tab_crit.setShowHorizontalLines(false);
			tab_crit.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			JScrollPane tableSP = new JScrollPane(tab_crit);
			//tableSP.setPreferredSize(new Dimension(300,150));
			tableSP.setPreferredSize(new Dimension(definition.tablesp_Large,definition.tablesp_Haut));
			
			panel_E2.add(tableSP, FlowLayout.LEFT);
			tab_crit.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,10));
			tab_crit.getTableHeader().setBackground(Color.YELLOW);
			tabcrit_Row = new Object[4];
	
			
			//icon bouton
			b_visualtrame = new JButton();
			 ImageIcon print_icon = new ImageIcon(getClass().getResource(definition.URL_PRINT));
			 print_label = new JLabel("IMPRIMER",print_icon,SwingConstants.CENTER);
			 print_label.setVerticalTextPosition(JLabel.BOTTOM);
			 print_label.setHorizontalTextPosition(JLabel.CENTER);
			 b_visualtrame.setPreferredSize(new Dimension(100,60));
			 b_visualtrame.add(print_label);
			
			 b_record = new JButton();
			 ImageIcon trans_icon = new ImageIcon(getClass().getResource(definition.URL_TRANSFERT));
			 trans_label = new JLabel("TRANSFERER",trans_icon,SwingConstants.CENTER);
			 trans_label.setVerticalTextPosition(JLabel.BOTTOM);
			trans_label.setHorizontalTextPosition(JLabel.CENTER);
			 b_record.setPreferredSize(new Dimension(120,60));
			 b_record.add(trans_label);
			 
			 histo = new JButton();
			 ImageIcon histo_icon = new ImageIcon(getClass().getResource(definition.URL_HISTORIQUE));
			 histo_label = new JLabel("HISTORIQUE",histo_icon,SwingConstants.CENTER);
			 histo_label.setVerticalTextPosition(JLabel.BOTTOM);
			histo_label.setHorizontalTextPosition(JLabel.CENTER);
			histo.setPreferredSize(new Dimension(110,60));
			histo.add(histo_label);
			
			
	        //association au panel
			panel_E3.add(histo, FlowLayout.LEFT);
			panel_E3.add(b_record, FlowLayout.LEFT);
			panel_E3.add(b_visualtrame, FlowLayout.LEFT);
		

		//Association boutons et action
				b_visualtrame.addActionListener(new PDFPRINT()); //visualition de la trame => PDF
				b_record.addActionListener(new MAJTrame()); // mise a jour de trame
				histo.addActionListener(new BoxHisto());   //affiche de l'historique des trames
				b_ajout.addActionListener(new ADDLINEPF()); // ajout critere
				
				b_suppr.addActionListener(new ActionListener() { //suppression d'un critere
					public void actionPerformed(ActionEvent e) {
						mod.removeRow(tab_crit.getSelectedRow());
						int sup_ligne =0;
						sup_ligne++;
						sup_ligne = Integer.parseInt(crit2_max.getText())+sup_ligne;
						crit2_max.setText(String.valueOf(sup_ligne));
				}});	
		
				//condition en fonciton de la selection ou non du VRAI/FAUX pour afficher directement dans MIN/MAX
				Vrai_Faux.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (Vrai_Faux.isSelected()) {
							tmin.setText("VRAI"); 
							tmax.setText("FAUX");
						     }else {
						    	 tmin.setText(""); 
									tmax.setText(""); 
						     }
					 }	
				});
				
				
		}
//*****************************************************************Class des  BOUTONS
		public class PDFPRINT implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				Document doc = new Document();
				chemin = chemin+"Trame_PF_"+cnom.getSelectedItem()+".pdf";
				
				
				 try {
				    PdfWriter.getInstance(doc, new FileOutputStream(chemin));
				    doc.open();
				    
			  //creation du contenu
				    Paragraph p1 = new Paragraph("TECHNIQUE BETON",FontFactory.getFont(FontFactory.HELVETICA,6));
				    Paragraph p2 = new Paragraph("CONTROLE DU PF - TRAME "+ctrame.getText(),FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
		            Paragraph p3 = new Paragraph("VALIDATION DE LA TRAME - VERSION "+tver.getText(),FontFactory.getFont(FontFactory.HELVETICA,10));
				    Paragraph block = new Paragraph("  ",FontFactory.getFont(FontFactory.HELVETICA,6));
				    p2.setAlignment(Element.ALIGN_CENTER); // centrer text
				    p3.setAlignment(Element.ALIGN_CENTER); 
				    
				    
		           PdfPTable tableValidation = new PdfPTable(1); 
		           PdfPCell cell = new PdfPCell();
		           cell.addElement(new Phrase("PAR REPONSABLE LABORATOIRE",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
		           cell.addElement(new Phrase("LE : ",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           cell.addElement(block);
		           cell.addElement(new Phrase("VISA :",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           cell.addElement(block);
		           cell.setBackgroundColor(new BaseColor(226, 226, 226));
		           tableValidation.addCell(cell);
		           
		      
		           PdfPTable TableInfo = new PdfPTable(2);
		           PdfPCell cellInfo1 = new PdfPCell(new Paragraph(lfreq.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
		           PdfPCell cellInfo2 = new PdfPCell(new Paragraph(tfreq.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
		           PdfPCell cellInfo3 = new PdfPCell(new Paragraph(lqtitep.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
		           
		           PdfPCell cellInfo4 = new PdfPCell(new Paragraph(tqtitep.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
		           PdfPCell cellInfo5 = new PdfPCell(new Paragraph(lmod.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
		           PdfPCell cellInfo6 = new PdfPCell(new Paragraph(tmod.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
		           PdfPCell cellInfo7 = new PdfPCell(new Paragraph(lprep.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
		           PdfPCell cellInfo8 = new PdfPCell(new Paragraph(tprep.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
		           PdfPCell cellInfo9 = new PdfPCell(new Paragraph(lpart.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
		           PdfPCell cellInfo10 = new PdfPCell(new Paragraph(tpart.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
		           cellInfo1.setBackgroundColor(new BaseColor(255, 255, 107));
		           cellInfo2.setBackgroundColor(new BaseColor(255, 255, 107));
		           cellInfo3.setBackgroundColor(new BaseColor(255, 255, 107));
		           cellInfo4.setBackgroundColor(new BaseColor(255, 255, 107));
		           cellInfo5.setBackgroundColor(new BaseColor(255, 255, 107));
		           cellInfo6.setBackgroundColor(new BaseColor(255, 255, 107));
		           cellInfo7.setBackgroundColor(new BaseColor(255, 255, 107));
		           cellInfo8.setBackgroundColor(new BaseColor(255, 255, 107));
		           cellInfo9.setBackgroundColor(new BaseColor(255, 255, 107));
		           cellInfo10.setBackgroundColor(new BaseColor(255, 255, 107));
		           TableInfo.addCell(cellInfo1);
		           TableInfo.addCell(cellInfo2);
		           TableInfo.addCell(cellInfo3);
		           TableInfo.addCell(cellInfo4);
		           TableInfo.addCell(cellInfo5);
		           TableInfo.addCell(cellInfo6);
		           TableInfo.addCell(cellInfo7);
		           TableInfo.addCell(cellInfo8);
		           TableInfo.addCell(cellInfo9);
		           TableInfo.addCell(cellInfo10);
		           TableInfo.setWidths(new int[] {20,120});
		           
		           PdfPTable TableStat = new PdfPTable(7);
		           TableStat.addCell(new Phrase("CUMLS SUR 1 AN",FontFactory.getFont(FontFactory.HELVETICA,7)));
		           TableStat.addCell(new Phrase("TONNAGE",FontFactory.getFont(FontFactory.HELVETICA,7)));
		           TableStat.addCell(new Phrase("  "));
		           TableStat.addCell(new Phrase("NC",FontFactory.getFont(FontFactory.HELVETICA,7)));
		           TableStat.addCell(new Phrase("  "));
		           TableStat.addCell(new Phrase("DEROG",FontFactory.getFont(FontFactory.HELVETICA,7)));
		           TableStat.addCell(new Phrase("   "));
		           
		           PdfPTable TableNom = new PdfPTable(2);
		           PdfPCell cellnom1 = new PdfPCell(new Paragraph("PF",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD)));
		           PdfPCell cellnom2 = new PdfPCell(new Paragraph((String)cnom.getSelectedItem(),FontFactory.getFont(FontFactory.HELVETICA,14,BaseColor.RED)));
		           cellnom2.setHorizontalAlignment(Element.ALIGN_CENTER);
		           TableNom.addCell(cellnom1);
		           TableNom.addCell(cellnom2);
		           TableNom.setWidths(new int[] {20,120});
		          
		           PdfPTable TableTracabililte = new PdfPTable(8);
		           TableTracabililte.addCell(new Phrase("CONFORMITE",FontFactory.getFont(FontFactory.HELVETICA,7)));
		           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,7)));
	//En fonction de type de trame => affichage de la case avec ou sans lot	          
		         if(ctrame.getText().equals("SANS LOT")) {
		        	   TableTracabililte.addCell(new Phrase("   ",FontFactory.getFont(FontFactory.HELVETICA,7)));
		               } else if (Box_lot.isSelected()){
		        	   TableTracabililte.addCell(new Phrase("N° DE LOT (Fictif)",FontFactory.getFont(FontFactory.HELVETICA,7)));
		               } else {
		            	   TableTracabililte.addCell(new Phrase("N° DE LOT",FontFactory.getFont(FontFactory.HELVETICA,7)));
		               }
		          
		           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           TableTracabililte.addCell(new Phrase("N° FICHE NC",FontFactory.getFont(FontFactory.HELVETICA,7)));
		           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           TableTracabililte.addCell(new Phrase("DEROG Oui/Vide",FontFactory.getFont(FontFactory.HELVETICA,7)));
		           TableTracabililte.addCell(new Phrase("   ",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           TableTracabililte.addCell(new Phrase("RESP DES ESSAIS",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
		       if (ctrame.getText().equals("SANS LOT")) {
		        	   TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           }else {
		        	   TableTracabililte.addCell(new Phrase("RESERVATION LOT",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           } 
		           TableTracabililte.addCell(new Phrase("   ",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           TableTracabililte.addCell(new Phrase("OBSERVATION",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           TableTracabililte.addCell(new Phrase("DATE CONTROLE",FontFactory.getFont(FontFactory.HELVETICA,6)));
		           TableTracabililte.addCell(new Phrase("   ",FontFactory.getFont(FontFactory.HELVETICA,7)));
		           TableTracabililte.setWidths(new int[] {30,25,25,25,25,25,25,25});
		           
		           PdfPTable TableCritere = new PdfPTable(4);
		           TableCritere.addCell(new Phrase(tqtite.getText(),FontFactory.getFont(FontFactory.HELVETICA,6)));
		           TableCritere.addCell(new Phrase("CRITERES",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
		           TableCritere.addCell(new Phrase("MIN",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
		           TableCritere.addCell(new Phrase("MAX",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
		           
		           for (int rows = 0; rows < tab_crit.getRowCount();rows++) {
		        	   for( int cols = 0; cols < tab_crit.getColumnCount(); cols++) {
		        		   TableCritere.addCell(new Phrase(tab_crit.getModel().getValueAt(rows, cols).toString(),FontFactory.getFont(FontFactory.HELVETICA,6)));
		        	   }
		           }
		           TableCritere.setWidths(new int[] {25,100,25,25});
				     
		           
		  //ajout contenu au doc
				    doc.add(p1);
				    doc.add(p2);
				    doc.add(p3);
				    doc.add(block);
				    doc.add(tableValidation);
				    doc.add(block);
				    doc.add(TableInfo);
				    doc.add(block);
				    doc.add(TableStat);
				    doc.add(block);
				    doc.add(TableNom);
				    doc.add(block);
				    doc.add(TableTracabililte);
				    doc.add(block);
				    doc.add(TableCritere);
				    
				    } catch (DocumentException de) {
				    	de.printStackTrace();
				   } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				 doc.close();
				 
				 try {
					Desktop.getDesktop().open(new File(chemin));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 chemin=""; 

			}	}
		
		
		public class BoxHisto implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				//creation de la fenetre
			JDialog boxH = new JDialog(fen_MTPF,"Historique Trame");
			boxH.setSize(200,200);
			boxH.setLocationRelativeTo(null);
			boxH.setVisible(true);
			JPanel paneBox = new JPanel(new BorderLayout());
			JLabel nom = new JLabel();
			nom.setText((String) cnom.getSelectedItem());
			JLabel TOTAL_RESULT = new JLabel("rs");
		
			
			   //affiche du Tableau
			JTable tab_hist = new JTable();
			Object [] hist_col = {"Trame Version","Nb" };
			DefaultTableModel mod_hist = new DefaultTableModel();
			mod_hist.setColumnIdentifiers(hist_col);
			tab_hist.setModel(mod_hist);
			JScrollPane tableHist = new JScrollPane(tab_hist);
			tableHist.setPreferredSize(new Dimension(30,50));
			tab_hist.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,10));
			Object[] row_hist = new Object[2];
			
			//insertion info BDD dans le tableau
			
			try {
				connect= connexion.getConnection(); //fait appel a la classe connexion
				String histo = "SELECT TRAME_VER, COUNT(TRAME_VER) AS NB FROM table_TPF WHERE NOM =? GROUP BY TRAME_VER";
				PreparedStatement pshisto = (PreparedStatement) connect.prepareStatement(histo);
				pshisto.setString(1, (String) cnom.getSelectedItem());
				ResultSet rshisto = pshisto.executeQuery(); // resultat de la selection
				while(rshisto.next()) {
					//ajout element dans tableau
					row_hist[0] = rshisto.getString("TRAME_VER");
					row_hist[1] = rshisto.getString("NB");
					mod_hist.addRow(row_hist);		
			     	}
				
				rshisto.close();
				pshisto.close();
				
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			   }
			
			// nb total de version
			try {
				connect= connexion.getConnection(); //fait appel a la classe connexion
				String histo = "SELECT COUNT(TRAME_VER) AS TOTAL FROM table_TPF WHERE NOM =? ";
				PreparedStatement pshisto = (PreparedStatement) connect.prepareStatement(histo);
				pshisto.setString(1, (String) cnom.getSelectedItem());
				ResultSet rshisto = pshisto.executeQuery(); // resultat de la selection
				while(rshisto.next()) {
					//ajout element dans tableau
					TOTAL_RESULT.setText("NB TOTAL DE VERSION : "+rshisto.getString("TOTAL"));
					
			     	}
				
				rshisto.close();
				pshisto.close();
				
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			   }
			
			
			
			//ajout à la fenetre les composan,t
			paneBox.add(nom, BorderLayout.NORTH);
			paneBox.add(tableHist,BorderLayout.CENTER);
			paneBox.add(TOTAL_RESULT,BorderLayout.SOUTH);
			
			boxH.add(paneBox);
			boxH.setVisible(true);
	}	}
		
		
		public class MAJTrame implements ActionListener {
			public void actionPerformed(ActionEvent ev) {	
				// insert une nouvelle feuille ds la BDD tout en gardant la version precedente
				 try {
						int t=14;
						int u=15;
						int v=16;
						int w=17;
					   connect= connexion.getConnection(); //fait appel a la classe connexion
						
			
					   
					   String str ="INSERT into TABLE_TPF (TYPE_TRAME,TRAME_VER,NOM,FREQUENCE,QTITE_PREL,MODOP,PREPARATION,PARTICULARITE,N_LOT,LOT_FICTIF,DATE_C,QTITE_UNIT,CAT,"
								+ "T1,C1,MIN1,MAX1,T2,C2,MIN2,MAX2,T3,C3,MIN3,MAX3,T4,C4,MIN4,MAX4,T5,C5,MIN5,MAX5,"
								+ "T6,C6,MIN6,MAX6,T7,C7,MIN7,MAX7,T8,C8,MIN8,MAX8,T9,C9,MIN9,MAX9,T10,C10,MIN10,MAX10,"
								+ "T11,C11,MIN11,MAX11,T12,C12,MIN12,MAX12,T13,C13,MIN13,MAX13,T14,C14,MIN14,MAX14,T15,C15,MIN15,MAX15,"
								+ "T16,C16,MIN16,MAX16,T17,C17,MIN17,MAX17,T18,C18,MIN18,MAX18,T19,C19,MIN19,MAX19,T20,C20,MIN20,MAX20,"
								+ "T21,C21,MIN21,MAX21,T22,C22,MIN22,MAX22,T23,C23,MIN23,MAX23,T24,C24,MIN24,MAX24,T25,C25,MIN25,MAX25)" 
						
								+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
								+        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
								+        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
								+        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
								+        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
								+        "?,?,?,?,?,?,?,?,?,?,?,?,?)";
					   
						PreparedStatement ps = (PreparedStatement) connect.prepareStatement(str);
						//champs info
						ps.setString(1,  ctrame.getText());
						ps.setString(2,  tver.getText());
						ps.setString(3,  (String) cnom.getSelectedItem());
						ps.setString(4,  tfreq.getText());
						ps.setString(5,  tqtitep.getText());
						ps.setString(6,  tmod.getText()); 
						ps.setString(7,  tprep.getText());
						ps.setString(8,  tpart.getText());
						ps.setString(9,  tnb.getText());
						ps.setBoolean(10, Box_lot.isSelected());
						ps.setString(11,  tdate.getText());
						ps.setString(12,  tqtite.getText());
						ps.setString(13, Categorie);
						
						//champ type+critere
						for (int l = 0; l < tab_crit.getRowCount(); l++) {
							
								 ps.setString(t,tab_crit.getValueAt(l, 0).toString()); //ajout a T(x)
								 ps.setString(u,tab_crit.getValueAt(l, 1).toString()); // ajout a C(x)
								 ps.setString(v,(String) tab_crit.getValueAt(l, 2)); // ajout a Min(x)
								 ps.setString(w,(String) tab_crit.getValueAt(l, 3)); // ajout a Max(x)
								 t=t+4;
								 u=u+4;
								 v=v+4;
								 w=w+4;
							}
		
						// pour completer BDD non utilisé
					   int somme = tab_crit.getRowCount()*4+14; //calcul du nombre de ligne actuel
					
						if (t<110) {
						  	for (t=somme; t<113; t=t+4) {
						
						  		ps.setString(t,"" );
						  		ps.setString(u,"");
						  		ps.setString(v,null);
						  		ps.setString(w,null);
						  		
						  		u=u+4;
						  		v=v+4;
						  		w=w+4;
						  	}
						}
						
						ps.executeUpdate();
						ps.close();
						
						JOptionPane.showMessageDialog(null,"Nouvelle Trame pour le Produit : "+cnom.getSelectedItem()+" ajoutée");
						fen_MTPF.dispose();
						//zonetxtsud.setText(new Date() + "      Donner enregistrer dans la base de donnees");
					   } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					   }
			}}
	
		
		public class ADDLINEPF implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				int ligne =0;
				
				Object[] row = new Object[4];
				row[0] = ctype.getSelectedItem();
				row[1] = ccrit.getSelectedItem();
				row[2] = tmin.getText();
				row[3] = tmax.getText();
				mod.addRow(row);
				
				//nb de paramatre
				ligne++;
				ligne = Integer.parseInt(crit2_max.getText())-ligne;
				
				crit2_max.setText(String.valueOf(ligne));
			}}
		
	// *** fonction diverses
		
		public class AppelTramePF implements ItemListener {  // AFFICHE INFO TRAME DANS LE FORMULAIRE
			@Override
			public void itemStateChanged(ItemEvent arg) {
				// TODO Auto-generated method stub
				 mod.setRowCount(0); //reinitialiser tableau
				 
	          if(arg.getStateChange() == ItemEvent.SELECTED){
	                
					
					//1 - appel la trame et rempli la trame
	                 try {
	                	
 	 
	                	 connect= connexion.getConnection(); //initialisation connection
	     				
	     				                // selecton type de donne de la base en fonction du derniere trame effectuée
	                	String sql = "SELECT * FROM TABLE_TPF  WHERE NOM = ? AND ID = (SELECT MAX(ID) FROM TABLE_TPF WHERE NOM= ?)"; 
	     				PreparedStatement pState2 = (PreparedStatement) connect.prepareStatement(sql);
	     				
	     				pState2.setString(1, (String) cnom.getSelectedItem());
	     				pState2.setString(2, (String) cnom.getSelectedItem());
	     				ResultSet rs = pState2.executeQuery(); // resultat de la selection
	     				
	     				//ajout element 1 a 1 à la fiche de controle
	     				while(rs.next()) {
	     				  
	     					//ajout element donne
	     			    	ctrame.setText(rs.getString(2));       //choix trame
	     					tfreq.setText(rs.getString("FREQUENCE")); 	
	     					tqtitep.setText(rs.getString("QTITE_PREL"));
	     					tmod.setText(rs.getString("MODOP"));
	     					tprep.setText(rs.getString("PREPARATION"));
	     					tpart.setText(rs.getString("PARTICULARITE"));
	     					tnb.setText(rs.getString("N_LOT"));
	     					  //condition pour cocher/decocher case lot fictif dans le formulaire 
	     					      if (rs.getInt("LOT_FICTIF") == 1) {
	     						     Box_lot.setSelected(true);     						
	     				           	} else {
	     						        Box_lot.setSelected(false);
	     					         }	
	     		  		    tver.setText(rs.getString("TRAME_VER"));
	     					tqtite.setText(rs.getString("QTITE_UNIT"));
	     				    tdate.setText(rs.getString("DATE_C"));
	     				   	Categorie = rs.getString("CAT");		    
	     				   
	     					//ajout element dans tableau
	     					for (int count=15; count<114; count=count+4) {
	     							
	     					  tabcrit_Row[0] = rs.getString(count);
	     						tabcrit_Row[1] = rs.getString(count+1);
	     						tabcrit_Row[2] = rs.getString(count+2);
	     						tabcrit_Row[3] = rs.getString(count+3);
	    	   					mod.addRow(tabcrit_Row);
	    	   					}
	     			     	}
	     				//ferme les connexions		
	     				rs.close();  pState2.close();
	     				prime.CentrerTableau(0, tab_crit);

	                      } catch (Exception e) {
	                	 e.printStackTrace();
	                      }  }
	          
	                //2 - redefini la dimension du tableau a afficher
					  int count2 =0;
					                   // compte nb de ligne non null
					      for(int ligne =0; ligne<tab_crit.getRowCount();ligne++) {
						         if(tab_crit.getValueAt(ligne, 3) != null) {
							              count2++;	 
						         }    } 
					       int ligne_max = count2;
					      
					                  //suprime les lignes null
					       for(int col =tab_crit.getRowCount()-1 ; col >= count2; col--) {
						  mod.removeRow(col);
					           }	
	              // 3 - Affiche le nombre de critere restant
		              crit2_max.setText(String.valueOf(25-ligne_max)); 
	     }}	
		
		
		//Affiche ou pas le tableau des critere en fct du type de version
  public class ChoixVersion implements ItemListener {
			public void itemStateChanged(ItemEvent choice) {
				  if(choice.getStateChange() == ItemEvent.SELECTED){
					  if(choice.getItem().equals("SOUS VERSION")) {
						b_ajout.setEnabled(false);
						b_suppr.setEnabled(false);
						
						tver.setEnabled(false);
						JOptionPane.showMessageDialog(null,"Pour une SOUS VERSION, les modifications ne portent pas sur les criteres de validité. Par conséquent, la date de la version n'est pas à modifier !");
					  } else {
						  b_ajout.setEnabled(true);
							b_suppr.setEnabled(true);
							tdate.setEnabled(true);
							tver.setEnabled(true);
					  }
				  }
  }}
		
	
		//affiche liste des nom PF dans la liste deroulante
		private void AfficheComboPF() {
			
			try {
				 cnom.removeAllItems(); //supprime element residuel
				connect= connexion.getConnection(); //initialisation connection
				
				String sql = "SELECT DISTINCT NOM FROM TABLE_TPF ORDER BY NOM"; // selecton type de donne de la base
				PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
				
				ResultSet rs = pState.executeQuery(); // resultat de la selection
				//ajout element 1 a 1
				while(rs.next()) {
					cnom.addItem(rs.getString("NOM"));
				}
				//ferme les connexions
				cnom.setSelectedIndex(-1);
				rs.close();  
				pState.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}}
		
		private void ChargeListeCritere() {  //dcharge liste de precision ** appel dans le constructeur **
			
			try {
				 ccrit.removeAllItems(); //supprime element residuel
				connect= connexion.getConnection(); //initialisation connection
				
				String sql = "SELECT CRITERES FROM CHOIX WHERE CRITERES IS NOT NULL ORDER BY CRITERES"; // selecton type de donne de la base
				PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
				
				ResultSet rs = pState.executeQuery(); // resultat de la selection
				//ajout element 1 a 1
				while(rs.next()) {
					ccrit.addItem(rs.getString("CRITERES"));
				}
				//ferme les connexions
				ccrit.setSelectedIndex(-1);
				rs.close();pState.close();  
				
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}}
	
}
