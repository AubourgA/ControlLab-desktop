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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

import connexion.BD.connexion;




public class Creer_TPF extends Habillage{

	//declaration des variables
		
	 private static Connection connect; //creer varaible connect
		
		
		JFrame fen_TPF;
		Image img_TPF;
		private JPanel panel_TPF, panel_titre,panel_O, panel_E, panel_E1, panel_E2, panel_E3, catpanel;
		private JLabel  ltrame, llotfictif,lnom, lver, lfreq, lqtitep, lmod, lprep,  lpart, lnb, ldate, lqtite, lcarac, ltype,lmin, lmax,crit_max,crit2_max,lcategorie;
		private JLabel print_label,trans_label,lVrai_Faux;
		private JTextField tnom, tver, tfreq, tqtitep, tmod, tpart, tnb, tdate, tmin, tmax;
	    private JTextArea tprep;
	    private JButton b_ajout, b_suppr, b_visualtrame, b_record;
	    private JTable tab_crit;
	    private JCheckBox Box_lot, Vrai_Faux;
	   private JRadioButton Rad_cat1,Rad_cat2,Rad_cat3;
	    DefaultTableModel mod;
	    private JComboBox <String> ctrame, cqtite, ctype, ccrit;
		private ButtonGroup group;
		 public static String chemin ="\\";
		 
		 
	public Creer_TPF() {	
		 fen_TPF();
		 ChargeListeCritere();
	}


	private void fen_TPF() {
		fen_TPF = new JFrame("CREATION : Trame PF");
		fen_TPF.setSize(definition.SLarge,definition.SHaut);
		fen_TPF.setLocationRelativeTo(null);
	    fen_TPF.setResizable(false);
	    panel_TPF = new CreerTPF_Panel();
	    panel_TPF.setLayout(new BorderLayout());
	    panel_titre = new JPanel();
	    panel_titre.setOpaque(false);
	    panel_O = new JPanel(new GridBagLayout()); // partie info
	    panel_O.setOpaque(false);
	    panel_E = new JPanel(new BorderLayout()); // partie controle
	    panel_E.setOpaque(false);
	    panel_E1 = new JPanel(new GridBagLayout());
	    panel_E1.setOpaque(false);
	    panel_E2 = new JPanel(new FlowLayout(0,0,30));
	    panel_E2.setOpaque(false);
	    panel_E3 = new JPanel(new FlowLayout(0,30,50));
	    panel_E3.setOpaque(false);
	    panel_E.add(panel_E1, BorderLayout.NORTH);
	    panel_E.add(panel_E2, BorderLayout.CENTER);
	    panel_E.add(panel_E3, BorderLayout.SOUTH);
	    
	    panel_TPF.add(panel_titre, BorderLayout.NORTH);
	    panel_TPF.add(panel_O, BorderLayout.WEST);
	    panel_TPF.add(panel_E, BorderLayout.EAST);
	      
	    info_PF(); // methode remplissage info MP
	    carac_PF(); // methode pour caracteristique MP
	    
	    fen_TPF.getContentPane().add(panel_TPF);
	    fen_TPF.setVisible(true);
	}

