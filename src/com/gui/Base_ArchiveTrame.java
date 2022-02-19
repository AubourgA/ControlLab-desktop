package com.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.jdbc.PreparedStatement;

import connexion.BD.connexion;



public class Base_ArchiveTrame extends Habillage{

	//declaration des variables
	JFrame fen_BAtrame;
	Image img_BAtrame;
	private JPanel panel_BAtrame, p_AMP, p_APF, ptitre, p1_centre,p1_sud, p2_centre,p2_sud;
	private JLabel  lab_text,lab_text2;
	private JTextField t_text,t_text2;
	
	private JTabbedPane panelOnglet;
	private JTable tab_ATrameMP, tab_ATramePF;
	private DefaultTableModel model_AtrameMP,model_AtramePF;
	private Object[] row_ATMP;
	private static Connection connect; //creer varaible connect
	
public Base_ArchiveTrame() {
	fen_BaseATrame();
}
	


private void fen_BaseATrame() {
	//creation frame principal
	fen_BAtrame = new JFrame("BASE DES ARCHIVES TRAMES MP & PF");
	
	//definition de la frame
	fen_BAtrame.setSize(definition.SLarge,definition.SHaut);
	fen_BAtrame.setLocationRelativeTo(null);

    fen_BAtrame.setResizable(false);
    
    panel_BAtrame = new TitreArchivePanel(); //panel principale
    panel_BAtrame.setLayout(new BorderLayout());
    
    //creation panel
    ptitre = new JPanel(); //panel NORTH
    ptitre.setOpaque(false);
    
    panelOnglet = new JTabbedPane(); //creation des onglets
    p_AMP = new BasePanel(); // creation des panel des onglets
    p_APF = new BasePanel();
    p_AMP.setLayout(new BorderLayout()); // creation des panel des onglets
    p_APF.setLayout(new BorderLayout());
    
    
    
    panelOnglet.addTab("Archive Trame MP",null,p_AMP); //ajout panel au onglet
    panelOnglet.addTab("Archive Trame PF",null,p_APF);
    
    panel_BAtrame.add(ptitre, BorderLayout.NORTH);
    panel_BAtrame.add(panelOnglet, BorderLayout.CENTER);
   
    //affichage ensemble panel
    titre();
    onglet_AtrameMP();
    onglet_AtramePF();
    
    //affichage fenetre
    fen_BAtrame.getContentPane().add(panel_BAtrame);
    fen_BAtrame.setVisible(true);
}


private void titre() {
	 ptitre.setPreferredSize(new Dimension(0,40));	
}

private void onglet_AtrameMP() {
	//creation panel et affectation
			p1_centre = new JPanel();
			p1_sud = new JPanel(new FlowLayout());
			p_AMP.add(p1_centre, BorderLayout.CENTER);
			p_AMP.add(p1_sud, BorderLayout.SOUTH);
			
			//pour laisser apparaitre image background
			p1_centre.setOpaque(false);
			p1_sud.setOpaque(false);
			
			
			//creation composant
			lab_text = new JLabel("Recherche d'une archive dans la base : ");
			t_text = new JTextField(20);
			
			//creation table
			tab_ATrameMP = new JTable();
			      // titre colonne
			Object [] col_trameAMP = { "TRAME", "TRAME_VER","MP","FREQ","QTITE_PREL","MODOP ET NORME","PREPARATION","PARTICULARITE","N_LOT","DATE_CREATION","QTITE_UNITE",
					"T1","C1","MIN1","MAX1","T2","C2","MIN2","MAX2","T3","C3","MIN3","MAX3","T4","C4","MIN4","MAX4","T5","C5","MIN5","MAX5","T6",
					"C6","MIN6","MAX6","T7","C7","MIN7","MAX7","T8","C8","MIN8","MAX8","T9","C9","MIN9","MAX9","T10","C10","MIN10","MAX10","T11",
					"C11","MIN11","MAX11","T12","C12","MIN12","MAX12","T13","C13","MIN13","MAX13","T14","C14","MIN14","MAX14","T15","C15","MIN15","MAX15"};
					
				
					
			          // model table 
			   model_AtrameMP = new DefaultTableModel();
			   model_AtrameMP.setColumnIdentifiers(col_trameAMP);
			   final TableRowSorter<TableModel> sorterATMP = new TableRowSorter<>(model_AtrameMP); 
			   
			        // caractistique table
			   tab_ATrameMP.setModel(model_AtrameMP);
			   tab_ATrameMP.setShowHorizontalLines(false);
			   tab_ATrameMP.setRowSorter(sorterATMP);
			   tab_ATrameMP.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,11));
			   tab_ATrameMP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 
			   row_ATMP = new Object[72];
			   
			      // ajout d'un barre de defillement
			  JScrollPane s1 = new JScrollPane(tab_ATrameMP);
			 
			  s1.getViewport().setOpaque(false);
			  s1.setPreferredSize(new Dimension(definition.s_Large,definition.s_Haut));
			
			  
			  //
			  //Insertion BDD dans la tableau
				 try {
					 connect= connexion.getConnection(); //fait appel a la classe connexion 
					 String tableatmp = "SELECT * FROM TABLE_ATMP ORDER BY NOM"; 
	  				 PreparedStatement pstableamp = (PreparedStatement) connect.prepareStatement(tableatmp);
	  				ResultSet rs = pstableamp.executeQuery(); // resultat de la selection
	  				while(rs.next()) {
	  							
	  					row_ATMP[0] = rs.getString(2); row_ATMP[11] = rs.getString(13);
	  					row_ATMP[1] = rs.getString(3);row_ATMP[12] = rs.getString(14);
	  					row_ATMP[2] = rs.getString(4);row_ATMP[13] = rs.getString(15);
	  					row_ATMP[3] = rs.getString(5);row_ATMP[14] = rs.getString(16);
	  					row_ATMP[4] = rs.getString(6);row_ATMP[15] = rs.getString(17);
	  					row_ATMP[5] = rs.getString(7);row_ATMP[16] = rs.getString(18);
	  					row_ATMP[6] = rs.getString(8);row_ATMP[17] = rs.getString(19);
	  					row_ATMP[7] = rs.getString(9); row_ATMP[18] =rs.getString(20);
	  					row_ATMP[8] = rs.getString(10);row_ATMP[19] =rs.getString(21);
	  					row_ATMP[9] = rs.getString(11);row_ATMP[20] =rs.getString(22);
	  					row_ATMP[10] = rs.getString(12);row_ATMP[21] =rs.getString(23);
	  					
	  					row_ATMP[22] = rs.getString(24); row_ATMP[23] = rs.getString(25);
	  					row_ATMP[24] = rs.getString(26);row_ATMP[25] = rs.getString(27);
	  					row_ATMP[26] = rs.getString(28);row_ATMP[27] = rs.getString(29);
	  					row_ATMP[28] = rs.getString(30);row_ATMP[29] = rs.getString(31);
	  					row_ATMP[30] = rs.getString(32);row_ATMP[31] = rs.getString(33);
	  					row_ATMP[32] = rs.getString(34);row_ATMP[33] = rs.getString(35);
	  					row_ATMP[34] = rs.getString(36);row_ATMP[35] = rs.getString(37);
	  					row_ATMP[36] = rs.getString(38); row_ATMP[37] =rs.getString(39);
	  					row_ATMP[38] = rs.getString(40);row_ATMP[39] =rs.getString(41);
	  					row_ATMP[40] = rs.getString(42);row_ATMP[41] =rs.getString(43);
	  					row_ATMP[42] = rs.getString(44);row_ATMP[43] =rs.getString(45);
	  					row_ATMP[44] = rs.getString(46); row_ATMP[45] = rs.getString(47);
	  					row_ATMP[46] = rs.getString(48);row_ATMP[47] = rs.getString(49);
	  					row_ATMP[48] = rs.getString(50);row_ATMP[49] = rs.getString(51);
	  					row_ATMP[50] = rs.getString(52);row_ATMP[51] = rs.getString(53);
	  					row_ATMP[52] = rs.getString(54);row_ATMP[53] = rs.getString(55);
	  					row_ATMP[54] = rs.getString(56);row_ATMP[55] = rs.getString(57);
	  					row_ATMP[56] = rs.getString(58);row_ATMP[57] = rs.getString(59);
	  					row_ATMP[58] = rs.getString(60); row_ATMP[59] =rs.getString(61);
	  					row_ATMP[60] = rs.getString(62);row_ATMP[61] =rs.getString(63);
	  					row_ATMP[62] = rs.getString(64);row_ATMP[63] =rs.getString(65);
	  					row_ATMP[64] = rs.getString(66);row_ATMP[65] =rs.getString(67);
	  					
	  					row_ATMP[66] = rs.getString(68);row_ATMP[67] =rs.getString(69);
	  					row_ATMP[68] = rs.getString(70);row_ATMP[69] =rs.getString(71);
	  					row_ATMP[70] = rs.getString(72);
	  					
	  					  				
	  					model_AtrameMP.addRow(row_ATMP);}
	  					
	  				
	  			//ferme les connexions		
	 				rs.close();  
	 				pstableamp.close();
					 
				 } catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					 
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

					  sorterATMP.setRowFilter(null);
					 } else {

					  sorterATMP.setRowFilter(RowFilter.regexFilter(s));
					 }
					 }
				 });
				  
			  
			  

			//affection au panel
			p1_sud.add(lab_text);
			p1_sud.add(t_text);
			p1_centre.add(s1);
}


