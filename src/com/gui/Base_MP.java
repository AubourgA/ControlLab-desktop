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
//import javax.swing.table.DefaultTableCellRenderer;
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



public class Base_MP extends Habillage{

	//declaration des variables
	
	private static Connection connect; //creer varaible connect
	
	JFrame fen_BMP;
	Image img_BMP;
	private JPanel panel_BMP, p1, p2, ptitre, p1_centre,p1_sud,p2_N,p2_S,p2_W,p2_E,p2_N1,p2_N2;
	private JLabel lversion,lab_text, lnom, lfreq, lmod, lpart,  lprep, lt, lnc, lderog;
	private JLabel mod_lab,print_lab, rech_lab;
	private JTextField tversion,t_text, tdd,tdf, tnom,tfreq,tmod,tpart,tq,tt,tn,tderog;
	private JTextArea tprep;
	private JTabbedPane panelOnglet;
	private JTable tab_base, res_table, res_table2;
	private DefaultTableModel model_BMP, model_restable, model2_restable;
	private JButton aff_but, exp_but,modifier_button;
	private TitledBorder paneN1, paneN2;
	private JLabel lver, ldd, ldf;
	private JComboBox<String> cver;
	private Object[] row_1, row_2,ope,crit;
    protected String temp_qtite;
   

	
    
public Base_MP() throws SQLException {
	fen_BMP();
	ListeNomMP();
}
	

private void fen_BMP() {
	//creation frame principal
	fen_BMP = new JFrame("BASE DES MATIERES PREMIERES");
	
	//definition de la frame
	fen_BMP.setSize(definition.SLarge,definition.SHaut);
	fen_BMP.setLocationRelativeTo(null);

    fen_BMP.setResizable(false);
    panel_BMP = new BaseMPPanel(); //panel principale
    panel_BMP.setLayout(new BorderLayout());
    
    //creation panel
    ptitre = new JPanel(); //panel NORTH
    ptitre.setOpaque(false);
    
    panelOnglet = new JTabbedPane(); //creation des onglets
    p1 = new BasePanel();
    p1.setLayout(new BorderLayout());
   // p1 = new JPanel(new BorderLayout()); // creation des panel des onglets
    p2 = new BasePanel();
    p2.setLayout(new BorderLayout());
    panelOnglet.addTab("Base MP",null,p1,"Recherche simple"); //ajout panel au onglet
    panelOnglet.addTab("Recherche Détaillée",null,p2);
    
    panel_BMP.add(ptitre, BorderLayout.NORTH);
    panel_BMP.add(panelOnglet, BorderLayout.CENTER);
   
    //affichage ensemble panel
    titre();
    onglet_1();
    onglet_2();
    
    //affichage fenetre
    fen_BMP.getContentPane().add(panel_BMP);
    fen_BMP.setVisible(true);
}


private void titre() {
    ptitre.setPreferredSize(new Dimension(0,40));
}

private void onglet_1() {
	//creation panel et affectation
	p1_centre = new JPanel();
	p1_sud = new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
	p1.add(p1_centre, BorderLayout.CENTER);
	p1.add(p1_sud, BorderLayout.SOUTH);
	
	//pour laisser apparaitre image background
	p1_centre.setOpaque(false);
	p1_sud.setOpaque(false);
	

	//creation composant
	lab_text = new JLabel("Recherche d'un contrôle dans la base : ");
	t_text = new JTextField(20);
	modifier_button = new JButton();
	
	//icon bouton
	 ImageIcon mod_icon = new ImageIcon(getClass().getResource(definition.URL_MODIFIER));
	 mod_lab = new JLabel("MODIFIER ESSAI",mod_icon,SwingConstants.CENTER);
	 mod_lab.setVerticalTextPosition(JLabel.BOTTOM);
	 mod_lab.setHorizontalTextPosition(JLabel.CENTER);
	 modifier_button.setPreferredSize(new Dimension(140,60));
	 modifier_button.add(mod_lab);

	 //action sur bouton
	 modifier_button.addActionListener(new ModifVal());
	
	//creation table
	tab_base = new JTable();
	      // titre colonne
	Object [] col_baseMP = {"TRAME", "TRAME_VER","MP","FREQ","QTITE_PRELEVE","MODOP ET NORME","PREPARATION","PARTICULARITE","CONFORMITE","N° LOT","FICHE_NC",
			"NB_NC","NB_DEROG","DATE_CONTROLE","QTITE","RESP","OBSERVATION","T1","C1","MIN1","MAX1","R1","T2","C2","MIN2","MAX2","R2","T3","C3","MIN3","MAX3","R3","T4","C4","MIN4","MAX4","R4","T5","C5","MIN5","MAX5","R5",
			"T6","C6","MIN6","MAX6","R6","T7","C7","MIN7","MAX7","R7","T8","C8","MIN8","MAX8","R8","T9","C9","MIN9","MAX9","R9","T10","C10","MIN10","MAX10","R10",
			"T11","C11","MIN11","MAX11","R11","T12","C12","MIN12","MAX12","R12","T13","C13","MIN13","MAX13","R13","T14","C14","MIN14","MAX14","R14","T15","C15","MIN15","MAX15","R15",
			"RS1","RS2","RS3","RS4","RS5","RS6","RS7","RS8","RS9","RS10","RS11","RS12","RS13","RS14","RS15",
			"RF1","RF2","RF3","RF4","RF5","RF6","RF7","RF8","RF9","RF10","RF11","RF12","RF13","RF14","RF15"};
			
	          // model table 
	   model_BMP = new DefaultTableModel() {
				private static final long serialVersionUID = 1L;			
				public boolean isCellEditable(int row, int col){  
						return col == 14 || col == 21 || col == 26 || col == 31 || col == 36 || col == 41 || col == 46 || col == 51 || col == 56 || col == 61 || col == 66 || col == 71 || col == 76 || col == 81 || col == 86 || col == 91;
					}};
					

	   model_BMP.setColumnIdentifiers(col_baseMP);
	   final TableRowSorter<TableModel> sorter_MP = new TableRowSorter<>(model_BMP); 
	        // caractistique table
	 tab_base.setModel(model_BMP);
	 tab_base.setShowHorizontalLines(false);
	 tab_base.setRowSorter(sorter_MP);
	 tab_base.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,11));
	 tab_base.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	 
	      // ajout d'un barre de defillement
	  JScrollPane s = new JScrollPane(tab_base);
	  s.getViewport().setOpaque(false);
	  s.setPreferredSize(new Dimension(definition.s_Large,definition.s_Haut));
	  Object[] row_MP = new Object[123];
	 
	  
	   //insertion BDD dans Jtable
	  try {
			 connect= connexion.getConnection(); //fait appel a la classe connexion 
			 String tablemp = "SELECT * FROM TABLE_MP ORDER BY NOM"; 
			 PreparedStatement pstable = (PreparedStatement) connect.prepareStatement(tablemp);
			ResultSet rs = pstable.executeQuery(); // resultat de la selection
			while(rs.next()) {
				    // boucle pour remplir le tableau				
				int a = 2;            
				for (int k= 0; k<122;k++) {
					row_MP[k] = rs.getString(a);
					a++;}
				model_BMP.addRow(row_MP);}
		
		//ferme les connexions		
			rs.close();  
			pstable.close();
			prime.CentrerTableau(1,tab_base);
			
		 } catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			  }
	  finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			  sorter_MP.setRowFilter(null);
			 } else {
			  sorter_MP.setRowFilter(RowFilter.regexFilter(s));
			 }
			 }
		 });
	  
	//affection au panel
	p1_centre.add(s);
	p1_sud.add(lab_text);
	p1_sud.add(t_text);
	p1_sud.add(modifier_button);	
}

 //paneau de recherche