	private void info_PF() {
		
		//cration panel pour jradiobutton choix de la categorie
		catpanel = new JPanel();
		Rad_cat1 = new JRadioButton("Poudre");
		Rad_cat2 = new JRadioButton("Liquide");
		Rad_cat3 = new JRadioButton("Pateux");
		
		group = new ButtonGroup();
		group.add(Rad_cat1);
		group.add(Rad_cat2);
		group.add(Rad_cat3);
		
		catpanel.add(Rad_cat1);
		catpanel.add(Rad_cat2);
		catpanel.add(Rad_cat3);
		catpanel.setOpaque(false);
		Rad_cat1.setOpaque(false);
		Rad_cat2.setOpaque(false);
		Rad_cat3.setOpaque(false);
		
		//panel O
		GridBagConstraints c1 = new GridBagConstraints();
		GridBagConstraints c2 = new GridBagConstraints();
		
	   //def et placement des composants
		ltrame = new JLabel("Type de trame : ");
		llotfictif = new JLabel("Si trame avec lot : ");
		lcategorie = new JLabel("Choix de la Categorie :");
		lnom = new JLabel("Nom du Produit : ");
		lver = new JLabel("Version trame : ");
		lfreq = new JLabel("Frequence Prélévement : ");
		lqtitep = new JLabel("Quantité à prélever : ");
		lmod = new JLabel("Modop et Norme: ");
		lprep = new JLabel("Préparation : ");
		lpart = new JLabel("Particularité : ");
		lnb = new JLabel("Numero de Lot : ");
		ldate = new JLabel("Date de création : ");
		lqtite = new JLabel("Choix de l'unité : ");
		
		ltrame.setForeground(Color.BLACK);
		llotfictif.setForeground(Color.BLACK);
		lcategorie.setForeground(Color.BLACK);
		lnom.setForeground(Color.BLACK);
		lver.setForeground(Color.BLACK);
		lfreq.setForeground(Color.BLACK);
		lqtitep.setForeground(Color.BLACK);
		lmod.setForeground(Color.BLACK);
		lprep.setForeground(Color.BLACK);
		lpart.setForeground(Color.BLACK);
		lnb.setForeground(Color.BLACK);
		ldate.setForeground(Color.BLACK);
		lqtite.setForeground(Color.BLACK);
		
		
		c1.gridx =0;
		c1.gridy =0;
		c1.anchor = GridBagConstraints.FIRST_LINE_END;
		c1.insets = new Insets(15,50,0,0);
		panel_O.add(ltrame, c1);
		c1.gridy=1;
		panel_O.add(llotfictif, c1);
		c1.gridy=2;
		panel_O.add(lcategorie, c1);
		c1.gridy=3;
		panel_O.add(lnom, c1);
		c1.gridy=4;
		panel_O.add(lver, c1);
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
		
		String[] PFtype = {"AVEC LOT","SANS LOT","CAPILLAIRE"};
		ctrame = new JComboBox<>(PFtype);
		Box_lot = new JCheckBox("Lot Fictif");
		Box_lot.setOpaque(false);
		tnom = new JTextField(15);
		tver = new JTextField(10);
		tver.setText("XXX - aa/mm/jj");
		tfreq = new JTextField(15);
		tfreq.setText("1 toutes les X Tournees");
		tqtitep = new JTextField(10);
		tmod = new JTextField(20);
		tprep = new JTextArea(5,20);
		tprep.setText("ECH A 20 °C +/- 2 °C");
		JScrollPane tprep_sp = new JScrollPane(tprep);
		
		tpart = new JTextField(20);
		tnb = new JTextField(5); 
		tnb.setText("0");
		tdate = new JTextField(10);
		tdate.setText("aa/mm/jj");
		String[] U = {"QUANTITE x 1000 Kg : ","QUANTITE x 1000 L : "};
		cqtite = new JComboBox<>(U);
		
		//placement composant
		c2.gridx =1;
		c2.gridy =0;
		c2.anchor = GridBagConstraints.FIRST_LINE_START;
	     c2.insets = new Insets(10,10,0,0);
		panel_O.add(ctrame, c2);
		c2.insets = new Insets(12,5,0,0);
		c2.gridy=1;
		panel_O.add(Box_lot,c2);
		c2.insets = new Insets(7,0,0,0);
		c2.gridy=2;
		panel_O.add(catpanel,c2);
		c2.insets = new Insets(13,10,0,0);
		c2.gridy=3;
		panel_O.add(tnom, c2);
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
		panel_O.add(cqtite, c2);
		
		//option sur CheckBox LOT FICTIF en fonction liste de choix du type de trame
		ctrame.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent e) {
               if(ctrame.getSelectedItem()=="SANS LOT" || ctrame.getSelectedItem()=="CAPILLAIRE") {
            	   Box_lot.setEnabled(false);
                                                        }
               else if(ctrame.getSelectedItem()=="AVEC LOT") {
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
		crit_max = new JLabel("Nb de Criteres Disponible : ");
		
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
		//c3.anchor = GridBagConstraints.FIRST_LINE_END;
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
		//tcarac = new JTextField(15);
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
		crit2_max = new JLabel("25");
		
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
		JScrollPane tableSP = new JScrollPane(tab_crit);
		//panel_E2.add(new JScrollPane(tab_crit));
		tableSP.setPreferredSize(new Dimension(definition.tablesp_Large,definition.tablesp_Haut));
		panel_E2.add(tableSP);
		tab_crit.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,10));
		tab_crit.getTableHeader().setBackground(Color.YELLOW);
		

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

		
		panel_E3.add(b_record, FlowLayout.LEFT);
		panel_E3.add(b_visualtrame, FlowLayout.LEFT);
		
		//actionlistener
		
		b_visualtrame.addActionListener(new PDFPRINT());
		b_record.addActionListener(new TRANSFERT()); //voir class pour bouton
		
		b_ajout.addActionListener(new ADDLINE_TPF()); //voir class  pour bouton
		
