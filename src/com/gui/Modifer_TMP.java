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



public class Modifer_TMP extends Habillage{

	//declaration des variables
	
	private static Connection connect;
	
		private JFrame fen_MTMP;
		Image img_Modifier_TMP;
		private JPanel panel_MTMP, panel_titre,panel_O, panel_E, panel_E1, panel_E2, panel_E3;
		private JLabel lveractu, ltrame, lnom, lver, lfreq, lqtitep, lmod, lprep,  lpart, lnb, ldate, lqtite, lcarac, ltype,lmin, lmax,crit_max,crit2_max;
		private JLabel print_label,trans_label,histo_label,lVrai_Faux;
		private JTextField tveractu,ttrame,  tfreq, tqtitep, tmod, tpart, tnb, tdate, tcarac, tmin, tmax;
	    private JTextArea tprep;
	    private JButton b_ajout, b_suppr, b_visualtrame, b_record, histo;
	    private JTable tab_crit;
	    private DefaultTableModel mod;
	    private JCheckBox Vrai_Faux;
	    private JComboBox <String> cnom, cqtite, ctype, typever;
	   private Object[] tabcrit_Row;
	   public static String chemin ="\\";
	   int i=25;
	    
	public Modifer_TMP() {
	
		fen_Modifier_TMP();
		AfficheComboMP();
		
	}




	private void fen_Modifier_TMP() {
		fen_MTMP = new JFrame("MODIFICATION : Trame MP");
		
		fen_MTMP.setSize(definition.SLarge,definition.SHaut);
		fen_MTMP.setLocationRelativeTo(null);

	    fen_MTMP.setResizable(false);
	  
	    panel_MTMP = new ModifierMP_Panel();
	    panel_MTMP.setLayout(new BorderLayout());
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
	    panel_E3 = new JPanel(new FlowLayout(0,5,50));
	    panel_E3.setOpaque(false);
	    panel_E.add(panel_E1, BorderLayout.NORTH);
	    panel_E.add(panel_E2, BorderLayout.CENTER);
	    panel_E.add(panel_E3, BorderLayout.SOUTH);
	    
	    panel_MTMP.add(panel_titre, BorderLayout.NORTH);
	    panel_MTMP.add(panel_O, BorderLayout.WEST);
	    panel_MTMP.add(panel_E, BorderLayout.EAST);
	    
	  
	    
	    info_MP(); // methode remplissage info MP
	    carac_MP(); // methode pour caracteristique MP
	    
	    fen_MTMP.getContentPane().add(panel_MTMP);
	    fen_MTMP.setVisible(true);
	}



	private void info_MP() {
		//panel O
		GridBagConstraints c1 = new GridBagConstraints();
		GridBagConstraints c2 = new GridBagConstraints();
		
	   //def et placement des composants
		ltrame = new JLabel("Type de Trame : ");
		lnom = new JLabel("Nom de la Matiere : ");
		lver = new JLabel("Choix Type de Version : ");
		lveractu = new JLabel("Version Actuelle : ");
		lfreq = new JLabel("Frequence Controle : ");
		lqtitep = new JLabel("Quantité à prélever : ");
		lmod = new JLabel("Modop : ");
		lprep = new JLabel("Préparation : ");
		lpart = new JLabel("Particularité : ");
		lnb = new JLabel("Dernier Numero de Lot : ");
		ldate = new JLabel("Date de nouvelle Version : ");
		lqtite = new JLabel("Choix de l'unité : ");
		
		//affichage + sombre des label
		ltrame.setForeground(Color.BLACK);
		
		lnom.setForeground(Color.BLACK);
		lver.setForeground(Color.BLACK);
		lveractu.setForeground(Color.BLACK);
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
		c1.insets = new Insets(20,50,0,0);
		panel_O.add(lnom, c1);
		c1.gridy=1;
		panel_O.add(ltrame, c1);
		c1.gridy=2;
		panel_O.add(lver, c1);
		c1.gridy=3;
		panel_O.add(lveractu, c1);
		c1.gridy=4;
		panel_O.add(lfreq, c1);
		c1.gridy=5;
		panel_O.add(lqtitep, c1);
		c1.gridy=6;
		panel_O.add(lmod, c1);
		c1.gridy=7;
		panel_O.add(lprep, c1);
		c1.gridy=8;
		panel_O.add(lpart, c1);
		c1.gridy=9;
		panel_O.add(lnb, c1);
		c1.gridy=10;
		panel_O.add(ldate, c1);
		c1.gridy=11;
		panel_O.add(lqtite, c1);
		
	
		String[] TypeVer = {"NOUVELLE VERSION","SOUS VERSION"};
		
		ttrame = new JTextField(10);
		cnom = new JComboBox<>();
		cnom.setPreferredSize(new Dimension(200,20));
		cnom.setEditable(true);
		typever = new JComboBox<>(TypeVer);
		tveractu = new JTextField(10);
		tfreq = new JTextField(10);
		tfreq.setText("Systematique");
		tqtitep = new JTextField(10);
		tmod = new JTextField(15);
		tprep = new JTextArea(5,20);
		JScrollPane tprep_sp = new JScrollPane(tprep);
		
		tpart = new JTextField(20);
		tnb = new JTextField(5);
		tdate = new JTextField(10);
		String[] U = {"Qtite x 1000 Kg : ","Qtite x 1000 L : "};
		cqtite = new JComboBox<>(U);
		
		//Action sur les combobox
		cnom.addItemListener(new AppelTrameMP()); // class appel la trame a modifier
		typever.addItemListener(new ChoixVersion()); //class active/desactive formulaire
		
		
		c2.gridx =1;
		c2.gridy =0;
		c2.anchor = GridBagConstraints.FIRST_LINE_START;
		c2.insets = new Insets(20,10,0,0);
		panel_O.add(cnom, c2);
		c2.gridy=1;
		panel_O.add(ttrame, c2);
		c2.gridy=2;
		panel_O.add(typever, c2);
		c2.gridy=3;
		panel_O.add(tveractu, c2);
		c2.gridy=4;
		panel_O.add(tfreq, c2);
		c2.gridy=5;
		panel_O.add(tqtitep, c2);
		c2.gridy=6;
		panel_O.add(tmod, c2);
		c2.gridy=7;
		panel_O.add(tprep_sp, c2);
		c2.gridy=8;
		panel_O.add(tpart, c2);
		c2.gridy=9;
		panel_O.add(tnb, c2);
		c2.gridy=10;
		panel_O.add(tdate, c2);
		c2.gridy=11;
		panel_O.add(cqtite, c2);
		
		
	}