private void onglet_2() {
	//creation des panel
	p2_N = new JPanel();
	p2_N.setLayout(new BoxLayout(p2_N,BoxLayout.X_AXIS));
	p2_N1 = new JPanel(new GridBagLayout());
	p2_N2 = new JPanel(new GridBagLayout());
	p2_S = new JPanel();
	p2_W= new JPanel();
	p2_E= new JPanel();
	
	//transparence des panels
	p2_N.setOpaque(false);
	p2_N1.setOpaque(false);
	p2_N2.setOpaque(false);
	p2_S.setOpaque(false);
	p2_W.setOpaque(false);
	p2_E.setOpaque(false);
	

	//affection panel
	p2_N.add(p2_N1);
	p2_N.add(p2_N2);
	p2.add(p2_N, BorderLayout.NORTH);
	p2.add(p2_W, BorderLayout.WEST);
	p2.add(p2_E, BorderLayout.EAST);
	p2.add(p2_S, BorderLayout.SOUTH);
	
	
	// p2 nord
	    // ****  p2_N1
	
	   //border panel
	paneN1 = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),"CRITERES DE SELECTION", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
	p2_N1.setBorder(paneN1);
	//p2_N1.setPreferredSize(new Dimension(300,152));
	
	   //creation des composants
	
	lver = new JLabel("Nom & Version : ");
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
	 rech_lab = new JLabel("<html><center>LANCER <br>RECHERCHE</center></html>",rech_icon,SwingConstants.CENTER);
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
	p2_N1.add(lver, g1);
	g1.gridy =1;
	p2_N1.add(ldd, g1);
	g1.gridy =2;
	p2_N1.add(ldf, g1);
	
	g1.gridx =1;
	g1.gridy =0;
	g1.anchor = GridBagConstraints.FIRST_LINE_START;
	p2_N1.add(cver, g1);
	g1.gridy =1;
	p2_N1.add(tdd, g1);
	g1.gridy =2;
	p2_N1.add(tdf, g1);
	
	g1.gridx =2;
	g1.gridy =0;
	//g1.gridwidth=1;
	g1.gridheight=3;
	g1.fill = GridBagConstraints.VERTICAL;
	p2_N1.add(aff_but, g1);
	
	
	// **** p2_N2
	 //border panel
		paneN2 = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),"INFORMATION SUR RECHERCHE MP", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
		p2_N2.setBorder(paneN2);
	
	
	   //creation des composants
	
	lnom = new JLabel("Nom MP : ");
	lfreq = new JLabel("Frequence : ");
	lversion = new JLabel("N° de Version");
	lmod = new JLabel("Modop & Norme : "); 
	lpart = new JLabel("Particularite : ");
	//lq = new JLabel("Quantite : ");
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
		
		p2_N2.add(lnom,g2);
		g2.gridy=1;
		p2_N2.add(lversion,g2);
		g2.gridy=2;
		p2_N2.add(lfreq,g2);
		g2.gridy=3;
		p2_N2.add(lprep,g2);
				
		g2.gridx =2; //col n3
        g2.gridy =0;
        p2_N2.add(lpart,g2);
		g2.gridy =1;
		p2_N2.add(lmod,g2);
		
		g2.gridx =4; //col 5
        g2.gridy =0;
        p2_N2.add(lt,g2);
        g2.gridy =1;
        p2_N2.add(lnc,g2);
        g2.gridy =2;
        p2_N2.add(lderog,g2);
        
        
        g2.gridx =1; //col n2
		g2.gridy =0;
		g2.anchor = GridBagConstraints.FIRST_LINE_START;
		p2_N2.add(tnom,g2);
		g2.gridy =1;
		p2_N2.add(tversion,g2);
		g2.gridy =2;
		p2_N2.add(tfreq,g2);
		g2.gridy=3;
		g2.gridwidth=5;
		g2.fill = GridBagConstraints.HORIZONTAL;
	    p2_N2.add(Tprep_sp,g2);
	        	        
		g2.gridx =3; //col 4
		g2.gridwidth = 1;
		g2.fill = GridBagConstraints.NONE;
		g2.gridy =0;
		p2_N2.add(tpart,g2);
		g2.gridy =1;
		p2_N2.add(tmod,g2);
		

		g2.gridx =5; //col 6
		g2.gridy =0;
		p2_N2.add(tt,g2);
		g2.gridy =1;
		p2_N2.add(tn,g2);
		g2.gridy =2;
		p2_N2.add(tderog,g2);
		
		
	//p2 WEST
		
		 //table
	    res_table = new JTable();
		Object [] name_col = { "DATE CONTROLE", "N° DE LOT","VALEUR QTITE","CONFORMITE", "FICHE NC","DEROG","R1","RS1","R2","RS2","R3","RS3","R4","RS4","R5","RS5",
				 "R6","RS6","R7","RS7","R8","RS8","R9","RS9","R10","RS10",
				 "R11","RS11","R12","RS12","R13","RS13","R14","RS14","R15","RS15"};
		
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
		p2_W.add(scp2);
	    row_1 = new Object[36];
	   
		
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
		p2_E.add(scp3);
		row_2 = new Object[7];
		
	//p2 sud
	exp_but = new JButton();
	ImageIcon print_icon = new ImageIcon(getClass().getResource(definition.URL_PRINT));
	 print_lab = new JLabel("IMPRIMER",print_icon,SwingConstants.CENTER);
	 print_lab.setVerticalTextPosition(JLabel.BOTTOM);
	 print_lab.setHorizontalTextPosition(JLabel.CENTER);
	 exp_but.setPreferredSize(new Dimension(100,60));
	 exp_but.add(print_lab);
	 
	 //action bouton
	 exp_but.addActionListener(new PDFPRINT());
	 
	p2_S.add(exp_but);
	}

