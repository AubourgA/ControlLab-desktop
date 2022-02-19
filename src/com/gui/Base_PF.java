package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;

import connexion.BD.connexion;





public class Base_PF extends Habillage{

	//declaration des variables
	
	private static Connection connect; //creer varaible connect
	
	
		JFrame fen_BPF;
		private JPanel panel_BPF, p11,p22,ptitle, p11_centre,p11_sud,p22_N,p22_S,p22_W,p22_E,p22_N1,p22_N2;
		private JLabel lab_text, lnom, lfreq, lmod, lpart,lprep, lt, lnc, lderog;
		private JLabel mod_lab,print_lab, xls_lab,rech_lab,lversion;
		private JTextField t_text, tdd,tdf, tnom,tfreq,tmod,tpart,tq,tt,tn,tderog,tversion;
		private JTextArea tprep;
		private JTabbedPane panelOnglet;
		private JTable tab_base, res_table, res_table2;
		private DefaultTableModel model_BPF, model_restable, model2_restable;
		private JButton aff_but, exp_but,exp2_but, modifier_button;
		private TitledBorder paneN1, paneN2;
		private JLabel lver, ldd, ldf;
		private JComboBox<String> cver;
		private Object [] crit,ope,row_1,row_2;
		 protected String temp_qtite;
		 
	public Base_PF() {	
		 fen_BPF();	
		 ListeNomPF();
	}


	private void fen_BPF() {
		fen_BPF = new JFrame("BASE DES PRODUITS FINI");
		
		fen_BPF.setSize(definition.SLarge,definition.SHaut);
		fen_BPF.setLocationRelativeTo(null);

	    fen_BPF.setResizable(false);
	    
	    panel_BPF = new BasePFPanel();
	    panel_BPF.setLayout(new BorderLayout());
	    
	    //creation panel
	    ptitle = new JPanel(); //panel NORTH
	    
	    
	    panelOnglet = new JTabbedPane(); //creation des onglets
	    p11 = new BasePanel();
	    p11.setLayout(new BorderLayout());
	   
	    p22 = new BasePanel();
	    p22.setLayout(new BorderLayout());
	    
	    panelOnglet.addTab("Base PF",null,p11,"Recherche simple"); //ajout panel au onglet
	    panelOnglet.addTab("Recherche Détaillée",null,p22);
	    panel_BPF.add(ptitle, BorderLayout.NORTH);
	    panel_BPF.add(panelOnglet, BorderLayout.CENTER);
	    
	    //affichage ensemble panel
	    titre();
	    onglet_11();
	    onglet_22();
	    
	    ptitle.setOpaque(false);
	    p11.setOpaque(false);
	    p22.setOpaque(false);
	    
	    
	    fen_BPF.getContentPane().add(panel_BPF);
	    fen_BPF.setVisible(true);
	}

	private void titre() {
		//creation composant et affectation
		
		ptitle.setPreferredSize(new Dimension(0,40));
	}