	private void carac_MP() {
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
		
		
		//info bulle
		ltype.setToolTipText("<html> O : pour valeur obligatoire<br/> S : pour critere de Secours</html>");
		
		
		//afficahge + sombre des label
				lcarac.setForeground(Color.BLACK);
				ltype.setForeground(Color.BLACK);
				lmin.setForeground(Color.BLACK);
				lmax.setForeground(Color.BLACK);
				crit_max.setForeground(Color.BLACK);
				lVrai_Faux.setForeground(Color.BLACK);
		
		//placement des labels
		c3.gridx =0;
		c3.gridy =0;
		c3.anchor = GridBagConstraints.FIRST_LINE_END;
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
		tcarac = new JTextField(15);
		String[] T = {"O","S"};
		ctype = new JComboBox<>(T);
		Vrai_Faux = new JCheckBox(" VRAI/FAUX "); 
		Vrai_Faux.setOpaque(false);
		tmin = new JTextField(5);
		tmax = new JTextField(5);
		b_suppr = new JButton("SUPPRIMER");
		crit2_max = new JLabel();
		
		c4.gridx =1;
		c4.gridy =0;
		c4.anchor = GridBagConstraints.FIRST_LINE_START;
		c4.insets = new Insets(60,10,0,40);
		panel_E1.add(ctype, c4);
		
		c4.insets = new Insets(10,10,0,40);
		c4.gridy=1;
		panel_E1.add(tcarac,c4);
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
		tableSP.setPreferredSize(new Dimension(definition.tablesp_Large,definition.tablesp_Haut));
		tabcrit_Row = new Object[4];
		
		panel_E2.add(tableSP, FlowLayout.LEFT);
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
		 trans_label = new JLabel("MISE A JOUR",trans_icon,SwingConstants.CENTER);
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
		
		
	     //ajout au panel
		panel_E3.add(histo, FlowLayout.LEFT);
		panel_E3.add(b_record, FlowLayout.LEFT);
		panel_E3.add(b_visualtrame, FlowLayout.LEFT);
		
		//Association boutons et action
		b_visualtrame.addActionListener(new PDFPRINT()); //visualition de la trame => PDF
		b_record.addActionListener(new MAJTrame()); // mise a jour de trame
		histo.addActionListener(new BoxHisto());   //affiche de l'historique des trames
		b_ajout.addActionListener(new ADDLINE2()); // ajout critere
		
		b_suppr.addActionListener(new ActionListener() { //suppression d'un critere
			public void actionPerformed(ActionEvent e) {
				mod.removeRow(tab_crit.getSelectedRow());
				int sup_ligne =0;
				sup_ligne++;
				sup_ligne = Integer.parseInt(crit2_max.getText())+sup_ligne;
				crit2_max.setText(String.valueOf(sup_ligne));
		}});
	}


// *************************Class des  BOUTONS
	
	public class PDFPRINT implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Document doc = new Document();
			