// ****************************  Fonction diverses* **********************
private void ListeNomMP() throws SQLException {
	
	try {
		 cver.removeAllItems(); //supprime element residuel
		cver.setPreferredSize(new Dimension(120,20));
		 connect= connexion.getConnection(); //initialisation connection
		
		String sql = "SELECT DISTINCT CONCAT(NOM,' - ver ', TRAME_VER) AS VER_NAME FROM TABLE_MP ORDER BY NOM "; // selecton type de donne de la base
		PreparedStatement pState = (PreparedStatement) connect.prepareStatement(sql);
		ResultSet rs = pState.executeQuery(); // resultat de la selection
		//ajout element 1 a 1
		while(rs.next()) {
			cver.addItem(rs.getString("VER_NAME"));
		}	
		
		//ferme les connexions
		cver.setSelectedIndex(-1);
		rs.close();  pState.close();
			
	}catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	}
 finally {
	 connect.close();
 }
}

// *************  CLASS BOUTON ******************
public class LANCER implements ActionListener {
	  public void actionPerformed(ActionEvent e) {	 
		 
		  //initialise tableau
		  model_restable.setRowCount(0);
		  model2_restable.setRowCount(0);
		  crit = new Object[103];
		  ope = new Object[45];
		
		  
		  // 1=> requete pour calcul des Qtite Tonnage, NC, DEROG
		  try {
			  connect= connexion.getConnection(); //initialisation connection
			  String launch0 = "SELECT SUM(Q_NC) AS CUMUL_NC, SUM(DEROG) AS CUMUL_DEROG, SUM(CASE WHEN CONFORMITE <>'NC' THEN QTITE ELSE 0 END) AS CUMUL_QTITE  "
			  		+ "FROM TABLE_MP WHERE CONCAT(NOM,' - ver ', TRAME_VER) = ?  AND DATE_C BETWEEN ? AND ? AND CONFORMITE <> ? "; // selecton type de donne de la base AND CONFORMITE <> ?
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
			  
			  rs_launch0.close();  plaunch0.close();
             
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
		 
		  String launch = "SELECT * FROM TABLE_MP WHERE CONCAT(NOM,' - ver ', TRAME_VER) = ?  AND DATE_C BETWEEN ? AND ? "; // selecton type de donne de la base
		
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
				model_restable.addRow(row_1);
	
				//association tableau critere requete (1)
				int c=20;
	           for (int i = 0; i<=90;i++)
			     	{     	
				   crit[i] = rs_launch.getString(c);
				   c++;
				}}
	
			    rs_launch.close(); plaunch.close(); 
			    prime.CentrerTableau(1,res_table);
			  
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
			  		                + "MIN(RF6), MAX(RF6), AVG(RF6), MIN(RF7), MAX(RF7), AVG(RF7), MIN(RF8),MAX(RF8),AVG(RF8),MIN(RF9),MAX(RF9),AVG(RF9),MIN(RF10),MAX(RF10), AVG(RF10),"
			  		                + "MIN(RF11),MAX(RF11),AVG(RF11), MIN(RF12), MAX(RF12),AVG(RF12), MIN(RF13),MAX(RF13),AVG(RF13), MIN(RF14),MAX(RF14),AVG(RF14),MIN(RF15),MAX(RF15), AVG(RF15) "
			  		                + "FROM TABLE_MP WHERE CONCAT(NOM,' - ver ', TRAME_VER) = ?  AND DATE_C BETWEEN ? AND ?  "; // selecton type de donne de la base
			
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
					}  
				 