	private void onglet_11() {
		//creation panel et affectation
		p11_centre = new JPanel();
		p11_sud = new JPanel(new FlowLayout());
		p11.add(p11_centre, BorderLayout.CENTER);
		p11.add(p11_sud, BorderLayout.SOUTH);
		
		//pour laisser apparaitre image background
		p11_centre.setOpaque(false);
		p11_sud.setOpaque(false);
	
		
		//creation composant
		lab_text = new JLabel("Recherche d'un contrôle dans la base : ");
		t_text = new JTextField(20);
		modifier_button = new JButton();
		
		//icon bouton
		
		 ImageIcon mod_icon = new ImageIcon(getClass().getResource(definition.URL_MODIFIER));
		 mod_lab = new JLabel("MODIFIER VALEUR",mod_icon,SwingConstants.CENTER);
		 mod_lab.setVerticalTextPosition(JLabel.BOTTOM);
		 mod_lab.setHorizontalTextPosition(JLabel.CENTER);
		 modifier_button.setPreferredSize(new Dimension(140,60));
		 modifier_button.add(mod_lab);
		
		 //action sur bouton
		 modifier_button.addActionListener(new ModifVal());
		
		
		//creation table
		tab_base = new JTable();
		      // titre colonne
		Object [] col_basePF = { "TYPE_TRAME", "TRAME_VER","PF","FREQ","QTITE_PREL","MODOP ET NORME","PREPARATION","PARTICULARITE","CONFORMITE","N° LOT","FICHE_NC","NB NC","NB_DEROG","DATE CONTROLE","QTITE","RESP",
				"OBSERVATION",
				"T1","C1","MIN1","MAX1","R1","T2","C2","MIN2","MAX2","R2","T3","C3","MIN3","MAX3","R3","T4","C4","MIN4","MAX4","R4","T5","C5","MIN5","MAX5","R5",
				"T6","C6","MIN6","MAX6","R6","T7","C7","MIN7","MAX7","R7","T8","C8","MIN8","MAX8","R8","T9","C9","MIN9","MAX9","R9","T10","C10","MIN10","MAX10","R10",
				"T11","C11","MIN11","MAX11","R11","T12","C12","MIN12","MAX12","R12","T13","C13","MIN13","MAX13","R13","T14","C14","MIN14","MAX14","R14","T15","C15","MIN15","MAX15","R15",
				"T16","C16","MIN16","MAX16","R16","T17","C17","MIN17","MAX17","R17","T18","C18","MIN18","MAX18","R18","T19","C19","MIN19","MAX19","R19","T20","C20","MIN20","MAX20","R20",
				"T21","C21","MIN21","MAX21","R21","T22","C22","MIN22","MAX22","R22","T23","C23","MIN23","MAX23","R23","T24","C24","MIN24","MAX24","R24","T25","C25","MIN25","MAX25","R25",
				"RS1","RS2","RS3","RS4","RS5","RS6","RS7","RS8","RS9","RS10","RS11","RS12","RS13","RS14","RS15","RS16","RS17","RS18","RS19","RS20","RS21","RS22","RS23","RS24","RS25",
				"RF1","RF2","RF3","RF4","RF5","RF6","RF7","RF8","RF9","RF10","RF11","RF12","RF13","RF14","RF15","RF16","RF17","RF18","RF19","RF20","RF21","RF22","RF23","RF24","RF25"};
				
		          // model table 
		   model_BPF = new DefaultTableModel() {
				private static final long serialVersionUID = 1L;			
				public boolean isCellEditable(int row, int col){  
						return col==14 || col == 21 || col == 26 || col == 31 || col == 36 || col == 41 || col == 46 || col == 51 || col == 56 || col == 61 || col == 66 || col == 71 || col == 76 || col == 81 || col == 86 || col == 91;
					}};
					
		   
		   model_BPF.setColumnIdentifiers(col_basePF);
		   final TableRowSorter<TableModel> sorter_PF = new TableRowSorter<>(model_BPF); 
		        // caractistique table
		 tab_base.setModel(model_BPF);
		 tab_base.setShowHorizontalLines(false);
		 tab_base.setRowSorter(sorter_PF);
		 tab_base.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,11));
		 tab_base.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 
		      // ajout d'un barre de defillement
		  JScrollPane s = new JScrollPane(tab_base);
		
		  s.getViewport().setOpaque(false);
		  s.setPreferredSize(new Dimension(definition.s_Large,definition.s_Haut));
		  Object[] row_PF = new Object[194];

		  
		  //insertion BDD dans Jtable
		  try {
				 connect= connexion.getConnection(); //fait appel a la classe connexion 
				 String tablepf = "SELECT * FROM TABLE_PF ORDER BY NOM"; 
				 PreparedStatement pstable = (PreparedStatement) connect.prepareStatement(tablepf);
				ResultSet rs = pstable.executeQuery(); // resultat de la selection
				while(rs.next()) {
							 
					row_PF[0] = rs.getString(2);    row_PF[11] = rs.getString(14);
					row_PF[1] = rs.getString(3);    row_PF[12] = rs.getString(15);
					row_PF[2] = rs.getString(4);    row_PF[13] = rs.getString(16);
					row_PF[3] = rs.getString(5);    row_PF[14] = rs.getString(17);
					row_PF[4] = rs.getString(6);    row_PF[15] = rs.getString(18);
					row_PF[5] = rs.getString(7);    row_PF[16] = rs.getString(19);
					row_PF[6] = rs.getString(8);    row_PF[17] = rs.getString(21);
					row_PF[7] = rs.getString(9);    row_PF[18] =rs.getString(22);
					row_PF[8] = rs.getString(10);	row_PF[19] =rs.getString(23);
					row_PF[9] = rs.getString(11);	row_PF[20] =rs.getString(24);
					row_PF[10] = rs.getString(13);	row_PF[21] =rs.getString(25);
	
					int j = 26;
					for (int i= 22; i<192;i++) {
						row_PF[i] = rs.getString(j);
						j++;
					}
						
					model_BPF.addRow(row_PF);}
			//ferme les connexions	
				
				rs.close();  
				pstable.close();
				 centerTable1();
			 } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				  } //fin du try
		  finally {
			  try {
				connect.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }
		  
		  // pour rechercher dans le tableau => FILTRE
			 t_text.getDocument().addDocumentListener(new DocumentListener(){
				 @Override public void insertUpdate(DocumentEvent e) {
				 search(t_text.getText());

				  }
				 @Override public void removeUpdate(DocumentEvent e) {
				 search(t_text.getText());
				 }
				 @Override public void changedUpdate(DocumentEvent e) {
				 search(t_text.getText());
				 }
				 public void search(String s)
				 {
				 if (s.length() == 0) {

				  sorter_PF.setRowFilter(null);
				 } else {

				  sorter_PF.setRowFilter(RowFilter.regexFilter(s));
				 }
				 }
			 });
		  
		  
		  
		//affection au panel
		p11_sud.add(lab_text);
		p11_sud.add(t_text);
		p11_sud.add(modifier_button);
		p11_centre.add(s);
		
	}

	private void onglet_22() {
		//creation des panel
		p22_N = new JPanel();
		p22_N.setLayout(new BoxLayout(p22_N,BoxLayout.X_AXIS));
		p22_N1 = new JPanel(new GridBagLayout());
		p22_N2 = new JPanel(new GridBagLayout());
		p22_S = new JPanel();
		p22_W= new JPanel();
		p22_E= new JPanel();
		
		//affection panel
		p22_N.add(p22_N1);
		p22_N.add(p22_N2);
		
		//transparence des panels
		p22_N.setOpaque(false);
		p22_N1.setOpaque(false);
		p22_N2.setOpaque(false);
		p22_S.setOpaque(false);
		p22_W.setOpaque(false);
		p22_E.setOpaque(false);
			
		p22.add(p22_N, BorderLayout.NORTH);
		p22.add(p22_W, BorderLayout.WEST);
		p22.add(p22_E, BorderLayout.EAST);
		p22.add(p22_S, BorderLayout.SOUTH);
		
		// p2 nord
		    // ****  p2_N1
		
		   //border panel
		paneN1 = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),"CRITERES DE SELECTION", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
		p22_N1.setBorder(paneN1);
		
		   //creation des composants
		lver = new JLabel("Version & Nom du PF : ");
		ldd = new JLabel("Date de début : ");
		ldf = new JLabel("Date de fin : ");
		
		cver = new JComboBox<>();
		tdd = new JTextField(8);
		tdf = new JTextField(8);
		tdd.setText("2020-01-01");
		tdf.setText("2020-12-31");
		
		
		//icon bouton
		aff_but = new JButton();
		ImageIcon rech_icon = new ImageIcon(getClass().getResource(definition.URL_RECHERCHER));
		 rech_lab = new JLabel("<html><center>LANCER <br>RECHERCHER</center></html>",rech_icon,SwingConstants.CENTER);
		 rech_lab.setVerticalTextPosition(JLabel.BOTTOM);
		 rech_lab.setHorizontalTextPosition(JLabel.CENTER);
		 aff_but.setPreferredSize(new Dimension(110,60));
		 aff_but.add(rech_lab);
		
		 //action bouton
		 aff_but.addActionListener(new LANCER());
		 
		
		   // definition du placement
		GridBagConstraints g1 = new GridBagConstraints();
		
		  //placement des composant
		g1.gridx =0;
		g1.gridy =0;
		
		g1.insets = new Insets(5,5,0,0);

		g1.anchor = GridBagConstraints.FIRST_LINE_END;
		
		p22_N1.add(lver, g1);
		g1.gridy =1;
		p22_N1.add(ldd, g1);
		g1.gridy =2;
		p22_N1.add(ldf, g1);
		
		g1.gridx =1;
		g1.gridy =0;
		g1.anchor = GridBagConstraints.FIRST_LINE_START;
		p22_N1.add(cver, g1);
		g1.gridy =1;
		p22_N1.add(tdd, g1);
		g1.gridy =2;
		p22_N1.add(tdf, g1);
		
		g1.gridx =2;
		g1.gridy =0;
		//g1.gridwidth=1;
		g1.gridheight=3;
		g1.fill = GridBagConstraints.VERTICAL;
		p22_N1.add(aff_but, g1);
		
		
		// **** p2_N2
		 //border panel
			paneN2 = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),"INFORMATION SUR RECHERCHE PF", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
			p22_N2.setBorder(paneN2);
		
		
		   //creation des composants
		
		lnom = new JLabel("Nom PF : ");
		lfreq = new JLabel("Frequence : ");
		lversion = new JLabel("N° de Version");
		lmod = new JLabel("Modop & Norme : "); 
		lpart = new JLabel("Particularite : ");
		lprep = new JLabel("Préparation : ");
		lt = new JLabel("Tonnage : ");
		lnc = new JLabel("Nb NC : ");
		lderog = new JLabel("Nb Derog : ");
		tnom = new JTextField(8);
		tnom.setEditable(false);
		tversion = new JTextField(8);
		tversion.setEditable(false);
		tfreq = new JTextField(8);
		tfreq.setEditable(false);
		tmod = new JTextField(13);
		tmod.setEditable(false);
		tpart = new JTextField(13);
		tpart.setEditable(false);
		tq = new JTextField(5);
		tq.setEditable(false);
		tprep = new JTextArea(3,10);
		JScrollPane Tprep_sp = new JScrollPane(tprep);
		tprep.setEditable(false);
		tprep.setOpaque(false);
		tt = new JTextField(4);
		tt.setEditable(false);
		tn = new JTextField(4);
		tn.setEditable(false);
		tderog = new JTextField(4);
		tderog.setEditable(false);
		
		//def du placement
		GridBagConstraints g2 = new GridBagConstraints();

		 //placement des composant
			g2.gridx =0;  //col n1
			g2.gridy =0;
			
			g2.anchor = GridBagConstraints.FIRST_LINE_END;
			g2.insets = new Insets(5,5,0,0);
			
			p22_N2.add(lnom,g2);
			g2.gridy=1;
			p22_N2.add(lversion,g2);
			g2.gridy=2;
			p22_N2.add(lfreq,g2);
			g2.gridy=3;
			p22_N2.add(lprep,g2);
			
			
			g2.gridx =2; //col n3
	        g2.gridy =0;
	        p22_N2.add(lpart,g2);
			g2.gridy =1;
			p22_N2.add(lmod,g2);
			
			g2.gridx =4; //col 5
	        g2.gridy =0;
	        p22_N2.add(lt,g2);
	        g2.gridy =1;
	        p22_N2.add(lnc,g2);
	        g2.gridy =2;
	        p22_N2.add(lderog,g2);
	        
	        
	        g2.gridx =1; //col n2
			g2.gridy =0;
			g2.anchor = GridBagConstraints.FIRST_LINE_START;
			p22_N2.add(tnom,g2);
			g2.gridy =1;
			p22_N2.add(tversion,g2);
			g2.gridy =2;
			p22_N2.add(tfreq,g2);
			g2.gridy=3;
			g2.gridwidth=5;
			g2.fill = GridBagConstraints.HORIZONTAL;
		    p22_N2.add(Tprep_sp,g2);
		        
		        
			g2.gridx =3; //col 4
			g2.gridwidth = 1;
			g2.fill = GridBagConstraints.NONE;
			g2.gridy =0;
			p22_N2.add(tpart,g2);
			g2.gridy =1;
			p22_N2.add(tmod,g2);
			
			g2.gridx =5; //col 6
			g2.gridy =0;
			p22_N2.add(tt,g2);
			g2.gridy =1;
			p22_N2.add(tn,g2);
			g2.gridy =2;
			p22_N2.add(tderog,g2);
			
			
		//p2 WEST
			
			 //table
		    res_table = new JTable();
			Object [] name_col = { "DATE FAB", "N° DE LOT","QTITE","CONFORMITE", "FICHE NC","DEROG","R1","RS1","R2","RS2","R3","RS3","R4","RS4","R5","RS5",
					 "R6","RS6","R7","RS7","R8","RS8","R9","RS9","R10","RS10",
					 "R11","RS11","R12","RS12","R13","RS13","R14","RS14","R15","RS15","R16","RS16","R17","RS17","R18","RS18","R19","RS19","R20","RS20","R21","RS21","R22","RS22","R23","RS23","R24","RS24","R25","RS25"};
			
			//model table
			model_restable = new DefaultTableModel();
			model_restable.setColumnIdentifiers(name_col);
			 
			 //association model à la table
			res_table.setModel(model_restable);
			res_table.setShowHorizontalLines(false);
			res_table.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,11));
			res_table.getTableHeader().setBackground(Color.YELLOW);
			res_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			//creation barre def et asso à la table
			 JScrollPane scp2 = new JScrollPane(res_table);
			scp2.setPreferredSize(new Dimension(definition.scp2_Large,definition.scp2_Haut));
			scp2.getViewport().setOpaque(false);
			p22_W.add(scp2);
			row_1 = new Object[56];
			
			
			
		//p2 East
			 //table
		    res_table2 = new JTable();
			Object [] name2_col = { "N°","CRITERES","MIN","MAX","Val_Min","Val_Max","Val_Moy"};
			
			//model table
			model2_restable = new DefaultTableModel();
			model2_restable.setColumnIdentifiers(name2_col);
			 
			 //association model à la table
			res_table2.setModel(model2_restable);
			res_table2.setShowHorizontalLines(false);
			res_table2.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,11));
			res_table2.getTableHeader().setBackground(Color.YELLOW);
			res_table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			//creation barre def et asso à la table
			 JScrollPane scp3 = new JScrollPane(res_table2);
			scp3.setPreferredSize(new Dimension(definition.scp3_Large,definition.scp3_Haut));
			scp3.getViewport().setOpaque(false);
			p22_E.add(scp3);
			row_2 = new Object[7];
			
		//p2 sud
		exp_but = new JButton();
		ImageIcon print_icon = new ImageIcon(getClass().getResource(definition.URL_PRINT));
		 print_lab = new JLabel("IMPRIMER",print_icon,SwingConstants.CENTER);
		 print_lab.setVerticalTextPosition(JLabel.BOTTOM);
		 print_lab.setHorizontalTextPosition(JLabel.CENTER);
		 exp_but.setPreferredSize(new Dimension(100,60));
		 exp_but.add(print_lab);
		
		 exp2_but = new JButton();
			ImageIcon print2_icon = new ImageIcon(getClass().getResource(definition.URL_XLS));
			 xls_lab = new JLabel("EXPORT XLS",print2_icon,SwingConstants.CENTER);
			 xls_lab.setVerticalTextPosition(JLabel.BOTTOM);
			 xls_lab.setHorizontalTextPosition(JLabel.CENTER);
			 exp2_but.setPreferredSize(new Dimension(110,60));
			 exp2_but.add(xls_lab);
		 
		 
		 exp_but.addActionListener(new PDFPRINT());
		 exp2_but.addActionListener(new EXPORT());
		 
		 
		p22_S.add(exp_but);	
		p22_S.add(exp2_but);
	}
	
	//*********************************  FONCTIONNALITE ************************************************************
	
	private void ListeNomPF() {
		
		try {
			 cver.removeAllItems(); //supprime element residuel
			cver.setPreferredSize(new Dimension(120,20));
			 connect= connexion.getConnection(); //initialisation connection
			
			String sql = "SELECT DISTINCT CONCAT(NOM,' : ', TRAME_VER) AS VER_NAME FROM TABLE_PF ORDER BY NOM "; // selecton type de donne de la base
			
			PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
			
			ResultSet rs = pState.executeQuery(); // resultat de la selection
			//ajout element 1 a 1
			while(rs.next()) {
				cver.addItem(rs.getString("VER_NAME"));
				
			}
			//ferme les connexions
			cver.setSelectedIndex(-1);
			rs.close();  
			pState.close();
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	  finally {
		  try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	}
	
	
	
	//methode pour centrer les tableaux
	private void centerTable1() {
		  DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		  custom.setHorizontalAlignment(JLabel.CENTER); // centre les données de ton tableau
		  for (int a=1 ; a < tab_base.getColumnCount() ; a++) // centre chaque cellule de ton tableau
			  tab_base.getColumnModel().getColumn(a).setCellRenderer(custom); 
		  
		  
	}

	private void centerTable2() {
		  DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		  custom.setHorizontalAlignment(JLabel.CENTER); // centre les données de ton tableau
		  for (int b=1 ; b < res_table.getColumnCount() ; b++) // centre chaque cellule de ton tableau
			  res_table.getColumnModel().getColumn(b).setCellRenderer(custom);
		  for (int b=0 ; b < res_table2.getColumnCount() ; b++) // centre chaque cellule de ton tableau
			  res_table2.getColumnModel().getColumn(b).setCellRenderer(custom);

	}
	
	
// **************** CLASS BOUTON
	public class LANCER implements ActionListener {
		  public void actionPerformed(ActionEvent e) {	 
			 
			  //initialise tableau
			  model_restable.setRowCount(0);
			  model2_restable.setRowCount(0);
			   crit = new Object[183];
			   ope = new Object[75];
			
			  
			  // 1=> requete pour calcul des Qtite Tonnage, NC, DEROG
			  try {
				  connect= connexion.getConnection(); //initialisation connection
				  String launch0 = "SELECT SUM(Q_NC) AS CUMUL_NC, SUM(DEROG) AS CUMUL_DEROG, SUM(CASE WHEN CONFORMITE <>'NC' THEN QTITE ELSE 0 END) AS CUMUL_QTITE  "
				  		+ "FROM TABLE_PF WHERE CONCAT(NOM,' : ', TRAME_VER) = ?  AND DATE_C BETWEEN ? AND ? AND CONFORMITE <> ?"; // selecton type de donne de la base
				  PreparedStatement plaunch0 = (PreparedStatement) connect.prepareStatement(launch0);
				  plaunch0.setString(1, (String) cver.getSelectedItem());
				  plaunch0.setString(2, tdd.getText());
				  plaunch0.setString(3, tdf.getText());
				  plaunch0.setString(4, "NC");
				  ResultSet rs_launch0 = plaunch0.executeQuery(); // resultat de la selection
				  while(rs_launch0.next()) {
					  tt.setText(rs_launch0.getString("CUMUL_QTITE"));
					  tn.setText(rs_launch0.getString("CUMUL_NC"));
					  tderog.setText(rs_launch0.getString("CUMUL_DEROG"));
				  }
				  
				  rs_launch0.close();  
				  plaunch0.close();
			  
			  }catch (Exception ev) {
					JOptionPane.showMessageDialog(null, ev);
				}
				  finally {
					  try {
						connect.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  }
				
			  
			  // 2 => requete de selection pour afficher donner dans tableau 1 et cadre information
			  
			  try {
			  connect= connexion.getConnection(); //initialisation connection
			 
			  String launch = "SELECT * FROM TABLE_PF WHERE CONCAT(NOM,' : ', TRAME_VER) = ?  AND DATE_C BETWEEN ? AND ? "; // selecton type de donne de la base
			
			  
			  
			  PreparedStatement plaunch = (PreparedStatement) connect.prepareStatement(launch);
			  plaunch.setString(1, (String) cver.getSelectedItem());
			  plaunch.setString(2, tdd.getText());
			  plaunch.setString(3, tdf.getText());
			  
			  ResultSet rs_launch = plaunch.executeQuery(); // resultat de la selection
				  while(rs_launch.next()) {
					//rempli cadre information
					  tnom.setText(rs_launch.getString("NOM"));
					  tversion.setText(rs_launch.getString("TRAME_VER"));
					  tfreq.setText(rs_launch.getString("FREQUENCE"));
					  tmod.setText(rs_launch.getString("MODOP"));
					  tpart.setText(rs_launch.getString("PARTICULARITE"));
					  tprep.setText(rs_launch.getString("PREPARATION"));
					  temp_qtite = rs_launch.getString("QTITE_PREL");
					  
					// remplir tableau_1  
					row_1[0] = rs_launch.getString("DATE_C");
					row_1[1] = rs_launch.getString("N_LOT");
					row_1[2] = rs_launch.getString("QTITE");
					row_1[3] = rs_launch.getString("CONFORMITE");
					row_1[4] = rs_launch.getString("FICHE_NC");
					row_1[5] = rs_launch.getString("DEROG");
					row_1[6] = rs_launch.getString("R1");
					row_1[7] = rs_launch.getString("RS1");
					row_1[8] = rs_launch.getString("R2");
					row_1[9] = rs_launch.getString("RS2");
					row_1[10] = rs_launch.getString("R3");
					row_1[11] = rs_launch.getString("RS3");
					row_1[12] = rs_launch.getString("R4");
					row_1[13] = rs_launch.getString("RS4");
					row_1[14] = rs_launch.getString("R5");
					row_1[15] = rs_launch.getString("RS5");
					row_1[16] = rs_launch.getString("R6");
					row_1[17] = rs_launch.getString("RS6");
					row_1[18] = rs_launch.getString("R7");
					row_1[19] = rs_launch.getString("RS7");
					row_1[20] = rs_launch.getString("R8");
					row_1[21] = rs_launch.getString("RS8");
					row_1[22] = rs_launch.getString("R9");
					row_1[23] = rs_launch.getString("RS9");
					row_1[24] = rs_launch.getString("R10");
					row_1[25] = rs_launch.getString("RS10");
					row_1[26] = rs_launch.getString("R11");
					row_1[27] = rs_launch.getString("RS11");
					row_1[28] = rs_launch.getString("R12");
					row_1[29] = rs_launch.getString("RS12");
					row_1[30] = rs_launch.getString("R13");
					row_1[31] = rs_launch.getString("RS13");
					row_1[32] = rs_launch.getString("R14");
					row_1[33] = rs_launch.getString("RS14");
					row_1[34] = rs_launch.getString("R15");
					row_1[35] = rs_launch.getString("RS15");	
					row_1[36] = rs_launch.getString("R16");
					row_1[37] = rs_launch.getString("RS16");
					row_1[38] = rs_launch.getString("R17");
					row_1[39] = rs_launch.getString("RS17");
					row_1[40] = rs_launch.getString("R18");
					row_1[41] = rs_launch.getString("RS18");
					row_1[42] = rs_launch.getString("R19");
					row_1[43] = rs_launch.getString("RS19");
					row_1[44] = rs_launch.getString("R20");
					row_1[45] = rs_launch.getString("RS20");
					row_1[46] = rs_launch.getString("R21");
					row_1[47] = rs_launch.getString("RS21");
					row_1[48] = rs_launch.getString("R22");
					row_1[49] = rs_launch.getString("RS22");
					row_1[50] = rs_launch.getString("R23");
					row_1[51] = rs_launch.getString("RS23");
					row_1[52] = rs_launch.getString("R24");
					row_1[53] = rs_launch.getString("RS24");
					row_1[54] = rs_launch.getString("R25");
					row_1[55] = rs_launch.getString("RS25");
							
					model_restable.addRow(row_1);
		
					//association tableau critere
					int c=22;
		           for (int i = 0; i<=171;i++)
				     	{     	
					   crit[i] = rs_launch.getString(c);
					   c++;
					}}
		
				  rs_launch.close();  
				  plaunch.close();
				  centerTable2(); // centre tableau
		  }catch (Exception ev) {
				JOptionPane.showMessageDialog(null, ev);
			}
			  
		finally {
			try {
				connect.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			//3 => requete pour remplir tableau 2  :   MIN/MAX/MOY
			 
			  try {
				  connect= connexion.getConnection(); //initialisation connection
				 
				  String launch3 = "SELECT MIN(RF1), MAX(RF1), AVG(RF1), "
				  		                + "MIN(RF2), MAX(RF2), AVG(RF2), "
				  		                + "MIN(RF3), MAX(RF3), AVG(RF3),"
				  		                + "MIN(RF4), MAX(RF4), AVG(RF4),"
				  		                + "MIN(RF5), MAX(RF5), AVG(RF5),"
				  		                + "MIN(RF6), MAX(RF6), AVG(RF6),  MIN(RF7),  MAX(RF7), AVG(RF7), MIN(RF8),  MAX(RF8),AVG(RF8),MIN(RF9),    MAX(RF9), AVG(RF9), MIN(RF10),MAX(RF10), AVG(RF10), "
				  		                + "MIN(RF11),MAX(RF11),AVG(RF11), MIN(RF12), MAX(RF12),AVG(RF12), MIN(RF13),MAX(RF13),AVG(RF13), MIN(RF14),MAX(RF14),AVG(RF14),MIN(RF15),MAX(RF15), AVG(RF15), "
				  		                + "MIN(RF16),MAX(RF16),AVG(RF16), MIN(RF17), MAX(RF17),AVG(RF17), MIN(RF18),MAX(RF18),AVG(RF18), MIN(RF19),MAX(RF19),AVG(RF19),MIN(RF20),MAX(RF20), AVG(RF20), "
				  		                + "MIN(RF21),MAX(RF21),AVG(RF21), MIN(RF22), MAX(RF22),AVG(RF22), MIN(RF23),MAX(RF23),AVG(RF23), MIN(RF24),MAX(RF24),AVG(RF24),MIN(RF25),MAX(RF25), AVG(RF25) "
				  		                + "FROM TABLE_PF WHERE CONCAT(NOM,' : ', TRAME_VER) = ?  AND DATE_C BETWEEN ? AND ?  "; // selecton type de donne de la base
				
				  
				  
				  PreparedStatement plaunch3 = (PreparedStatement) connect.prepareStatement(launch3);
				  plaunch3.setString(1, (String) cver.getSelectedItem());
				  plaunch3.setString(2, tdd.getText());
				  plaunch3.setString(3, tdf.getText());
				  
				  ResultSet rs_launch3 = plaunch3.executeQuery(); // resultat de la selection
					  while(rs_launch3.next()) {
						  ope[0] = rs_launch3.getString("MIN(RF1)"); ope[1] = rs_launch3.getString("MAX(RF1)"); ope[2] = rs_launch3.getString("AVG(RF1)");
						  ope[3] = rs_launch3.getString("MIN(RF2)"); ope[4] = rs_launch3.getString("MAX(RF2)"); ope[5] = rs_launch3.getString("AVG(RF2)");
						  ope[6] = rs_launch3.getString("MIN(RF3)"); ope[7] = rs_launch3.getString("MAX(RF3)"); ope[8] = rs_launch3.getString("AVG(RF3)");
						  ope[9] = rs_launch3.getString("MIN(RF4)"); ope[10] = rs_launch3.getString("MAX(RF4)"); ope[11] = rs_launch3.getString("AVG(RF4)");
						  ope[12] = rs_launch3.getString("MIN(RF5)"); ope[13] = rs_launch3.getString("MAX(RF5)"); ope[14] = rs_launch3.getString("AVG(RF5)");
						  ope[15] = rs_launch3.getString("MIN(RF6)"); ope[16] = rs_launch3.getString("MAX(RF6)"); ope[17] = rs_launch3.getString("AVG(RF6)");
						  ope[18] = rs_launch3.getString("MIN(RF7)"); ope[19] = rs_launch3.getString("MAX(RF7)"); ope[20] = rs_launch3.getString("AVG(RF7)");
						  ope[21] = rs_launch3.getString("MIN(RF8)"); ope[22] = rs_launch3.getString("MAX(RF8)"); ope[23] = rs_launch3.getString("AVG(RF8)");
						  ope[24] = rs_launch3.getString("MIN(RF9)"); ope[25] = rs_launch3.getString("MAX(RF9)"); ope[26] = rs_launch3.getString("AVG(RF9)");
						  ope[27] = rs_launch3.getString("MIN(RF10)"); ope[28] = rs_launch3.getString("MAX(RF10)"); ope[29] = rs_launch3.getString("AVG(RF10)");
						  ope[30] = rs_launch3.getString("MIN(RF11)"); ope[31] = rs_launch3.getString("MAX(RF11)"); ope[32] = rs_launch3.getString("AVG(RF11)");
						  ope[33] = rs_launch3.getString("MIN(RF12)"); ope[34] = rs_launch3.getString("MAX(RF12)"); ope[35] = rs_launch3.getString("AVG(RF12)");
						  ope[36] = rs_launch3.getString("MIN(RF13)"); ope[37] = rs_launch3.getString("MAX(RF13)"); ope[38] = rs_launch3.getString("AVG(RF13)");
						  ope[39] = rs_launch3.getString("MIN(RF14)"); ope[40] = rs_launch3.getString("MAX(RF14)"); ope[41] = rs_launch3.getString("AVG(RF14)");
						  ope[42] = rs_launch3.getString("MIN(RF15)"); ope[43] = rs_launch3.getString("MAX(RF15)"); ope[44] = rs_launch3.getString("AVG(RF15)");
						  
						  ope[45] = rs_launch3.getString("MIN(RF16)"); ope[46] = rs_launch3.getString("MAX(RF16)"); ope[47] = rs_launch3.getString("AVG(RF16)");
						  ope[48] = rs_launch3.getString("MIN(RF17)"); ope[49] = rs_launch3.getString("MAX(RF17)"); ope[50] = rs_launch3.getString("AVG(RF17)");
						  ope[51] = rs_launch3.getString("MIN(RF18)"); ope[52] = rs_launch3.getString("MAX(RF18)"); ope[53] = rs_launch3.getString("AVG(RF18)");
						  ope[54] = rs_launch3.getString("MIN(RF19)"); ope[55] = rs_launch3.getString("MAX(RF19)"); ope[56] = rs_launch3.getString("AVG(RF19)");
						  ope[57] = rs_launch3.getString("MIN(RF20)"); ope[58] = rs_launch3.getString("MAX(RF20)"); ope[59] = rs_launch3.getString("AVG(RF20)");
						  
						  ope[60] = rs_launch3.getString("MIN(RF21)"); ope[61] = rs_launch3.getString("MAX(RF21)"); ope[62] = rs_launch3.getString("AVG(RF21)");
						  ope[63] = rs_launch3.getString("MIN(RF22)"); ope[64] = rs_launch3.getString("MAX(RF22)"); ope[65] = rs_launch3.getString("AVG(RF22)");
						  ope[66] = rs_launch3.getString("MIN(RF23)"); ope[67] = rs_launch3.getString("MAX(RF23)"); ope[68] = rs_launch3.getString("AVG(RF23)");
						  ope[69] = rs_launch3.getString("MIN(RF24)"); ope[70] = rs_launch3.getString("MAX(RF24)"); ope[71] = rs_launch3.getString("AVG(RF24)");
						  ope[72] = rs_launch3.getString("MIN(RF25)"); ope[73] = rs_launch3.getString("MAX(RF25)"); ope[74] = rs_launch3.getString("AVG(RF25)");
						  
					  }
						
					  rs_launch3.close();  
					  plaunch3.close();
					  centerTable2();
			  }catch (Exception ev) {
					JOptionPane.showMessageDialog(null, ev);
				}
			  finally {
				  try {
					connect.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
	 
			  //initlaise compteur
			  int count=1;  //pour le nombre de critere pour chaque PF
			  int m=0;   //pour afficher MIN, MAX, AVG en fonction PF
			
			  // Boucle pour remplir tableau 2 avec donné récupéré  requette (1) et 2em requete (2)
	     for (int j=0; j<121;j=j+5) { //nombre de controle effectue
			
		    row_2[0] = count; //numero du critere
			row_2[1] = crit[j]; //crite
			row_2[2] = crit[j+1]; // min
			row_2[3] = crit[j+2]; // max
			row_2[4] =  ope[m];         //min val
			m++;
			row_2[5] =  ope[m];          //max val
			m++;
			row_2[6] =  ope[m];         //moyenne val
		     count++;
		      m++;
			model2_restable.addRow(row_2);
			  }	  
		  }}

	public class ModifVal  implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
		     //recupere localisation de la ligne et du bon produit a changer
			  int row_1 = tab_base.getSelectedRow();   //n° de ligne
		      int column_1 = tab_base.getSelectedColumn(); //n° de colonne
		      String nlot = (String) tab_base.getValueAt(row_1,9); // n° de lot
		      String nom = (String) tab_base.getValueAt(row_1, 2); //nom PF
		     String critere = (String) tab_base.getValueAt(row_1, column_1 - 3);
		       
		      //Suivi modif valeur => stocakge dans table
		      try {
		    	  connect= connexion.getConnection(); 
		    	  
		    	  String H_val = "INSERT INTO TALE_HISTO VALUE (?,?,NOW(),?,?)";
		    	  PreparedStatement psH_val= (PreparedStatement) connect.prepareStatement(H_val);		
		    	  
		    	  psH_val.setString(1,null);
		    	  psH_val.setString(2, nom);
		    	  psH_val.setString(3, nlot);
		    	  
		    	  if (tab_base.getColumnName(column_1) == "QTITE") {
		    		  psH_val.setString(4, "QUANTITE");
		    	  } else {
		    		  psH_val.setString(4, critere);
		    	  }
		    	 
		    	  psH_val.executeUpdate();
		    	  psH_val.close();
		    	
		      } catch (Exception insertException) {
	            	JOptionPane.showMessageDialog(null, insertException);
	            }
	      finally {
	    	  try {
				connect.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }
		      
		      
		      
		      // Changement de valeur dans la table principal
		      String columnName = tab_base.getColumnName(column_1); //obtenir titre colonne jtable de la cellule amodifier
		           try {
		        	   connect= connexion.getConnection(); //initialisation connection
		        	   String updVal= "UPDATE table_pf SET "+columnName+" = ? WHERE NOM = ? AND N_LOT = ? ";
		        	   PreparedStatement upd = (PreparedStatement) connect.prepareStatement(updVal);
		 			   upd.setString(1, (String) tab_base.getValueAt(row_1, column_1) );
		 			  upd.setString(2,  nom);
		 			  upd.setString(3, nlot);
		    	       upd.executeUpdate();
		    	       
		    	       upd.close();
		    	       JOptionPane.showMessageDialog(null, "Essai mis a jour dans la table");
		            } catch (Exception insertException) {
		            	JOptionPane.showMessageDialog(null, insertException);
		            }
		      finally {
		    	  try {
					connect.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      }
		  } 
		  }	
	
	
	public class PDFPRINT implements ActionListener {
		//@SuppressWarnings("null")
		public void actionPerformed(ActionEvent ev) {
			Document doc = new Document();
			
			definition.getPathDesktop = definition.getPathDesktop+tnom.getText()+".pdf";
			
			try {
			    PdfWriter.getInstance(doc, new FileOutputStream(definition.getPathDesktop));
			    doc.setPageSize(PageSize.A4.rotate()); // format paysage
			    doc.open();
			    
			    //creation du contenu
			    Paragraph p1 = new Paragraph("TECHNIQUE BETON",FontFactory.getFont(FontFactory.HELVETICA,6));
			    Paragraph block = new Paragraph("  ",FontFactory.getFont(FontFactory.HELVETICA,6));
			    Paragraph titre1 = new Paragraph((String) cver.getSelectedItem(),FontFactory.getFont(FontFactory.HELVETICA,12));
			    titre1.setAlignment(Element.ALIGN_CENTER);
			    
			    PdfPTable tableInfo = new PdfPTable(10);
			    tableInfo.setSpacingBefore(15);
			    PdfPCell cell1 = new PdfPCell(new Phrase("Frequence Controle",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
			    PdfPCell cell2 = new PdfPCell(new Phrase(tfreq.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
			    PdfPCell cell3 = new PdfPCell(new Phrase("Quantite Preleve" ,FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
			    PdfPCell cell4 = new PdfPCell(new Phrase(temp_qtite,FontFactory.getFont(FontFactory.HELVETICA,8)));
			    PdfPCell cell5 = new PdfPCell(new Phrase("Modop" ,FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
			    PdfPCell cell6 = new PdfPCell(new Phrase(tmod.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
			    PdfPCell cell7 = new PdfPCell(new Phrase("Preparation" ,FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
			    PdfPCell cell8 = new PdfPCell(new Phrase(tprep.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
			    PdfPCell cell9 = new PdfPCell(new Phrase("Particularite" ,FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
			    PdfPCell cell10 = new PdfPCell(new Phrase(tpart.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
			    
	   
			    tableInfo.addCell(cell1);
			    tableInfo.addCell(cell2);
			    tableInfo.addCell(cell3);
			    tableInfo.addCell(cell4);
			    tableInfo.addCell(cell5);
			    tableInfo.addCell(cell6);
			    tableInfo.addCell(cell7);
			    tableInfo.addCell(cell8);
			    tableInfo.addCell(cell9);
			    tableInfo.addCell(cell10);
			    tableInfo.setWidths(new int[] {22,40,20,15,20,40,24,40,24,40});
			    
			    PdfPTable tableStat = new PdfPTable(6);
			    tableStat.setWidthPercentage(35);
			    tableStat.setHorizontalAlignment(Element.ALIGN_LEFT);
			    PdfPCell Scell0 = new PdfPCell(new Phrase("CUMUL POUR CETTE VERSION DU "+tdd.getText()+" AU "+tdf.getText(),FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
			    Scell0.setColspan(6);
			    PdfPCell Scell1 = new PdfPCell(new Phrase("Tonnage accepte",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
			    PdfPCell Scell2 = new PdfPCell(new Phrase(tt.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
			    PdfPCell Scell3 = new PdfPCell(new Phrase("Nb NC",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
			    PdfPCell Scell4 = new PdfPCell(new Phrase(tn.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
			    PdfPCell Scell5 = new PdfPCell(new Phrase("Nb DEROG",FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD,BaseColor.BLACK)));
			    PdfPCell Scell6 = new PdfPCell(new Phrase(tderog.getText(),FontFactory.getFont(FontFactory.HELVETICA,8)));
			    
			    tableStat.setSpacingBefore(20);
			    tableStat.setSpacingAfter(20);
			    tableStat.addCell(Scell0);
			    tableStat.addCell(Scell1);
			    tableStat.addCell(Scell2);
			    tableStat.addCell(Scell3);
			    tableStat.addCell(Scell4);
			    tableStat.addCell(Scell5);
			    tableStat.addCell(Scell6);
			    tableStat.setWidths(new int[] {30,30,30,30,30,30});
			   
			    //stocker valeur du tableau 
			    String [] [] TableTempo = new String[res_table2.getColumnCount()][res_table2.getRowCount()];
			   
			    
			    int col=0;
			    int ligne=0;
			     for(col =0; col<res_table2.getColumnCount();col++) {
			    	 for(ligne=0; ligne<res_table2.getRowCount(); ligne++) {
			    		 if (res_table2.getValueAt(ligne, col)!= null) {
			    			 
			    		 
			    		     TableTempo[col][ligne] = res_table2.getValueAt(ligne, col).toString();
			    		    
			    	      } else {
			    		     TableTempo[col][ligne] = "0";
			    	 }
			    		// System.out.println("Valeur de la table ligne : "+ligne+" "+"et col : "+col+" et de la valeur : "+TableTempo[col][ligne]);
			    	 }
			    	 
			     }
			    
			     
			
			   PdfPTable tableCal = new PdfPTable(26);
			   tableCal.setSpacingBefore(20);
			   tableCal.setWidthPercentage(93);
			   tableCal.setHorizontalAlignment(Element.ALIGN_RIGHT);
			   tableCal.setSpacingAfter(3);
			   
			   int compteur =0;
			   tableCal.addCell(new Phrase(res_table2.getColumnName(0),FontFactory.getFont(FontFactory.HELVETICA,4)));
			    
			   for (int column = 0 ; column<res_table2.getColumnCount();column++) {
				   for (int row = 0 ; row <res_table2.getRowCount();row++) {
					   
					   tableCal.addCell(new Phrase(TableTempo[column][row],FontFactory.getFont(FontFactory.HELVETICA,4)));
					  
					   compteur++;
					  
					       switch (compteur) {
					         case 25:
					    	 tableCal.addCell(new Phrase(res_table2.getColumnName(1),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
					    	break;
					         case 50:
						    	 tableCal.addCell(new Phrase(res_table2.getColumnName(2),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
						    	 break;
					         case 75:
						    	 tableCal.addCell(new Phrase(res_table2.getColumnName(3),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
						    	 break;
					         case 100:
						    	 tableCal.addCell(new Phrase(res_table2.getColumnName(4),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
						    	 break;
					         case 125:
						    	 tableCal.addCell(new Phrase(res_table2.getColumnName(5),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
						    	 break;
					         case 150:
						    	 tableCal.addCell(new Phrase(res_table2.getColumnName(6),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
						    	 break;
					    default:
					    	break;
					    
					    }
				   }
			   }
			   tableCal.setWidths(new int[] {31,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30}); 
			    
			    PdfPTable tableVal = new PdfPTable(res_table.getColumnCount());
			    tableVal.setWidthPercentage(100);
			    
			    for(int colname =0; colname < res_table.getColumnCount(); colname++) {
			    tableVal.addCell(new Phrase(res_table.getColumnName(colname),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
			    }
			    
			    
			    for (int rows = 0; rows < res_table.getRowCount();rows++) {
		        	   for( int cols = 0; cols < res_table.getColumnCount(); cols++) {
		        		   if(res_table.getModel().getValueAt(rows, cols)!=null) {
		        			   
		        			   tableVal.addCell(new Phrase(res_table.getValueAt(rows, cols).toString(),FontFactory.getFont(FontFactory.HELVETICA,4)));
		        			   
		        		   }
		        		   else {
		        			   tableVal.addCell(new Phrase(""));
		        			  
		        		   }
		        	   }
			    }	    
			    
			    //ajout contenu au doc
			    doc.add(p1);
			    doc.add(block);
			    doc.add(titre1);
			    doc.add(block);
			    doc.add(tableInfo);
			    doc.add(tableStat);
			    doc.add(tableCal);
			    doc.add(tableVal);

			     doc.close();
			  } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			 try {
					Desktop.getDesktop().open(new File(definition.getPathDesktop));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 definition.getPathDesktop=""; 
		}
	}	
	
	public class EXPORT implements ActionListener {
		  public void actionPerformed(ActionEvent e) {	 
			  SaveData PrintExcel = new SaveData();
			  PrintExcel.TableToExcel(res_table, new File(definition.getPathDesktop+File.separator+"Export.xls"),"Fichier créé sur le bureau : Export.xls");
		  }
	}
	
} //fin de class