			chemin = chemin+"Trame_MP_"+cnom.getSelectedItem()+".pdf";
			
			
			 try {
			    PdfWriter.getInstance(doc, new FileOutputStream(chemin));
			    doc.open();
			    
		  //creation du contenu
			    Paragraph p1 = new Paragraph("TECHNIQUE BETON",FontFactory.getFont(FontFactory.HELVETICA,6));
			    Paragraph p2 = new Paragraph("CONTROLE DE MP - TRAME "+ttrame.getText(),FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
	            Paragraph p3 = new Paragraph("VALIDATION DE LA TRAME - VERSION "+tveractu.getText(),FontFactory.getFont(FontFactory.HELVETICA,10));
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
	           PdfPCell cellnom1 = new PdfPCell(new Paragraph("MP",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD)));
	           PdfPCell cellnom2 = new PdfPCell(new Paragraph((String)cnom.getSelectedItem(),FontFactory.getFont(FontFactory.HELVETICA,14,BaseColor.RED)));
	           cellnom2.setHorizontalAlignment(Element.ALIGN_CENTER);
	           TableNom.addCell(cellnom1);
	           TableNom.addCell(cellnom2);
	           TableNom.setWidths(new int[] {20,120});
	          
	           PdfPTable TableTracabililte = new PdfPTable(8);
	           TableTracabililte.addCell(new Phrase("CONFORMITE",FontFactory.getFont(FontFactory.HELVETICA,7)));
	           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,7)));
	           TableTracabililte.addCell(new Phrase("N° DE LOT",FontFactory.getFont(FontFactory.HELVETICA,7)));
	           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
	           TableTracabililte.addCell(new Phrase("N° FICHE NC",FontFactory.getFont(FontFactory.HELVETICA,7)));
	           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
	           TableTracabililte.addCell(new Phrase("DEROG Oui/Vide",FontFactory.getFont(FontFactory.HELVETICA,7)));
	           TableTracabililte.addCell(new Phrase("   ",FontFactory.getFont(FontFactory.HELVETICA,6)));
	           TableTracabililte.addCell(new Phrase("RESP DES ESSAIS",FontFactory.getFont(FontFactory.HELVETICA,6)));
	           TableTracabililte.addCell(new Phrase("  ",FontFactory.getFont(FontFactory.HELVETICA,6)));
	           TableTracabililte.addCell(new Phrase("RESERVATION LOT",FontFactory.getFont(FontFactory.HELVETICA,6)));
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
	           
	           //insertion données
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
	
	public class BoxHisto implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			//creation de la fenetre
		JDialog boxH = new JDialog(fen_MTMP,"Historique Trame");
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
			String histo = "SELECT TRAME_VER, COUNT(TRAME_VER) AS NB FROM table_TMP WHERE NOM =? GROUP BY TRAME_VER";
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
			String histo = "SELECT COUNT(TRAME_VER) AS TOTAL FROM table_TMP WHERE NOM =? ";
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
	}}
	
	
	public class MAJTrame implements ActionListener {
		public void actionPerformed(ActionEvent ev) {	
			// insert une nouvelle feuille ds la BDD tout en gardant la version precedente
			 try {
					int t=12;
					int u=13;
					int v=14;
					int w=15;
				   connect= connexion.getConnection(); //fait appel a la classe connexion
				   
				   String str ="INSERT into TABLE_TMP (TYPE_TRAME,TRAME_VER,NOM,FREQUENCE,QTITE_PREL,MODOP,PREPARATION,PARTICULARITE,N_LOT,DATE_C,QTITE_UNIT,"
							+ "T1,C1,MIN1,MAX1,T2,C2,MIN2,MAX2,T3,C3,MIN3,MAX3,T4,C4,MIN4,MAX4,T5,C5,MIN5,MAX5,T6,C6,MIN6,MAX6,T7,C7,MIN7,MAX7,T8,C8,MIN8,MAX8,"
							+ "T9,C9,MIN9,MAX9,T10,C10,MIN10,MAX10,T11,C11,MIN11,MAX11,T12,C12,MIN12,MAX12,T13,C13,MIN13,MAX13,T14,C14,MIN14,MAX14,T15,C15,MIN15,MAX15)" 
					
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					
							
					PreparedStatement ps = (PreparedStatement) connect.prepareStatement(str);
					//champs info
					ps.setString(1, ttrame.getText());
					ps.setString(2,  tveractu.getText());
					ps.setString(3, (String) cnom.getSelectedItem());
					ps.setString(4,  tfreq.getText());
					ps.setString(5,  tqtitep.getText());
					ps.setString(6,  tmod.getText());
					ps.setString(7,  tprep.getText());
					ps.setString(8,  tpart.getText());
					ps.setString(9,  tnb.getText());
					ps.setString(10,  tdate.getText());
					ps.setString(11,  (String) cqtite.getSelectedItem());
					
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
				   int somme = tab_crit.getRowCount()*4+12; //calcul du nombre de ligne bdd utilisé
					
					if (t<68) {
					  	for (t=somme; t<72; t=t+4) {	  		
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
					
					JOptionPane.showMessageDialog(null,"Nouvelle Trame pour la matiere : "+cnom.getSelectedItem()+" ajoutée");
					fen_MTMP.dispose();
					//zonetxtsud.setText(new Date() + "      Donner enregistrer dans la base de donnees");
				   } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				   }
}}
	
	
	public class ADDLINE2 implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			int ligne =0;
			
			Object[] row = new Object[4];
			row[0] = ctype.getSelectedItem();
			row[1] = tcarac.getText();
			row[2] = tmin.getText();
			row[3] = tmax.getText();
			mod.addRow(row);
			
			//nb de paramatre
			ligne++;
			ligne = Integer.parseInt(crit2_max.getText())-ligne;
			
			crit2_max.setText(String.valueOf(ligne));
		}}
	
	 
	
    //******************  class diverses
	public class ChoixVersion implements ItemListener {
		public void itemStateChanged(ItemEvent choice) {
			  if(choice.getStateChange() == ItemEvent.SELECTED){
				  if(choice.getItem().equals("SOUS VERSION")) {
					b_ajout.setEnabled(false);
					b_suppr.setEnabled(false);
					
					tveractu.setEnabled(false);
					JOptionPane.showMessageDialog(null,"Pour une SOUS VERSION, les modifications ne portent pas sur les criteres de validité. Par conséquent, la date de la version n'est pas à modifier !");
				  } else {
					  b_ajout.setEnabled(true);
						b_suppr.setEnabled(true);
						tdate.setEnabled(true);
						tveractu.setEnabled(true);
				  }
			  }
		}}
	
	
	
	public class AppelTrameMP implements ItemListener {  // AFFICHE INFO TRAME DANS LE FORMULAIRE
		@Override
		public void itemStateChanged(ItemEvent arg) {
			// TODO Auto-generated method stub
			 mod.setRowCount(0); //reinitialiser tableau
			 
          if(arg.getStateChange() == ItemEvent.SELECTED){
                
				
				//1 - appel la trame et rempli la trame
                 try {
                	 connect= connexion.getConnection(); //initialisation connection
     				
     				                // selecton type de donne de la base en fonction du derniere trame effectuée
                	String sql = "SELECT * FROM TABLE_TMP  WHERE NOM = ? AND ID = (SELECT MAX(ID) FROM TABLE_TMP WHERE NOM= ?)"; 
     				PreparedStatement pState2 = (PreparedStatement) connect.prepareStatement(sql);
     				
     				pState2.setString(1, (String) cnom.getSelectedItem());
     				pState2.setString(2, (String) cnom.getSelectedItem());
     				ResultSet rs = pState2.executeQuery(); // resultat de la selection
     				
     				//ajout element 1 a 1 à la fiche de controle
     				while(rs.next()) {
     				    int count;
     					//ajout element donne
     					ttrame.setText(rs.getString(2));
     					tfreq.setText(rs.getString("FREQUENCE"));
     					tqtitep.setText(rs.getString("QTITE_PREL"));
     					tmod.setText(rs.getString("MODOP"));
     					tprep.setText(rs.getString("PREPARATION"));
     					tpart.setText(rs.getString("PARTICULARITE"));
     					tnb.setText(rs.getString("N_LOT"));
     					tveractu.setText(rs.getString("TRAME_VER"));
     					cqtite.setSelectedItem(rs.getString("QTITE_UNIT"));
     				    tdate.setText(rs.getString("DATE_C"));
     					
     					//ajout element dans tableau
     					for (count=13; count<73; count=count+4) {
     						tabcrit_Row[0] = rs.getString(count);
     						tabcrit_Row[1] = rs.getString(count+1);
     						tabcrit_Row[2] = rs.getString(count+2);
     						tabcrit_Row[3] = rs.getString(count+3);
    	   					mod.addRow(tabcrit_Row);}
     				}
     				//ferme les connexions		
     				rs.close(); pState2.close(); 
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
	              crit2_max.setText(String.valueOf(15-ligne_max));
          }}
	
	
	// ************ Methodes  diverses	
	private void AfficheComboMP() {   // AFFICHE LISTE MP dans combobox
		
		try {
			 cnom.removeAllItems(); //supprime element residuel
			connect= connexion.getConnection(); //initialisation connection
			
			String sql = "SELECT DISTINCT NOM FROM TABLE_TMP ORDER BY NOM"; // selecton type de donne de la base
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
		
}
	