				  rs_launch3.close();  plaunch3.close();
				 
				  prime.CentrerTableau(0, res_table2);
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
		  int count=1;
		  int m=0;
		  // Boucle pour remplir tableau 2 avec donné récupéré  requette (1) et 2em requete (2)
     for (int j=0; j<72;j=j+5) { //nombre de controle effectue
		
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
	      String nom = (String) tab_base.getValueAt(row_1, 2); //nom MP
          
	      String columnName = tab_base.getColumnName(column_1); //obtenir titre colonne jtable de la cellule amodifier
	           try {
	        	   connect= connexion.getConnection(); //initialisation connection
	        	   String updVal= "UPDATE table_mp SET "+columnName+" = ? WHERE NOM = ? AND N_LOT = ? ";
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
		
		
		definition.getPathDesktop = definition.getPathDesktop+File.separator+tnom.getText()+".pdf";
		
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
		            } 	 
		     }
  
		
		   PdfPTable tableCal = new PdfPTable(16);
		   tableCal.setSpacingBefore(20);
		   tableCal.setWidthPercentage(89);
		   tableCal.setHorizontalAlignment(Element.ALIGN_RIGHT);
		   tableCal.setSpacingAfter(3);
		   
		   int compteur =0;
		   tableCal.addCell(new Phrase(res_table2.getColumnName(0),FontFactory.getFont(FontFactory.HELVETICA,4)));
		    