		b_suppr.addActionListener(new ActionListener() {
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

	
	//***************************Class des  BOUTONS*************************************
	
	public class PDFPRINT implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Document doc = new Document();
			chemin = chemin+"Trame_PF_"+tnom.getText()+".pdf";
			
			
			 try {
			    PdfWriter.getInstance(doc, new FileOutputStream(chemin));
			    doc.open();
			    
		  //creation du contenu
			    Paragraph p1 = new Paragraph("TECHNIQUE BETON",FontFactory.getFont(FontFactory.HELVETICA,6));
			    Paragraph p2 = new Paragraph("CONTROLE DU PF - TRAME "+ctrame.getSelectedItem(),FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
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
	           PdfPCell cellnom2 = new PdfPCell(new Paragraph(tnom.getText(),FontFactory.getFont(FontFactory.HELVETICA,14,BaseColor.RED)));
	           cellnom2.setHorizontalAlignment(Element.ALIGN_CENTER);
	           TableNom.addCell(cellnom1);
	           TableNom.addCell(cellnom2);
	           TableNom.setWidths(new int[] {20,120});
	          
	           PdfPTable TableTracabililte = new PdfPTable(8);
	           TableTracabililte.addCell(new Phrase("CONFORMITE",FontFactory.getFont(FontFactory.HELVETICA,7)));
	           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,7)));
//En fonction de type de trame => affichage de la case avec ou sans lot	          
	         if(ctrame.getSelectedItem()=="SANS LOT") {
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
	       if (ctrame.getSelectedItem()=="SANS LOT") {
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
	           TableCritere.addCell(new Phrase((String)cqtite.getSelectedItem(),FontFactory.getFont(FontFactory.HELVETICA,6)));
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
			
	}}
	
	
	public class ADDLINE_TPF implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			int ligne=0;
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
			prime.CentrerTableau(0, tab_crit);
		}
	}
	public class TRANSFERT implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			 try {
					int t=14;
					int u=15;
					int v=16;
					int w=17;
				   connect= connexion.getConnection(); //fait appel a la classe connexion
					
		
				   
				   String str ="INSERT into TABLE_TPF (TYPE_TRAME,TRAME_VER,NOM,FREQUENCE,QTITE_PREL,MODOP,PREPARATION,PARTICULARITE,N_LOT,LOT_FICTIF,DATE_C,QTITE_UNIT,CAT," //13
							+ "T1,C1,MIN1,MAX1,T2,C2,MIN2,MAX2,T3,C3,MIN3,MAX3,T4,C4,MIN4,MAX4,T5,C5,MIN5,MAX5," //20
							+ "T6,C6,MIN6,MAX6,T7,C7,MIN7,MAX7,T8,C8,MIN8,MAX8,T9,C9,MIN9,MAX9,T10,C10,MIN10,MAX10," //20
							+ "T11,C11,MIN11,MAX11,T12,C12,MIN12,MAX12,T13,C13,MIN13,MAX13,T14,C14,MIN14,MAX14,T15,C15,MIN15,MAX15," //20
							+ "T16,C16,MIN16,MAX16,T17,C17,MIN17,MAX17,T18,C18,MIN18,MAX18,T19,C19,MIN19,MAX19,T20,C20,MIN20,MAX20," //20
							+ "T21,C21,MIN21,MAX21,T22,C22,MIN22,MAX22,T23,C23,MIN23,MAX23,T24,C24,MIN24,MAX24,T25,C25,MIN25,MAX25)" //20
					
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," //20
							+        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
							+        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
							+        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
							+        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
							+        "?,?,?,?,?,?,?,?,?,?,?,?,?)";                //13
				   
					PreparedStatement ps = connect.prepareStatement(str);
					//champs info
					ps.setString(1, (String) ctrame.getSelectedItem());
					ps.setString(2,  tver.getText());
					ps.setString(3,  tnom.getText());
					ps.setString(4,  tfreq.getText());
					ps.setString(5,  tqtitep.getText());
					ps.setString(6,  tmod.getText()); 
					ps.setString(7,  tprep.getText());
					ps.setString(8,  tpart.getText());
					ps.setString(9,  tnb.getText());
					ps.setBoolean(10, Box_lot.isSelected());
					ps.setString(11,  tdate.getText());
					ps.setString(12,  (String) cqtite.getSelectedItem());
			

					// condition pour connaite le type de categorie du produit
					if (Rad_cat1.isSelected()) {
				       	ps.setString(13,  "POU");
					       } else if (Rad_cat2.isSelected()) {
						        ps.setString(13,  "LIQ");
				               	} else { ps.setString(13,  "PAT"); }
					
					
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
				   System.out.println(somme);
					if (t<110) {
					  	for (t=somme; t<113; t=t+4) {
					  		ps.setString(t,"" );
					  		ps.setString(u,"");
					  		ps.setString(v,null);
					  		ps.setString(w,null);
					  		u=u+4;
					  		v=v+4;
					  		w=w+4;
					 } 	}
					ps.executeUpdate();ps.close();
							
					JOptionPane.showMessageDialog(null,"Nouvelle Trame pour la matiere : "+tnom.getText()+" ajoutée");
					fen_TPF.dispose();
					//zonetxtsud.setText(new Date() + "      Donner enregistrer dans la base de donnees");
				   } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				   }
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