private void onglet_AtramePF() {
	//creation panel et affectation
		p2_centre = new JPanel();
		p2_sud = new JPanel(new FlowLayout());
		p_APF.add(p2_centre, BorderLayout.CENTER);
		p_APF.add(p2_sud, BorderLayout.SOUTH);
		
		
		//pour laisser apparaitre image background
		p2_centre.setOpaque(false);
		p2_sud.setOpaque(false);
		
		//creation composant
		lab_text2 = new JLabel("Recherche d'une archive dans la base : ");
		t_text2 = new JTextField(20);
		
		//creation table
		tab_ATramePF = new JTable();
		      // titre colonne
		Object [] col_trameAPF = { "TRAME", "TRAME_VER","PF","FREQ","QTITE_PREL","MODOP","PREPARATION","PARTICULARITE","N_LOT","LOT_FICTIF",
				"DATE_VALIDATION","QTITE_UNIT","CAT",
				"T1","C1","MIN1","MAX1","T2","C2","MIN2","MAX2","T3","C3","MIN3","MAX3","T4","C4","MIN4","MAX4","T5","C5","MIN5","MAX5",
				"T6","C6","MIN6","MAX6","T7","C7","MIN7","MAX7","T8","C8","MIN8","MAX8","T9","C9","MIN9","MAX9","T10","C10","MIN10","MAX10",
				"T11","C11","MIN11","MAX11","T12","C12","MIN12","MAX12","T13","C13","MIN13","MAX13","T14","C14","MIN14","MAX14","T15","C15","MIN15","MAX15",
				"T16","C16","MIN16","MAX16","T17","C17","MIN17","MAX17","T18","C18","MIN18","MAX18","T19","C19","MIN19","MAX19","T20","C20","MIN120","MAX20",
				"T21","C21","MIN21","MAX21","T22","C22","MIN22","MAX22","T23","C23","MIN23","MAX23","T24","C24","MIN24","MAX24","T25","C25","MIN25","MAX25"};
		 
		// model table 
		   model_AtramePF = new DefaultTableModel();
		   model_AtramePF.setColumnIdentifiers(col_trameAPF);
		   final TableRowSorter<TableModel> sorterATPF = new TableRowSorter<>(model_AtramePF); 
		  
		   
		   // caractistique table
		   tab_ATramePF.setModel(model_AtramePF);
		   tab_ATramePF.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,11));
		   tab_ATramePF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		   tab_ATramePF.setRowSorter(sorterATPF);
		   tab_ATramePF.setShowHorizontalLines(false);
		   
		      // ajout d'un barre de defillement
		  JScrollPane s2 = new JScrollPane(tab_ATramePF);
		 
		  s2.getViewport().setOpaque(false);
		  s2.setPreferredSize(new Dimension(definition.s_Large,definition.s_Haut));
		  Object[] row_ATPF = new Object[114];

		  
		  //Insertion BDD dans la tableau
			 try {
				 connect= connexion.getConnection(); //fait appel a la classe connexion 
				 String tableatpf = "SELECT * FROM TABLE_ATPF ORDER BY NOM"; 
				 PreparedStatement pstableapf = (PreparedStatement) connect.prepareStatement(tableatpf);
				ResultSet rs = pstableapf.executeQuery(); // resultat de la selection
				while(rs.next()) {
							
					row_ATPF[0] = rs.getString(2); row_ATPF[11] = rs.getString(13);
					row_ATPF[1] = rs.getString(3);row_ATPF[12] = rs.getString(14);
					row_ATPF[2] = rs.getString(4);row_ATPF[13] = rs.getString(15);
					row_ATPF[3] = rs.getString(5);row_ATPF[14] = rs.getString(16);
					row_ATPF[4] = rs.getString(6);row_ATPF[15] = rs.getString(17);
					row_ATPF[5] = rs.getString(7);row_ATPF[16] = rs.getString(18);
					row_ATPF[6] = rs.getString(8);row_ATPF[17] = rs.getString(19);
					row_ATPF[7] = rs.getString(9); row_ATPF[18] =rs.getString(20);
					row_ATPF[8] = rs.getString(10);row_ATPF[19] =rs.getString(21);
					row_ATPF[9] = rs.getString(11);row_ATPF[20] =rs.getString(22);
					row_ATPF[10] = rs.getString(12);row_ATPF[21] =rs.getString(23);					
					row_ATPF[22] = rs.getString(24); row_ATPF[23] = rs.getString(25);
					row_ATPF[24] = rs.getString(26);row_ATPF[25] = rs.getString(27);
					row_ATPF[26] = rs.getString(28);row_ATPF[27] = rs.getString(29);
					row_ATPF[28] = rs.getString(30);row_ATPF[29] = rs.getString(31);
					row_ATPF[30] = rs.getString(32);row_ATPF[31] = rs.getString(33);
					row_ATPF[32] = rs.getString(34);row_ATPF[33] = rs.getString(35);
					row_ATPF[34] = rs.getString(36);row_ATPF[35] = rs.getString(37);
					row_ATPF[36] = rs.getString(38); row_ATPF[37] =rs.getString(39);
					row_ATPF[38] = rs.getString(40);row_ATPF[39] =rs.getString(41);
					row_ATPF[40] = rs.getString(42);row_ATPF[41] =rs.getString(43);
					row_ATPF[42] = rs.getString(44);row_ATPF[43] =rs.getString(45);
					row_ATPF[44] = rs.getString(46); row_ATPF[45] = rs.getString(47);
					row_ATPF[46] = rs.getString(48);row_ATPF[47] = rs.getString(49);
					row_ATPF[48] = rs.getString(50);row_ATPF[49] = rs.getString(51);
					row_ATPF[50] = rs.getString(52);row_ATPF[51] = rs.getString(53);
					row_ATPF[52] = rs.getString(54);row_ATPF[53] = rs.getString(55);
					row_ATPF[54] = rs.getString(56);row_ATPF[55] = rs.getString(57);
					row_ATPF[56] = rs.getString(58);row_ATPF[57] = rs.getString(59);
					row_ATPF[58] = rs.getString(60); row_ATPF[59] =rs.getString(61);
					row_ATPF[60] = rs.getString(62);row_ATPF[61] =rs.getString(63);
					row_ATPF[62] = rs.getString(64);row_ATPF[63] =rs.getString(65);
					row_ATPF[64] = rs.getString(66);row_ATPF[65] =rs.getString(67);	
					row_ATPF[66] = rs.getString(68);row_ATPF[67] =rs.getString(69);
					row_ATPF[68] = rs.getString(70);row_ATPF[69] =rs.getString(71);
					row_ATPF[70] = rs.getString(72);row_ATPF[71] =rs.getString(73);
					row_ATPF[72] = rs.getString(74);row_ATPF[73] =rs.getString(75);
					row_ATPF[74] = rs.getString(76);row_ATPF[75] =rs.getString(77);
					row_ATPF[76] = rs.getString(78);row_ATPF[77] =rs.getString(79);
					row_ATPF[78] = rs.getString(80);row_ATPF[79] =rs.getString(81);
					row_ATPF[80] = rs.getString(82);row_ATPF[81] =rs.getString(83);
					row_ATPF[82] = rs.getString(84);row_ATPF[83] =rs.getString(85);	
					row_ATPF[84] = rs.getString(86);row_ATPF[85] =rs.getString(87);
					row_ATPF[86] = rs.getString(88);row_ATPF[87] =rs.getString(89);
					row_ATPF[88] = rs.getString(90);row_ATPF[89] =rs.getString(91);
					row_ATPF[90] = rs.getString(92);row_ATPF[91] =rs.getString(93);
					row_ATPF[92] = rs.getString(94);row_ATPF[93] =rs.getString(95);
					row_ATPF[94] = rs.getString(96);row_ATPF[95] =rs.getString(97);
					
					row_ATPF[96] = rs.getString(98);row_ATPF[97] =rs.getString(99);
					row_ATPF[98] = rs.getString(100);row_ATPF[99] =rs.getString(101);	
					row_ATPF[100] = rs.getString(102);row_ATPF[101] =rs.getString(103);
					row_ATPF[102] = rs.getString(104);row_ATPF[103] =rs.getString(105);
					row_ATPF[104] = rs.getString(106);row_ATPF[105] =rs.getString(107);
					row_ATPF[106] = rs.getString(108);row_ATPF[107] =rs.getString(109);
					row_ATPF[108] = rs.getString(110);row_ATPF[109] =rs.getString(111);
					row_ATPF[110] = rs.getString(112);row_ATPF[111] =rs.getString(113);
					row_ATPF[112] =rs.getString(114);		  				
					
					model_AtramePF.addRow(row_ATPF);
					
				}
			//ferme les connexions		
				rs.close();  
				pstableapf.close();
				 
			 } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				 
			 }
		  
			 // pour rechercher dans le tableau => FILTRE
			 t_text2.getDocument().addDocumentListener(new DocumentListener(){
				 @Override public void insertUpdate(DocumentEvent e) {
				 search(t_text2.getText());

				  }
				 @Override public void removeUpdate(DocumentEvent e) {
				 search(t_text2.getText());
				 }
				 @Override public void changedUpdate(DocumentEvent e) {
				 search(t_text2.getText());
				 }
				 public void search(String s)
				 {
				 if (s.length() == 0) {

				  sorterATPF.setRowFilter(null);
				 } else {

				  sorterATPF.setRowFilter(RowFilter.regexFilter(s));
				 }
				 }
			 });
		  

		//affection au panel
		p2_sud.add(lab_text2);
		p2_sud.add(t_text2);
		p2_centre.add(s2);
	}

}