		   for (int column = 0 ; column<res_table2.getColumnCount();column++) {
			   for (int row = 0 ; row <res_table2.getRowCount();row++) {
				   
				   tableCal.addCell(new Phrase(TableTempo[column][row],FontFactory.getFont(FontFactory.HELVETICA,4)));
				  
				   compteur++;
				  
				       switch (compteur) {
				         case 15:
				    	 tableCal.addCell(new Phrase(res_table2.getColumnName(1),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
				    	break;
				         case 30:
					    	 tableCal.addCell(new Phrase(res_table2.getColumnName(2),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
					    	 break;
				         case 45:
					    	 tableCal.addCell(new Phrase(res_table2.getColumnName(3),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
					    	 break;
				         case 60:
					    	 tableCal.addCell(new Phrase(res_table2.getColumnName(4),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
					    	 break;
				         case 75:
					    	 tableCal.addCell(new Phrase(res_table2.getColumnName(5),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
					    	 break;
				         case 90:
					    	 tableCal.addCell(new Phrase(res_table2.getColumnName(6),FontFactory.getFont(FontFactory.HELVETICA,4,Font.BOLD,BaseColor.BLACK)));
					    	 break;
				    default:
				    	break;
				    
				    }
			   }
		   }
		   tableCal.setWidths(new int[] {30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30}); 
		    
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
		 //reinitialise variable
		 definition.getPathDesktop=""; 
	}
}

} //fin de classe 
